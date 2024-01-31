package ksv.view;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

class LocaleManager {
    private static Locale currentLocale = Locale.getDefault();
    private static ResourceBundle messagesBundle = ResourceBundle.getBundle("Messages", currentLocale);

    private LocaleManager() {
    }

    public static void setLocale(Locale locale) {
        currentLocale = locale;
        messagesBundle = ResourceBundle.getBundle("Messages", currentLocale);
    }

    public static String getMessage(String key, Object... args) {
        String pattern = messagesBundle.getString(key);
        return MessageFormat.format(pattern, args);
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }
}