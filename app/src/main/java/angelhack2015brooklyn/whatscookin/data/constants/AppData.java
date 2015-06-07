package angelhack2015brooklyn.whatscookin.data.constants;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppData {

    public static final String FTF_API_BASE_URL = "http://food2fork.com/api/search";
    public static final String API_KEY = "cae1aed4795c6f38111ad437ac264503";
    public static final String FTF_API_URL = AppData.FTF_API_BASE_URL + "?key=" + AppData.API_KEY;
    private static RequestQueue mRequestQueue;

    public static RequestQueue getRequestQueue(Context context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
        return mRequestQueue;
    }
}