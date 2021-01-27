package lambda;

public class StringUtil {

    private StringUtil() {

    }

    public static String toLowerCase(String s) {
        return s != null ? s.toLowerCase() : null;
    }

    public static String toUpperCase(String s) {
        return s != null ? s.toUpperCase() : null;
    }
}
