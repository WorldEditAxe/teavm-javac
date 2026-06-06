package processing.mode.java.preproc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import processing.mode.java.preproc.TextTransform.Edit;


public class SourceUtil {

  // No longer needed with use of ANTLR in the preprocessor service.
  private static final boolean PERFORM_SOURCE_UTIL_TRANSFORMS = false;

  public static final Pattern IMPORT_REGEX =
      Pattern.compile("(?:^|;)\\s*(import\\s+(?:(static)\\s+)?((?:\\w+\\s*\\.)*)\\s*(\\S+)\\s*;)",
                      Pattern.MULTILINE | Pattern.DOTALL);

  public static final Pattern IMPORT_REGEX_NO_KEYWORD =
      Pattern.compile("^\\s*((?:(static)\\s+)?((?:\\w+\\s*\\.)*)\\s*(\\S+))",
                      Pattern.MULTILINE | Pattern.DOTALL);

  public static List<ImportStatement> parseProgramImports(CharSequence source) {
    List<ImportStatement> result = new ArrayList<>();
    Matcher matcher = IMPORT_REGEX.matcher(source);
    while (matcher.find()) {
      ImportStatement is = ImportStatement.parse(matcher.toMatchResult());
      result.add(is);
    }
    return result;
  }

  /*
  public static List<Edit> parseProgramImports(CharSequence source,
                                               List<ImportStatement> outImports) {
    List<Edit> result = new ArrayList<>();
    Matcher matcher = IMPORT_REGEX.matcher(source);
    while (matcher.find()) {
      ImportStatement is = ImportStatement.parse(matcher.toMatchResult());
      outImports.add(is);
      int idx = matcher.start(1);
      int len = matcher.end(1) - idx;
      // Remove the import from the main program
      // Substitute with white spaces
      result.add(Edit.move(idx, len, 0));
      result.add(Edit.insert(0, "\n"));
    }
    return result;
  }
  */


  private static final String[] TYPE_CONSTRUCTORS = {
      "boolean", "byte", "char", "float", "int"
  };

  public static List<Edit> replaceTypeConstructors(CharSequence source) {

    List<Edit> result = new ArrayList<>();

    for (int offset = 0; offset < source.length(); offset++) {
      if (!hasNonWordBefore(source, offset)) {
        continue;
      }
      for (String match : TYPE_CONSTRUCTORS) {
        int length = match.length();
        if (!matchesAt(source, offset, match)) {
          continue;
        }
        int next = skipWhitespace(source, offset + length);
        if (next >= source.length() || source.charAt(next) != '(') {
          continue;
        }
        result.add(Edit.insert(offset, "PApplet."));
        String replace = "parse"
            + Character.toUpperCase(match.charAt(0)) + match.substring(1);
        result.add(Edit.replace(offset, length, replace));
        offset += length - 1;
        break;
      }
    }

    return result;
  }


  public static List<Edit> replaceHexLiterals(CharSequence source) {
    // Find all #[webcolor] and replace with 0xff[webcolor]
    // Should be 6 digits only.
    List<Edit> result = new ArrayList<>();

    for (int offset = 0; offset < source.length(); offset++) {
      if (source.charAt(offset) != '#' || !hasNonWordBefore(source, offset)) {
        continue;
      }
      int end = offset + 7;
      if (end > source.length() || !hasNonWordAfter(source, end)) {
        continue;
      }
      boolean hex = true;
      for (int index = offset + 1; index < end; index++) {
        if (!isHexDigit(source.charAt(index))) {
          hex = false;
          break;
        }
      }
      if (hex) {
        result.add(Edit.replace(offset, 1, "0xff"));
        offset = end - 1;
      }
    }

    return result;
  }


