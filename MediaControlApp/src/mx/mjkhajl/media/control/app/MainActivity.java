package mx.mjkhajl.media.control.app;

import mx.mjkhajl.media.control.app.event.EventListener;
import mx.mjkhajl.media.control.app.event.EventQueuePoller;
import mx.mjkhajl.media.control.common.SourceControl;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		findViewById(R.id.left_button).setOnTouchListener(new EventListener(SourceControl.LEFT_BUTTON));
		findViewById(R.id.center_button).setOnTouchListener(new EventListener(SourceControl.CENTER_BUTTON));
		findViewById(R.id.right_button).setOnTouchListener(new EventListener(SourceControl.RIGHT_BUTTON));
		findViewById(R.id.touch_pane).setOnTouchListener(new EventListener(SourceControl.TOUCH_PANE));
		
		// start the poller
		new Thread( new EventQueuePoller() ).start();
	}
}
