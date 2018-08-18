// IBookManagerInterface.aidl
package com.dyj.aidldemocallback;

import com.dyj.aidldemocallback.ICallbacklInterface;

// Declare any non-default types here with import statements

interface IManagerInterface {
    void test();
    void setCallBack(ICallbacklInterface callback);
}