  // Verifies that whole input String is floating point literal. Can't be used for searching.
  // https://docs.oracle.com/javase/specs/jls/se8/html/jls-3.html#jls-DecimalFloatingPointLiteral
  public static final Pattern FLOATING_POINT_LITERAL_VERIFIER;
  static {
    // TODO lots of "Unnecessary non-capturing group" sequences here,
    //      but not touching until someone can look more closely.
    final String DIGITS = "(?:[0-9]|[0-9][0-9_]*[0-9])";
    final String EXPONENT_PART = "(?:[eE][+-]?" + DIGITS + ")";
    FLOATING_POINT_LITERAL_VERIFIER = Pattern.compile(
        "(?:^" + DIGITS + "\\." + DIGITS + "?" + EXPONENT_PART + "?[fFdD]?$)|" +
            "(?:^\\." + DIGITS + EXPONENT_PART + "?[fFdD]?$)|" +
            "(?:^" + DIGITS + EXPONENT_PART + "[fFdD]?$)|" +
            "(?:^" + DIGITS + EXPONENT_PART + "?[fFdD]$)");
  }

  public static List<Edit> preprocessAST(Object cu) {
    return new ArrayList<>();
  }


  public static List<Edit> replaceColorRegex(CharSequence source) {
    final List<Edit> edits = new ArrayList<>();

    for (int offset = 0; offset < source.length(); offset++) {
      if (!hasNonWordBefore(source, offset) || !matchesAt(source, offset, "color")) {
        continue;
      }
      int end = offset + 5;
      if (end >= source.length() || !Character.isWhitespace(source.charAt(end))) {
        continue;
      }
      int next = skipWhitespace(source, end);
      if (next < source.length() && source.charAt(next) == '(') {
        continue;
      }
      edits.add(Edit.replace(offset, 5, "int"));
      offset = end - 1;
    }

    return edits;
  }


  public static final Pattern NUMBER_LITERAL_REGEX =
      Pattern.compile("[-+]?[0-9]*\\.?[0-9]+(?:[eE][-+]?[0-9]+)?");

  public static List<Edit> fixFloatsRegex(CharSequence source) {
    final List<Edit> edits = new ArrayList<>();

    Matcher matcher = NUMBER_LITERAL_REGEX.matcher(source);
    while (matcher.find()) {
      int offset = matcher.start();
      int end = matcher.end();
      String group = matcher.group().toLowerCase();
      boolean isFloatingPoint = group.contains(".") || group.contains("e");
      boolean hasSuffix = end < source.length() &&
          Character.toLowerCase(source.charAt(end)) != 'f' &&
          Character.toLowerCase(source.charAt(end)) != 'd';
      if (isFloatingPoint && !hasSuffix) {
        edits.add(Edit.insert(offset, "f"));
      }
    }

    return edits;
  }

  private static boolean matchesAt(CharSequence source, int offset, String value) {
    if (offset + value.length() > source.length()) {
      return false;
    }
    for (int index = 0; index < value.length(); index++) {
      if (source.charAt(offset + index) != value.charAt(index)) {
        return false;
      }
    }
    return true;
  }

  private static int skipWhitespace(CharSequence source, int offset) {
    while (offset < source.length() && Character.isWhitespace(source.charAt(offset))) {
      offset++;
    }
    return offset;
  }

  private static boolean hasNonWordBefore(CharSequence source, int offset) {
    return offset == 0 || !isRegexWord(source.charAt(offset - 1));
  }

  private static boolean hasNonWordAfter(CharSequence source, int offset) {
    return offset == source.length() || !isRegexWord(source.charAt(offset));
  }

  private static boolean isRegexWord(char value) {
    return Character.isLetterOrDigit(value) || value == '_';
  }

  private static boolean isHexDigit(char value) {
    return value >= '0' && value <= '9'
        || value >= 'a' && value <= 'f'
        || value >= 'A' && value <= 'F';
  }


  /*
  static public String scrubCommentsAndStrings(String p) {
    StringBuilder sb = new StringBuilder(p);
    scrubCommentsAndStrings(sb);
    return sb.toString();
  }
  */


