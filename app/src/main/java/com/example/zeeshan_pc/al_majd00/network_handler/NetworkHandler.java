package com.example.zeeshan_pc.al_majd00.network_handler;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.zeeshan_pc.al_majd00.listeners.ServiceListener;
import com.example.zeeshan_pc.al_majd00.utils.AppLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zeeshan-PC on 4/12/2018.
 */

public class NetworkHandler {

    private RequestQueue requestQueue;


    private NetworkHandler() {
        requestQueue = Volley.newRequestQueue(MySingleton.getCtx());
    }


    public static boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) MySingleton.getCtx().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = cm.getActiveNetworkInfo();
        return net != null && net.isAvailable() && net.isConnected();
    }

    public void invoke(int method, final String url, final String auth, @Nullable final Map<String, String> map, final ServiceListener listener) {
        StringRequest request = new StringRequest(method, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String jsonObject) {
                        try {
                            JSONObject jsonObject1 = new JSONObject(jsonObject);
                            if (jsonObject1.has("status")) {
                                if (jsonObject1.getString("status").equalsIgnoreCase("1")) {
                                    AppLog.e("invokeResponce", "--Success--\n" + jsonObject);

                                    listener.success(jsonObject);
                                } else {
                                    AppLog.e("invokeResponce", "--fail--" + jsonObject);
                                    listener.fail(jsonObject);
                                }
                            } else {
                                AppLog.e("invokeResponce", "--fail--" + jsonObject);
                                listener.fail(jsonObject);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                failCallback(volleyError, listener);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                //if map is not null means the request is either post or put or delete
                if (map != null) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        params.put(entry.getKey(), entry.getValue());
                    }
                }
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Token", auth);
//                params.put("Accept", "application/json");
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                return super.parseNetworkResponse(response);
            }

        };
        request.setRetryPolicy(
                new DefaultRetryPolicy(60000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    private void failCallback(VolleyError volleyError, ServiceListener listener) {
        NetworkResponse response = volleyError.networkResponse;
        AppLog.d("invokeResponce", "--Fail--" + response);

        if (volleyError instanceof TimeoutError) {
            listener.fail("Timeout Error");
        } else if (volleyError instanceof AuthFailureError) {
            listener.fail("AuthFailure Error, invalid_credentials");
        } else if (volleyError instanceof NoConnectionError) {
            listener.fail("No Connection Error");
        } else if (volleyError instanceof ServerError) {
            listener.fail("Server Error");
        } else if (volleyError instanceof ParseError) {
            listener.fail("Parse Error");
        } else if (volleyError.getCause() instanceof NullPointerException) {
            listener.fail("NullPointer Error");
        } else {
            listener.fail("Unknown Error");
        }
    }
}
