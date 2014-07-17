package com.example.frontcamera;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;


public class CameraActivity extends Activity {

    static Camera mCamera;
    private Camera_preview mPreview;
    FrameLayout preview;
    
    public static int orientation;
	public static final int HORIZONTAL=0;
	public static final int VERTICAL=1;
	public int w,h;
	public int piroca;
    public TextView t;        	
    
    public Button btnVoltar;
	
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        getWindow().setFormat(PixelFormat.TRANSLUCENT);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        
	        setContentView(R.layout.cameraactivity);
	        w=h=0;
	        // Create an instance of Camera
	        mCamera = getCameraInstance();
	      
	        // Create our Preview view and set it as the content of our activity.
	        mPreview = new Camera_preview(this, mCamera);
	        preview = (FrameLayout) findViewById(R.id.camera_preview);
	        preview.addView(mPreview);
	       
	        t = (TextView)findViewById(R.id.textViewCountDown2);
	        
	        orientation=1;
	        	   
	    }
		
			    
		protected void onResume() {
			super.onResume();
		}
	    
	    private void TirarFoto(){
//	    	mCamera.takePicture(null, null, mPicture);
	        mCamera.takePicture(shutterCallback, null, mPicture);        
	    }
	    
	    ShutterCallback shutterCallback = new ShutterCallback() {
	    	public void onShutter() {
	    		Log.d("FOTO", "onShutter'd");
	    	}
		};
		
		PictureCallback rawCallback = new PictureCallback() {
			public void onPictureTaken(byte[] data, Camera camera) {
				Log.d("rawCallBack", "onPictureTaken - raw");
			}
		};	    
		    
		PictureCallback mPicture = new PictureCallback(){		
			public void onPictureTaken(byte[] data, Camera camera) {
//				Gozada.setImageResource(R.drawable.gozada1);
				releaseCamera();
			}
		};
		
	    private void releaseCamera() {
			 if (mCamera != null){
//				 Log.i("CAMERAACTIVITY","Release camera NULL");
				 mCamera.release();
				 mCamera = null;
		        }
		} 
	    
	    
	    @Override
	    public boolean dispatchKeyEvent(KeyEvent event) {
	        int action = event.getAction();
	        int keyCode = event.getKeyCode();
	        switch (keyCode) {
	            case KeyEvent.KEYCODE_VOLUME_UP:
	            	Log.i("BOTAO", "key event up");
	                if (action == KeyEvent.ACTION_DOWN) {
	                    //TODO
	                	TirarFoto();
	                }
	                return true;
	            case KeyEvent.KEYCODE_VOLUME_DOWN:
	            	Log.i("BOTAO", "key event down");
	                if (action == KeyEvent.ACTION_DOWN) {
	                    //TODO
	                	TirarFoto();
	                }
	                return true;
	            default:
	                return super.dispatchKeyEvent(event);
	        }
	    }    	    
	    
	    public static Camera getCameraInstance(){
	        Camera c = null;
	        try {
	        	c = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
	            //c = Camera.open(); // attempt to get a Camera instance
	        }
	        catch (Exception e){
	        	
	        }
	        return c;
	    }

	}