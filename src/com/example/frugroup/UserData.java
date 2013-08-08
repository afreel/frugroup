package com.example.frugroup;


import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserData {
	
	public static final String KEY_USER_ID = "user_id";
	public static final String KEY_USER_NAME = "user_name";
	public static final String KEY_USER_PASS = "user_pass";
	
	public static final String KEY_PURCHASE_ID = "purchase_id";
	public static final String KEY_PURCHASE_COST = "purchase_cost";
	public static final String KEY_PURCHASE_DESC = "purchase_desc";
	public static final String KEY_PURCHASE_TAG = "purchase_tag";
	public static final String KEY_PURCHASE_TIME = "purchase_time";
	
	public static final String KEY_GAME_ID = "game_id";
	public static final String KEY_GAME_TIME = "game_time";
	
	public static final String KEY_USER_GAME_ID = "user_game_id";
	public static final String KEY_USER_GAME_BUDGET = "user_game_budget";
	public static final String KEY_USER_GAME_BALANCE = "user_game_balance";
	public static final String KEY_USER_GAME_TAG = "user_game_tag";
	public static final String KEY_USER_GAME_BOOL = "user_game_bool";
			
	public static final String KEY_GOAL_ID = "user_goal_id";
	public static final String KEY_GOAL_COST = "user_goal_cost";
	public static final String KEY_GOAL_DESC = "user_goal_desc";
	
	public static final String DATABASE_NAME = "Frugroupdb";
	public static final String DATABASE_TABLE_LOGIN = "LoginTable";
	public static final String DATABASE_TABLE_PURCHASE = "PurchaseTable";
	public static final String DATABASE_TABLE_USER_GAME = "UserGameTable";
	public static final String DATABASE_TABLE_GROUP_GAME = "GroupGameTable";
	public static final String DATABASE_TABLE_GOAL = "GoalTable";
	public static final int DATABASE_VERSION = 7;
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class DbHelper extends SQLiteOpenHelper {
		
		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE_LOGIN + " (" +
					KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_USER_NAME + " TEXT NOT NULL, " +
					KEY_USER_PASS + " TEXT NOT NULL);"
			);
			
			db.execSQL("CREATE TABLE " + DATABASE_TABLE_PURCHASE + " (" +
					KEY_PURCHASE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_USER_NAME + " TEXT NOT NULL, " +
					KEY_PURCHASE_COST + " DOUBLE NOT NULL, " +
					KEY_PURCHASE_DESC + " TEXT NOT NULL, " +
					KEY_PURCHASE_TIME + " DATETIME NOT NULL, " +
					KEY_PURCHASE_TAG + " TEXT NOT NULL);"
			);
			
			db.execSQL("CREATE TABLE " + DATABASE_TABLE_USER_GAME + " (" +
					KEY_USER_GAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_USER_NAME + " TEXT NOT NULL, " +
					KEY_USER_GAME_BUDGET + " DOUBLE NOT NULL, " +
					KEY_USER_GAME_BALANCE + " DOUBLE NOT NULL, " +
					KEY_USER_GAME_TAG + " TEXT NOT NULL, " +
					KEY_USER_GAME_BOOL + " BOOLEAN NOT NULL);"
			);
			
			db.execSQL("CREATE TABLE " + DATABASE_TABLE_GROUP_GAME + " (" +
					KEY_GAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_GAME_TIME + " TEXT NOT NULL);"
			);
			
			db.execSQL("CREATE TABLE " + DATABASE_TABLE_GOAL + " (" +
					KEY_GOAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_USER_NAME + " TEXT NOT NULL, " +
					KEY_GOAL_COST + " TEXT NOT NULL, " +
					KEY_GOAL_DESC + " TEXT NOT NULL);"
			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_LOGIN);
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_PURCHASE);
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_USER_GAME);
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_GROUP_GAME);
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_GOAL);
			onCreate(db);
		}
	}
	
	public UserData(Context c) {
		ourContext = c;
	}
	
	public UserData open() {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		ourHelper.close();
	}

	public long createRegisterEntry(String name, String pass) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_USER_NAME, name);
		cv.put(KEY_USER_PASS, pass);
		return ourDatabase.insert(DATABASE_TABLE_LOGIN, null, cv);
	}
	
	public long createPurchaseEntry(String desc, Double cost, String tag, Date date, String user) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_PURCHASE_DESC, desc);
		cv.put(KEY_PURCHASE_COST, cost);
		cv.put(KEY_PURCHASE_TAG, tag);
		cv.put(KEY_PURCHASE_TIME, date.toString()); //NOTE: converted from type Date --> String
		cv.put(KEY_USER_NAME, user);
		
		return ourDatabase.insert(DATABASE_TABLE_PURCHASE, null, cv);
	}
	
