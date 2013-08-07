package com.example.frugroup;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_screen);
		
		Log.d("Data", "test2");

		UserData info = new UserData(this);
		info.open();
		String data = info.getLoginData();
		info.close();
		
		Log.d("Data", data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	public void onNewGame(View view) {
    	Intent intent = new Intent(this, NewGameActivity.class);
    	Bundle bundle = getIntent().getExtras();
    	intent.putExtras(bundle);
    	startActivity(intent);
    }
	
	public void onGame(View view) {
    	Intent intent = new Intent(this, GameActivity.class);
    	Bundle bundle = getIntent().getExtras();
    	intent.putExtras(bundle);
    	startActivity(intent);
    }
	
	public void onNewGoal(View view) {
    	Intent intent = new Intent(this, NewGoalActivity.class);
    	Bundle bundle = getIntent().getExtras();
    	intent.putExtras(bundle);
    	startActivity(intent); 
    }
	
	public void onGoal(View view) {
    	Intent intent = new Intent(this, GoalActivity.class);
    	Bundle bundle = getIntent().getExtras();
    	intent.putExtras(bundle);
    	startActivity(intent);
    }
	
	public void onTransaction(View view) {
    	Intent intent = new Intent(this, NewTransactionActivity.class);
    	Bundle bundle = getIntent().getExtras();
    	intent.putExtras(bundle);
    	startActivity(intent);
    }

}
