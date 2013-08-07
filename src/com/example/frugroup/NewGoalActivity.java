package com.example.frugroup;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class NewGoalActivity extends Activity {
	
	EditText goalName, goalCost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_goal);
		
		goalName = (EditText) findViewById(R.id.edit_goal_description);
        goalCost = (EditText) findViewById(R.id.edit_goal_cost);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_goal, menu);
		return true;
	}
	
	public void onSubmitGoal(View view) {
    	String name = goalName.getText().toString();
    	String cost = goalCost.getText().toString();
    	Double numCost = 0.00;
    	
    	boolean inputTest = true;
    	try{
    		numCost = Double.parseDouble(cost);
    	}
    	catch(Exception ex){
    		inputTest = false;
    	}
    	
    	if (inputTest){
        	Bundle bundle = getIntent().getExtras();
    		String localUser = bundle.getString("localUser");
    		Log.d("Data", localUser);
        	
        	UserData entry = new UserData(NewGoalActivity.this);
        	entry.open();
        	entry.createGoalEntry(name, numCost, localUser);
        	String result = entry.getGoalData();
        	Log.d("Data", result);
        	entry.close();
        	
    		Intent intent = new Intent(this, HomeActivity.class);
    		intent.putExtras(bundle);
        	startActivity(intent);
    	}
	}

}
