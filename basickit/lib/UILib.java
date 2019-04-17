package org.koreanlab.lib;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class UILib {
    private static Context mContext;
    private static UILib instance;
    int newUiOptions = 0;

    public static UILib getInstance(@NonNull Context context) {
        if (instance == null) {
            synchronized (UILib.class) {
                if (instance == null) {
                    instance = new UILib();
                    mContext = context;
                }
            }
        }
        return instance;
    }

    public UILib setHideNavigation(boolean flag) {
        // 아이스크림 샌드위치(4.0) 이상일 경우
        if (Build.VERSION.SDK_INT >= 14 && flag) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        return instance;
    }

    public UILib setFullScreen(boolean flag) {
        // 젤리빈(4.1) 이상일 경우
        if (Build.VERSION.SDK_INT >= 16 && flag) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }

        return instance;
    }

    public UILib setImmerseiveSticky() {
        // 킷캣(4.4) 이상일 경우
        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            return instance;
        }

        return instance;
    }

    public UILib setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // finally change the color
            ((Activity) mContext).getWindow().setStatusBarColor(color);
        }

        return instance;
    }

    public void apply() {
        ((Activity) mContext).getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
    }
}
