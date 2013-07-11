package mx.mjkhajl.media.control.app.event;

import mx.mjkhajl.media.control.app.util.Logger;
import mx.mjkhajl.media.control.common.event.Event;

public class EventQueuePoller implements Runnable {

	private static final Logger log = new Logger(EventQueuePoller.class);

	private static final EventQueue queue = EventQueue.getInstance();

	private boolean started = false;

	private static int eventCounter = 0;

	@Override
	public void run() {

		log.i("started polling");

		started = true;
		
		while (started) {

			poll();
			sleep(200);
		}

		System.out.println("Poller stoped");
	}
	
	private void poll(){

		Event event;
		EventDispatcher dispatcher = new EventDispatcher();

		try {

			if (queue.peek() != null) {

				dispatcher.start();

				while ((event = queue.poll()) != null) {

					log.d("dispatching: " + eventCounter++);
					dispatcher.dispatch(event);

				}
			}

		} catch (Exception e) {

			log.e("Error dispatching event: " + e, e);
		} finally {

			if (dispatcher != null) {

				dispatcher.close();
			}
		}
	}

	public void stop() {

		started = false;
		log.i("stopped polling");
	}

	public void sleep(int millis) {
		try {

			Thread.sleep(millis);
		} catch (InterruptedException e) {

			log.wtf("¿sleep interrupted?", e);
		}
	}
}
