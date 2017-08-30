package androidproject.com.androidproject.net;


import androidproject.com.androidproject.ApApplication;
import androidproject.com.androidproject.entity.BaseResponse;

/**
 * Created by mahaifeng on 16/1/20.
 */
public class BaseNetController {
    protected CallService mCallService;

    public BaseNetController() {
        mCallService = ApApplication.getCallService();
    }

    public interface BaseCallBack {
        void onResult(BaseResponse response);
    }
}
