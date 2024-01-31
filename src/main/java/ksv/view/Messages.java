package ksv.view;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {
    private static Locale currentLocale = Locale.ENGLISH;
    private static ResourceBundle messagesBundle = ResourceBundle.getBundle("Messages", currentLocale);

    private Messages() {
    }

    public static void setLocale(Locale locale) {
        currentLocale = locale;
    }

    public static String getMessage(String key, Object... args) {
        try {
            String pattern = messagesBundle.getString(key);
            return MessageFormat.format(pattern, args);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + key;
        }
    }
}


