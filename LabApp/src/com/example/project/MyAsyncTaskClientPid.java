package com.example.project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


/**
 * This class implements the AsyncTask of the client Pid
 * and it makes the connection between the client and the server
 * @author María Martínez Martínez 03922597-Q
 * @author Miguel Morales Rodríguez 75169800-G                              
 *                           
 */


public class MyAsyncTaskClientPid extends AsyncTask<Void, Integer, Boolean> {
    	
	
	private Socket clientSocket;
	private Context context;
	private DataOutputStream dataOutputStream = null;
	private DataInputStream dataInputStream = null;
	private String ip="";
	private String recived="A";
	private Double y=0.0;
	private Double u=0.0;
	private PidParametersActivity PidActivity;
	private Boolean success=false;
	private Boolean finish=false;
	private DataBaseHelper bd;

	/**
     * Constructor that associate the field PidActivity of the class
     * with the PidParametersActivity that has create this AsyncTask
     *
     * @param a the PidParametersActivity that has create this AsyncTask
     */
	public MyAsyncTaskClientPid(PidParametersActivity a) {
		PidActivity=a;
	}
	
	/**
     * Override this method to perform a computation on a background thread. 
     * 
     * The specified parameters are the parameters passed to execute(Params...)
     * by the caller of this task. 
     * 
     * This method can call publishProgress(Progress...) to publish updates 
     * on the UI thread.
     * 
     * This function is the responsable of the start of the connection and
     * send and recived all datums that it requires
     *  
     *  
     * @param params The parameters of the task.
     * 
     * @return A result, defined by the subclass of this task.
     */
	@Override
	protected Boolean doInBackground(Void... params) {

		while(!PidActivity.isStopped() && !finish){
			Boolean number_recived=false;
			try {
				if(!ip.equals(""))
					clientSocket = new Socket(ip, 8888);
				else
					clientSocket = new Socket("192.168.1.2", 8888);
				
				dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
				dataInputStream = new DataInputStream(clientSocket.getInputStream());
				//If the connection has been established send the number
				if(PidActivity.getConected())
					dataOutputStream.writeUTF("Request");
				else{
					dataOutputStream.writeUTF("Conect");
					PidActivity.setConected(true);
				}
				//dataOutputStream.flush();
				recived="A";
				while(!recived.equals("Conect") && !number_recived){//While I don't recived a new connection or a number "Y"
					recived=dataInputStream.readUTF().toString();
					try{
						y=Double.parseDouble(recived);
						PidActivity.setY_valor(y);
						number_recived=true;
					}catch (Exception e){
						number_recived=false;
					}
				}
				if(number_recived){
					u=PidActivity.computeU();//Compute (SP-y)*Kp
					dataOutputStream.writeUTF(u.toString()); //I send number "U"
				}
				publishProgress(1);
				try {
					dataOutputStream.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
					
			} catch (UnknownHostException e) {
				publishProgress(0);
				e.printStackTrace();
			} catch (IOException e) {
				publishProgress(0);
				e.printStackTrace();
			}
			try {
			    Thread.sleep(2000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
		return null;

	}
	
	/**
	* Runs on the UI thread after publishProgress(Progress...) is invoked. 
	* The specified values are the values passed to publishProgress(Progress...).
	* 
	* This functions makes the computation with the datum that the
	* AsyncTask has recived from the server. Also it shows on the screen the computation that it has done.
	* It saves the values "y" and "u" in the data base
	* 
	* @param progress The values indicating progress. This value says if the server is or is not available
	*/
	@Override
    protected void onProgressUpdate(Integer... progress) {
		if(progress[0].equals(0)){
			Toast.makeText(context, "The server is not available", Toast.LENGTH_SHORT).show();
			success=false;
			if(PidActivity.getConected()){
				PidActivity.setConected(false);
				finish=true;
				PidActivity.changeConected();
			}
		}else if(!recived.equals("Conect")){
			PidActivity.setY(y.intValue());			
			PidActivity.setU(u.intValue());
			Toast.makeText(context, "u= "+u.toString()+"\ny= "+ y.toString(), Toast.LENGTH_SHORT).show();
			bd.insertPID(u, y);
		}
    }
	
	/**
	* Runs on the UI thread before doInBackground(Params...).
	* 
	* It shows on the screen what the AsyncTask is going to do.
	* It creates an object of the class MYSQLiteHelper where the structure
	* of our database is defined
	*/
	@Override
	protected void onPreExecute() {
		bd = new DataBaseHelper(context); //Creat an object of our class DataBaseHelper where we have defined our data base structure
		Toast.makeText(context, "Conecting...", Toast.LENGTH_SHORT).show();
	}
	
	/**
	* Runs on the UI thread after doInBackground(Params...).
	* 
	* It shows on the screen what the AsyncTask has done.
	* 
	*/
	@Override
	protected void onPostExecute(Boolean result) {
		if(recived.equals("Conect"))
			Toast.makeText(context, "Conected", Toast.LENGTH_SHORT).show();
		else if(success)
			Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * It's the getter of the context of the PidParametersActivity that has called this AsyncTask
	 * @return The context of the PidParametersActivity that has called this AsyncTask
	 */
	public Context getContext() {
		return context;
	}

	/**
	 * It's the setter of the context of the PidParametersActivity that has called this AsyncTask
	 * @param context This value will be assign to the field of Context type of this class
	 */
	public void setContext(Context context) {
		this.context = context;
	}


	/**
	 * It's the setter of the IP that we want to connect
	 * @param ip The IP of the server we are going to connect
	 */
	public void setIP(String ip) {
		this.ip=ip;
		
	}

}