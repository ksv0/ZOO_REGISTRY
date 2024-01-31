package ksv.view;

import java.util.Locale;

public enum Language {
    ENGLISH(new Locale("en", "EN")),
    RUSSIAN(new Locale("ru", "RU"));

    private final Locale locale;

    Language(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }
}