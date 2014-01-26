package com.example.project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This class implements the DataBase of the application and all of the possible
 * operations that you can do with the DataBase
 * @author María Martínez Martínez 03922597-Q
 * @author Miguel Morales Rodríguez 75169800-G                              
 *                           
 */
public class DataBaseHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "database.db";
	protected static String TablePI = "pi";
	protected static String TablePID = "pid";
	//Prueba commit

	
	private String sqlCreatePI = "create table pi (_id integer primary key autoincrement, u double, y double, server text)";
	private String sqlCreatePID = "create table pid (_id integer primary key autoincrement, u double, y double, server text)";
	
	/**
     * Constructor that create a new DataBaseHelper to create, open, and/or manage a database. 
     * 
     * @param context to use to open or create the database
     */
	public DataBaseHelper(Context context) {
		super(context, DB_NAME, null,1);
	}
	
	/**
     * Called when the database is created for the first time. This is where the creation of tables and the initial population of the tables should happen.
     * 
     *  @param db	The database.
     */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(sqlCreatePI);
		db.execSQL(sqlCreatePID);
	}

	/**
	 * It inserts in the PI table a new row with the values passed in the parameters
	 * 
	 * @param u The value U of the new row
	 * @param y The value Y of the new row
	 */
	public void insertPI(Double u, Double y){
		 SQLiteDatabase db = getWritableDatabase();
		 if(db!=null){
		  db.execSQL("INSERT INTO " + TablePI + 
		  " (u, y, server) " +
		  " VALUES(" + u + ", " + y + ", ' pi ' ) ");
		  db.close();   
		 }
		}
	
	/**
	 * It inserts in the PID table a new row with the values passed in the parameters
	 * 
	 * @param u The value U of the new row
	 * @param y The value Y of the new row
	 */
	public void insertPID(Double u, Double y){
		 SQLiteDatabase db = getWritableDatabase();
		 if(db!=null){
		  db.execSQL("INSERT INTO " + TablePID + 
		  " (u, y, server) " +
		  " VALUES(" + u + ", " + y + ", ' pid ' ) ");
		  db.close();   
		 }
		}
	
	/**
	 * It will read in the PI table and return a cursor with the query of a select of the values "u" and "y" of every rows in the PI table
	 * 
	 * @return It return a cursor with the query of a select of the values "u" and "y" of every rows in the PI table
	 */
	public Cursor readPI(){
		 SQLiteDatabase db = getReadableDatabase();
		  
		 return db.rawQuery("SELECT u , y "+
		  " FROM "+ TablePI , null);  
		}
	
	/**
	 * It will drop the PI table if exists and will create a new one
	 * 
	 */
	public void dropPI(){
		
		SQLiteDatabase db = getReadableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + TablePI);
		db.execSQL(sqlCreatePI);
		db.close();
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	/**
	 * It will drop the PID table if exists and will create a new one
	 * 
	 */
	public void dropPID() {
		SQLiteDatabase db = getReadableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + TablePID);
		db.execSQL(sqlCreatePID);
		db.close();
		
	}

	/**
	 * It will read in the PID table and return a cursor with the query of a select of the values "u" and "y" of every rows in the PI table
	 * 
	 * @return It return a cursor with the query of a select of the values "u" and "y" of every rows in the PI table
	 */
	public Cursor readPID(){
		 SQLiteDatabase db = getReadableDatabase();
		  
		 return db.rawQuery("SELECT u , y "+
		  " FROM "+ TablePID , null);  
		}

}
