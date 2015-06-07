package angelhack2015brooklyn.whatscookin.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private RecyclerView recyclerView;

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
        recyclerView = (RecyclerView) recipeActivity.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(recipeActivity);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recipeActivity, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(AppData.getRecipeAdapter(recipeActivity, AppData.getSavedRecipes()));
    }
}