package androidproject.com.androidproject.entity;


/**
 * Created by mahaifeng on 17/8/30.
 */

public class DemoResponseEvent extends BaseResponse {
    public String userName;
    public String message;
    public String documentation_url;

    public DemoResponseEvent(Result result) {
        this.result = result;
    }
}
