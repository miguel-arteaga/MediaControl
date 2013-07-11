package mx.mjkhajl.media.control.app.event;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;

import mx.mjkhajl.media.control.app.util.Logger;
import mx.mjkhajl.media.control.common.event.Event;

import com.google.gson.Gson;

public class EventDispatcher {

	private static final Logger log = new Logger(EventDispatcher.class);

	private static final String HOST = "192.168.0.97";

	private static final Integer PORT = 9999;

	private Gson gson = new Gson();

	private Socket socket;

	private Writer writer;
	
	public EventDispatcher() {
		writer = null;
		socket = null;
	}
	
	public synchronized void start()  throws IOException{
		log.d( "socket opened" );
		
		socket = new Socket();
		socket.connect(new InetSocketAddress(HOST, PORT), 200);

		writer = new OutputStreamWriter(socket.getOutputStream());
	}

	public synchronized void dispatch(Event event) throws Exception {

		gson.toJson(event, writer);
		writer.write('\n');
		writer.flush();
	}

	public synchronized void close() {
		try {
			if( writer != null ){
				writer.write( "\n" );
				writer.flush();
				writer.close();
			}
			if( socket != null ){
				socket.close();
				log.d( "socket closed" );
			}
		} catch (IOException e1) {

			log.e("Error closing socket", e1);
		}
	}
}
