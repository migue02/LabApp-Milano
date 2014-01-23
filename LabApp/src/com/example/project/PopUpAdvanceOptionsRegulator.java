package com.example.project;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PopUpAdvanceOptionsRegulator extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(
				R.menu.activity_pop_up_advance_options_regulator, menu);
		
		
		return true;
	}

}
