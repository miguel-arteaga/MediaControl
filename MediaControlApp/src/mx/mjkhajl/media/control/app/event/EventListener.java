package mx.mjkhajl.media.control.app.event;

import mx.mjkhajl.media.control.app.event.factory.EventFactory;
import mx.mjkhajl.media.control.app.util.Logger;
import mx.mjkhajl.media.control.common.SourceControl;
import mx.mjkhajl.media.control.common.event.Event;
import android.view.MotionEvent;
import android.view.View;

public class EventListener implements View.OnTouchListener {

	private static final Logger log = new Logger(EventListener.class);

	private static final EventQueue queue = EventQueue.getInstance();

	private static final EventFactory factory = new EventFactory();

	private SourceControl sourceControl;

	public EventListener(SourceControl sourceControl) {
		this.sourceControl = sourceControl;
	}

	@Override
	public boolean onTouch(View v, MotionEvent motionEvent) {

		log.d("onTouch: " + sourceControl);

		Event event = factory.buildEvent(motionEvent, sourceControl);
		
		if( event != null ){
		
			queue.add(event);
			return true;
		}
		return false;
	}
}
