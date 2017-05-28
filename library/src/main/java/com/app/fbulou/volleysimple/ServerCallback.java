package com.app.fbulou.volleysimple;

import com.android.volley.VolleyError;

/**
 * Callback Interface used for handling Server API response
 */

public interface ServerCallback<T> {
    /**
     * Called when a response is received.
     *
     * @param apiTag tag to uniquely distinguish Volley requests. Null is allowed
     */
    void onAPIResponse(String apiTag, T response);

    /**
     * Callback method that an error has been occurred with the
     * provided error code and optional user-readable message.
     *
     * @param apiTag tag to uniquely distinguish Volley requests. Null is allowed
     */
    void onErrorResponse(String apiTag, VolleyError error);
}
