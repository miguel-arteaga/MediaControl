package mx.mjkhajl.media.control.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		final View leftButton = findViewById(R.id.left_button);
		final View centerButton = findViewById(R.id.center_button);
		final View rightButton = findViewById(R.id.right_button);

		leftButton.setOnClickListener(  logOnClickListener);
		centerButton.setOnClickListener(logOnClickListener);
		rightButton.setOnClickListener(logOnClickListener);
	}

	View.OnClickListener logOnClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Log.d("MainActivity", "touch" + v.getId() );
			System.out.println("touch" + v.getId());
		}
	};
}
