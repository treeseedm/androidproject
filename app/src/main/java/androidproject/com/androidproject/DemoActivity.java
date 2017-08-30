package androidproject.com.androidproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidproject.com.androidproject.entity.BaseResponse;
import androidproject.com.androidproject.entity.DemoResponseEvent;
import androidproject.com.androidproject.net.BaseNetController;
import androidproject.com.androidproject.net.DemoController;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mahaifeng on 16/1/19.
 */
public class DemoActivity extends BaseActivity {
    @BindView(R.id.btn_send)
    Button viewSend;
    @BindView(R.id.tv_username)
    EditText viewText;
    private DemoController demoController = new DemoController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ButterKnife.bind(this);

        viewSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demoController.test(new BaseNetController.BaseCallBack() {
                    @Override
                    public void onResult(BaseResponse response) {
                        DemoResponseEvent demoResponseEvent = (DemoResponseEvent) response;
                        viewText.setText(demoResponseEvent.message);
                    }
                });
            }
        });
    }
}
