package org.blanco.android.tiempo;


import roboguice.activity.RoboActivity;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MainActivity extends RoboActivity {

	@InjectView(R.id.main_hours_number_picker) NumberPicker hours;
	@InjectView(R.id.main_btn_start) Button bntStart;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void startTimer(){
    	Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();
    }
    
}
