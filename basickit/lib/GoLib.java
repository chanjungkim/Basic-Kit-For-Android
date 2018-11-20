package com.koreanlab.bestfood.lib;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.koreanlab.bestfood.activity.InfoActivity;
import com.koreanlab.bestfood.activity.ProfileActivity;
import com.koreanlab.bestfood.activity.RegisterActivity;

/**
 * Created by Chanj on 18/02/2018.
 */

public class GoLib {
    public final String TAG = GoLib.class.getSimpleName();
    private volatile static GoLib instance;

    public static GoLib getInstance(){
        if(instance == null){
            synchronized (GoLib.class){
                if(instance == null){
                    instance = new GoLib();
                }
            }
        }
        return instance;
    }

    public void goFragment(FragmentManager fragmentManager, int containerViewId, Fragment fragment){
        fragmentManager.beginTransaction()
                .replace(containerViewId, fragment)
                .commit();
    }

    public void goFragmentBack(FragmentManager fragmentManager, int containerViewId, Fragment fragment){
        fragmentManager.beginTransaction()
                .replace(containerViewId, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void goBackFragment(FragmentManager fragmentManager){
        fragmentManager.popBackStack();
    }

    public void goProfileActivity(Context context){
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void goRegisterActivity(Context context){
        Intent intent = new Intent(context, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void goInfoActivity(Context context, int infoSeq){
        Intent intent = new Intent(context, InfoActivity.class);
        intent.putExtra(InfoActivity.INFO_SEQ, infoSeq);
        context.startActivity(intent);
    }
}
