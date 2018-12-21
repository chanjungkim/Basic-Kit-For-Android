import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class OpenApi {
    private final static String TAG = OpenApi.class.getSimpleName();

    private final static String BASIC_URL = "https://aaa.bbb.com/search";
    private final static String MY_KEY = "JD7SJKJAXM8ADFDJSDHMLQ7JA9";     // example

    HttpURLConnection httpURLConnection;
    String fullUrl;
    BufferedReader br = null;
    String result;

    public String makeURL(String key) {
        Log.d(TAG, "makeURL= key: " + key);
        try {
            fullUrl = BASIC_URL
                    + makeParamStart("key", MY_KEY)
                    + makeParam("a", "aaa")
                    + makeParam("b", "bbb")
                    + makeParam("c", "ccc")
                    + makeParam("d", URLEncoder.encode(key, "UTF-8"))   // For non-English
                    + makeParamEnd("e", "eee");
            Log.d(TAG, fullUrl);
            URL url = new URL(fullUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                        Bundle bun = new Bundle();
                        result = "";
                        String line;
                        while ((line = br.readLine()) != null) {
                            result = result + line + "\n";
                        }
                        Log.d(TAG, "Thread:" + result);
//                        bun.putString("result", result); // Choice2
//                        Message msg = handler.obtainMessage();
//                        msg.setData(bun);
//                        handler.sendMessage(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
            thread.join(); // Choice 1: After thread finishes, it works.
            Log.d(TAG, result); // Choice 1: So, it gets the result correctly and return it.
            return result;
        } catch (Exception e) {
            e.printStackTrace();

            return "-1";
        }
    }

// Choice 2: You can use this when you work on something inside of this Class.
//    Handler handler = new Handler() {
//        public void handleMessage(Message msg) {
//            Bundle bun = msg.getData();
//            String result = bun.getString("result");
//            Log.d(TAG, "Handler: " + result);
//        }
//    };

    public String makeParamStart(String key, String value) {
        return "?" + makeParam(key, value);
    }

    public String makeParamEnd(String key, String value) {
        return key + "=" + value;
    }

    public String makeParam(String key, String value) {
        return key + "=" + value + "&";
    }
}
