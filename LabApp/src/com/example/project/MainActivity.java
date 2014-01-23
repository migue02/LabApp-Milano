package com.example.project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/**
 * This class implements the Main Activity and shows the two differents
 * kinds of users that you can be (Regulator or Process)
 * @author María Martínez Martínez 03922597-Q
 * @author Miguel Morales Rodríguez 75169800-G                              
 *                           
 */
public class MainActivity extends Activity {
	
	private Button regulator,process;

	/**
	 * Called when the activity is starting.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		process = (Button) findViewById(R.id.buttonProcess);
		regulator = (Button) findViewById(R.id.buttonRegulator);
		
		/*Listener Regulator Button*/
    	regulator.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			Intent myIntent = new Intent(MainActivity.this, RegulatorActivity.class);
			finish();
			startActivity(myIntent);
			}
		});
    	
    	/*Listener Process Button*/
    	process.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			Intent myIntent = new Intent(MainActivity.this, ProcessActivity.class);
			finish();
			startActivity(myIntent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK))
		{
			finish();
			return true;
	
		}
	
		return super.onKeyDown(keyCode, event);
	}

}
