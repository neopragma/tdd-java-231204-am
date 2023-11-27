package com.neopragma.carrental.util;

import java.util.Locale;
import java.util.ResourceBundle;

import static com.neopragma.carrental.Constants.RESOURCE_BUNDLE_BASE_NAME;

public class I18n {

    private static Locale locale;
    private static ResourceBundle bundle;

    public static void setLocale(String language) {
        Strings.assertValidStringArgs(language);
        locale = Locale.of(language);
        loadResourceBundle();
    }

    public static void setLocale(String language, String country) {
        Strings.assertValidStringArgs(language, country);
        locale = Locale.of(language, country);
        loadResourceBundle();
    }

    public static void setLocale(String language, String country, String variant) {
        Strings.assertValidStringArgs(language, country, variant);
        locale = Locale.of(language, country, variant);
        loadResourceBundle();
    }

    public static void setDefaultLocale() {
        locale = Locale.getDefault();
        loadResourceBundle();
    }

    public static Locale currentLocale() {
        return locale;
    }

    public static String textOf(String key) {
        return bundle.getString(key);
    }


    private static void loadResourceBundle() {
        bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE_NAME, locale);
    }
}
