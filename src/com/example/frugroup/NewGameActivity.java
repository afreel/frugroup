package com.example.frugroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class NewGameActivity extends Activity {
	
	EditText gameName, gameBudget, gameTag;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_game);
		
		gameName = (EditText) findViewById(R.id.edit_game_description);
        gameBudget = (EditText) findViewById(R.id.edit_user_game_budget);
        gameTag = (EditText) findViewById(R.id.edit_game_tag);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_game, menu);
		return true;
	}

	public void onSubmitUserGame(View view) {
    	String name = gameName.getText().toString();
    	String budget = gameBudget.getText().toString();
    	Double numBudget = 0.00;
    	String tag = gameTag.getText().toString();
    	
    	boolean inputTest = true;
    	try{
    		numBudget = Double.parseDouble(budget);
    	}
    	catch(Exception ex){
    		inputTest = false;
    	}
    	
    	if (inputTest){
    		Calendar c = Calendar.getInstance();
        	Date datetime = c.getTime();
        	
        	Bundle bundle = getIntent().getExtras();
    		String localUser = bundle.getString("localUser");
    		Log.d("Data", localUser);
        	
        	UserData entry = new UserData(NewGameActivity.this);
        	entry.open();
        	long gameId = entry.createGroupGameEntry(name, datetime);
        	entry.createUserGameEntry(localUser, gameId, numBudget, tag, true);
        	String groupResults = entry.getGroupGameData();
        	Log.d("Data", groupResults);
        	ArrayList<String> userResults = entry.getUserGameData(localUser);
        	for (String result : userResults){
        		Log.d("Data", result);
        	}
        	entry.close();
        	
    		Intent intent = new Intent(this, HomeActivity.class);
    		intent.putExtras(bundle);
        	startActivity(intent);
    	}
	}
}
