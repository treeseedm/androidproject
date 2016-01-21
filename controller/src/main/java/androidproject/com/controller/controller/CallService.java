package androidproject.com.controller.controller;

import java.util.List;

import androidproject.com.controller.demo.event.DemoResponseEvent;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;


/**
 * Created by mahaifeng on 16/1/20.
 */
public interface CallService {
    @GET("users/{user}/repos")
    Call<List<Object>> listRepos(@Path("user") String user);

    @GET("/telematics/v3/weather?location=%E5%8C%97%E4%BA%AC&output=json&ak=E4805d16520de693a3fe707cdc962045")
    Call<DemoResponseEvent> repos();

}
