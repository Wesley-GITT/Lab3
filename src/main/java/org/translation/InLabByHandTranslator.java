package org.translation;

import java.util.ArrayList;
import java.util.List;

// Extra Task: if your group has extra time, you can add support for another country code in this class.

/**
 * An implementation of the Translator interface which translates
 * the country code "can" to several languages.
 */
public class InLabByHandTranslator implements Translator {
    /**
     * Returns the language abbreviations for all languages whose translations are
     * available for the given country.
     *
     * @param country the country
     * @return list of language abbreviations which are available for this country
     */

    public static final String CANADA = "can";

    @Override
    public List<String> getCountryLanguages(String country) {
        if (CANADA.equals(country)) {
            return new ArrayList<>(List.of("de", "en", "zh", "es", "fr"));
        }
        return new ArrayList<>();
    }

    /**
     * Returns the country abbreviations for all countries whose translations are
     * available from this Translator.
     *
     * @return list of country abbreviations for which we have translations available
     */
    @Override
    public List<String> getCountries() {
        return new ArrayList<>(List.of(CANADA));
    }

    /**
     * Returns the name of the country based on the specified country abbreviation and language abbreviation.
     *
     * @param country  the country
     * @param language the language
     * @return the name of the country in the given language or null if no translation is available
     */
    @Override
    public String translate(String country, String language) {
        if (!CANADA.equals(country)) {
            return null;
        }
        String result;
        if ("de".equals(language)) {
            result = "Kanada";
        }
        else if ("en".equals(language)) {
            result = "Canada";
        }
        else if ("zh".equals(language)) {
            result = "加拿大";
        }
        else if ("es".equals(language)) {
            result = "Canadá";
        }
        else if ("fr".equals(language)) {
            result = "Canada";
        }
        else {
            return null;
        }

        return  result;
    }
}
