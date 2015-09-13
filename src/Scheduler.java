
public class Scheduler {

	private int eventNumber;
	private EventType eventType;
	private float time;
	private float sortition;
	
	public Scheduler(EventType eventType, int eventNumber, float time, float sortition){
		this.eventType = eventType;
		this.time = time;
		this.sortition = sortition;
		this.eventNumber = eventNumber;
	}
	
	public int getEventNumber() {
		return eventNumber;
	}
	
	public EventType getEventType() {
		return eventType;
	}

	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}
	public float getSortition() {
		return sortition;
	}
	public void setSortition(float sortition) {
		this.sortition = sortition;
	}
}
