package angelhack2015brooklyn.whatscookin.data.constants;

import android.app.Activity;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import angelhack2015brooklyn.whatscookin.adapter.RecipeListAdapter;
import angelhack2015brooklyn.whatscookin.data.model.Recipe;

public class AppData {

    public static final String FTF_API_BASE_URL = "http://food2fork.com/api/search";
    public static final String API_KEY = "cae1aed4795c6f38111ad437ac264503";
    public static final String FTF_API_URL = AppData.FTF_API_BASE_URL + "?key=" + AppData.API_KEY;
    public static final String WEB_VIEW_URL_TAG = "Web View Url";
    private static RecipeListAdapter recipeAdapter;
    private static RequestQueue mRequestQueue;
    private static List<Recipe> savedRecipes;

    public static RequestQueue getRequestQueue(Context context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
        return mRequestQueue;
    }

    public static RecipeListAdapter getRecipeAdapter(Activity activity, List<Recipe> recipes) {
        if (recipeAdapter == null) {
            recipeAdapter = new RecipeListAdapter(recipes, activity);
        }
        return recipeAdapter;
    }

    public static List<Recipe> getSavedRecipes() {
        if (savedRecipes == null) {
            savedRecipes = new ArrayList<>();
        }
        return savedRecipes;
    }
}