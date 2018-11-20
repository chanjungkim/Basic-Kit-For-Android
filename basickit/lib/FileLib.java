package com.koreanlab.bestfood.lib;

import android.content.Context;
import android.os.Environment;

import com.koreanlab.bestfood.Constant;

import java.io.File;

/**
 * Created by Chanj on 22/02/2018.
 */

public class FileLib {
    public static final String TAG = FileLib.class.getSimpleName();

    private volatile static FileLib instance;

    public static FileLib getInstance() {
        if (instance == null) {
            synchronized (FileLib.class) {
                if (instance == null) {
                    instance = new FileLib();
                }
            }
        }

        return instance;
    }

    private File getFileDir(Context context) {
        String state = Environment.getExternalStorageState();
        File filesDir;

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            filesDir = context.getExternalFilesDir(null);
        } else {
            filesDir = context.getFilesDir();
        }

        MyLog.d(TAG, "getFileDir() = " + filesDir);
        return filesDir;
    }

    public File getProfileIconFile(Context context, String name) {
        MyLog.d(TAG, "getProfileIconFile() = name + "+".png");
        return new File(FileLib.getInstance().getFileDir(context), name + ".png");
    }

    public File getImageFile(Context context, String name){
        MyLog.d(TAG, "getImageFile() = name + "+".png");
        return new File(FileLib.getInstance().getFileDir(context), name + ".png");
    }
}
