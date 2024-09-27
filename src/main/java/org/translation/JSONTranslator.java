package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * An implementation of the Translator interface which reads in the translation
 * data from a JSON file. The data is read in once each time an instance of this class is constructed.
 */
public class JSONTranslator implements Translator {

    private Map<String, Map<String, String>> countryInfoMap = new HashMap<>();

    /**
     * Constructs a JSONTranslator using data from the sample.json resources file.
     */
    public JSONTranslator() {
        this("sample.json");
    }

    /**
     * Constructs a JSONTranslator populated using data from the specified resources file.
     * @param filename the name of the file in resources to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public JSONTranslator(String filename) {
        // read the file to get the data to populate things...
        try {

            String jsonString = Files.readString(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));

            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                Iterator<String> jsonObjKeys = jsonObj.keys();

                String countryCode = jsonObj.getString("alpha3");
                Map<String, String> infoMap = new HashMap<>();

                while (jsonObjKeys.hasNext()) {
                    String jsonObjKey = jsonObjKeys.next();
                    if (!"id".equals(jsonObjKey) && !"alpha2".equals(jsonObjKey) && !"alpha3".equals(jsonObjKey)) {
                        infoMap.put(jsonObjKey, jsonObj.getString(jsonObjKey));
                    }
                }

                countryInfoMap.put(countryCode, infoMap);
            }

        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<String> getCountryLanguages(String country) {
        ArrayList<String> languages = new ArrayList<>();
        Map<String, String> languageMap = countryInfoMap.get(country);
        for (String languageCode: languageMap.keySet()) {
            languages.add(languageCode);
        }
        return languages;
    }

    @Override
    public List<String> getCountries() {
        ArrayList<String> countries = new ArrayList<>();
        for (String countryCode: countryInfoMap.keySet()) {
            countries.add(countryCode);
        }
        return countries;
    }

    @Override
    public String translate(String country, String language) {
        return countryInfoMap.get(country).get(language);
    }
}
