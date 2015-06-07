package angelhack2015brooklyn.whatscookin.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import angelhack2015brooklyn.whatscookin.R;
import angelhack2015brooklyn.whatscookin.activity.RecipeActivity;

public class RecipeListFragment extends Fragment {

    private RecipeActivity recipeActivity;
    private static final String TAG = RecipeListFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recipeActivity = (RecipeActivity) getActivity();
        return inflater.inflate(R.layout.fragment_recipe_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}