package com.koreanlab.bestfood.lib;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;

import com.koreanlab.bestfood.R;

/**
 * Created by Chanj on 18/02/2018.
 */

public class DialogLib {
    public final String TAG = DialogLib.class.getSimpleName();
    private volatile static DialogLib instance;

    public static DialogLib getInstance(){
        if(instance == null){
            synchronized (DialogLib.class){
                if(instance == null){
                    instance = new DialogLib();
                }
            }
        }

        return instance;
    }

    public void showKeepInsertDialog(Context context, final Handler handler, final int memberSeq, final int infoSeq){
        new AlertDialog.Builder(context)
                .setTitle(R.string.keep_insert)
                .setMessage(R.string.keep_insert_message)
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        KeepLib.getInstance().insertKeep(handler, memberSeq, infoSeq);
                    }
                })
                .show();
    }

    public void showKeepDeleteDialog(Context context, final Handler handler, final int memberSeq, final int infoSeq){
        new AlertDialog.Builder(context)
                .setTitle(R.string.keep_delete)
                .setMessage(R.string.keep_delete_message)
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        KeepLib.getInstance().deleteKeep(handler, memberSeq, infoSeq);
                    }
                })
                .show();
    }
}
