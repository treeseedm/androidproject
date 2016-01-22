package androidproject.com.controller.controller;


import android.content.Context;

import de.greenrobot.event.EventBus;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by mahaifeng on 16/1/20.
 */
public class BaseNetController {
    private static final String HOST = "http://api.map.baidu.com";
    protected CallService mCallService;
    protected Context mContext;
    private boolean isNeedRegister;

    public void setNeedRegister(boolean isNeedRegister) {
        this.isNeedRegister = isNeedRegister;
    }

    public boolean getNeedRegister() {
        return isNeedRegister;
    }

    public BaseNetController(Context context) {
        this.mContext = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mCallService = retrofit.create(CallService.class);
        if (isNeedRegister) {
            EventBus.getDefault().register(this);
        }
    }
}
