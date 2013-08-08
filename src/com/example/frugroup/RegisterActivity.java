package com.example.frugroup;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity {
	
	EditText textUser, textPass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.landing_screen);
		
		textUser = (EditText) findViewById(R.id.nameHolder);
        textPass = (EditText) findViewById(R.id.passwordHolder);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	public void onRegister(View view) {
    	String userName = textUser.getText().toString();
    	String userPass = textPass.getText().toString();
    	UserData entry = new UserData(RegisterActivity.this);
    	entry.open();
    	boolean valid = entry.validateRegister(userName);
    	if (valid){
    		entry.createRegisterEntry(userName, userPass);
    		entry.close();
        	
        	Bundle localUserBundle = new Bundle();
        	localUserBundle.putString("localUser", userName);
        	
        	Intent intentRegister = new Intent(this, HomeActivity.class);
        	intentRegister.putExtras(localUserBundle);
        	startActivity(intentRegister);
    	}  	
    }
}
