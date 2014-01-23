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
 * This class implements the Activity of the Fopdt Server
 * and shows every parameters that you can change
 * @author María Martínez Martínez 03922597-Q
 * @author Miguel Morales Rodríguez 75169800-G                              
 *                           
 */
public class FopdtParametersActivity extends Activity {
	
	final Context context = this;
	private Button advanceOptions;
	private Button stopSimulation;
	private Button updateParameters;
	private FopdtParametersActivity aux = this;
	private MyAsynTaskServerFopdt taskServer;
	private ProgressBar u;
	private ProgressBar y;
	private EditText mu;
	private EditText T;
	private EditText D;
	private Double Ts_valor=0.1;
	private Double mu_valor=1.0;
	private Double T_valor=1.0;
	private Double D_valor=0.0;
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
	 * It's the getter of the valor of T
	 *
	 * @return The T valor
	 */
	public Double getT_valor() {
		return T_valor;
	}

	/**
	 * It's the setter of the valor of T
	 * 
	 * @param t_valor This value will be assign to the field that have the valor of T
	 */
	public void setT_valor(Double t_valor) {
		T_valor = t_valor;
	}
	
	/**
	 * It's the getter of the valor of D
	 *
	 * @return The D valor
	 */
	public Double getD_valor() {
		return D_valor;
	}

	/**
	 * It's the setter of the valor of D
	 * 
	 * @param d_valor This value will be assign to the field that have the valor of D
	 */
	public void setD_valor(Double d_valor) {
		D_valor = d_valor;
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
	 * It's the getter of the valor that is written in the TextField of T
	 *
	 * @return The valor that is written in the TextField of T, if there is not a value it return 1.0
	 */
	public Double getT() {
		Double aux;
		try{
			aux = Double.parseDouble(T.getText().toString()); 
		}catch(Exception e){
			aux=1.0;
		}
		return aux;
	}

	/**
	 * It's the setter of the valor in the TextField of T
	 * 
	 * @param T The new value 
	 */
	public void setT(Double T) {
		this.T.setText(T.toString());
	}

	/**
	 * It's the getter of the valor that is written in the TextField of D
	 *
	 * @return The valor that is written in the TextField of D, if there is not a value it return 0.0
	 */
	public Double getD() {
		Double aux;
		try{
			aux = Double.parseDouble(D.getText().toString()); 
		}catch(Exception e){
			aux=0.0;
		}
		return aux;
	}

	/**
	 * It's the setter of the valor in the TextField of D
	 * 
	 * @param D The new value 
	 */
	public void setD(Double D) {
		this.D.setText(D.toString());
	}
	
	/**
	 * Called when the activity is starting.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fopdt_parameters);
		advanceOptions = (Button) findViewById(R.id.buttonAdvanceOptions_FOPDT);
		stopSimulation = (Button) findViewById(R.id.buttonStopSimulation_FOPDT);
		updateParameters = (Button) findViewById(R.id.buttonUpdateParameters_FOPDT);
		T = (EditText) findViewById(R.id.editTextT_FOPDT);
		D = (EditText) findViewById(R.id.editTextD_FOPDT);
		mu = (EditText) findViewById(R.id.editTextMU_FOPDT);
		u = (ProgressBar) findViewById(R.id.progressBarU_FOPDT);
		setU(getU_valor().intValue());
		y = (ProgressBar) findViewById(R.id.progressBarY_FOPDT);
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
			        	taskServer = new MyAsynTaskServerFopdt(aux);
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
				setT_valor(getT());
			}catch(Exception e){
				setT_valor(1.0);
			}
			try{
				setMU_valor(getMU());
	    	}catch(Exception e){
	    		setMU_valor(1.0);
			}
			try{
				setD_valor(getD());
	    	}catch(Exception e){
	    		setD_valor(0.0);
			}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_fopdt_parameters, menu);
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
			Intent myIntent = new Intent(FopdtParametersActivity.this, ProcessActivity.class);
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
		 * To compute the new value of y for a FOPDT you can define a variable
		 * 
		 * p = 1-Ts/T;
		 * 
		 * where Ts is the parameter present in the advanced options, 
		 * and T is the parameter in the main interface of the FOPDT. 
		 * Then, to compute the next value of y, you can use the formula
		 * 
		 * y = p*y + (1-p)*mu*u;
		 * 
		 * where mu is the parameter present in the main interface of the FOPDT, 
		 * u is the value received from the regulator, 
		 * and y is the last stored value of y.
		 * 
		 */
		
		Double p = (1-Ts_valor)/T_valor;
		y_valor= (p*y_valor) + ( (1-p)*mu_valor*u_valor );
		return y_valor;
	}

	
}
