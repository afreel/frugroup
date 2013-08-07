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
    	Intent intent = new Intent(this, HomeActivity.class);
    	startActivity(intent);
    }
    
    public void onRegister(View view) {
    	String name = textUser.getText().toString();
    	String pass = textPass.getText().toString();
    	Log.d("Before","-----------------------------------------");
    	UserData entry = new UserData(LoginActivity.this);
    	entry.open();
    	entry.createEntry(name, pass);
    	entry.close();
    	Log.d("AFTER","000000000000000000000000000000000000000000000");
    	Intent intent = new Intent(this, RegisterActivity.class);
    	startActivity(intent);
    }
    
}
