package angelhack2015brooklyn.whatscookin.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
import angelhack2015brooklyn.whatscookin.adapter.CardAdapter;
import angelhack2015brooklyn.whatscookin.data.constants.AppData;
import angelhack2015brooklyn.whatscookin.data.model.Recipe;


public class RecipeActivity extends Activity {

    private static final String TAG = RecipeActivity.class.getSimpleName();
    private static final int TIMEOUT_MS = 10000;
    private SwipeFlingAdapterView cardContainer;
    private CardAdapter cardStackAdapter;
    private List<Recipe> recipes;
    private int pageNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        cardContainer = (SwipeFlingAdapterView) findViewById(R.id.card_container);
        recipes = new ArrayList<>();
        setupCardContainer(recipes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recipe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getRecipesNames(String url, int pageNum) {
        RequestQueue queue = AppData.getRequestQueue(this);
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
        cardStackAdapter = new CardAdapter(RecipeActivity.this, R.layout.list_card_item, items);
        cardContainer.setAdapter(cardStackAdapter);

        cardContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                items.remove(0);
                cardStackAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                Toast.makeText(RecipeActivity.this, "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(RecipeActivity.this, "Right!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                getRecipesNames(AppData.FTF_API_URL, pageNum);
                pageNum++;
                cardStackAdapter.notifyDataSetChanged();
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                RecipeActivity.this.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                RecipeActivity.this.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });
    }
}