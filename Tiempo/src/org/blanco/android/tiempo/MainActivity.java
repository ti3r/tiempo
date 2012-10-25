package org.blanco.android.tiempo;


import static org.blanco.android.tiempo.utils.TimeConstants.DAY_MAX_HOURS;
import static org.blanco.android.tiempo.utils.TimeConstants.HOUR_MAX_MINS;
import static org.blanco.android.tiempo.utils.TimeConstants.MIN_MAX_SECS;
import static org.blanco.android.tiempo.utils.TimeConstants.SEC_MAX_MILLIS;
import static org.blanco.android.tiempo.utils.TimeConstants.ZERO;

import org.blanco.android.tiempo.runnables.TimeOutListener;
import org.blanco.android.tiempo.runnables.ToastTimeOutRunnable;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;

public class MainActivity extends RoboActivity implements TimeOutListener {

	@InjectView(R.id.main_hours_number_picker) NumberPicker hours;
	@InjectView(R.id.main_minutes_number_picker) NumberPicker minutes;
	@InjectView(R.id.main_seconds_number_picker) NumberPicker seconds;
	@InjectView(R.id.main_millis_number_picker) NumberPicker millis;
	@InjectView(R.id.main_btn_start) Button bntStart;
	@InjectView(R.id.main_loop_alarm_check) CheckBox chkLoop;
	
	
	Handler handler = null;
	
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
        handler = new Handler();
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
    	
    	long time = (mills*100) + ( secs * 1000) + (mins * 60000) + (hrs * 360000);
    	postTimeOutAlarm(time);
    }
    
    public void postTimeOutAlarm(long time){
    	hours.setEnabled(false);minutes.setEnabled(false); seconds.setEnabled(false);
    	millis.setEnabled(false);
    	
    	ToastTimeOutRunnable runnable = new ToastTimeOutRunnable(getBaseContext(), time);
    	runnable.setListener(this);
    	handler.postDelayed(runnable, time);
    	
    }

	@Override
	public void onTimeOutComplete(Long time) {
		if (chkLoop != null && chkLoop.isChecked()){
			postTimeOutAlarm(time);
		}else{
			hours.setEnabled(true);minutes.setEnabled(true); seconds.setEnabled(true);
	    	millis.setEnabled(true);
		}
	}
    
}
