package com.example.project;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

/**
 * This class implements the Activity of the Sop Server
 * and shows every parameters that you can change
 * @author María Martínez Martínez 03922597-Q
 * @author Miguel Morales Rodríguez 75169800-G                              
 *                           
 */
public class SopParametersActivity extends Activity {
	
	final Context context = this;
	private Button advanceOptions;
	private Button stopSimulation;
	private Button updateParameters;
	private SopParametersActivity aux = this;
	private MyAsynTaskServerSop taskServer;
	private ProgressBar u;
	private ProgressBar y;
	private EditText mu;
	private EditText T1;
	private EditText T2;
	private Double Ts_valor=0.1;
	private Double mu_valor=1.0;
	private Double T1_valor=1.0;
	private Double T2_valor=10.0;
	private Double u_valor=0.0;
	private Double y_valor=0.0;
	
	/**
	 * It's the getter of the valor Ts of advance options
	 *
	 * @return The Ts valor
	 */
	public Double getTS_valor() {
		return Ts_valor;
	}

	/**
	 * It's the setter of the valor Ts of advance options
	 * 
	 * @param tS_valor This value will be assign to the field that have the valor of Ts
	 */
	public void setTS_valor(Double tS_valor) {
		Ts_valor = tS_valor;
	}
	
	/**
	 * It's the getter of the valor of U
	 *
	 * @return The U valor
	 */
	public Double getU_valor() {
		return u_valor;
	}

	/**
	 * It's the setter of the valor of U
	 * 
	 * @param u_valor This value will be assign to the field that have the valor of U 
	 */
	public void setU_valor(Double u_valor) {
		this.u_valor = u_valor;
	}


	/**
	 * It's the getter of the valor of Y
	 *
	 * @return The Y valor
	 */
	public Double getY_valor() {
		return y_valor;
	}

	/**
	 * It's the setter of the valor of Y
	 * 
	 * @param y_valor This value will be assign to the field that have the valor of Y
	 */
	public void setY_valor(Double y_valor) {
		this.y_valor = y_valor;
	}
	
	/**
	 * It's the getter of the valor of mu
	 *
	 * @return The mu valor
	 */
	public Double getMU_valor() {
		return mu_valor;
	}

	/**
	 * It's the setter of the valor of MU
	 * 
	 * @param mu_valor This value will be assign to the field that have the valor of mu
	 */
	public void setMU_valor(Double mu_valor) {
		this.mu_valor = mu_valor;
	}

	/**
	 * It's the getter of the valor of T1
	 *
	 * @return The T1 valor
	 */
	public Double getT1_valor() {
		return T1_valor;
	}

	/**
	 * It's the setter of the valor of T1
	 * 
	 * @param t1_valor This value will be assign to the field that have the valor of T1
	 */
	public void setT1_valor(Double t1_valor) {
		T1_valor = t1_valor;
	}
	
	/**
	 * It's the getter of the valor of T2
	 *
	 * @return The T2 valor
	 */
	public Double getT2_valor() {
		return T2_valor;
	}

	/**
	 * It's the setter of the valor of T2
	 * 
	 * @param t2_valor This value will be assign to the field that have the valor of T2
	 */
	public void setT2_valor(Double t2_valor) {
		T2_valor = t2_valor;
	}
	
	/**
	 * It's the getter of the valor that is shown in the ProgressBar of U
	 *
	 * @return The valor that is shown in the ProgressBar of U
	 */
	public Integer getU() {
		return u.getProgress();
	}

	/**
	 * It's the setter of progress in the ProgressBar of U
	 * 
	 * @param u The new value 
	 */
	public void setU(Integer u) {
		this.u.setProgress(u);
	}
	
	/**
	 * It's the getter of the valor that is shown in the ProgressBar of Y
	 *
	 * @return The valor that is shown in the ProgressBar of Y
	 */
	public Integer getY() {
		return y.getProgress();
	}

	/**
	 * It's the setter of progress in the ProgressBar of Y
	 * 
	 * @param y The new value 
	 */
	public void setY(Integer y) {
		this.y.setProgress(y);
	}

	/**
	 * It's the getter of the valor that is written in the TextField of MU
	 *
	 * @return The valor that is written in the TextField of MU, if there is not a value it return 1.0
	 */
	public Double getMU() {
		Double aux;
		try{
			aux = Double.parseDouble(mu.getText().toString()); 
		}catch(Exception e){
			aux=1.0;
		}
		return aux;
	}

	/**
	 * It's the setter of the valor in the TextField of MU
	 * 
	 * @param mu The new value 
	 */
	public void setMU(Double mu) {
		this.mu.setText(mu.toString());
	}
	
	/**
	 * It's the getter of the valor that is written in the TextField of T1
	 *
	 * @return The valor that is written in the TextField of T1, if there is not a value it return 1.0
	 */
	public Double getT1() {
		Double aux;
		try{
			aux = Double.parseDouble(T1.getText().toString()); 
		}catch(Exception e){
			aux=1.0;
		}
		return aux;
	}

	/**
	 * It's the setter of the valor in the TextField of T1
	 * 
	 * @param T1 The new value 
	 */
	public void setT1(Double T1) {
		this.T1.setText(T1.toString());
	}

