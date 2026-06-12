/*
 *  Copyright 2026 Alexey Andreev.
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

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class TScanner implements Iterator<String>, Closeable {
    private static final Pattern DEFAULT_DELIMITER = Pattern.compile("\\p{javaWhitespace}+");
    private static final Pattern BOOLEAN_PATTERN = Pattern.compile("true|false", Pattern.CASE_INSENSITIVE);
    private static final int BUFFER_SIZE = 2048;

    private Readable source;
    private Pattern delimiter = DEFAULT_DELIMITER;
    private Locale locale = Locale.getDefault();
    private final Locale initialLocale = locale;
    private int radix = 10;
    private IOException lastIOException;
    private String input;
    private int position;
    private boolean closed;
    private MatchResult lastMatch;

    public TScanner(Readable source) {
        this.source = Objects.requireNonNull(source);
    }

    public TScanner(InputStream source) {
        this(new InputStreamReader(Objects.requireNonNull(source), Charset.defaultCharset()));
    }

    public TScanner(InputStream source, String charsetName) {
        this(source, Charset.forName(Objects.requireNonNull(charsetName)));
    }

    public TScanner(InputStream source, Charset charset) {
        this(new InputStreamReader(Objects.requireNonNull(source), Objects.requireNonNull(charset)));
    }

    public TScanner(File source) throws FileNotFoundException {
        this(new FileInputStream(Objects.requireNonNull(source)));
    }

    public TScanner(File source, String charsetName) throws FileNotFoundException {
        this(new InputStreamReader(new FileInputStream(Objects.requireNonNull(source)),
                Charset.forName(Objects.requireNonNull(charsetName))));
    }

    public TScanner(File source, Charset charset) throws IOException {
        this(new FileInputStream(Objects.requireNonNull(source)), charset);
    }

    public TScanner(Path source) throws IOException {
        this(Files.newInputStream(Objects.requireNonNull(source)));
    }

    public TScanner(Path source, String charsetName) throws IOException {
        this(source, Charset.forName(Objects.requireNonNull(charsetName)));
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
        this(source, Charset.forName(Objects.requireNonNull(charsetName)));
    }

    public TScanner(ReadableByteChannel source, Charset charset) {
        this(new ChannelReader(Objects.requireNonNull(source), Objects.requireNonNull(charset)));
    }

    @Override
    public void close() {
        if (closed) {
            return;
        }
        closed = true;
        if (source instanceof Closeable) {
            try {
                ((Closeable) source).close();
            } catch (IOException e) {
                lastIOException = e;
            }
        }
    }

    public IOException ioException() {
        return lastIOException;
    }

    public Pattern delimiter() {
        return delimiter;
    }

    public TScanner useDelimiter(Pattern pattern) {
        delimiter = Objects.requireNonNull(pattern);
        return this;
    }

    public TScanner useDelimiter(String pattern) {
        return useDelimiter(Pattern.compile(Objects.requireNonNull(pattern)));
    }

    public Locale locale() {
        return locale;
    }

    public TScanner useLocale(Locale locale) {
        this.locale = Objects.requireNonNull(locale);
        return this;
    }

    public int radix() {
        return radix;
    }

    public TScanner useRadix(int radix) {
        checkRadix(radix);
        this.radix = radix;
        return this;
    }

    public MatchResult match() {
        requireOpen();
        if (lastMatch == null) {
            throw new IllegalStateException();
        }
        return lastMatch;
    }

    @Override
    public String toString() {
        return "java.util.Scanner[delimiters=" + delimiter + " position=" + position + " closed=" + closed
                + " radix=" + radix + " locale=" + locale + "]";
    }

    @Override
    public boolean hasNext() {
        requireOpen();
        return peekToken() != null;
    }

    @Override
    public String next() {
        requireOpen();
        Token token = peekToken();
        if (token == null) {
            throw new NoSuchElementException();
        }
        position = token.end;
        lastMatch = new SimpleMatchResult(token.start, token.end, token.text);
        return token.text;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public boolean hasNext(String pattern) {
        return hasNext(Pattern.compile(Objects.requireNonNull(pattern)));
    }

    public String next(String pattern) {
        return next(Pattern.compile(Objects.requireNonNull(pattern)));
    }

    public boolean hasNext(Pattern pattern) {
        requireOpen();
        Objects.requireNonNull(pattern);
        Token token = peekToken();
        if (token == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(token.text);
        if (!matcher.matches()) {
            return false;
        }
        lastMatch = new OffsetMatchResult(matcher.toMatchResult(), token.start);
        return true;
    }

    public String next(Pattern pattern) {
        requireOpen();
        Objects.requireNonNull(pattern);
        Token token = peekToken();
        if (token == null) {
            throw new NoSuchElementException();
        }
        Matcher matcher = pattern.matcher(token.text);
        if (!matcher.matches()) {
            throw new TInputMismatchException();
        }
        position = token.end;
        lastMatch = new OffsetMatchResult(matcher.toMatchResult(), token.start);
        return token.text;
    }

    public boolean hasNextLine() {
        requireOpen();
        ensureLoaded();
        return position < input.length();
    }

    public String nextLine() {
        requireOpen();
        ensureLoaded();
        if (position >= input.length()) {
            throw new NoSuchElementException();
        }
        Line line = findLine(position);
        String result = input.substring(position, line.end);
        lastMatch = new SimpleMatchResult(position, line.end, result);
        position = line.next;
        return result;
    }

    public String findInLine(String pattern) {
        return findInLine(Pattern.compile(Objects.requireNonNull(pattern)));
    }

    public String findInLine(Pattern pattern) {
        requireOpen();
        Objects.requireNonNull(pattern);
        ensureLoaded();
        Line line = findLine(position);
        return find(pattern, line.end);
    }

    public String findWithinHorizon(String pattern, int horizon) {
        return findWithinHorizon(Pattern.compile(Objects.requireNonNull(pattern)), horizon);
    }

    public String findWithinHorizon(Pattern pattern, int horizon) {
        requireOpen();
        Objects.requireNonNull(pattern);
        if (horizon < 0) {
            throw new IllegalArgumentException("horizon < 0");
        }
        ensureLoaded();
        int end = horizon == 0 ? input.length() : offsetByCodePoints(position, horizon);
        return find(pattern, end);
    }

    public TScanner skip(Pattern pattern) {
        requireOpen();
        Objects.requireNonNull(pattern);
        ensureLoaded();
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
        return skip(Pattern.compile(Objects.requireNonNull(pattern)));
    }

    public boolean hasNextBoolean() {
        return hasNextParsed(BOOLEAN_PATTERN, token -> null);
    }

    public boolean nextBoolean() {
        String token = nextParsed(BOOLEAN_PATTERN, tokenText -> tokenText);
        return Boolean.parseBoolean(token);
    }

    public boolean hasNextByte() {
        return hasNextByte(radix);
    }

    public boolean hasNextByte(int radix) {
        return hasNextParsed(radix, token -> Byte.parseByte(normalizeInteger(token, radix), radix));
    }

    public byte nextByte() {
        return nextByte(radix);
    }

    public byte nextByte(int radix) {
        return nextParsed(radix, token -> Byte.parseByte(normalizeInteger(token, radix), radix));
    }

    public boolean hasNextShort() {
        return hasNextShort(radix);
    }

    public boolean hasNextShort(int radix) {
        return hasNextParsed(radix, token -> Short.parseShort(normalizeInteger(token, radix), radix));
    }

    public short nextShort() {
        return nextShort(radix);
    }

    public short nextShort(int radix) {
        return nextParsed(radix, token -> Short.parseShort(normalizeInteger(token, radix), radix));
    }

    public boolean hasNextInt() {
        return hasNextInt(radix);
    }

    public boolean hasNextInt(int radix) {
        return hasNextParsed(radix, token -> Integer.parseInt(normalizeInteger(token, radix), radix));
    }

    public int nextInt() {
        return nextInt(radix);
    }

    public int nextInt(int radix) {
        return nextParsed(radix, token -> Integer.parseInt(normalizeInteger(token, radix), radix));
    }

    public boolean hasNextLong() {
        return hasNextLong(radix);
    }

    public boolean hasNextLong(int radix) {
        return hasNextParsed(radix, token -> Long.parseLong(normalizeInteger(token, radix), radix));
    }

    public long nextLong() {
        return nextLong(radix);
    }

    public long nextLong(int radix) {
        return nextParsed(radix, token -> Long.parseLong(normalizeInteger(token, radix), radix));
    }

    public boolean hasNextFloat() {
        return hasNextParsed(token -> Float.parseFloat(normalizeDecimal(token)));
    }

    public float nextFloat() {
        return nextParsed(token -> Float.parseFloat(normalizeDecimal(token)));
    }

    public boolean hasNextDouble() {
        return hasNextParsed(token -> Double.parseDouble(normalizeDecimal(token)));
    }

    public double nextDouble() {
        return nextParsed(token -> Double.parseDouble(normalizeDecimal(token)));
    }

    public boolean hasNextBigInteger() {
        return hasNextBigInteger(radix);
    }

    public boolean hasNextBigInteger(int radix) {
        return hasNextParsed(radix, token -> new BigInteger(normalizeInteger(token, radix), radix));
    }

    public BigInteger nextBigInteger() {
        return nextBigInteger(radix);
    }

    public BigInteger nextBigInteger(int radix) {
        return nextParsed(radix, token -> new BigInteger(normalizeInteger(token, radix), radix));
    }

    public boolean hasNextBigDecimal() {
        return hasNextParsed(token -> new BigDecimal(normalizeDecimal(token)));
    }

    public BigDecimal nextBigDecimal() {
        return nextParsed(token -> new BigDecimal(normalizeDecimal(token)));
    }

    public TScanner reset() {
        delimiter = DEFAULT_DELIMITER;
        locale = initialLocale;
        radix = 10;
        return this;
    }

    public Stream<String> tokens() {
        requireOpen();
        Iterator<String> iterator = new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return TScanner.this.hasNext();
            }

            @Override
            public String next() {
                return TScanner.this.next();
            }
        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED
                | Spliterator.NONNULL), false).onClose(this::close);
    }

    public Stream<MatchResult> findAll(Pattern pattern) {
        requireOpen();
        Objects.requireNonNull(pattern);
        Iterator<MatchResult> iterator = new Iterator<MatchResult>() {
            private MatchResult next;
            private boolean ready;

            @Override
            public boolean hasNext() {
                if (!ready) {
                    String found = findWithinHorizon(pattern, 0);
                    if (found != null) {
                        next = match();
                    }
                    ready = true;
                }
                return next != null;
            }

            @Override
            public MatchResult next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                MatchResult result = next;
                next = null;
                ready = false;
                return result;
            }
        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED
                | Spliterator.NONNULL), false).onClose(this::close);
    }

    public Stream<MatchResult> findAll(String patString) {
        return findAll(Pattern.compile(Objects.requireNonNull(patString)));
    }

    private String find(Pattern pattern, int end) {
        Matcher matcher = pattern.matcher(input);
        matcher.region(position, end);
        if (!matcher.find()) {
            return null;
        }
        position = matcher.end();
        lastMatch = matcher.toMatchResult();
        return matcher.group();
    }

    private Token peekToken() {
        ensureLoaded();
        int start = skipDelimiters(position);
        if (start > input.length()) {
            return null;
        }
        if (start == input.length()) {
            return null;
        }
        Matcher matcher = delimiter.matcher(input);
        if (matcher.find(start)) {
            return new Token(start, matcher.start());
        }
        return new Token(start, input.length());
    }

    private int skipDelimiters(int start) {
        Matcher matcher = delimiter.matcher(input);
        if (start < input.length()) {
            matcher.region(start, input.length());
            if (matcher.lookingAt() && matcher.end() > start) {
                return matcher.end();
            }
        }
        return start;
    }

    private Line findLine(int start) {
        int index = start;
        while (index < input.length()) {
            char c = input.charAt(index);
            if (c == '\n') {
                return new Line(index, index + 1);
            } else if (c == '\r') {
                int next = index + 1;
                if (next < input.length() && input.charAt(next) == '\n') {
                    next++;
                }
                return new Line(index, next);
            } else if (c == '\u0085' || c == '\u2028' || c == '\u2029') {
                return new Line(index, index + 1);
            }
            index++;
        }
        return new Line(input.length(), input.length());
    }

    private int offsetByCodePoints(int start, int count) {
        int index = start;
        while (count-- > 0 && index < input.length()) {
            index += Character.charCount(input.codePointAt(index));
        }
        return index;
    }

    private void ensureLoaded() {
        if (input != null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        CharBuffer buffer = CharBuffer.allocate(BUFFER_SIZE);
        try {
            while (true) {
                buffer.clear();
                int read = source.read(buffer);
                if (read < 0) {
                    break;
                }
                buffer.flip();
                sb.append(buffer, 0, buffer.length());
            }
        } catch (IOException e) {
            lastIOException = e;
        }
        input = sb.toString();
    }

    private void requireOpen() {
        if (closed) {
            throw new IllegalStateException("Scanner closed");
        }
    }

    private boolean hasNextParsed(Parser<?> parser) {
        requireOpen();
        Token token = peekToken();
        if (token == null) {
            return false;
        }
        try {
            parser.parse(token.text);
            lastMatch = new SimpleMatchResult(token.start, token.end, token.text);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    private boolean hasNextParsed(Pattern pattern, Parser<?> parser) {
        requireOpen();
        Token token = peekToken();
        if (token == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(token.text);
        if (!matcher.matches()) {
            return false;
        }
        parser.parse(token.text);
        lastMatch = new OffsetMatchResult(matcher.toMatchResult(), token.start);
        return true;
    }

    private boolean hasNextParsed(int radix, Parser<?> parser) {
        checkRadix(radix);
        return hasNextParsed(parser);
    }

    private <T> T nextParsed(Parser<T> parser) {
        requireOpen();
        Token token = peekToken();
        if (token == null) {
            throw new NoSuchElementException();
        }
        try {
            T result = parser.parse(token.text);
            position = token.end;
            lastMatch = new SimpleMatchResult(token.start, token.end, token.text);
            return result;
        } catch (RuntimeException e) {
            throw new TInputMismatchException();
        }
    }

    private <T> T nextParsed(Pattern pattern, Parser<T> parser) {
        requireOpen();
        Token token = peekToken();
        if (token == null) {
            throw new NoSuchElementException();
        }
        Matcher matcher = pattern.matcher(token.text);
        if (!matcher.matches()) {
            throw new TInputMismatchException();
        }
        T result = parser.parse(token.text);
        position = token.end;
        lastMatch = new OffsetMatchResult(matcher.toMatchResult(), token.start);
        return result;
    }

    private <T> T nextParsed(int radix, Parser<T> parser) {
        checkRadix(radix);
        return nextParsed(parser);
    }

    private String normalizeInteger(String token, int radix) {
        String normalized = normalizeSignAndSeparators(token, false);
        StringBuilder sb = new StringBuilder(normalized.length());
        for (int i = 0; i < normalized.length();) {
            int cp = normalized.codePointAt(i);
            int digit = Character.digit(cp, radix);
            if (digit >= 0) {
                sb.append(Character.forDigit(digit, radix));
            } else {
                sb.appendCodePoint(cp);
            }
            i += Character.charCount(cp);
        }
        return sb.toString();
    }

    private String normalizeDecimal(String token) {
        DecimalFormatSymbols symbols = decimalSymbols();
        if (token.equals(symbols.getNaN())) {
            return "NaN";
        } else if (token.equals(symbols.getInfinity())) {
            return "Infinity";
        } else if (token.equals("+" + symbols.getInfinity()) || token.equals("+Infinity")) {
            return "Infinity";
        } else if (token.equals("-" + symbols.getInfinity()) || token.equals("-Infinity")) {
            return "-Infinity";
        }

        String normalized = normalizeSignAndSeparators(token, true);
        StringBuilder sb = new StringBuilder(normalized.length());
        for (int i = 0; i < normalized.length();) {
            int cp = normalized.codePointAt(i);
            int digit = Character.digit(cp, 10);
            if (digit >= 0) {
                sb.append((char) ('0' + digit));
            } else {
                sb.appendCodePoint(cp);
            }
            i += Character.charCount(cp);
        }
        return sb.toString();
    }

    private String normalizeSignAndSeparators(String token, boolean decimal) {
        DecimalFormat format = decimalFormat();
        DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
        String value = token;
        boolean negative = false;

        String negativePrefix = format.getNegativePrefix();
        String negativeSuffix = format.getNegativeSuffix();
        if (!negativePrefix.isEmpty() && value.startsWith(negativePrefix)
                && (negativeSuffix.isEmpty() || value.endsWith(negativeSuffix))) {
            value = value.substring(negativePrefix.length(), value.length() - negativeSuffix.length());
            negative = true;
        } else {
            String positivePrefix = format.getPositivePrefix();
            String positiveSuffix = format.getPositiveSuffix();
            if (!positivePrefix.isEmpty() && value.startsWith(positivePrefix)
                    && (positiveSuffix.isEmpty() || value.endsWith(positiveSuffix))) {
                value = value.substring(positivePrefix.length(), value.length() - positiveSuffix.length());
            }
        }

        char group = symbols.getGroupingSeparator();
        char decimalSeparator = symbols.getDecimalSeparator();
        StringBuilder sb = new StringBuilder(value.length() + 1);
        if (negative && (value.isEmpty() || value.charAt(0) != '-')) {
            sb.append('-');
        }
        for (int i = 0; i < value.length(); ++i) {
            char c = value.charAt(i);
            if (c == group) {
                continue;
            }
            sb.append(decimal && c == decimalSeparator ? '.' : c);
        }
        return sb.toString();
    }

    private DecimalFormat decimalFormat() {
        NumberFormat format = NumberFormat.getNumberInstance(locale);
        if (format instanceof DecimalFormat) {
            return (DecimalFormat) format;
        }
        return (DecimalFormat) NumberFormat.getNumberInstance(Locale.ROOT);
    }

    private DecimalFormatSymbols decimalSymbols() {
        return decimalFormat().getDecimalFormatSymbols();
    }

    private void checkRadix(int radix) {
        if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX) {
            throw new IllegalArgumentException("radix:" + radix);
        }
    }

    private interface Parser<T> {
        T parse(String token);
    }

    private class Token {
        final int start;
        final int end;
        final String text;

        Token(int start, int end) {
            this.start = start;
            this.end = end;
            this.text = input.substring(start, end);
        }
    }

    private static class Line {
        final int end;
        final int next;

        Line(int end, int next) {
            this.end = end;
            this.next = next;
        }
    }

    private static class SimpleMatchResult implements MatchResult {
        private final int start;
        private final int end;
        private final String text;

        SimpleMatchResult(int start, int end, String text) {
            this.start = start;
            this.end = end;
            this.text = text;
        }

        @Override
        public int start() {
            return start;
        }

        @Override
        public int start(int group) {
            checkGroup(group);
            return start;
        }

        @Override
        public int end() {
            return end;
        }

        @Override
        public int end(int group) {
            checkGroup(group);
            return end;
        }

        @Override
        public String group() {
            return text;
        }

        @Override
        public String group(int group) {
            checkGroup(group);
            return text;
        }

        @Override
        public int groupCount() {
            return 0;
        }

        private void checkGroup(int group) {
            if (group != 0) {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    private static class OffsetMatchResult implements MatchResult {
        private final MatchResult result;
        private final int offset;

        OffsetMatchResult(MatchResult result, int offset) {
            this.result = result;
            this.offset = offset;
        }

        @Override
        public int start() {
            return result.start() + offset;
        }

        @Override
        public int start(int group) {
            int start = result.start(group);
            return start < 0 ? start : start + offset;
        }

        @Override
        public int end() {
            return result.end() + offset;
        }

        @Override
        public int end(int group) {
            int end = result.end(group);
            return end < 0 ? end : end + offset;
        }

        @Override
        public String group() {
            return result.group();
        }

        @Override
        public String group(int group) {
            return result.group(group);
        }

        @Override
        public int groupCount() {
            return result.groupCount();
        }
    }

    private static class ChannelReader extends Reader {
        private final ReadableByteChannel channel;
        private final CharsetDecoder decoder;
        private final ByteBuffer bytes = ByteBuffer.allocate(BUFFER_SIZE);
        private final CharBuffer chars = CharBuffer.allocate(BUFFER_SIZE);
        private boolean endOfInput;
        private boolean closed;

        ChannelReader(ReadableByteChannel channel, Charset charset) {
            this.channel = channel;
            this.decoder = charset.newDecoder();
            bytes.limit(0);
            chars.limit(0);
        }

        @Override
        public int read(char[] cbuf, int off, int len) throws IOException {
            if (closed) {
                throw new IOException("Reader closed");
            }
            if (len == 0) {
                return 0;
            }
            int read = 0;
            while (read < len) {
                if (chars.hasRemaining()) {
                    int count = Math.min(len - read, chars.remaining());
                    chars.get(cbuf, off + read, count);
                    read += count;
                    continue;
                }
                if (!fill()) {
                    break;
                }
            }
            return read > 0 ? read : -1;
        }

        private boolean fill() throws IOException {
            chars.clear();
            while (true) {
                CoderResult result = decoder.decode(bytes, chars, endOfInput);
                if (result.isOverflow()) {
                    chars.flip();
                    return true;
                }
                if (result.isError()) {
                    result.throwException();
                }
                if (endOfInput) {
                    result = decoder.flush(chars);
                    if (result.isError()) {
                        result.throwException();
                    }
                    chars.flip();
                    return chars.hasRemaining();
                }
                bytes.compact();
                int count = channel.read(bytes);
                bytes.flip();
                if (count < 0) {
                    endOfInput = true;
                }
            }
        }

        @Override
        public void close() throws IOException {
            closed = true;
            channel.close();
        }
    }
}
