package androidproject.com.controller.controller;

import de.greenrobot.event.EventBus;

/**
 * Created by mahaifeng on 16/1/20.
 */
public class BaseNetController {
    public BaseNetController() {
        EventBus.getDefault().register(this);
    }
}
