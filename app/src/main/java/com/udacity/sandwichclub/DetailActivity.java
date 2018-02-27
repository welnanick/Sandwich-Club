package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {

            closeOnError();

        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {

            // EXTRA_POSITION not found in intent
            closeOnError();
            return;

        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {

            // Sandwich data unavailable
            closeOnError();
            return;

        }

        populateUI(sandwich);
        Picasso.with(this).load(sandwich.getImage()).into(ingredientsIv);

        setTitle(sandwich.getMainName());

    }

    private void closeOnError() {

        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();

    }

    private void populateUI(Sandwich sandwich) {

        TextView alsoKnownTv = findViewById(R.id.also_known_tv);
        List<String> alsoKnownAs = sandwich.getAlsoKnownAs();
        if (alsoKnownAs.size() == 0) {

            alsoKnownTv.setText(R.string.aka_not_applicable);

        }
        else {

            StringBuilder akaStringBuilder = new StringBuilder();
            for (String name : alsoKnownAs) {

                if (akaStringBuilder.length() != 0) {

                    akaStringBuilder.append(", ");

                }
                akaStringBuilder.append(name);

            }
            alsoKnownTv.setText(akaStringBuilder.toString());

        }

        TextView originTv = findViewById(R.id.origin_tv);
        String origin = sandwich.getPlaceOfOrigin();
        if (origin != null && !origin.equals("")) {

            originTv.setText(origin);

        }
        else {

            originTv.setText(R.string.origin_unknown);

        }

        TextView descriptionTv = findViewById(R.id.description_tv);
        String description = sandwich.getDescription();
        if (description != null && !description.equals("")) {

            descriptionTv.setText(description);

        }

        TextView ingredientsTv = findViewById(R.id.ingredients_tv);
        List<String> ingredients = sandwich.getIngredients();
        if (ingredients.size() == 0) {

            ingredientsTv.setText(R.string.aka_not_applicable);

        }
        else {

            StringBuilder ingredientsStringBuilder = new StringBuilder();
            for (String ingredient : ingredients) {

                if (ingredientsStringBuilder.length() != 0) {

                    ingredientsStringBuilder.append(", ");

                }
                ingredientsStringBuilder.append(ingredient);

            }
            ingredientsTv.setText(ingredientsStringBuilder.toString());

        }

    }

}
