package angelhack2015brooklyn.whatscookin.activity;

import android.app.Activity;
import android.os.Bundle;

import angelhack2015brooklyn.whatscookin.R;
import angelhack2015brooklyn.whatscookin.adapter.SlidingPagerAdapter;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;


public class RecipeActivity extends Activity {

    private static final String TAG = RecipeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        VerticalViewPager viewPager = (VerticalViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SlidingPagerAdapter(getFragmentManager()));
        viewPager.setCurrentItem(1);
    }
}