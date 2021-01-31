package com.example.skeleton;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Main extends Activity {
	private Globals globals;

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		globals=(Globals)((globalcontainer)getApplicationContext()).globals;
		super.onCreate(savedInstanceState);
		if (!isTaskRoot()) { finish(); return; } // fixes Market double-start
	}
	@Override
	public void onResume() {
		super.onResume();
		setContentView(R.layout.main);
		TextView tv;
		tv=(TextView)findViewById(R.id.helloworld);
		tv.setText("Hello world, no ant!");
		Log.e(globals.DBG,"Hello log file!");
		tv.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					((TextView)v).setText("Clicked");
					Log.e(globals.DBG,"Clicked!");
				}
		});
	}
}