  static public void scrubCommentsAndStrings(StringBuilder p) {
    if (!PERFORM_SOURCE_UTIL_TRANSFORMS) {
      return;
    }

    final int length = p.length();

    final int OUT = 0;
    final int IN_BLOCK_COMMENT = 1;
    final int IN_EOL_COMMENT = 2;
    final int IN_STRING_LITERAL = 3;
    final int IN_CHAR_LITERAL = 4;

    int blockStart = -1;

    int prevState = OUT;
    int state = OUT;

    for (int i = 0; i <= length; i++) {
      char ch = (i < length) ? p.charAt(i) : 0;
      char pch = (i == 0) ? 0 : p.charAt(i-1);
      // Get rid of double backslash immediately, otherwise
      // the second backslash incorrectly triggers a new escape sequence
      if (pch == '\\' && ch == '\\') {
        p.setCharAt(i-1, ' ');
        p.setCharAt(i, ' ');
        pch = ' ';
        ch = ' ';
      }
      switch (state) {
        case OUT:
          switch (ch) {
            case '\'': state = IN_CHAR_LITERAL; break;
            case '"': state = IN_STRING_LITERAL; break;
            case '*': if (pch == '/') state = IN_BLOCK_COMMENT; break;
            case '/': if (pch == '/') state = IN_EOL_COMMENT; break;
          }
          break;
        case IN_BLOCK_COMMENT:
          if (pch == '*' && ch == '/' && (i - blockStart) > 0) {
            state = OUT;
          }
          break;
        case IN_EOL_COMMENT:
          if (ch == '\r' || ch == '\n') {
            state = OUT;
          }
          break;
        case IN_STRING_LITERAL:
          if ((pch != '\\' && ch == '"') || ch == '\r' || ch == '\n') {
            state = OUT;
          }
          break;
        case IN_CHAR_LITERAL:
          if ((pch != '\\' && ch == '\'') || ch == '\r' || ch == '\n') {
            state = OUT;
          }
          break;
      }

      // Terminate ongoing block at last char
      if (i == length) {
        state = OUT;
      }

      // Handle state changes
      if (state != prevState) {
        if (state != OUT) {
          // Entering block
          blockStart = i + 1;
        } else {
          // Exiting block
          int blockEnd = i;
          if (prevState == IN_BLOCK_COMMENT && i < length) blockEnd--;  // preserve star in '*/'
          for (int j = blockStart; j < blockEnd; j++) {
            char c = p.charAt(j);
            if (c != '\n' && c != '\r') p.setCharAt(j, ' ');
          }
        }
      }

      prevState = state;
    }
  }


  // TODO: move this to a better place when JavaBuild starts using JDT and we
  //       don't need to check errors at two different places [jv 2017-09-19]
  /**
   * Checks a single code fragment (such as a tab) for non-matching braces.
   * Broken out to allow easy use in JavaBuild.
   * @param c Program code scrubbed of comments and string literals.
   * @param start Start index, inclusive.
   * @param end End index, exclusive.
   * @return {@code int[4]} Depth at which the loop stopped, followed by the
   *         line number, column, and string index (within the range) at which
   *         an error was found, if any.
   */
  static public int[] checkForMissingBraces(CharSequence c, int start, int end) {
    int depth = 0;
    int lineNumber = 0;
    int lineStart = start;
    for (int i = start; i < end; i++) {
      char ch = c.charAt(i);
      switch (ch) {
        case '{':
          depth++;
          break;
        case '}':
          depth--;
          break;
        case '\n':
          lineNumber++;
          lineStart = i;
          break;
      }
      if (depth < 0) {
        return new int[] {depth, lineNumber, i - lineStart, i - start};
      }
    }
    return new int[] {depth, lineNumber - 1, end - lineStart - 2, end - start - 2};
  }


  /**
   * Determine how many times a string appears in another.
   *
   * @param body The string in which occurrences should be counted.
   * @param search The string to look for.
   * @return The number of times search appears in body.
   */
  public static int getCount(String body, String search) {
    int count = 0;
    if (search.length() == 1) {
      for (int i = 0; i < body.length(); i++) {
        if (body.charAt(i) == search.charAt(0)) {
          count++;
        }
      }
    } else {
      for (int i = 0; i < body.length(); i++) {
        if (body.substring(i).startsWith(search)) {
          count++;
        }
      }
    }
    return count;
  }
}
