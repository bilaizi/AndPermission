package com.yanzhenjie.permission;

/**
 * Created by Yan Zhenjie on 2016/9/9.
 */
public interface Permission {

    /**
     * Here to fill in all of this to apply for permission, can be a, can be more.
     *
     * @param permissions permissions.
     * @return {@link Permission}.
     */
    Permission permission(String... permissions);

    /**
     * Request code.
     *
     * @param requestCode int, the first parameter in callback {@code onRequestPermissionsResult(int, String[], int[])}}.
     * @return {@link Permission}.
     */
    Permission requestCode(int requestCode);

    /**
     * With user privilege refused many times, the Listener will be called back, you can prompt the user permissions role in this method.
     *
     * @param listener {@link RationaleListener}.
     * @return {@link Permission}.
     */
    Permission rationale(RationaleListener listener);

    /**
     * Request permission.
     */
    void send();

}
