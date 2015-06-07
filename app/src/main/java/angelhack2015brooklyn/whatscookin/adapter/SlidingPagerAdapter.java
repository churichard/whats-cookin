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

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        if (inNeutralArea(ev.getX(), ev.getY())) {
//            //--events re-directed to this ViewPager's onTouch() and to its child views from there--
//            return false;
//        } else {
//            //--events intercepted by this ViewPager's default implementation, where it looks for swipe gestures--
//            return super.onInterceptTouchEvent(ev);
//        }
//    }
//
//    private boolean inNeutralArea(float x, float y) {
//        //--check if x,y inside non reactive area, return true/false accordingly--
//        return false;
//    }
}