package mx.mjkhajl.media.control.server;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Miguel Arteaga
 *
 */
@Component
public class MediaControlServerImpl implements MediaControlServer{
	
	@Override
	public void start() {
		
		System.out.println( "start" );
	}
	
	@Override
	public void stop() {
		
		System.out.println( "stop" );
	}
}
