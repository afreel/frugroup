package com.example.frugroup;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_screen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	public void onNewGame(View view) {
    	Intent intent = new Intent(this, NewGameActivity.class);
    	startActivity(intent);
    }
	
	public void onGame(View view) {
    	Intent intent = new Intent(this, GameActivity.class);
    	startActivity(intent);
    }
	
	public void onNewGoal(View view) {
    	Intent intent = new Intent(this, NewGoalActivity.class);
    	startActivity(intent);
    }
	
	public void onGoal(View view) {
    	Intent intent = new Intent(this, GoalActivity.class);
    	startActivity(intent);
    }
	
	public void onTransaction(View view) {
    	Intent intent = new Intent(this, NewTransactionActivity.class);
    	startActivity(intent);
    }

}
