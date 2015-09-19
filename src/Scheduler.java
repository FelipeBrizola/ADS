
public class Scheduler {

	private int eventNumber;
	private EventType eventType;
	private double time;
	private double sortition;
	
	public Scheduler(EventType eventType, int eventNumber, double time, double sortition){
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

	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public double getSortition() {
		return sortition;
	}
	public void setSortition(double sortition) {
		this.sortition = sortition;
	}
}
