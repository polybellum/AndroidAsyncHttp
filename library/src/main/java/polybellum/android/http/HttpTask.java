// Copyright (C) 2017 polybellum
// Licensed under http://www.apache.org/licenses/LICENSE-2.0 <see LICENSE file>

package polybellum.android.http;

import android.content.Intent;

import polybellum.android.async.IntentResult;
import polybellum.http.HttpClient;
import polybellum.http.Request;
import polybellum.http.Response;
import polybellum.http.SimpleHttpClient;

/**
 * Created by Nic Wilson on 3/7/17.
 */

/**
 * A Runnable task that performs an Http Request and returns a callback result Intent
 */
public class HttpTask implements Runnable, IntentResult {

    /**************************************
     *            STATIC FIELDS
     *************************************/

    /**
     * Intent constant for the Http response code
     */
    public final static String RESULT_CODE = "RESULT_CODE";
    /**
     * Intent constant for the Http response data
     */
    public final static String RESULT_DATA = "RESULT_DATA";
    /**
     * Intent constant for the Http request url
     */
    public final static String REQUEST_URL = "REQUEST_URL";
    /**
     * Intent constant for the Http request verb
     */
    public final static String REQUEST_VERB = "REQUEST_VERB";
    /**
     * Intent constant for the Http request data
     */
    public final static String REQUEST_DATA = "REQUEST_DATA";

    /**************************************
     *          MEMBER VARIABLES
     *************************************/

    /**
     * The result code from the Response
     */
    private int _resultCode;
    /**
     * The data from the Response
     */
    private byte[] _resultData;
    /**
     * The Request to be executed
     */
    private Request _request;

    /**
     * The web client to use for the Request
     */
    private HttpClient _httpClient;

    /**************************************
     *            CONSTRUCTORS
     *************************************/

    /**
     * Initialize the variable with the request
     *
     * @param request The Http request to execute
     */
    public HttpTask(Request request){
        _httpClient = new SimpleHttpClient();
        _request = request;
    }

    /**
     * Initialize the variable with the request and client
     *
     * @param request The Http request to execute
     * @param httpClient The Http client to execute the request with
     */
    public HttpTask(Request request, HttpClient httpClient){
        _httpClient = httpClient;
        _request = request;
    }

    /**************************************
     *           PUBLIC METHODS
     *************************************/

    /**
     * Execute the Http request with a simple Http client
     */
    @Override
    public void run() {
        Response response = _httpClient.execute(_request);
        _resultCode = response.getResponse();
        _resultData = response.getDataByteArray();
    }

    /**
     * Store the Response from the Http request
     *
     * @param intent The Intent to store the result in
     */
    //@Override
    public void storeResult(Intent intent) {
        intent.putExtra(RESULT_CODE, _resultCode);
        intent.putExtra(RESULT_DATA, _resultData);
        intent.putExtra(REQUEST_URL, _request.getURL().toExternalForm());
        intent.putExtra(REQUEST_VERB, _request.getVerb());
        intent.putExtra(REQUEST_DATA, _request.getDataBytes());
    }

}
