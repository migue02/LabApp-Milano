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
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * This class implements the Activity of the Pi client 
 * and shows every parameters that you can change
 * @author María Martínez Martínez 03922597-Q
 * @author Miguel Morales Rodríguez 75169800-G                              
 *                           
 */

public class PiParametersActivity extends Activity {

	private final Context context=this;
	private Button advanceOptions;
	private Button showGraphic;
	private Button startControl;
	private Button stopControl;
	private Button updateSP;
	private Button updateParameters;
	private ProgressBar u;
	private ProgressBar y;
	private EditText sp;
	private EditText k;
	private EditText Ti;
	private String ip;
	private MyAsyncTaskClientPi taskClient;
	private PiParametersActivity aux = this;
	private Boolean conected=false;
	private Double u_valor=0.0;
	private Double y_valor=0.0;
	private Double sp_valor=0.0;
	private Double k_valor=1.0;
	private Double Ti_valor=1.0;
	private Double Umin_valor=-100.0;
	private Double Umax_valor=100.0;
	private Double TS_valor=0.1;
	private Boolean manual_valor=false;
	private DataBaseHelper bd = new DataBaseHelper(this);
	
	/**
	 * It's the getter of the valor Umin of advance options
	 *
	 * @return The Umin valor
	 */
	public Double getUmin_valor() {
		return Umin_valor;
	}
	
	/**
	 * It's the setter of the valor Umin of advance options
	 * 
	 * @param umin_valor This value will be assign to the field that have the valor of Umin 
	 */
	public void setUmin_valor(Double umin_valor) {
		Umin_valor = umin_valor;
	}

	/**
	 * It's the getter of the valor Umax of advance options
	 *
	 * @return The Umax valor
	 */
	public Double getUmax_valor() {
		return Umax_valor;
	}

	/**
	 * It's the setter of the valor Umax of advance options
	 * 
	 * @param umax_valor This value will be assign to the field that have the valor of Umax 
	 */
	public void setUmax_valor(Double umax_valor) {
		Umax_valor = umax_valor;
	}

	/**
	 * It's the getter of the valor Ts of advance options
	 *
	 * @return The Ts valor
	 */
	public Double getTS_valor() {
		return TS_valor;
	}

	/**
	 * It's the setter of the valor Ts of advance options
	 * 
	 * @param tS_valor This value will be assign to the field that have the valor of Ts
	 */
	public void setTS_valor(Double tS_valor) {
		TS_valor = tS_valor;
	}

	/**
	 * It's the getter of the valor manual of advance options
	 *
	 * @return The manual valor
	 */
	public Boolean getManual_valor() {
		return manual_valor;
	}

