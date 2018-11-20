package com.koreanlab.bestfood.lib;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Chanj on 17/02/2018.
 */

public class MyToast {
    public static void s(Context context, int id){
        Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
    }

    public static void s(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void l(Context context, int id){
        Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
    }

    public static void l(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
