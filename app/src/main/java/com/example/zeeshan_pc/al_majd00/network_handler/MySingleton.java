package com.example.zeeshan_pc.al_majd00.network_handler;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Zeeshan-PC on 4/12/2018.
 */

public class MySingleton extends Application{

    private RequestQueue requestQueue;
    private Context ctx;
    private static MySingleton mInstance;

    public MySingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }
    public static Context getCtx() {
        return mInstance.getApplicationContext();
    }

    public RequestQueue getRequestQueue() {

        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MySingleton getInstance(Context context){
        if(mInstance == null){
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    public <T> void addToRequestQueue(Request<T> request){
        requestQueue.add(request);
    }

}
