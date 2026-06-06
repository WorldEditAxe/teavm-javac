package processing.app;

import java.util.HashMap;
import java.util.Map;

public final class Preferences {
    private static final Map<String, String> VALUES = new HashMap<>();

    static {
        VALUES.put("editor.tabs.size", "4");
        VALUES.put("preproc.substitute_unicode", "false");
        VALUES.put("export.application.present", "false");
        VALUES.put("export.application.fullscreen", "false");
        VALUES.put("export.application.stop", "true");
        VALUES.put("run.present.bgcolor", "#666666");
        VALUES.put("run.present.stop.color", "#cccccc");
    }

    private Preferences() {
    }

    public static String get(String attribute) {
        return VALUES.get(attribute);
    }

    public static boolean getBoolean(String attribute) {
        return Boolean.parseBoolean(get(attribute));
    }

    public static int getInteger(String attribute) {
        var value = get(attribute);
        return value != null ? Integer.parseInt(value) : 0;
    }
}
