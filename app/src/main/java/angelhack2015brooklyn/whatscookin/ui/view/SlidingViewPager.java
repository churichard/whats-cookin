package angelhack2015brooklyn.whatscookin.ui.view;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;

import angelhack2015brooklyn.whatscookin.activity.RecipeActivity;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class SlidingViewPager extends VerticalViewPager {

    public static RecipeActivity recipeActivity;

    public SlidingViewPager(Context context) {
        super(context);
    }

    public SlidingViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (inSwipeArea(ev.getX(), ev.getY())) {
            return false;
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    private boolean inSwipeArea(float x, float y) {
        Display display = recipeActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
//        int height = getHeight();

        return height <= 100;
    }
}