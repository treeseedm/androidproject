package androidproject.com.androidproject;

import android.os.Bundle;

import de.greenrobot.event.EventBus;
import roboguice.activity.RoboActivity;

/**
 * Created by mahaifeng on 16/1/19.
 */
public class BaseActivity extends RoboActivity {
    private boolean isNeedRegister;

    public void setNeedRegister(boolean isNeedRegister) {
        this.isNeedRegister = isNeedRegister;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        if (isNeedRegister) {
            EventBus.getDefault().register(this);
        }
        super.onStart();
    }

    @Override
    protected void onStop() {
        if (isNeedRegister) {
            EventBus.getDefault().unregister(this);
        }
        super.onStop();
    }
}
