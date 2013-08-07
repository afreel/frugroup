package com.example.frugroup;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class NewTransactionActivity extends Activity {
	
	EditText transName, transCost, transTag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_transaction);
		
		transName = (EditText) findViewById(R.id.edit_trans_description);
        transCost = (EditText) findViewById(R.id.edit_trans_cost);
        transTag = (EditText) findViewById(R.id.edit_trans_tag);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_transaction, menu);
		return true;
	}
	
	public void onSubmitPurchase(View view) {
    	String name = transName.getText().toString();
    	String cost = transCost.getText().toString();
    	Double numCost = 0.00;
    	String tag = transTag.getText().toString();
    	
    	boolean inputTest = true;
    	try{
    		numCost = Double.parseDouble(cost);
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
        	
        	UserData entry = new UserData(NewTransactionActivity.this);
        	entry.open();
        	entry.createPurchaseEntry(name, numCost, tag, datetime, localUser);
        	String result = entry.getPurchaseData();
        	Log.d("Data", result);
        	entry.close();
        	
    		Intent intent = new Intent(this, HomeActivity.class);
    		intent.putExtras(bundle);
        	startActivity(intent);
    	}
	}

}
