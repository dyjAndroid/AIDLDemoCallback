package com.dyj.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dyj.aidldemocallback.ICallbacklInterface;
import com.dyj.aidldemocallback.IManagerInterface;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "dyj";
    private IManagerInterface mIBookManagerInterface;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "[Client] onServiceConnected success :" + Thread.currentThread().getName());
            mIBookManagerInterface = IManagerInterface.Stub.asInterface(service);
            try {
                mIBookManagerInterface.setCallBack(mICallbacklInterface);
                mIBookManagerInterface.test();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            unbindService(mServiceConnection);
            Log.d(TAG, "[Client] onServiceConnected fail  :" + Thread.currentThread().getName());
        }
    };

    private ICallbacklInterface mICallbacklInterface = new ICallbacklInterface.Stub() {
        @Override
        public void call() throws RemoteException {
            Log.d("dyj", "[Client] server call client :" + Thread.currentThread().getName());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "[Client] onDestroy");
        super.onDestroy();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bind:
                Log.d(TAG, "[Client] bindService");
                Intent intent = new Intent();
                intent.setClassName("com.dyj.aidldemocallback", "com.dyj.aidldemocallback.BookManagerService");
                bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.unBind:
                Log.d(TAG, "[Client] unbindService");
                unbindService(mServiceConnection);
                break;
            case R.id.isLive:
                if (mIBookManagerInterface != null) {
                    Log.d(TAG, "[Client] is live :" + mIBookManagerInterface.asBinder().isBinderAlive());
                }
                break;
            case R.id.startService:
                Log.d(TAG, "[Client] startService");
                Intent intent1 = new Intent();
                intent1.setClassName("com.dyj.aidldemocallback", "com.dyj.aidldemocallback.BookManagerService");
                startService(intent1);
                break;
            default:
                Log.d(TAG, "default***********");
        }
    }
}
