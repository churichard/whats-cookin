package angelhack2015brooklyn.whatscookin.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import angelhack2015brooklyn.whatscookin.fragment.RecipeListFragment;
import angelhack2015brooklyn.whatscookin.fragment.RecipeSwipeFragment;

public class SlidingPagerAdapter extends FragmentPagerAdapter {
    private static final String[] titles = {"List", "Swipe"};
    private static final int NUM_OF_ITEMS = 2;

    public SlidingPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return NUM_OF_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new RecipeListFragment();
        } else {
            return new RecipeSwipeFragment();
        }
    }

    @Override
    public String getPageTitle(int position) {
        return titles[position];
    }
}