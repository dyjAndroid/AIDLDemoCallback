package com.dyj.aidldemocallback;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by duanyuanjin
 * on 2018/8/3.
 */
public class BookManagerService extends Service {

    private static final String TAG = "dyj";

    private ICallbacklInterface mICallbacklInterface;

    private Binder mBinder = new IManagerInterface.Stub() {
        @Override
        public void test() throws RemoteException {
            Log.d(TAG, "[Server] client call server test:" + Thread.currentThread().getName());
            mICallbacklInterface.call();
        }
        @Override
        public void setCallBack(ICallbacklInterface callback) throws RemoteException {
            mICallbacklInterface = callback;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "[Server] [onBind] :" + Thread.currentThread().getName());
        return mBinder;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "[Server] [onCreate]");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d(TAG, "[Server] [onStart] startId:" + startId);
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "[Server] [onStartCommand] startId:" + startId + ",flags:" + flags);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "[Server] [onDestroy]");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "[Server] [onUnbind]");
        return super.onUnbind(intent);
    }
}
