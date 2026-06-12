/*
 *  Copyright 2026.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.teavm.classlib.java.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormatSymbols;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class TScanner implements Iterator<String>, Closeable {
    private static final int BUFFER_SIZE = 1024;
    private static final Pattern DEFAULT_DELIMITER = Pattern.compile("\\p{javaWhitespace}+");
    private static final Pattern BOOLEAN_PATTERN = Pattern.compile("true|false", Pattern.CASE_INSENSITIVE);

    private String input;
    private int position;
    private Pattern delimiter = DEFAULT_DELIMITER;
    private Locale locale = Locale.getDefault();
    private int radix = 10;
    private IOException ioException;
    private MatchResult lastMatch;
    private Closeable closeable;
    private boolean closed;

    public TScanner(Readable source) {
        Objects.requireNonNull(source);
        readReadable(source);
        if (source instanceof Closeable) {
            closeable = (Closeable) source;
        }
    }

    public TScanner(InputStream source) {
        this(new InputStreamReader(Objects.requireNonNull(source)));
    }

    public TScanner(InputStream source, String charsetName) {
        this(new InputStreamReader(Objects.requireNonNull(source), Charset.forName(charsetName)));
    }

    public TScanner(InputStream source, Charset charset) {
        this(new InputStreamReader(Objects.requireNonNull(source), Objects.requireNonNull(charset)));
    }

    public TScanner(File source) throws FileNotFoundException {
        this(new FileInputStream(Objects.requireNonNull(source)));
    }

    public TScanner(File source, String charsetName) throws FileNotFoundException {
        this(new FileInputStream(Objects.requireNonNull(source)), charsetName);
    }

    public TScanner(File source, Charset charset) throws IOException {
        this(new FileInputStream(Objects.requireNonNull(source)), charset);
    }

    public TScanner(Path source) throws IOException {
        this(Files.newInputStream(Objects.requireNonNull(source)));
    }

    public TScanner(Path source, String charsetName) throws IOException {
        this(Files.newInputStream(Objects.requireNonNull(source)), charsetName);
    }

    public TScanner(Path source, Charset charset) throws IOException {
        this(Files.newInputStream(Objects.requireNonNull(source)), charset);
    }

    public TScanner(String source) {
        input = Objects.requireNonNull(source);
    }

    public TScanner(ReadableByteChannel source) {
        this(source, Charset.defaultCharset());
    }

    public TScanner(ReadableByteChannel source, String charsetName) {
        this(source, Charset.forName(charsetName));
    }

    public TScanner(ReadableByteChannel source, Charset charset) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(charset);
        closeable = source;
        readChannel(source, charset);
    }

    @Override
    public void close() {
        if (closed) {
            return;
        }
        closed = true;
        input = "";
        position = 0;
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                ioException = e;
            }
        }
    }

    public IOException ioException() {
        return ioException;
    }

    public Pattern delimiter() {
        ensureOpen();
        return delimiter;
    }

    public TScanner useDelimiter(Pattern pattern) {
        ensureOpen();
        delimiter = Objects.requireNonNull(pattern);
        return this;
    }

    public TScanner useDelimiter(String pattern) {
        return useDelimiter(Pattern.compile(pattern));
    }

    public Locale locale() {
        ensureOpen();
        return locale;
    }

    public TScanner useLocale(Locale locale) {
        ensureOpen();
        this.locale = Objects.requireNonNull(locale);
        return this;
    }

    public int radix() {
        ensureOpen();
        return radix;
    }

    public TScanner useRadix(int radix) {
        ensureOpen();
        if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX) {
            throw new IllegalArgumentException("radix:" + radix);
        }
        this.radix = radix;
        return this;
    }

    public MatchResult match() {
        ensureOpen();
        if (lastMatch == null) {
            throw new IllegalStateException("No match result available");
        }
        return lastMatch;
    }

    @Override
    public String toString() {
        return "java.util.Scanner[delimiters=" + delimiter + "][position=" + position + "][match valid="
                + (lastMatch != null) + "][closed=" + closed + "][radix=" + radix + "][locale=" + locale + "]";
    }

    @Override
    public boolean hasNext() {
        ensureOpen();
        return skipDelimiterPosition(position) < input.length();
    }

    @Override
    public String next() {
        ensureOpen();
        int start = skipDelimiterPosition(position);
        if (start >= input.length()) {
            throw new NoSuchElementException();
        }
        int end = findTokenEnd(start);
        position = end;
        setLastMatch(Pattern.compile("(?s).*").matcher(input.substring(start, end)));
        return input.substring(start, end);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public boolean hasNext(String pattern) {
        return hasNext(Pattern.compile(pattern));
    }

    public String next(String pattern) {
        return next(Pattern.compile(pattern));
    }

    public boolean hasNext(Pattern pattern) {
        ensureOpen();
        Objects.requireNonNull(pattern);
        int start = skipDelimiterPosition(position);
        if (start >= input.length()) {
            return false;
        }
        int end = findTokenEnd(start);
        Matcher matcher = pattern.matcher(input.substring(start, end));
        boolean result = matcher.matches();
        if (result) {
            lastMatch = matcher.toMatchResult();
        }
        return result;
    }

    public String next(Pattern pattern) {
        ensureOpen();
        Objects.requireNonNull(pattern);
        int start = skipDelimiterPosition(position);
        if (start >= input.length()) {
            throw new NoSuchElementException();
        }
        int end = findTokenEnd(start);
        String token = input.substring(start, end);
        Matcher matcher = pattern.matcher(token);
        if (!matcher.matches()) {
            throw inputMismatch();
        }
        position = end;
        lastMatch = matcher.toMatchResult();
        return token;
    }

    public boolean hasNextLine() {
        ensureOpen();
        return position < input.length();
    }

    public String nextLine() {
        ensureOpen();
        if (position >= input.length()) {
            throw new NoSuchElementException("No line found");
        }
        LineEnd lineEnd = findLineEnd(position);
        String result = input.substring(position, lineEnd.contentEnd);
        Matcher matcher = Pattern.compile(".*").matcher(result);
        matcher.matches();
        lastMatch = matcher.toMatchResult();
        position = lineEnd.nextPosition;
        return result;
    }

    public String findInLine(String pattern) {
        return findInLine(Pattern.compile(pattern));
    }

    public String findInLine(Pattern pattern) {
        ensureOpen();
        Objects.requireNonNull(pattern);
        int end = findLineEnd(position).contentEnd;
        Matcher matcher = pattern.matcher(input);
        matcher.region(position, end);
        if (!matcher.find()) {
            return null;
        }
        position = matcher.end();
        lastMatch = matcher.toMatchResult();
        return matcher.group();
    }

    public String findWithinHorizon(String pattern, int horizon) {
        return findWithinHorizon(Pattern.compile(pattern), horizon);
    }

    public String findWithinHorizon(Pattern pattern, int horizon) {
        ensureOpen();
        Objects.requireNonNull(pattern);
        if (horizon < 0) {
            throw new IllegalArgumentException("horizon < 0");
        }
        int end = horizon == 0 ? input.length() : Math.min(input.length(), position + horizon);
        Matcher matcher = pattern.matcher(input);
        matcher.region(position, end);
        if (!matcher.find()) {
            return null;
        }
        position = matcher.end();
        lastMatch = matcher.toMatchResult();
        return matcher.group();
    }

    public TScanner skip(Pattern pattern) {
        ensureOpen();
        Objects.requireNonNull(pattern);
        Matcher matcher = pattern.matcher(input);
        matcher.region(position, input.length());
        if (!matcher.lookingAt()) {
            throw new NoSuchElementException();
        }
        position = matcher.end();
        lastMatch = matcher.toMatchResult();
        return this;
    }

    public TScanner skip(String pattern) {
        return skip(Pattern.compile(pattern));
    }

    public boolean hasNextBoolean() {
        return hasNext(BOOLEAN_PATTERN);
    }

    public boolean nextBoolean() {
        return Boolean.parseBoolean(next(BOOLEAN_PATTERN));
    }

    public boolean hasNextByte() {
        return hasNextByte(radix);
    }

    public boolean hasNextByte(int radix) {
        try {
            Byte.parseByte(normalizeIntegerToken(peekToken()), radix);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public byte nextByte() {
        return nextByte(radix);
    }

    public byte nextByte(int radix) {
        String token = peekToken();
        try {
            byte result = Byte.parseByte(normalizeIntegerToken(token), radix);
            consumePeekedToken(token);
            return result;
        } catch (RuntimeException e) {
            throw inputMismatch();
        }
    }

    public boolean hasNextShort() {
        return hasNextShort(radix);
    }

    public boolean hasNextShort(int radix) {
        try {
            Short.parseShort(normalizeIntegerToken(peekToken()), radix);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public short nextShort() {
        return nextShort(radix);
    }

    public short nextShort(int radix) {
        String token = peekToken();
        try {
            short result = Short.parseShort(normalizeIntegerToken(token), radix);
            consumePeekedToken(token);
            return result;
        } catch (RuntimeException e) {
            throw inputMismatch();
        }
    }

    public boolean hasNextInt() {
        return hasNextInt(radix);
    }

    public boolean hasNextInt(int radix) {
        try {
            Integer.parseInt(normalizeIntegerToken(peekToken()), radix);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public int nextInt() {
        return nextInt(radix);
    }

    public int nextInt(int radix) {
        String token = peekToken();
        try {
            int result = Integer.parseInt(normalizeIntegerToken(token), radix);
            consumePeekedToken(token);
            return result;
        } catch (RuntimeException e) {
            throw inputMismatch();
        }
    }

    public boolean hasNextLong() {
        return hasNextLong(radix);
    }

    public boolean hasNextLong(int radix) {
        try {
            Long.parseLong(normalizeIntegerToken(peekToken()), radix);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public long nextLong() {
        return nextLong(radix);
    }

    public long nextLong(int radix) {
        String token = peekToken();
        try {
            long result = Long.parseLong(normalizeIntegerToken(token), radix);
            consumePeekedToken(token);
            return result;
        } catch (RuntimeException e) {
            throw inputMismatch();
        }
    }

    public boolean hasNextFloat() {
        try {
            Float.parseFloat(normalizeDecimalToken(peekToken()));
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public float nextFloat() {
        String token = peekToken();
        try {
            float result = Float.parseFloat(normalizeDecimalToken(token));
            consumePeekedToken(token);
            return result;
        } catch (RuntimeException e) {
            throw inputMismatch();
        }
    }

    public boolean hasNextDouble() {
        try {
            Double.parseDouble(normalizeDecimalToken(peekToken()));
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public double nextDouble() {
        String token = peekToken();
        try {
            double result = Double.parseDouble(normalizeDecimalToken(token));
            consumePeekedToken(token);
            return result;
        } catch (RuntimeException e) {
            throw inputMismatch();
        }
    }

    public boolean hasNextBigInteger() {
        return hasNextBigInteger(radix);
    }

    public boolean hasNextBigInteger(int radix) {
        try {
            new BigInteger(normalizeIntegerToken(peekToken()), radix);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public BigInteger nextBigInteger() {
        return nextBigInteger(radix);
    }

    public BigInteger nextBigInteger(int radix) {
        String token = peekToken();
        try {
            BigInteger result = new BigInteger(normalizeIntegerToken(token), radix);
            consumePeekedToken(token);
            return result;
        } catch (RuntimeException e) {
            throw inputMismatch();
        }
    }

    public boolean hasNextBigDecimal() {
        try {
            new BigDecimal(normalizeDecimalToken(peekToken()));
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public BigDecimal nextBigDecimal() {
        String token = peekToken();
        try {
            BigDecimal result = new BigDecimal(normalizeDecimalToken(token));
            consumePeekedToken(token);
            return result;
        } catch (RuntimeException e) {
            throw inputMismatch();
        }
    }

    public TScanner reset() {
        ensureOpen();
        delimiter = DEFAULT_DELIMITER;
        locale = Locale.getDefault();
        radix = 10;
        return this;
    }

    public Stream<String> tokens() {
        ensureOpen();
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<String>(Long.MAX_VALUE,
                Spliterator.ORDERED | Spliterator.NONNULL) {
            @Override
            public boolean tryAdvance(Consumer<? super String> action) {
                Objects.requireNonNull(action);
                if (!TScanner.this.hasNext()) {
                    return false;
                }
                action.accept(TScanner.this.next());
                return true;
            }
        }, false);
    }

    public Stream<MatchResult> findAll(Pattern pattern) {
        ensureOpen();
        Objects.requireNonNull(pattern);
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<MatchResult>(Long.MAX_VALUE,
                Spliterator.ORDERED | Spliterator.NONNULL) {
            @Override
            public boolean tryAdvance(Consumer<? super MatchResult> action) {
                Objects.requireNonNull(action);
                String match = TScanner.this.findWithinHorizon(pattern, 0);
                if (match == null) {
                    return false;
                }
                action.accept(TScanner.this.lastMatch);
                return true;
            }
        }, false);
    }

    public Stream<MatchResult> findAll(String pattern) {
        return findAll(Pattern.compile(pattern));
    }

    private void readReadable(Readable source) {
        StringBuilder sb = new StringBuilder();
        CharBuffer buffer = CharBuffer.allocate(BUFFER_SIZE);
        try {
            while (true) {
                int count = source.read(buffer);
                if (count < 0) {
                    break;
                }
                buffer.flip();
                sb.append(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            ioException = e;
        }
        input = sb.toString();
    }

    private void readChannel(ReadableByteChannel source, Charset charset) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        try {
            while (true) {
                int count = source.read(buffer);
                if (count < 0) {
                    break;
                }
                buffer.flip();
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                output.write(bytes, 0, bytes.length);
                buffer.clear();
            }
        } catch (IOException e) {
            ioException = e;
        }
        input = new String(output.toByteArray(), charset);
    }

    private void ensureOpen() {
        if (closed) {
            throw new IllegalStateException("Scanner closed");
        }
    }

    private int skipDelimiterPosition(int start) {
        int result = start;
        while (result < input.length()) {
            Matcher matcher = delimiter.matcher(input);
            matcher.region(result, input.length());
            if (!matcher.lookingAt()) {
                break;
            }
            int end = matcher.end();
            if (end <= result) {
                break;
            }
            result = end;
        }
        return result;
    }

    private int findTokenEnd(int start) {
        Matcher matcher = delimiter.matcher(input);
        matcher.region(start, input.length());
        if (!matcher.find()) {
            return input.length();
        }
        int end = matcher.start();
        return end < start ? start : end;
    }

    private String peekToken() {
        ensureOpen();
        int start = skipDelimiterPosition(position);
        if (start >= input.length()) {
            throw new NoSuchElementException();
        }
        return input.substring(start, findTokenEnd(start));
    }

    private void consumePeekedToken(String token) {
        int start = skipDelimiterPosition(position);
        position = findTokenEnd(start);
        setLastMatch(Pattern.compile("(?s).*").matcher(token));
    }

    private void setLastMatch(Matcher matcher) {
        matcher.matches();
        lastMatch = matcher.toMatchResult();
    }

    private String normalizeIntegerToken(String token) {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(locale);
        char grouping = symbols.getGroupingSeparator();
        if (grouping != 0) {
            token = token.replace(String.valueOf(grouping), "");
        }
        return normalizeSigns(token, symbols);
    }

    private String normalizeDecimalToken(String token) {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(locale);
        char grouping = symbols.getGroupingSeparator();
        if (grouping != 0) {
            token = token.replace(String.valueOf(grouping), "");
        }
        char decimal = symbols.getDecimalSeparator();
        if (decimal != '.') {
            token = token.replace(decimal, '.');
        }
        return normalizeSigns(token, symbols);
    }

    private String normalizeSigns(String token, DecimalFormatSymbols symbols) {
        String minus = symbols.getMinusSign() == '-' ? null : String.valueOf(symbols.getMinusSign());
        if (minus != null && token.startsWith(minus)) {
            token = "-" + token.substring(minus.length());
        }
        if (token.startsWith("+")) {
            return token.substring(1);
        }
        return token;
    }

    private TInputMismatchException inputMismatch() {
        return new TInputMismatchException();
    }

    private LineEnd findLineEnd(int start) {
        int index = start;
        while (index < input.length()) {
            char c = input.charAt(index);
            if (c == '\n') {
                return new LineEnd(index, index + 1);
            }
            if (c == '\r') {
                int next = index + 1;
                if (next < input.length() && input.charAt(next) == '\n') {
                    next++;
                }
                return new LineEnd(index, next);
            }
            if (c == '\u0085' || c == '\u2028' || c == '\u2029') {
                return new LineEnd(index, index + 1);
            }
            index++;
        }
        return new LineEnd(input.length(), input.length());
    }

    private static final class LineEnd {
        final int contentEnd;
        final int nextPosition;

        LineEnd(int contentEnd, int nextPosition) {
            this.contentEnd = contentEnd;
            this.nextPosition = nextPosition;
        }
    }
}
