package com.yanzhenjie.permission.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yanzhenjie.permission.sample.fragment.FragmentActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.btn_activity).setOnClickListener(this);
        findViewById(R.id.btn_fragment).setOnClickListener(this);
        findViewById(R.id.btn_rationale).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_activity: {
                startActivity(new Intent(this, PermissionActivity.class));
                break;
            }
            case R.id.btn_fragment: {
                startActivity(new Intent(this, FragmentActivity.class));
                break;
            }
            case R.id.btn_rationale: {
                startActivity(new Intent(this, RationalePermissionActivity.class));
                break;
            }
        }
    }
}
