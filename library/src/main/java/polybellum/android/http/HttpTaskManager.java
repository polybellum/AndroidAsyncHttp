// Copyright (C) 2017 polybellum
// Licensed under http://www.apache.org/licenses/LICENSE-2.0 <see LICENSE file>

package polybellum.android.http;

import android.content.Context;

import polybellum.android.async.TaskManager;
import polybellum.http.Request;

/**
 * Created by Nic Wilson on 3/7/17.
 */

/**
 * Task Manager utility methods for Http requests
 */
public abstract class HttpTaskManager {

    /**************************************
     *           STATIC METHODS
     *************************************/

    /**
     * Post an Http request task to the Task Manager.
     *
     * @param appContext The Android Application Context
     * @param request An Http request to execute
     */
    public static void postTask(Context appContext, Request request){
        TaskManager.postTask(appContext, new HttpTask(request));
    }

}
