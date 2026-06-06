package processing.mode.java.preproc;

final class StringJoiner {
    private final String delimiter;
    private final StringBuilder content = new StringBuilder();
    private boolean empty = true;

    StringJoiner(String delimiter) {
        this.delimiter = delimiter;
    }

    void add(String value) {
        if (!empty) {
            content.append(delimiter);
        }
        content.append(value);
        empty = false;
    }

    @Override
    public String toString() {
        return content.toString();
    }
}
