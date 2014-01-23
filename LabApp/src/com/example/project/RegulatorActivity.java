package com.example.project;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This class implements the Regulator Activity and shows the two differents
 * kinds of Regulator that you can choose (Pi or Pid) and a TextField where you have to write the ServerIP
 * @author María Martínez Martínez 03922597-Q
 * @author Miguel Morales Rodríguez 75169800-G                              
 *                           
 */
public class RegulatorActivity extends Activity {

	private Button pi, pid;
	private EditText ip;
	
	/**
	 * Called when the activity is starting.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regulator);
		pi = (Button) findViewById(R.id.PI);
		pid = (Button) findViewById(R.id.PID);
		ip = (EditText) findViewById(R.id.IPtext);
		
		/*Listener PI Button*/
    	pi.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			if(isAValidIp() && ip.getText().toString()!=""){
				Intent myIntent = new Intent(RegulatorActivity.this, PiParametersActivity.class);
				myIntent.putExtra("ip", ip.getText().toString());
				finish();
				startActivity(myIntent);
			}else
				Toast.makeText(getApplicationContext(), "It is not a valid ip direction.", Toast.LENGTH_SHORT).show();
			}

    	});
    	
    	/*Listener PID Button*/
    	pid.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			if(isAValidIp() && ip.getText().toString()!=""){
				Intent myIntent = new Intent(RegulatorActivity.this, PidParametersActivity.class);
				myIntent.putExtra("ip", ip.getText().toString());
				finish();
				startActivity(myIntent);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_regulator, menu);
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
			Intent myIntent = new Intent(RegulatorActivity.this, MainActivity.class);
    		finish();
			startActivity(myIntent); 
		
			return true;
	
		}
	
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * Check if the written IP is a valid IP direction. 
	 * 
	 */
	public boolean isAValidIp() {
		Boolean valid=true;
		String sIP=ip.getText().toString();
		if(sIP.length()>6 && sIP.length()<16){
			try{
				Integer numbers=0;
				for(int i=0;i<sIP.length() && valid;i++){
					String aux="";
					for(;i<sIP.length() && sIP.charAt(i)!='.' ;i++)
						aux+=sIP.charAt(i);
					Integer a = Integer.parseInt(aux);
					if(a>255 || a<0)
						valid=false;
					numbers++;
				}
				if(numbers!=4 && valid)
					valid=false;
			}catch(Exception e){
				valid=false;
			}
		}else 
			valid=false;
		return valid;
	}
	
}
