package com.yunzhanghu.androiddemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_enter).setOnClickListener(this);
        findViewById(R.id.btn_js).setOnClickListener(this);
        findViewById(R.id.btn_auth).setOnClickListener(this);
        findViewById(R.id.btn_invest).setOnClickListener(this);
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
            case R.id.btn_auth:
                Toast.makeText(this, "正在开发中", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_invest:
                Toast.makeText(this, "正在开发中", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
