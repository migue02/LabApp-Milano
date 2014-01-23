 package com.example.project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


/**
 * This class implements the AsyncTask of the Server Fop
 * and it makes the connection between the client and the server
 * @author María Martínez Martínez 03922597-Q
 * @author Miguel Morales Rodríguez 75169800-G                              
 *                           
 */

public class MyAsynTaskServerFop extends AsyncTask<Void, Integer, Boolean> {
    	
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private Context context;
	private DataInputStream dataInputStream = null;
	private DataOutputStream dataOutputStream = null;
	private Double u;
	private Double y;
	private FopParametersActivity activityFOP=null;
	private String recived;
	
	
	/**
     * Constructor that associate the field FopActivity of the class
     * with the FopParametersActivity that has create this AsyncTask
     *
     * @param a the FopParametersActivity that has create this AsyncTask
     */
	public MyAsynTaskServerFop(FopParametersActivity a){
		activityFOP=a;
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
     * recived and send all datums that it requires and do the computation of
     * the value "y"
     *  
     *  
     * @param params The parameters of the task.
     * 
     * @return A result, defined by the subclass of this task.
     */
	@Override
	protected Boolean doInBackground(Void... params) {

		u=0.0;
		while(!activityFOP.isStopped()){
			try {
				clientSocket = serverSocket.accept();
			    dataInputStream = new DataInputStream(clientSocket.getInputStream());
			    dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
			    recived=dataInputStream.readUTF();
			    
			    try {
			    	if(recived.equals("Conect"))
			    		dataOutputStream.writeUTF("Conect");
			    	else if(recived.equals("Request")){ //If i recived a request I send "Y" and I recived "U"
			    		y=activityFOP.computeY();//I compute "Y"...
			    		dataOutputStream.writeUTF(y.toString());// I send "Y"...
			    		recived="A";
			    		while(recived.equals("A")) //While I don't recive a number "U"...
			    			recived=dataInputStream.readUTF();
			    		u=Double.parseDouble(recived);//I've recived "U"
			    		activityFOP.setU_valor(u);//Update "U" valor
			    	}
			    	publishProgress();
				} catch (IOException e) {
					Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
				}
			    
			} catch (IOException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
			finally{
				if(clientSocket!= null){
					try {
						clientSocket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			    
			    if( dataInputStream!= null){
			    	try {
			    		dataInputStream.close();
			    	} catch (IOException e) {
			    		// TODO Auto-generated catch block
			    		e.printStackTrace();
			    	}
			    }
			    
			    if( dataOutputStream!= null){
			    	try {
			    		dataOutputStream.close();
			    	} catch (IOException e) {
			    		// TODO Auto-generated catch block
			    		e.printStackTrace();
			    	}
			    }
			}
			try{
				Thread.sleep(2000); //Simulate a (big) process of data 
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
		return true;
	}
	
	/**
	* Runs on the UI thread after publishProgress(Progress...) is invoked. 
	* The specified values are the values passed to publishProgress(Progress...).
	* 
	* This functions change the values of the fields "y" and "u" of the FopActivity with the datum that the
	* AsyncTask has recived from the client. Also it shows on the screen the computation that it has done.
	* 
	* @param progress The values indicating progress. This value is not used
	*/
	@Override
    protected void onProgressUpdate(Integer... progress) {
		if(recived.equals("Conect")){
			Toast.makeText(context, "Conected", Toast.LENGTH_SHORT).show();
		}else{
			activityFOP.setY(y.intValue()); //Update "Y" bar
			activityFOP.setU(u.intValue());//Update "U" bar
			Toast.makeText(context, "u= "+u.toString()+"\ny= "+ y.toString(), Toast.LENGTH_SHORT).show();
		}
    }
	
	
	/**
	* Runs on the UI thread before doInBackground(Params...).
	* 
	* It shows on the screen what the server will be ready for future connections.
	* It constructs a new ServerSocket instance bound to the given port (8888 in this case). 
	* 
	*/
	@Override
	protected void onPreExecute() {
		
		try {
			serverSocket = new ServerSocket(8888);
			Toast.makeText(context, "Ready for conections", Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	protected void onPostExecute(Boolean result) {
	}
	
	/**
	* 
	* It cancels the connection by closing the ServerSocket 
	* 
	*/
	@Override
	protected void onCancelled() {
		
		try {
			serverSocket.close();
			Toast.makeText(context, "The conection has been canceled", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(context, "An error ocurred", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
	}

	/**
	 * It's the getter of the context of the FopParametersActivity that has called this AsyncTask
	 *
	 * @return The context of the FopParametersActivity that has called this AsyncTask
	 */
	public Context getContext() {
		return context;
	}

	/**
	 * It's the setter of the context of the FopParametersActivity that has called this AsyncTask
	 * @param context This value will be assign to the field of Context type of this class 
	 */
	public void setContext(Context context) {
		this.context = context;
	}





}