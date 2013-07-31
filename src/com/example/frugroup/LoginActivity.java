package com.example.frugroup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
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
    	Intent intent = new Intent(this, RegisterActivity.class);
    	startActivity(intent);
    }
    
}
