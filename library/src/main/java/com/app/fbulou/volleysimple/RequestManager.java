package com.app.fbulou.volleysimple;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton Class used for handling Volley requests
 */

@SuppressWarnings({"unused", "unchecked"})
public class RequestManager {

    @SuppressLint("StaticFieldLeak")
    private static RequestManager Instance;
    private RequestQueue mRequestQueue;
    private RetryPolicy retryPolicy;
    private Context mCtx;
    private int initialTimeoutInMs = 3000;
    private int maxNoOfTries = 2;

    private RequestManager(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
        retryPolicy = new DefaultRetryPolicy(initialTimeoutInMs, maxNoOfTries, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    /**
     * Returns the singleton instance of {@link RequestManager}
     *
     * @param context context of the respective Activity
     * @return Instance of {@link RequestManager}
     */
    public static RequestManager getInstance(Context context) {
        if (Instance == null)
            Instance = new RequestManager(context);
        return Instance;
    }

    /**
     * Creates a default instance of the worker pool and calls RequestQueue.start() on it.
     *
     * @return A started RequestQueue instance.
     */
    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext(), new HurlStack());
        return mRequestQueue;
    }

    /**
     * Adds a Request to the dispatch queue.
     *
     * @param request The request to service
     */
    private void addToRequestQueue(Request request) {
        getRequestQueue().add(request);
    }

    /**
     * Sets the initial timeout.
     * Default value = 3000ms
     *
     * @param initialTimeoutMs The initial timeout for the policy (in milliseconds)
     */
    public void setInitialTimeoutMs(int initialTimeoutMs) {
        this.initialTimeoutInMs = initialTimeoutMs;
        retryPolicy = new DefaultRetryPolicy(initialTimeoutMs, maxNoOfTries, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    /**
     * Sets the maximum number of retries
     * Default value = 2
     *
     * @param maxNoOfTries The maximum number of retries.
     */
    public void setMaxNoOfTries(int maxNoOfTries) {
        this.maxNoOfTries = maxNoOfTries;
        retryPolicy = new DefaultRetryPolicy(initialTimeoutInMs, maxNoOfTries, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    /**
     * @param apiTag         tag to uniquely distinguish Volley requests. Null is allowed
     * @param url            URL to fetch the string at
     * @param httpMethod     the request method to use (GET or POST)
     * @param params         A HashMap<String,String> to post with the request. Null is allowed and
     *                       indicates no parameters will be posted along with request.
     * @param headers        optional Http headers
     * @param serverCallback Listener to receive the String response
     */
    public void placeStringRequest(@Nullable final String apiTag, String url, int httpMethod, @Nullable final HashMap<String, String> params, final HashMap<String, String> headers, final ServerCallback serverCallback) {

        Request request = new StringRequest(httpMethod, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                serverCallback.onAPIResponse(apiTag, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                serverCallback.onErrorResponse(apiTag, error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params != null ? params : super.getParams();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers != null ? headers : super.getHeaders();
            }
        };

        request.setRetryPolicy(retryPolicy);

        addToRequestQueue(request);
    }

    /**
     * @param apiTag         tag to uniquely distinguish Volley requests. Null is allowed
     * @param url            URL to fetch the string at
     * @param httpMethod     the request method to use (GET or POST)
     * @param params         A {@link JSONObject} to post with the request. Null is allowed and
     *                       indicates no parameters will be posted along with request.
     * @param headers        optional Http headers
     * @param serverCallback Listener to receive the String response
     */
    public void placeJsonObjectRequest(@Nullable final String apiTag, String url, int httpMethod, @Nullable JSONObject params, final @Nullable HashMap<String, String> headers, final ServerCallback serverCallback) {

        Request request = new JsonObjectRequest(httpMethod, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                serverCallback.onAPIResponse(apiTag, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                serverCallback.onErrorResponse(apiTag, error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers != null ? headers : super.getHeaders();
            }
        };

        request.setRetryPolicy(retryPolicy);

        addToRequestQueue(request);
    }

    /**
     * @param apiTag         tag to uniquely distinguish Volley requests. Null is allowed
     * @param url            URL to fetch the string at
     * @param httpMethod     the request method to use (GET or POST)
     * @param params         A {@link JSONArray} to post with the request. Null is allowed and
     *                       indicates no parameters will be posted along with request.
     * @param headers        optional Http headers
     * @param serverCallback Listener to receive the String response
     */
    public void placeJsonArrayRequest(@Nullable final String apiTag, String url, int httpMethod, @Nullable JSONArray params, final @Nullable HashMap<String, String> headers, final ServerCallback serverCallback) {

        Request request = new JsonArrayRequest(httpMethod, url, params, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                serverCallback.onAPIResponse(apiTag, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                serverCallback.onErrorResponse(apiTag, error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers != null ? headers : super.getHeaders();
            }
        };

        request.setRetryPolicy(retryPolicy);

        addToRequestQueue(request);
    }

}
