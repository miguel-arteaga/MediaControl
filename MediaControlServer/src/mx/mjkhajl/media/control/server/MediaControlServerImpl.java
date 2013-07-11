package mx.mjkhajl.media.control.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import mx.mjkhajl.media.control.server.handler.RequestHandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Miguel Arteaga
 * 
 */
@Component
public class MediaControlServerImpl implements MediaControlServer {

	@Value("9999")
	private Integer port;

	private Boolean started = false;

	@Override
	public void start() {

		System.out.println("start");

		ServerSocket srvrSckt = null;
		try {
			srvrSckt = new ServerSocket(port);

			started = true;

			while (started) {

				try {

					Socket socket = srvrSckt.accept();
					RequestHandler handler = new RequestHandler(socket);
					new Thread(handler).start();

				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		finally {
			
			if( srvrSckt != null ){

				try {
					srvrSckt.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void stop() {

		System.out.println("stop");
		started = false;
	}
}
