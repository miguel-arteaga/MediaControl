package mx.mjkhajl.media.control.server.handler;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import mx.mjkhajl.media.control.common.EventType;
import mx.mjkhajl.media.control.common.SourceControl;
import mx.mjkhajl.media.control.common.event.Event;
import mx.mjkhajl.media.control.common.event.MoveEvent;

import com.google.gson.Gson;

public class RequestHandler implements Runnable {

	private Socket socket;

	private static Robot robot;

	private static Double MAX_X;
	private static Double MAX_Y;

	private static int eventCounter = 0;

	static {

		try {

			robot = new Robot();

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

			MAX_X = screenSize.getWidth();
			MAX_Y = screenSize.getHeight();

			System.out.println("screensize: " + MAX_X + "," + MAX_Y);
		} catch (AWTException e) {

			e.printStackTrace();
			System.exit(0);
		}
	}

	private Gson gson;

	public RequestHandler(Socket socket) {
		this.socket = socket;
		this.gson = new Gson();
	}

	@Override
	public void run() {

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String eventString;

			while ((eventString = reader.readLine()).length() != 0) {

				System.out.println("Event received: " + eventCounter++ + " '" + eventString + "'");

				Event event = gson.fromJson(eventString, Event.class);

				if (EventType.ACTION_MOVE.equals(event.getType())) {

					event = gson.fromJson(eventString, MoveEvent.class);
				}

				handle(event);
			}

		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

			try {

				if (reader != null) {

					reader.close();
				}
				socket.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}
	
	private static EventType lastEventType=null;
	
	private void handle(Event event) {

		switch (event.getType()) {
			case ACTION_DOWN:
				if (!SourceControl.TOUCH_PANE.equals(event.getSource())) {
					robot.mousePress(resolveButtonMask(event.getSource()));
				}
				break;
			case ACTION_UP:
				if (!SourceControl.TOUCH_PANE.equals(event.getSource())) {
					robot.mouseRelease(resolveButtonMask(event.getSource()));
				} else if( EventType.ACTION_DOWN.equals( lastEventType ) ){
					robot.mousePress(resolveButtonMask(event.getSource()));
					robot.mouseRelease(resolveButtonMask(event.getSource()));
				}
				break;
			case ACTION_MOVE:
				MoveEvent moveEvent = (MoveEvent) event;
				mouseMove(moveEvent.getDeltaX(), moveEvent.getDeltaY());
				break;
			default:
		}
		lastEventType = event.getType();
	}

	private void mouseMove(Float deltaX, Float deltaY) {

		Integer x;
		Integer y;

		Point mousePos = MouseInfo.getPointerInfo().getLocation();

		x = (int) (mousePos.getX() + deltaX);
		y = (int) (mousePos.getY() + deltaY);

		System.out.println("MOUSE_POS (" + mousePos.getX() + "," + mousePos.getY() + ") ---> " + x + "," + y);

		robot.mouseMove(x, y);
	}

	protected int resolveButtonMask(SourceControl source) {
		switch (source) {
			case LEFT_BUTTON:
			case TOUCH_PANE:
				return InputEvent.BUTTON1_MASK;
			case RIGHT_BUTTON:
				return InputEvent.BUTTON3_MASK;
			case CENTER_BUTTON:
				return InputEvent.BUTTON2_MASK;
			default:
		}
		return -1;
	}
}
