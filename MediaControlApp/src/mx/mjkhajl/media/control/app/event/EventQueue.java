package mx.mjkhajl.media.control.app.event;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import mx.mjkhajl.media.control.common.event.Event;

public class EventQueue {

	private static final EventQueue instance = new EventQueue();

	private final Queue<Event> queue;

	private EventQueue() {

		this.queue = new ConcurrentLinkedQueue<Event>();
	}

	public static EventQueue getInstance() {
		return instance;
	}

	public void add(Event event) {
		
		queue.add(event);
	}

	public Event poll() {

		return queue.poll();
	}

	public Event peek() {

		return queue.peek();
	}
}
