package com.koreanlab.bestfood.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Parcel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Chanj on 25/02/2018.
 */

public class BitmapLib {
    public static final String TAG = BitmapLib.class.getSimpleName();
    private volatile static BitmapLib instatance;

    public static BitmapLib getInstance(){
        if(instatance == null){
            synchronized (BitmapLib.class) {
                if(instatance == null){
                    instatance = new BitmapLib();
                }
            }
        }

        return  instatance;
    }

    public void saveBitmapToFileThread(final Handler handler, final File file,
                                      final Bitmap bitmap){
        new Thread(){
            @Override
            public void run() {
                saveBitmapToFile(file, bitmap);
                handler.sendEmptyMessage(0);
            }
        }.start();
    }

    private boolean saveBitmapToFile(File file, Bitmap bitmap){
        if (bitmap == null) return false;

        boolean save = false;
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(file);

            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            save = true;
        } catch (FileNotFoundException e) {
            save = false;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
            }
        }

        return save;
    }
}
