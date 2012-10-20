package org.blanco.android.tiempo;


import static org.blanco.android.tiempo.utils.TimeConstants.DAY_MAX_HOURS;
import static org.blanco.android.tiempo.utils.TimeConstants.HOUR_MAX_MINS;
import static org.blanco.android.tiempo.utils.TimeConstants.MIN_MAX_SECS;
import static org.blanco.android.tiempo.utils.TimeConstants.SEC_MAX_MILLIS;
import static org.blanco.android.tiempo.utils.TimeConstants.ZERO;

import org.blanco.android.tiempo.utils.TimerAsyncTask;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.ResultReceiver;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MainActivity extends RoboActivity {

	@InjectView(R.id.main_hours_number_picker) NumberPicker hours;
	@InjectView(R.id.main_minutes_number_picker) NumberPicker minutes;
	@InjectView(R.id.main_seconds_number_picker) NumberPicker seconds;
	@InjectView(R.id.main_millis_number_picker) NumberPicker millis;
	@InjectView(R.id.main_btn_start) Button bntStart;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hours.setMaxValue(DAY_MAX_HOURS);
        hours.setMinValue(ZERO);
        minutes.setMinValue(ZERO);
        minutes.setMaxValue(HOUR_MAX_MINS);
        seconds.setMinValue(ZERO);
        seconds.setMaxValue(MIN_MAX_SECS);
        millis.setMaxValue(SEC_MAX_MILLIS);
        millis.setMinValue(ZERO);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /**
     * Method executed when start button/options is/are selected
     * @param view The View view that triggered the method
     */
    public void startTimer(View view){
    	retrieveTimeFromValues();
    }
    
    
    public void retrieveTimeFromValues(){
    	int hrs = hours.getValue();
    	int mins = minutes.getValue();
    	int secs = seconds.getValue();
    	int mills = millis.getValue();
    	ResultReceiver res = new ResultReceiver(new Handler(new Callback() {
			
			@Override
			public boolean handleMessage(Message msg) {
				Bundle b = msg.getData();
				Toast.makeText(getApplicationContext(), b.toString(), 500).show();
				return true;
			}
		}));
    	TimerAsyncTask timer = new TimerAsyncTask(100, res);
    	timer.execute();
    }
    
}
