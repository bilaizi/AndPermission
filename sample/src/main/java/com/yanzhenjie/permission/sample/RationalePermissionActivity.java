package com.yanzhenjie.permission.sample;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.RationaleListener;

/**
 * Created by Yan Zhenjie on 2016/9/10.
 */
public class RationalePermissionActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rationale_permission);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.btn_request_location).setOnClickListener(this);
    }

    /**
     * 申请SD卡权限，单个的。
     */
    private void requestLocationPermission() {
        AndPermission.with(this)
                .requestCode(100)
                .permission(Manifest.permission.ACCESS_FINE_LOCATION)
                .rationale(rationaleListener)
                .send();
    }

    private RationaleListener rationaleListener =
            (requestCode, rationale) -> new AlertDialog.Builder(RationalePermissionActivity.this)
                    .setTitle("友好提醒")
                    .setMessage("您已拒绝过定位权限，没有定位权限无法为您推荐附近妹子，请把定位权限赐给我吧！")
                    .setPositiveButton("好，给你", (dialog, which) -> {
                        dialog.cancel();
                        rationale.resume();
                    })
                    .setNegativeButton("我拒绝", (dialog, which) -> {
                        dialog.cancel();
                        rationale.cancel();
                    }).show();

    @PermissionYes(100)
    private void getLocationYes() {
        Toast.makeText(this, "获取定位权限成功", Toast.LENGTH_SHORT).show();
    }

    @PermissionNo(100)
    private void getLocationNo() {
        Toast.makeText(this, "获取定位权限失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // 这个Activity中没有Fragment，这句话可以注释。
        // super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_request_location: {
                requestLocationPermission();
                break;
            }
        }
    }
}
