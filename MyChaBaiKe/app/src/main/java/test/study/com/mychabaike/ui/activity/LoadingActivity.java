package test.study.com.mychabaike.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import test.study.com.mychabaike.R;
import test.study.com.mychabaike.utils.ConstantKey;
import test.study.com.mychabaike.utils.PrefUtils;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    Intent intent = new Intent();
                if(PrefUtils.isFirst(LoadingActivity.this, ConstantKey.PREF_FIRST_KEY)){
                    intent.setClass(LoadingActivity.this,WelcomeActivity.class);
                }else{
                    intent.setClass(LoadingActivity.this,WelcomeActivity.class);
                }
                    startActivity(intent);

            }
        },3000);
    }
}
