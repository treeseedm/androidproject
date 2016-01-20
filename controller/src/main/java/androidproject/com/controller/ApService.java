package androidproject.com.controller;

import android.content.Intent;
import android.os.IBinder;

import com.google.inject.Inject;

import androidproject.com.commonlib.ApLog;
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
        ApLog.d(this, "---service start" + mDemoController);
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
