package androidproject.com.controller;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.inject.Inject;

import androidproject.com.controller.controller.ServiceStartEvent;
import androidproject.com.controller.demo.DemoController;
import de.greenrobot.event.EventBus;
import roboguice.service.RoboService;

/**
 * Created by mahaifeng on 16/1/19.
 */
public class ApService extends RoboService {
    @Inject
    private DemoController mDemoController;

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().post(new ServiceStartEvent());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(mDemoController);
        super.onDestroy();
    }
}
