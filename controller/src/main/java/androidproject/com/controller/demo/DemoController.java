package androidproject.com.controller.demo;


import com.google.inject.Inject;
import com.google.inject.Singleton;

import androidproject.com.commonlib.ApLog;
import androidproject.com.controller.controller.BaseNetController;
import androidproject.com.controller.demo.event.DemoRequestEvent;
import androidproject.com.controller.demo.event.DemoResponseEvent;
import de.greenrobot.event.EventBus;

/**
 * Created by mahaifeng on 16/1/19.
 */
@Singleton
public class DemoController extends BaseNetController {
    public DemoController() {
        super();
    }

    public void onEventBackgroundThread(DemoRequestEvent requestEvent) {
        ApLog.d(this, requestEvent.userName);
        EventBus.getDefault().post(new DemoResponseEvent("你好" + requestEvent.userName));
    }
}
