package androidproject.com.controller.demo;


import android.content.Context;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import androidproject.com.controller.common.ApLog;
import androidproject.com.controller.controller.BaseNetController;
import androidproject.com.controller.controller.BaseResponse;
import androidproject.com.controller.demo.event.DemoRequestEvent;
import androidproject.com.controller.demo.event.DemoResponseEvent;
import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by mahaifeng on 16/1/19.
 */
@Singleton
public class DemoController extends BaseNetController {
    @Inject
    public DemoController(Context context) {
        super(context);
        setNeedRegister(true);
    }

    public void onEventBackgroundThread(final DemoRequestEvent requestEvent) {
        final Call<DemoResponseEvent> res = mCallService.repos();
        ApLog.d(this, "---context" + mContext);
        res.enqueue(new Callback<DemoResponseEvent>() {
            @Override
            public void onResponse(Response<DemoResponseEvent> response, Retrofit retrofit) {
                response.body().userName = requestEvent.userName;
                EventBus.getDefault().post(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                EventBus.getDefault().post(new DemoResponseEvent(BaseResponse.Result.FAINL));

            }
        });
    }
}
