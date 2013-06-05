package mx.mjkhajl.media.control.server.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class TestRobot {
	
	public static void main(String[] args) {
		
		try {
			
			final Robot robot = new Robot(); 
			robot.delay( robot.getAutoDelay() );
			robot.mouseMove( 400, 650);
			robot.mousePress( InputEvent.BUTTON1_DOWN_MASK );
			robot.mouseRelease( InputEvent.BUTTON1_DOWN_MASK );
			robot.keyPress( KeyEvent.VK_B );
			robot.keyPress( KeyEvent.VK_A );
			robot.keyPress( KeyEvent.VK_K );
			robot.keyPress( KeyEvent.VK_A );
			robot.waitForIdle();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
	}
}
