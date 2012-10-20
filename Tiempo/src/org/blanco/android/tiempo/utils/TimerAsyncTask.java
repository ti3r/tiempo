package org.blanco.android.tiempo.utils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class TimerAsyncTask extends AsyncTask<Integer, Void, Void> {

	public long callBackTime = -1;
	Handler handler = null;
	ResultReceiver listener = null;
	
	public TimerAsyncTask(long timeForCallBack, ResultReceiver listener){
		this.callBackTime = timeForCallBack;
		this.listener = listener;
	}
	
	@Override
	protected Void doInBackground(Integer... params) {
		try {
			long start = System.currentTimeMillis();
			Thread.sleep(callBackTime);
			long end = System.currentTimeMillis();
			Bundle b = new Bundle();
			b.putLong("millis",end-start);
			listener.send(0, b);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
