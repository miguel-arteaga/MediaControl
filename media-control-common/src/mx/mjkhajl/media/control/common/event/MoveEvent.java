package mx.mjkhajl.media.control.common.event;

import mx.mjkhajl.media.control.common.EventType;
import mx.mjkhajl.media.control.common.SourceControl;

public class MoveEvent extends Event {

	private static final long serialVersionUID = 1L;

	private Float deltaX;

	private Float deltaY;

	public MoveEvent(SourceControl source, Float deltaX, Float deltaY) {
		super(source, EventType.ACTION_MOVE);

		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	public Float getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(Float deltaX) {
		this.deltaX = deltaX;
	}

	public Float getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(Float deltaY) {
		this.deltaY = deltaY;
	}
}
