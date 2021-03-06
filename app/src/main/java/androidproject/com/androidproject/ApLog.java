package androidproject.com.androidproject;

import android.util.Log;

/**
 * Created by mahaifeng on 17/8/30.
 */

public class ApLog {
    private static String TAG = "ApLog";

    private static boolean isShow = true;

    public static void d(Object o, String d) {
        if (isShow) {
            Log.d(o.getClass().getSimpleName(), d);
        }
    }

    public static void v(String tag, String msg) {
        if (isShow) {
            Log.v(tag, msg);
        }

    }
}
