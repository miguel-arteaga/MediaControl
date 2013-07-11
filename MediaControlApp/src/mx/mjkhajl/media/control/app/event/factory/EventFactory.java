package mx.mjkhajl.media.control.app.event.factory;

import mx.mjkhajl.media.control.app.util.Logger;
import mx.mjkhajl.media.control.common.EventType;
import mx.mjkhajl.media.control.common.SourceControl;
import mx.mjkhajl.media.control.common.event.Event;
import mx.mjkhajl.media.control.common.event.MoveEvent;
import android.view.InputEvent;
import android.view.MotionEvent;

public class EventFactory {

	private static final Logger log = new Logger(EventFactory.class);

	private static Float lastX = -1f;
	private static Float lastY = -1f;

	public Event buildEvent(InputEvent event, SourceControl source) {

		if (event instanceof MotionEvent) {

			return buildMotionEvent((MotionEvent) event, source);
		}

		return null;
	}

	public Event buildMotionEvent(MotionEvent motionEvent, SourceControl source) {

		switch (motionEvent.getAction()) {
			case MotionEvent.ACTION_DOWN:
				return new Event(source, EventType.ACTION_DOWN);

			case MotionEvent.ACTION_MOVE:
				if (source.handles(EventType.ACTION_MOVE)) {
					return buildMoveEvent(motionEvent, source);
				}
				break;
			case MotionEvent.ACTION_UP:
				lastX = -1f;
				lastY = -1f;
				return new Event(source, EventType.ACTION_UP);
		}

		return null;
	}

	public Event buildMoveEvent(MotionEvent event, SourceControl source) {
		
		if( lastX == -1f && lastY == -1f ){
			
			lastX = event.getX();
			lastY = event.getY();
			
			return null;
		}
		
		Float deltaX = event.getX() - lastX;
		Float deltaY = event.getY() - lastY;

		lastX = event.getX();
		lastY = event.getY();

		log.d("last(" + lastX + "," + lastY + ") current(" + event.getX() + "," + event.getY() + ")");

		return new MoveEvent(source, deltaX, deltaY);
	}
}
