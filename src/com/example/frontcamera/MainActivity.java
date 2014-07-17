package com.example.frontcamera;


import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity {

	private boolean wasExecuted = false;
    private Intent mainActivity;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		setContentView(R.layout.activity_main);
	}	
	
    public void init()
    {
    	mainActivity = new Intent(getApplicationContext(), CameraActivity.class);
        wasExecuted = false;
    }

    public void proxima(View view){
        
    	if(!wasExecuted){
    	    wasExecuted = true;
            startActivity(mainActivity);
        }
    }
    
    public void onBackPressed(){
 	   Log.i("HA", "Finishing");
 	   Intent intent = new Intent(Intent.ACTION_MAIN);
 	   intent.addCategory(Intent.CATEGORY_HOME);
 	   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
 	   startActivity(intent);
 }

}
