package angelhack2015brooklyn.whatscookin.data.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Recipe {
    private static final String TAG = "Recipe";

    private String id;
    private String title;
    private String recipeUrl;
    private String imgUrl;
    private double socialRank;

    public Recipe(JSONObject jsonObj) {
        try {
            title = jsonObj.getString("title");
            id = jsonObj.getString("recipe_id");
            recipeUrl = jsonObj.getString("source_url");
            imgUrl = jsonObj.getString("image_url");
            socialRank = jsonObj.getLong("social_rank");
        } catch (JSONException e) {
            Log.e(TAG, e.toString(), e);
        }
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRecipeUrl() {
        return recipeUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public double getSocialRank() {
        return socialRank;
    }
}