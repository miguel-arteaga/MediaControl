package mx.mjkhajl.media.control.common.event;

import java.io.Serializable;

import mx.mjkhajl.media.control.common.EventType;
import mx.mjkhajl.media.control.common.SourceControl;

public class Event implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private SourceControl source;
	private EventType type;
	
	public Event(SourceControl source, EventType type) {
		super();
		this.source = source;
		this.type = type;
	}
	
	public SourceControl getSource() {
		return source;
	}
	public void setSource(SourceControl source) {
		this.source = source;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
}
