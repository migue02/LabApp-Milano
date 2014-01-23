package com.example.project;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
public class PopUpAdvanceOptionsProcess extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pop_up_advance_options_process);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(
				R.menu.activity_pop_up_advance_options_process, menu);
		
		
		return true;
	}

}
