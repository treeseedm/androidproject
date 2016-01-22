package androidproject.com.androidproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidproject.com.controller.controller.ServiceStartEvent;
import androidproject.com.controller.demo.event.DemoRequestEvent;
import androidproject.com.controller.demo.event.DemoResponseEvent;
import de.greenrobot.event.EventBus;
import roboguice.inject.InjectView;

/**
 * Created by mahaifeng on 16/1/19.
 */
public class DemoActivity extends BaseActivity {
    @InjectView(R.id.tv_username)
    EditText tvName;
    @InjectView(R.id.btn_send)
    Button btnSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        setNeedRegister(true);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DemoRequestEvent(tvName.getText().toString()));
            }
        });
    }

    public void onEventMainThread(DemoResponseEvent event) {
        tvName.setText(event.userName+":"+event.message);
    }

    public void onEventMainThread(ServiceStartEvent event) {
        EventBus.getDefault().post(new DemoRequestEvent(tvName.getText().toString()));
    }

    ;
}