	/**
	 * It's the getter of the valor that is written in the TextField of T2
	 *
	 * @return The valor that is written in the TextField of T2, if there is not a value it return 10.0
	 */
	public Double getT2() {
		Double aux;
		try{
			aux = Double.parseDouble(T2.getText().toString()); 
		}catch(Exception e){
			aux=10.0;
		}
		return aux;
	}

	/**
	 * It's the setter of the valor in the TextField of T2
	 * 
	 * @param T2 The new value 
	 */
	public void setT2(Double T2) {
		this.T2.setText(T2.toString());
	}

	/**
	 * Called when the activity is starting.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sop_parameters);
		advanceOptions = (Button) findViewById(R.id.buttonAdvanceOptions_SOP);
		stopSimulation = (Button) findViewById(R.id.buttonStopSimulation_SOP);
		updateParameters = (Button) findViewById(R.id.buttonUpdateParameters_SOP);
		T1 = (EditText) findViewById(R.id.editTextT1_SOP);
		T2 = (EditText) findViewById(R.id.editTextT2_SOP);
		mu = (EditText) findViewById(R.id.editTextMU_SOP);
		u = (ProgressBar) findViewById(R.id.progressBarU_SOP);
		setU(getU_valor().intValue());
		y = (ProgressBar) findViewById(R.id.progressBarY_SOP);
		setY(getY_valor().intValue());
		aux=this;
		
		/*Listener AdvanceOptions Button*/
    	advanceOptions.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {

			LayoutInflater layInf = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

			View ViewDlg = layInf.inflate(R.layout.activity_pop_up_advance_options_process, null);

			AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
			alertBuilder.setView(ViewDlg);

			
			final EditText DlgTs= (EditText)ViewDlg.findViewById(R.id.editTextTs_pro);

			DlgTs.setText(Ts_valor.toString());
			
			alertBuilder
				.setCancelable(false)
				.setPositiveButton("Save Changes", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {

						try{
							Ts_valor=Double.parseDouble(DlgTs.getText().toString());
						}catch(Exception e){
							Ts_valor=0.1;
						}
					}
				})

				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});

			AlertDialog alert = alertBuilder.create();
			alert.show();
			
			}
		});
    	
    	/*Listener StopSimulation Button*/
    	stopSimulation.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
				try{
					
			        if(stopSimulation.getText().equals("Stop Simulation")){
			        	stopSimulation.setText(R.string.StartSimulation);
			        	taskServer.onCancelled();
			        }else{
			        	taskServer = new MyAsynTaskServerSop(aux);
				        taskServer.setContext(getDialogContext());
			        	taskServer.execute();
			        	stopSimulation.setText(R.string.StopSimulation);
			        }
				}catch(Exception e){
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
					AlertDialog alertDialog;
					alertDialogBuilder.setTitle("Alert");
					alertDialogBuilder.setMessage(e.toString());
					alertDialog = alertDialogBuilder.create();
					alertDialog.show();
					e.printStackTrace();
				}
			}
		});
    	
    	/*Listener UpdateParameters Button*/
    	updateParameters.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			try{
				setT1_valor(getT1());
			}catch(Exception e){
				setT1_valor(1.0);
			}
			try{
				setMU_valor(getMU());
	    	}catch(Exception e){
	    		setMU_valor(1.0);
			}
			try{
				setT2_valor(getT2());
	    	}catch(Exception e){
	    		setT2_valor(10.0);
			}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_sop_parameters, menu);
		return true;
	}
	
	/**
	* Called when the key down was pressed down and not handled by any of the views inside of the activity.
	* This key return to the Process Activity.
	*/
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK))
		{
			Intent myIntent = new Intent(SopParametersActivity.this, ProcessActivity.class);
    		finish();
			startActivity(myIntent); 
		
			return true;
	
		}
	
		return super.onKeyDown(keyCode, event);
	}
	
	/**
	 * Get the context of this activity
	 * 
	 * @return The actual context of the activity
	 */
	private Context getDialogContext() {
	    Context context;
	    if (getParent() != null) context = getParent();
	    else context = this;
	    return context;
	}
	
	/**
	 * If the connection is stopped
	 *
	 * @return If the connection is stopped by checking if in the stopControl Button is written "Start Simulation" 
	 */
	public Boolean isStopped(){
		Boolean a=false;
		if(stopSimulation.getText().equals("Start Simulation"))
			a=true;
		return a;
	}

	/**
	* It computes the new value of Y by the others known values
	* 
	* @return The new value of Y
	*/
	public Double computeY() {
		
		/*
		 * To compute the new value of y for a SOP you can define a variable
		 * 
		 * p = 1-Ts/T1;
		 * 
		 * where Ts is the parameter present in the advanced options, 
		 * and T is the parameter in the main interface of the SOP. 
		 * Then, to compute the next value of y, you can use the formula
		 * 
		 * y = p*y + (1-p)*mu*u;
		 * 
		 * where mu is the parameter present in the main interface of the SOP, 
		 * u is the value received from the regulator, 
		 * and y is the last stored value of y.
		 * 
		 */
		
		Double p = (1-Ts_valor)/T1_valor;
		y_valor= (p*y_valor) + ( (1-p)*mu_valor*u_valor );
		return y_valor;
	}

}
