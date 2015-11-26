package com.yunzhanghu.androiddemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_enter).setOnClickListener(this);
        findViewById(R.id.btn_js).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_enter:
                startActivity(new Intent(this, WebViewActivity.class));
                break;
            case R.id.btn_js:
                startActivity(new Intent(this, JSActivity.class));
                break;
        }
    }
}
