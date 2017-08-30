package androidproject.com.androidproject.net;

import androidproject.com.androidproject.entity.DemoResponseEvent;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by mahaifeng on 17/8/30.
 */

public class DemoController extends BaseNetController {
    public void test(final BaseCallBack callBack) {
        final Call<DemoResponseEvent> res = mCallService.repos();
        res.enqueue(new Callback<DemoResponseEvent>() {
            @Override
            public void onResponse(Response<DemoResponseEvent> response, Retrofit retrofit) {
                callBack.onResult(response.body());
            }

            @Override
            public void onFailure(Throwable t) {


            }
        });
    }
}
