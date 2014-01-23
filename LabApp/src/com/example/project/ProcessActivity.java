package com.example.project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


/**
 * This class implements the Process Activity and shows the three differents
 * kinds of Regulator that you can choose (Fop, Fopdt or Sop) 
 * @author María Martínez Martínez 03922597-Q
 * @author Miguel Morales Rodríguez 75169800-G                              
 *                           
 */
public class ProcessActivity extends Activity {

	private Button fop, fopdt, sop;
	
	/**
	 * Called when the activity is starting.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_process);
		fop = (Button) findViewById(R.id.FOP);
		fopdt = (Button) findViewById(R.id.FOPDT);
		sop = (Button) findViewById(R.id.SOP);
		
		/*Listener FOP Button*/
    	fop.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			Intent myIntent = new Intent(ProcessActivity.this, FopParametersActivity.class);
			finish();
			startActivity(myIntent);
			}
		});
    	
    	/*Listener FOPDT Button*/
    	fopdt.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			Intent myIntent = new Intent(ProcessActivity.this, FopdtParametersActivity.class);
			finish();
			startActivity(myIntent);
			}
		});
    	
    	/*Listener SOP Button*/
    	sop.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			Intent myIntent = new Intent(ProcessActivity.this, SopParametersActivity.class);
			finish();
			startActivity(myIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_process, menu);
		return true;
	}
	
	/**
	* Called when the key down was pressed down and not handled by any of the views inside of the activity.
	* This key return to the Main Activity.
	*/
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK))
		{
			Intent myIntent = new Intent(ProcessActivity.this, MainActivity.class);
    		finish();
			startActivity(myIntent); 
		
			return true;
	
		}
	
		return super.onKeyDown(keyCode, event);
	}

}
