import android.app.Application;
import android.os.StrictMode;
import android.util.Log;

import com.koreanlab.bestfood.item.RestaurantInfoItem;
import com.koreanlab.bestfood.item.MemberInfoItem;

import java.lang.reflect.Method;

/**
 * Global Class ( on the App )
 * Created by Chanj on 11/02/2018.
 */

public class MyApp extends Application{
    private String TAG = this.getClass().getSimpleName();

    private MemberInfoItem memberInfoItem;
    private RestaurantInfoItem restaurantInfoItem;

    @Override
    public void onCreate() {
        super.onCreate();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    public MemberInfoItem getMemberInfoItem(){
        if (memberInfoItem == null) {
            memberInfoItem = new MemberInfoItem();
        }

        Method nowmethod = new Object(){}.getClass().getEnclosingMethod();

        Log.d(TAG+ " > " + nowmethod.getName(), memberInfoItem.toString());

        return memberInfoItem;
    }

    public void setMemberInfoItem(MemberInfoItem item){
        this.memberInfoItem = item;
    }

    public int getMemberSeq(){
        return memberInfoItem.seq;
    }

    public void setRestaurantInfoItem(RestaurantInfoItem restaurantInfoItem){
        this.restaurantInfoItem = restaurantInfoItem;
    }

    public RestaurantInfoItem getRestaurantInfoItem(){
        return restaurantInfoItem;
    }
}

