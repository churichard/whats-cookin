package angelhack2015brooklyn.whatscookin.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import angelhack2015brooklyn.whatscookin.R;
import angelhack2015brooklyn.whatscookin.activity.RecipeActivity;
import angelhack2015brooklyn.whatscookin.data.constants.AppData;
import angelhack2015brooklyn.whatscookin.ui.view.DividerItemDecoration;

public class RecipeListFragment extends Fragment {

    private static final String TAG = RecipeListFragment.class.getSimpleName();
    private RecipeActivity recipeActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recipeActivity = (RecipeActivity) getActivity();
        return inflater.inflate(R.layout.fragment_recipe_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
    }

    // Sets up the recycler view for trending posts
    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) recipeActivity.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(recipeActivity);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recipeActivity, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(AppData.getRecipeAdapter(recipeActivity, AppData.getSavedRecipes()));

        // Init swipe to dismiss logic
        ItemTouchHelper swipeToDismissTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // callback for swipe to dismiss, removing item from data and adapter
                AppData.getSavedRecipes().remove(viewHolder.getAdapterPosition());
                AppData.getRecipeAdapter(recipeActivity, AppData.getSavedRecipes()).notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        swipeToDismissTouchHelper.attachToRecyclerView(recyclerView);
    }
}