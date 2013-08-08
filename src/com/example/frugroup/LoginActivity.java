package com.example.frugroup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity{

	EditText textUser, textPass;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        
        textUser = (EditText) findViewById(R.id.edit_user);
        textPass = (EditText) findViewById(R.id.edit_pass);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void onLogin(View view) {
    	
    	String userName = textUser.getText().toString();
    	String userPass = textPass.getText().toString();
    	UserData entry = new UserData(LoginActivity.this);
    	entry.open();
    	//WRITE METHOD in UserData to validate Login credentials
    	entry.close();
    	
    	Bundle localUserBundle = new Bundle();
    	localUserBundle.putString("localUser", userName);
    	
    	Intent intentRegister = new Intent(this, HomeActivity.class);
    	intentRegister.putExtras(localUserBundle);
    	startActivity(intentRegister);
    	
    }
    
    public void onRegister(View view) {
    	
    	Intent intent = new Intent(this, RegisterActivity.class);
    	startActivity(intent);
    }
    
}
