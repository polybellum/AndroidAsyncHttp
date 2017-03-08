#AndroidAsyncHttp

Written by Nic Wilson @mtear (github.com/mtear)

An easy way to make Http request tasks on Android in different threads. 

Licensed with the Apache License-- Feel free to use and modify!
  
## Get from Jitpack 

Use this library from Jitpack! Add jitpack support to your Gradle file.  

```
repositories {
    jcenter()
    maven { url "https://jitpack.io" }
}
dependencies {
    compile 'com.github.polybellum:AndroidAsyncHttp:-SNAPSHOT'
}
```

## Usage

Under the hood there's a pool of Threads that allow you to queue up a certain number of Http request tasks and they will be executed when a free `Thread` is available.  
Just make a call like this:  

```
HttpTaskManager.postTask(getApplicationContext(), myRequest);
```

The `Request` argument variable will be added to the queue and will be executed as soon as it can. 

The `HttpTaskManager` needs a reference to the Application Context if you want it to be able to return the result of the task and get back to the UI thread. **If you don't care about** the result you can leave that argument `null`. Note: The application context will be set only once as it is a singleton. If you will ever want to callback to the context make sure at least the first call you make uses the Application Context.  

## Callbacks

You can have callbacks sent to your application. Callbacks use Broadcasts from the `LocalBroadcastManager`. If you'd like to handle the registering and unregistering of your own `BroadcastReceiver` you can do so by regstering it with `TaskManager.getIntentFilter()`.  

Otherwise you can have *async* take care of the Receiver lifecycle by extending `TaskActivity` for Activities and `TaskCompatActivity` for AppCompatActivity (remember this extends from `FragmentActivity`). All you have to do after this is override `public void onTaskComplete(Intent intent)` from the TaskCallback interface.  

By just subclassing your `Activity` and adding the callback method you will be able to see that a task finished **but you won't be able to see which one or get any other data from it**. If you only want one task at a time and you don't care about any other information this is all you need to do.  

Get data parameters from the `Intent` using the static name fields from `HttpTask` when you are getting extras. Data is stored in `byte[]` and the Response code will be an `int`. 


