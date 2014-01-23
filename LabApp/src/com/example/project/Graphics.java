package com.example.project;


import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewStyle;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


/**
 * This class is the responsable of getting the data of the database
 * and show in the graphic the values that "U" and "Y" have taken in the last simulation
 * @author María Martínez Martínez 03922597-Q
 * @author Miguel Morales Rodríguez 75169800-G                              
 *                           
 */
public class Graphics extends Activity {
	
	private Button buttonReturn;
	private String regulator="";
	private String ip="";

	@SuppressWarnings("resource")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Bundle extras = getIntent().getExtras();
		regulator = extras.getString("regulator");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graphics);	   
		ip = extras.getString("ip");
		
		buttonReturn = (Button) findViewById(R.id.buttonReturn);
		
		/*Listener Return Button*/
    	buttonReturn.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			Intent myIntent;
			if(regulator.equals("pi"))
				myIntent = new Intent(Graphics.this, PiParametersActivity.class);
			else
				myIntent = new Intent(Graphics.this, PidParametersActivity.class);	
    		finish();
			startActivity(myIntent); 
		}
		});
		try{
			DataBaseHelper bd = new DataBaseHelper(this);
			Cursor cursor; //Create a cursor which is going to have the output of the query
			
			if(regulator.equals("pi"))
				cursor = bd.readPI();
			else
				cursor = bd.readPID();
			
			Double max=0.0,min=0.0;
			 
			GraphViewData[] data = new GraphViewData[cursor.getCount()]; //Create a new GraphViewData for the data of U value with the number of rows of the output of the query 
			if(cursor.getCount()>0){
				int i=0;
				if(cursor.moveToFirst()){
					do{
						data[i] = new GraphViewData(i,Double.valueOf(cursor.getString(0)));  //add a new value
						i++;
						if(Double.valueOf(cursor.getString(0))<min)
							min=Double.valueOf(cursor.getString(0));
						if(Double.valueOf(cursor.getString(0))>max)
							max=Double.valueOf(cursor.getString(0));
					}while(cursor.moveToNext()); //While there is rows
				}
				 
				 
				GraphViewSeries seriesU = new GraphViewSeries("U curve", new GraphViewStyle(Color.BLUE, 2), data); //Set the caracteristics of the U curve
				 
				data = new GraphViewData[cursor.getCount()];  //Create a new GraphViewData for the data of Y value with the number of rows of the output of the query
				 
				if(regulator.equals("pi"))
					cursor = bd.readPI();
				else
					cursor = bd.readPID();
				 
				i=0;
				if(cursor.moveToFirst()){
					do{
						data[i] = new GraphViewData(i,Double.valueOf(cursor.getString(1)));  //add a new value 
						i++;
						if(Double.valueOf(cursor.getString(1))<min)
							min=Double.valueOf(cursor.getString(1));
						if(Double.valueOf(cursor.getString(1))>max)
							max=Double.valueOf(cursor.getString(1));
					}while(cursor.moveToNext());
				}
				 
				GraphViewSeries seriesY = new GraphViewSeries("Y curve", new GraphViewStyle(Color.RED, 2), data); //Set the caracteristics of the U curve
				 
				GraphView graphView = new LineGraphView(  //Create the Graph
					      this
					      , "Graph of " + regulator  
				);  
				
				
			     
			    // add data  
			    graphView.addSeries(seriesU);
			    graphView.addSeries(seriesY);
		
			    // Show the legend
			    graphView.setShowLegend(true);
			    
			    //graphView.setVerticalLabels(new String[] {max.toString(), min.toString()});  
			    
			    // add the graph to the layout created for showing the graph
			    LinearLayout layout = (LinearLayout) findViewById(R.id.graph);  
			    layout.addView(graphView);  
			}else 
				Toast.makeText(getApplicationContext(), "There is not any values to show yet",Toast.LENGTH_SHORT).show();
		}catch (Exception e) {
			Intent myIntent;
			if(regulator.equals("pi"))
				myIntent = new Intent(Graphics.this, PiParametersActivity.class);
			else
				myIntent = new Intent(Graphics.this, PidParametersActivity.class);
			myIntent.putExtra("ip", ip.toString());
			finish();
			startActivity(myIntent); 
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.graphics, menu);
		return true;
	}
	
	/**
	* Called when the key down was pressed down and not handled by any of the views inside of the activity.
	* This key return to the Pi or Pid Activity.
	*/
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK))
		{

			Intent myIntent;
			if(regulator.equals("pi"))
				myIntent = new Intent(Graphics.this, PiParametersActivity.class);
			else
				myIntent = new Intent(Graphics.this, PidParametersActivity.class);
			finish();
			startActivity(myIntent); 
		
			return true;
	
		}
	
		return super.onKeyDown(keyCode, event);
	}

}
