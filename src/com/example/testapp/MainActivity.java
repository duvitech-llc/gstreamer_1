package com.example.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.freedesktop.gstreamer.GStreamer;

public class MainActivity extends Activity {
    private native String nativeGetGStreamerInfo();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        try {
            GStreamer.init(this);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            finish(); 
            return;
        }

        
		setContentView(R.layout.activity_main);
		
        TextView tv = (TextView)findViewById(R.id.textview_info);
        tv.setText("Welcome to " + nativeGetGStreamerInfo() + " !");
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
    static {
        System.loadLibrary("gstreamer_android");
        System.loadLibrary("TestApp");
    }
}
