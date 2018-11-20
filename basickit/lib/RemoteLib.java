package com.koreanlab.bestfood.lib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.koreanlab.bestfood.remote.RemoteService;
import com.koreanlab.bestfood.remote.ServiceGenerator;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Chanj on 17/02/2018.
 */

public class RemoteLib {
    public static final String TAG = RemoteLib.class.getSimpleName();

    private volatile static RemoteLib instance;

    /**
     * Library related to Network Server.
     * @return
     */
    public static RemoteLib getInstance(){
        if(instance == null){
            synchronized (RemoteLib.class){
                if(instance == null){
                    instance = new RemoteLib();
                }
            }
        }
        return instance;
    }

    /**
     * Network Check
     * @param context
     * @return
     */
    public boolean isConnected(Context context){
        Log.d(TAG, "isConnected() = context : " + context);

        try{
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();

            if(info != null) return true;
            else return false;
        }catch (Exception e) {
            return false;
        }
    }

    public void uploadMemberIcon(int memberSeq, File file){
        Log.d(TAG, "uploadMemberIcon() = memberSeq : " + memberSeq + " | file : " + file);

        RemoteService remoteService = ServiceGenerator.createService(RemoteService.class);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        RequestBody memberSeqBody = RequestBody.create(MediaType.parse("multipart/form-data"), "" + memberSeq);

        Call<ResponseBody> call = remoteService.uploadMemberIcon(memberSeqBody, body);

        call.enqueue(new Callback<ResponseBody>(){
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                MyLog.d(TAG, "uploadMemberIcon() = success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                MyLog.e(TAG, "uploadMemberIcon() = fail");
            }
        });
    }

    /**
     * Upload Restaurant Image to Server.
     * @param infoSeq Serial Number of Restaurant Info
     * @param imageMemo Image Description
     * @param file File Object
     * @param finishHandler Hasndler to response its result
     */
    public void uploadRestaurantImage(int infoSeq, String imageMemo, File file, final Handler finishHandler){
        RemoteService remoteService = ServiceGenerator.createService(RemoteService.class);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        RequestBody infoSeqBody = RequestBody.create(MediaType.parse("multipart/form-data"), "" + infoSeq);
        RequestBody imageMemoBody = RequestBody.create(MediaType.parse("multipart/form-data"), imageMemo);

        Call<ResponseBody> call = remoteService.uploadRestaurantImage(infoSeqBody, imageMemoBody, body);
        call.enqueue(new Callback<ResponseBody>(){
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                MyLog.d(TAG, "uploadRestaurantImage(): success");

                finishHandler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                finishHandler.sendEmptyMessage(0);
                MyLog.e(TAG, "uploadRestaurantImage(): fail\n"+t.toString());
            }
        });
    }
}
