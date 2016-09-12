package com.yanzhenjie.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yan Zhenjie on 2016/9/9.
 */
public class AndPermission {
    private AndPermission() {
    }

    /**
     * In the Activity.
     *
     * @param activity {@link Activity}.
     * @return {@link Permission}.
     */
    public static Permission with(Activity activity) {
        return new ImplPermission(activity);
    }

    /**
     * In the Activity.
     *
     * @param fragment {@link Fragment}.
     * @return {@link Permission}.
     */
    public static Permission with(Fragment fragment) {
        return new ImplPermission(fragment);
    }

    /**
     * Request permissions in the activity.
     *
     * @param activity    {@link Activity}.
     * @param requestCode request code.
     * @param permissions all permissions.
     */
    public static void send(Activity activity, int requestCode, String... permissions) {
        with(activity).requestCode(requestCode).permission(permissions).send();
    }

    /**
     * Request permissions in the activity.
     *
     * @param fragment    {@link Fragment}.
     * @param requestCode request code.
     * @param permissions all permissions.
     */
    public static void send(Fragment fragment, int requestCode, String... permissions) {
        with(fragment).requestCode(requestCode).permission(permissions).send();
    }

    /**
     * Parse the request results.
     *
     * @param o            {@link Activity} or {@link Fragment}.
     * @param requestCode  request code.
     * @param permissions  all permissions.
     * @param grantResults results.
     */
    public static void onRequestPermissionsResult(Object o, int requestCode, String[] permissions, int[] grantResults) {
        List<String> deniedPermissions = new ArrayList<>(1);
        for (int i = 0; i < grantResults.length; i++)
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED)
                deniedPermissions.add(permissions[i]);
        callback(o, deniedPermissions.size() > 0 ? PermissionNo.class : PermissionYes.class, requestCode);
    }

    static <T extends Annotation> void callback(Object o, Class<T> clazz, int requestCode) {
        Method[] methods = PermissionUtils.findMethodForRequestCode(o.getClass(), clazz, requestCode);
        try {
            for (Method method : methods) {
                if (!method.isAccessible())
                    method.setAccessible(true);
                method.invoke(o, (Object) null);
            }
        } catch (Exception e) {
            Log.e("AndPermission", "Callback methods fail.");
        }
    }
}
