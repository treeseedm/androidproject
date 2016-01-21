package androidproject.com.controller.demo.event;

import androidproject.com.controller.controller.BaseResponse;

/**
 * Created by mahaifeng on 16/1/19.
 */
public class DemoResponseEvent extends BaseResponse{
    public String userName;
    public String message;
    public  String documentation_url;
    public DemoResponseEvent(Result result){
       this.result=result;
    }
}
