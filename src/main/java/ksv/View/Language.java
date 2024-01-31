package ksv.View;

import java.util.Locale;

public enum Language {
    ENGLISH(Locale.ENGLISH),
    RUSSIAN(new Locale("ru", "RU"));

    private final Locale locale;

    Language(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }
}