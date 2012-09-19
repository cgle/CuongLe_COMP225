package org.example.contact_book;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database {
	public static final String ID ="_id";
	public static final String NAME = "name";
	public static final String AGE = "age";
	public static final String NUMBER = "number";
	
	private static final String DATABASE_NAME = "contactdb";
	private static final String DATABASE_TABLE = "contacttable";
	private static final int DATABASE_VERSION = 1;
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL( "CREATE TABLE " + DATABASE_TABLE + " (" +
				ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				NAME + " TEXT NOT NULL, " + 
				AGE + " TEXT NOT NULL, " +
				NUMBER + " TEXT NOT NULL);"
				);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	public Database(Context c){
		ourContext =c;
	}
	
	public Database open() throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}

	public long createContact(String name1, int age1, String number1) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(NAME, name1);
		cv.put(AGE, age1);
		cv.put(NUMBER, number1);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ID,NAME,AGE,NUMBER};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
		int iRow = c.getColumnIndex(ID);
		int iName = c.getColumnIndex(NAME);
		int iAge = c.getColumnIndex(AGE);
		int iNumber = c.getColumnIndex(NUMBER);
		
		for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			result = result + c.getString(iRow) + " " +
							c.getString(iName) + " " +
							c.getString(iAge) + " " +
							c.getString(iNumber) + "\n";
							}
		
	    return result;
	}
	
}
