package br.com.ranz.combustivelbarato.receiver;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by Ricardo on 10/10/2015.
 */
public class GenericResultReceiver extends ResultReceiver {

    public static final int RUNNING = 0;
    public static final int FINISHING = 1;
    public static final int ERROR = 2;

    private Receiver mReceiver;

    public GenericResultReceiver(Handler handler) {
        super(handler);
    }

    public void setReceiver(Receiver receiver) {
        mReceiver = receiver;
    }

    public interface Receiver {
        public void onReceiveResult(int resultCode, Bundle resultData);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null) {
            mReceiver.onReceiveResult(resultCode, resultData);
        }
    }
}
