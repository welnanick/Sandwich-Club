package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {

            JSONObject sandwichJSON;
            sandwichJSON = new JSONObject(json);
            JSONObject name = sandwichJSON.getJSONObject("name");
            String mainName = name.getString("mainName");
            JSONArray alsoKnownAsJSON = name.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsJSON.length(); i++) {

                alsoKnownAs.add(alsoKnownAsJSON.getString(i));

            }
            String placeOfOrigin = sandwichJSON.getString("placeOfOrigin");
            String description = sandwichJSON.getString("description");
            String image = sandwichJSON.getString("image");
            JSONArray ingredientsJSON = sandwichJSON.getJSONArray("ingredients");
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
