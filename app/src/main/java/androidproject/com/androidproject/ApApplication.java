package androidproject.com.androidproject;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import androidproject.com.androidproject.net.CallService;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by mahaifeng on 16/1/20.
 */
public class ApApplication extends Application {
    private static final String HOST = "http://api.map.baidu.com";
    private static CallService mCallService;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(getApplicationContext());
        initNet();
    }

    private void initNet() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mCallService = retrofit.create(CallService.class);
    }

    public static CallService getCallService() {
        return mCallService;
    }
}