	/**
	 * It's the setter of the valor manual of advance options
	 * 
	 * @param manual_valor This value will be assign to the field that have the valor of manual 
	 */
	public void setManual_valor(Boolean manual_valor) {
		this.manual_valor = manual_valor;
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
	 * It's the getter of the valor of Sp
	 *
	 * @return The Sp valor
	 */
	public Double getSp_valor() {
		return sp_valor;
	}

	
	/**
	 * It's the setter of the valor of Sp
	 * 
	 * @param sp_valor This value will be assign to the field that have the valor of Sp
	 */
	public void setSp_valor(Double sp_valor) {
		this.sp_valor = sp_valor;
	}

	/**
	 * It's the getter of the valor of K
	 *
	 * @return The K valor
	 */
	public Double getK_valor() {
		return k_valor;
	}

	/**
	 * It's the setter of the valor of K
	 * 
	 * @param k_valor This value will be assign to the field that have the valor of K 
	 */
	public void setK_valor(Double k_valor) {
		this.k_valor = k_valor;
	}

	/**
	 * It's the getter of the valor of Ti
	 *
	 * @return The Ti valor
	 */
	public Double getTi_valor() {
		return Ti_valor;
	}

	/**
	 * It's the setter of the valor of Ti
	 * 
	 * @param ti_valor This value will be assign to the field that have the valor of Ti
	 */
	public void setTi_valor(Double ti_valor) {
		Ti_valor = ti_valor;
	}
	
	/**
	 * It's the getter of the valor of conected (The value that indicates if there is a connection)
	 *
	 * @return If there is or there is not a conection
	 */
	public Boolean getConected() {
		return conected;
	}

	/**
	 * It's the setter of the valor of the field conected
	 * 
	 * @param conected True if there is a conection, false if there is not any conection
	 */
	public void setConected(Boolean conected) {
		this.conected = conected;
	}
	
	/**
	 * It's the getter of the valor that is written in the TextField of SP
	 *
	 * @return The valor that is written in the TextField of SP, if there is not a value it return 0.0
	 */
	public Double getSP() {
		Double aux;
		try{
			aux = Double.parseDouble(sp.getText().toString()); 
		}catch(Exception e){
			aux=0.0;
		}
		return aux;
	}

	/**
	 * It's the setter of the valor in the TextField of Sp
	 * 
	 * @param sp The new value 
	 */
	public void setSP(Double sp) {
		this.sp.setText(sp.toString());
	}

	/**
	 * It's the getter of the valor that is written in the TextField of K
	 *
	 * @return The valor that is written in the TextField of K, if there is not a value it return 1.0
	 */
	public Double getK() {
		Double aux;
		try{
			aux = Double.parseDouble(k.getText().toString()); 
		}catch(Exception e){
			aux=1.0;
		}
		return aux;
	}

	/**
	 * It's the setter of the valor in the TextField of K
	 * 
	 * @param k The new value 
	 */
	public void setK(Double k) {
		this.k.setText(k.toString());
	}
	
	/**
	 * It's the getter of the valor that is written in the TextField of Ti
	 *
	 * @return The valor that is written in the TextField of Ti, if there is not a value it return 1.0
	 */
	public Double getTi() {
		Double aux;
		try{
			aux = Double.parseDouble(Ti.getText().toString()); 
		}catch(Exception e){
			aux=1.0;
		}
		return aux;
	}

	/**
	 * It's the setter of the valor in the TextField of Ti
	 * 
	 * @param Ti The new value 
	 */
	public void setTi(Double Ti) {
		this.Ti.setText(Ti.toString());
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
	 * It sets the Button startControl to clickable and the Button stopControl not clickable
	 */
	public void changeConected() {
		startControl.setClickable(true);
		stopControl.setClickable(false);
	}
	
	/**
	 * If the connection is stopped
	 *
	 * @return If the connection is stopped by checking if the startControl is clickable
	 */
	public Boolean isStopped(){
		return startControl.isClickable();
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
	 * Called when the activity is starting.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		try{
			Bundle extras = getIntent().getExtras();
			ip = extras.getString("ip");
		}catch (Exception e){
			ip="";
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pi_parameters);
		
		advanceOptions = (Button) findViewById(R.id.buttonAdvanceOptions_PI);
		startControl = (Button) findViewById(R.id.buttonStartControl_PI);
		stopControl = (Button) findViewById(R.id.buttonStopControl_PI);
		updateParameters = (Button) findViewById(R.id.buttonUpdateParameters_PI);
		updateSP = (Button) findViewById(R.id.buttonUpdateSP_PI);
		showGraphic = (Button) findViewById(R.id.buttonShowGraphic_PI);
		sp = (EditText) findViewById(R.id.editTextSP_PI);
		k = (EditText) findViewById(R.id.editTextK_PI);
		Ti = (EditText) findViewById(R.id.editTextTi_PI);
		u = (ProgressBar) findViewById(R.id.progressBarU_PI);
		setU(getU_valor().intValue());
		y = (ProgressBar) findViewById(R.id.progressBarY_PI);
		setY(getY_valor().intValue());
		stopControl.setClickable(false);
		aux=this;
		
		/*Listener AdvanceOptions Button*/
    	advanceOptions.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			
			LayoutInflater layInf = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

			View ViewDlg = layInf.inflate(R.layout.activity_pop_up_advance_options_regulator, null);

			AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
			alertBuilder.setView(ViewDlg);

			
			final EditText DlgUmin = (EditText) ViewDlg.findViewById(R.id.editTextUmin_reg);
			final EditText DlgUmax = (EditText) ViewDlg.findViewById(R.id.editTextUmax_reg);
			final EditText DlgTs = (EditText) ViewDlg.findViewById(R.id.editTextTs_reg);
			final RadioButton DlgManual_false = (RadioButton) ViewDlg.findViewById(R.id.radioButtonManualFalse);
			final RadioButton DlgManual_true = (RadioButton) ViewDlg.findViewById(R.id.radioButtonManualTrue);
			
			DlgUmax.setText(Umax_valor.toString());
			DlgUmin.setText(Umin_valor.toString());
			DlgTs.setText(TS_valor.toString());
			if(manual_valor)
				DlgManual_true.setChecked(true);
			else
				DlgManual_false.setChecked(true);
			
			alertBuilder
				.setCancelable(false)
				.setPositiveButton("Save Changes", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {

						try{
							Umax_valor=Double.parseDouble(DlgUmax.getText().toString());
						}catch(Exception e){
							Umax_valor=100.0;
						}
						try{
							Umin_valor=Double.parseDouble(DlgUmin.getText().toString());
						}catch(Exception e){
							Umin_valor=-100.0;
						}
						try{
							TS_valor=Double.parseDouble(DlgTs.getText().toString());
				    	}catch(Exception e){
							TS_valor=0.1;
						}
						if(DlgManual_false.isChecked())
							manual_valor=false;
						else 
							manual_valor=true;
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
    	
    	/*Listener StartControl Button (MyAsynTaskDialog)*/
    	startControl.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			try{
			
				
				bd.dropPI();
				taskClient = new MyAsyncTaskClientPi(aux);
			    taskClient.setContext(getDialogContext());
			    taskClient.setIP(ip);
			    taskClient.execute();
		        
				startControl.setClickable(false);
				stopControl.setClickable(true);
				
			}catch(Exception e){
				Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
			}
		}
		});
    	
    	/*Listener StopControl Button*/
    	stopControl.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			startControl.setClickable(true);
			stopControl.setClickable(false);
		}
		});
    	
    	/*Listener ShowGraphic Button*/
    	showGraphic.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {

			
			Intent myIntent = new Intent(PiParametersActivity.this, Graphics.class);
			myIntent.putExtra("regulator", "pi");
			myIntent.putExtra("ip", ip);
			finish();
			startActivity(myIntent);
		     

		}
		});
    	
    	/*Listener UpdateSP Button*/
    	updateSP.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			setSp_valor(getSP());
			}
		});
    	
    	/*Listener UpdateParameters Button*/
    	updateParameters.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			setK_valor(getK());
			setTi_valor(getTi());
			}
		});
		
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_pi_parameters, menu);
		return true;
	}
	
	/**
	* Called when the key down was pressed down and not handled by any of the views inside of the activity.
	* This key return to the Regulator Activity.
	*/
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK))
		{
			Intent myIntent = new Intent(PiParametersActivity.this, RegulatorActivity.class);
    		finish();
			startActivity(myIntent); 
		
			return true;
	
		}
	
		return super.onKeyDown(keyCode, event);
	}

	/**
	* It computes the new value of U by the others known values
	* 
	* @return The new value of U
	*/
	public Double computeU() {
		u_valor = (getSp_valor()-getY_valor())*getK_valor();
		return u_valor;
	}
	
}
