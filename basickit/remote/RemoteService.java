package com.koreanlab.bestfood.remote;

import android.util.Log;

import com.koreanlab.bestfood.item.KeepItem;
import com.koreanlab.bestfood.item.MemberInfoItem;
import com.koreanlab.bestfood.item.RestaurantInfoItem;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Chanj on 17/02/2018.
 */

public interface RemoteService {
    String TAG = RemoteService.class.getSimpleName();

    String BASE_URL = "http://140.82.12.74:2000/";
    // AWS
//    String BASE_URL = "http://ec2-18-217-218-101.us-east-2.compute.amazonaws.com:3000";
    String MEMBER_ICON_URL = BASE_URL + "/member/";
    String IMAGE_URL = BASE_URL + "/img/";

    // User Info
    @GET("/member/{phone}")
    Call<MemberInfoItem> selectMemberInfo(@Path("phone") String phone);

//    @POST("/member/info")
//    Call<String> insertMemberInfo(@Body MemberInfoItem memberInfoItem);

    @POST("/member/info")
    Call<String> updateMemberInfo(@Body MemberInfoItem memberInfoItem);

    @FormUrlEncoded
    @POST("/member/phone")
    Call<String> insertMemberPhone(@Field("phone") String phone);

    // Profile Icon
    @Multipart
    @POST("/member/icon_upload")
    Call<ResponseBody> uploadMemberIcon(@Part("member_seq") RequestBody memberSeq,
                                        @Part MultipartBody.Part file);

    // info seq
    // restaurant insert
    @POST("/restaurant/info")
    Call<String> insertRestaurantInfo(@Body RestaurantInfoItem infoItem);

    // restaurant image upload
    @Multipart
    @POST("/restaurant/info/image")
    Call<ResponseBody> uploadRestaurantImage(@Part("info_seq") RequestBody infoSeq,
                                       @Part("image_memo") RequestBody imageMemo,
                                       @Part MultipartBody.Part file);

    // restaurant/list
    @GET("/restaurant/info/list")
    Call<ArrayList<RestaurantInfoItem>> selectAllRestaurantsInfo(@Query("member_seq") int memberSeq,
                                                                  @Query("user_latitude") double userLatitude,
                                                                  @Query("user_longitude") double userLongitude,
                                                                  @Query("order_type") String orderType,
                                                                  @Query("current_page") int currentPage);

    // restaurant/info
    @GET("/restaurant/info/{restaurant_seq}")
    Call<RestaurantInfoItem> selectRestaurantInfo(@Path("restaurant_seq") int restaurantInfoSeq,
                                                  @Query("member_seq") int memberSeq);

    //map/list
    //keep post
    @POST("/keep/{member_seq}/{info_seq}")
    Call<String> insertKeep(@Path("member_seq") int memberSeq, @Path("info_seq") int infoSeq);

    //keep delete
    @POST("/keep/{member_seq}/{info_seq}")
    Call<String> deleteKeep(@Path("member_seq") int memberSeq, @Path("info_seq") int infoSeq);

    //keep get
    @GET("/keep/list")
    Call<ArrayList<KeepItem>> listKeep(@Query("member_seq") int memberSeq, @Query("user_latitude") double userLatitude, @Query("user_longitude") double userLongitude);

    //map list
    @GET("/restaurant/maplist")
    Call<ArrayList<RestaurantInfoItem>> listMap(@Query("member_seq") int memberSeq,
                                                @Query("latitude") double latitude,
                                                @Query("longitude") double longitude,
                                                @Query("distance") double distance,
                                                @Query("user_latitude") double userLatitude,
                                                @Query("user_longitude") double userLongitude);
}