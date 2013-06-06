package mx.mjkhajl.media.control.server;

import mx.mjkhajl.media.control.server.tray.MediaControlTrayIcon;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	private static final ApplicationContext context = new ClassPathXmlApplicationContext("mx/mjkhajl/media/control/server/resources/media-control-server-context.xml");
	
	public static void main(String[] args) {
		
		final MediaControlTrayIcon trayIcon = context.getBean( MediaControlTrayIcon.class );
		final MediaControlServer server = context.getBean( MediaControlServer.class );
		
		trayIcon.show();
		server.start();
	}
}
