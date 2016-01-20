package androidproject.com.androidproject;

import android.app.Application;
import android.content.Intent;

import androidproject.com.controller.ApService;

/**
 * Created by mahaifeng on 16/1/20.
 */
public class ApApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, ApService.class));
    }
}
