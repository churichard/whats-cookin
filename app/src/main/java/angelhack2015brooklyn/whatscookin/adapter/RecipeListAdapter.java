package angelhack2015brooklyn.whatscookin.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import angelhack2015brooklyn.whatscookin.R;
import angelhack2015brooklyn.whatscookin.activity.RecipeActivity;
import angelhack2015brooklyn.whatscookin.activity.ShowRecipeActivity;
import angelhack2015brooklyn.whatscookin.data.constants.AppData;
import angelhack2015brooklyn.whatscookin.data.model.Recipe;
import angelhack2015brooklyn.whatscookin.ui.holder.RecipeViewHolder;


public class RecipeListAdapter extends RecyclerView.Adapter<RecipeViewHolder> {
    private RecipeActivity recipeActivity;
    private List<Recipe> recipes;

    public RecipeListAdapter(List<Recipe> recipes, Activity recipeActivity) {
        this.recipes = recipes;
        this.recipeActivity = (RecipeActivity) recipeActivity;
    }

    public RecipeListAdapter() {
        this.recipes = null;
    }

    // Create new views
    @Override
    public RecipeViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recipe_item, parent, false);
        return new RecipeViewHolder(v, new RecipeViewHolder.RecipeViewHolderClick() {
            public void onClick(View v, int position) {
                Intent intent = new Intent(recipeActivity, ShowRecipeActivity.class);
                intent.putExtra(AppData.WEB_VIEW_URL_TAG, recipes.get(position).getRecipeUrl());
                recipeActivity.startActivity(intent);
            }
        });
    }

    // Replace the contents of a view
    @Override
    public void onBindViewHolder(RecipeViewHolder viewHolder, int position) {
        Recipe recipe = recipes.get(position);
        viewHolder.title.setText(recipe.getTitle());
        Glide.with(recipeActivity)
                .load(recipe.getImgUrl())
                .into(viewHolder.image);
    }

    // Return the number of posts
    @Override
    public int getItemCount() {
        if (recipes != null) {
            return recipes.size();
        }
        return 0;
    }
}