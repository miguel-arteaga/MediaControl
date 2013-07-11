package mx.mjkhajl.media.control.common;

import static mx.mjkhajl.media.control.common.EventType.*;
import java.util.HashSet;
import java.util.Set;


public enum SourceControl {
	LEFT_BUTTON,
	RIGHT_BUTTON,
	CENTER_BUTTON,
	TOUCH_PANE( ACTION_MOVE ),
	SCROLL_PANE( ACTION_MOVE );
	
	private Set<EventType> handles = new HashSet<EventType>();
	
	private SourceControl( EventType... handles ){
		
		for( EventType type: handles ){
			this.handles.add( type );
		}
	}
	
	public boolean handles( EventType type ){
		
		return this.handles.contains( type );
	}
}
