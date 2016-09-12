package com.yanzhenjie.permission;

/**
 * Created by Yan Zhenjie on 2016/9/10.
 */
public interface Rationale {

    /**
     * Cancel request permission.
     */
    void cancel();

    /**
     * Go request permission.
     */
    void resume();

}
