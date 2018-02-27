package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String JSON_NAME_KEY = "name";
    public static final String JSON_MAIN_NAME_KEY = "mainName";
    public static final String JSON_ALSO_KNOWN_AS_KEY = "alsoKnownAs";
    public static final String JSON_PLACE_OF_ORIGIN_KEY = "placeOfOrigin";
    public static final String JSON_DESCRIPTION_KEY = "description";
    public static final String JSON_IMAGE_KEY = "image";
    public static final String JSON_INGREDIENTS_KEY = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        try {

            JSONObject sandwichJSON;
            sandwichJSON = new JSONObject(json);
            JSONObject name = sandwichJSON.getJSONObject(JSON_NAME_KEY);
            String mainName = name.optString(JSON_MAIN_NAME_KEY);
            JSONArray alsoKnownAsJSON = name.getJSONArray(JSON_ALSO_KNOWN_AS_KEY);
            List<String> alsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsJSON.length(); i++) {

                alsoKnownAs.add(alsoKnownAsJSON.getString(i));

            }
            String placeOfOrigin = sandwichJSON.optString(JSON_PLACE_OF_ORIGIN_KEY);
            String description = sandwichJSON.optString(JSON_DESCRIPTION_KEY);
            String image = sandwichJSON.optString(JSON_IMAGE_KEY);
            JSONArray ingredientsJSON = sandwichJSON.getJSONArray(JSON_INGREDIENTS_KEY);
            List<String> ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientsJSON.length(); i++) {

                ingredients.add(ingredientsJSON.getString(i));

            }

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image,
                    ingredients);

        }
        catch (JSONException e) {

            e.printStackTrace();

        }

        return null;

    }

}
