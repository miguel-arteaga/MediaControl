package mx.mjkhajl.media.control.app.util;

import android.util.Log;

public class Logger{
	
	private final String tag;

	public Logger(Class<?> clazz) {
		super();
		this.tag = clazz.getName();
	}
	
	public int d( String msg ){
		return Log.d( tag, msg );
	}
	
	public int d( String msg, Throwable e ){
		return Log.d( tag, msg, e );
	}
	
	public int e( String msg ){
		return Log.e( tag, msg );
	}
	
	public int e( String msg, Throwable e ){
		return Log.e( tag, msg, e );
	}

	public int w( String msg ){
		return Log.w( tag, msg );
	}
	
	public int w( String msg, Throwable e ){
		return Log.w( tag, msg, e );
	}
	
	public int wtf( String msg ){
		return Log.wtf( tag, msg );
	}
	
	public int wtf( String msg, Throwable e ){
		return Log.wtf( tag, msg, e );
	}
	
	public int i( String msg ){
		return Log.i( tag, msg );
	}
	
	public int i( String msg, Throwable e ){
		return Log.i( tag, msg, e );
	}
	
	
}