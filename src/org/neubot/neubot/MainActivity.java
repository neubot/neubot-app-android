package org.neubot.neubot;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;
import org.neubot.neubot.swig.*;

public class MainActivity extends Activity {
	
	Button startB;
	Button stopB;
	
	Poller poller;
	EchoServer echoServer;
	
	Runnable echoServerRunnable= new Runnable() {
        public void run() {
        	Log.d("Loop" , "Begin");
        	poller.loop();
        	Log.d("Loop" , "End");
        }
	};
        
     Thread echoServerThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
	
		/*if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}*/
		
		System.loadLibrary("neubotjava");  /* XXX */
		
		startB = (Button)findViewById(R.id.startB);
		startB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("Main" , "Start button pressed"); 
				if(echoServerThread == null || !echoServerThread.isAlive())
				{
					echoServerThread = new Thread(echoServerRunnable, "EchoServerThread");
					echoServerThread.start();
				}
				startB.setBackgroundColor(Color.argb(255, 0, 255, 0));
				stopB.setBackgroundColor(Color.argb(255, 150, 150, 150));
			}
		});
		
		stopB = (Button)findViewById(R.id.stopB);
		stopB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				poller.break_loop();
				Log.d("Main" , "Stop button pressed");
				startB.setBackgroundColor(Color.argb(255, 150, 150, 150));
				stopB.setBackgroundColor(Color.argb(255, 255, 0, 0));
				
				
			}
		});
		
		poller = new Poller();
		echoServer = new EchoServer(poller , 0 , "0.0.0.0" , "12345");
			
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	/*public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}*/

}