//	public long createGameEntry(String name, String game) {
//		// TODO Auto-generated method stub
//		ContentValues cv = new ContentValues();
//		cv.put(KEY_NAME, name);
//		cv.put(KEY_GAME, game);
//		return ourDatabase.insert(DATABASE_TABLE_GAME, null, cv);
//	}
	
	public long createGoalEntry(String desc, Double cost, String user) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_GOAL_DESC, desc);
		cv.put(KEY_GOAL_COST, cost);
		cv.put(KEY_USER_NAME, user);
		return ourDatabase.insert(DATABASE_TABLE_GOAL, null, cv);
	}
	
	public String getLoginData() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_USER_ID, KEY_USER_NAME, KEY_USER_PASS};
		Cursor c = ourDatabase.query(DATABASE_TABLE_LOGIN, columns, null, null, null, null, null);
		String result = "";
		
		int iRow = c.getColumnIndex(KEY_USER_ID);
		int iName = c.getColumnIndex(KEY_USER_NAME);
		int iPass = c.getColumnIndex(KEY_USER_PASS);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = result + c.getString(iRow) + " " + c.getString(iName) + " " + c.getString(iPass) +"\n";
		}
		
		return result;
	}

	public String getPurchaseData() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_USER_NAME, KEY_PURCHASE_ID, KEY_PURCHASE_DESC, KEY_PURCHASE_COST, KEY_PURCHASE_TAG, KEY_PURCHASE_TIME};
		Cursor c = ourDatabase.query(DATABASE_TABLE_PURCHASE, columns, null, null, null, null, null);
		String result = "";
		
		int iUser = c.getColumnIndex(KEY_USER_NAME);
		int iPurchaseId = c.getColumnIndex(KEY_PURCHASE_ID);
		int iDesc = c.getColumnIndex(KEY_PURCHASE_DESC);
		int iCost = c.getColumnIndex(KEY_PURCHASE_COST);
		int iTag = c.getColumnIndex(KEY_PURCHASE_TAG);
		int iTime = c.getColumnIndex(KEY_PURCHASE_TIME);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = result + c.getString(iUser) + " : " + c.getString(iPurchaseId) + " : " + c.getString(iDesc) + " : " + c.getString(iCost)
					+ " : " + c.getString(iTag) + " : " + c.getString(iTime) + "\n";
		}
		
		return result;
	}
	
	public String getGoalData() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_USER_NAME, KEY_GOAL_ID, KEY_GOAL_DESC, KEY_GOAL_COST};
		Cursor c = ourDatabase.query(DATABASE_TABLE_GOAL, columns, null, null, null, null, null);
		String result = "";
		
		int iUser = c.getColumnIndex(KEY_USER_NAME);
		int iGoalId = c.getColumnIndex(KEY_GOAL_ID);
		int iDesc = c.getColumnIndex(KEY_GOAL_DESC);
		int iCost = c.getColumnIndex(KEY_GOAL_COST);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = result + c.getString(iUser) + " " + c.getString(iGoalId) + " " + c.getString(iDesc) + " " + c.getString(iCost) + "\n";
		}
		
		return result;
	}
	
	public boolean validateRegister(String userName) {
		String[] columns = new String[]{ KEY_USER_NAME, KEY_USER_PASS};
		Cursor c = ourDatabase.query(DATABASE_TABLE_LOGIN, columns, null, null, null, null, null);
		boolean validity = true;
		String errorMessage = "Valid Username";
		
		int iUser = c.getColumnIndex(KEY_USER_NAME);
		int iPass = c.getColumnIndex(KEY_USER_PASS);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			Log.d("Data", c.getString(iUser));
			Log.d("Data", userName);
			if (userName.contentEquals(c.getString(iUser))){
				Log.d("Data", "HI");
				errorMessage = "That Username is taken.";
				validity = false;
				break;
			}
		}
		
		Log.d("Data", errorMessage);
		return validity;
	}

}
