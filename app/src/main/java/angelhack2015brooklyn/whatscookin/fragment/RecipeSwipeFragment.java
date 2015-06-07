package angelhack2015brooklyn.whatscookin.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import angelhack2015brooklyn.whatscookin.R;
import angelhack2015brooklyn.whatscookin.activity.RecipeActivity;
import angelhack2015brooklyn.whatscookin.adapter.CardAdapter;
import angelhack2015brooklyn.whatscookin.data.constants.AppData;
import angelhack2015brooklyn.whatscookin.data.model.Recipe;

public class RecipeSwipeFragment extends Fragment {

    private static final String TAG = RecipeSwipeFragment.class.getSimpleName();
    private RecipeActivity recipeActivity;
    private static final int TIMEOUT_MS = 10000;
    private SwipeFlingAdapterView cardContainer;
    private CardAdapter cardStackAdapter;
    private List<Recipe> recipes;
    private int pageNum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recipeActivity = (RecipeActivity) getActivity();
        return inflater.inflate(R.layout.fragment_recipe_swipe, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cardContainer = (SwipeFlingAdapterView) recipeActivity.findViewById(R.id.card_container);
        recipes = new ArrayList<>();
        setupCardContainer(recipes);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void getRecipesNames(String url, int pageNum) {
        RequestQueue queue = AppData.getRequestQueue(recipeActivity);
        url += "&sort=t&page=" + pageNum;

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Create an array of buzzes
                            JSONObject jsonObj = new JSONObject(response);
                            JSONArray jsArray = jsonObj.getJSONArray("recipes");
                            int count = jsonObj.getInt("count");

                            // Populate recipes and card stack
                            for (int i = 0; i < count; i++) {
                                recipes.add(new Recipe((JSONObject) jsArray.get(i)));
                            }

                            cardStackAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Log.e(TAG, e.toString(), e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.toString(), error);
                    }
                });

        request.setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);
    }

    private void setupCardContainer(final List<Recipe> items) {
        pageNum = 1;
        cardStackAdapter = new CardAdapter(recipeActivity, R.layout.list_card_item, items);
        cardContainer.setAdapter(cardStackAdapter);

        cardContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            private Recipe swipedRecipe;

            @Override
            public void removeFirstObjectInAdapter() {
                swipedRecipe = items.remove(0);
                cardStackAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                recipeActivity.findViewById(R.id.item_swipe_left_indicator).setAlpha(0);
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                if (swipedRecipe != null) {
                    AppData.getSavedRecipes().add(swipedRecipe);
                    AppData.getRecipeAdapter(recipeActivity, AppData.getSavedRecipes()).notifyDataSetChanged();
                }
                recipeActivity.findViewById(R.id.item_swipe_right_indicator).setAlpha(0);
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                getRecipesNames(AppData.FTF_API_URL, pageNum);
                pageNum++;
                cardStackAdapter.notifyDataSetChanged();
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                recipeActivity.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                recipeActivity.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });
    }
}