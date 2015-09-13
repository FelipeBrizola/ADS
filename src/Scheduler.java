
public class Scheduler {

	private String event;
	private float time;
	private float sortition;
	
	public Scheduler(String event, float time, float sortition){
		this.event = event;
		this.time = time;
		this.sortition = sortition;
	}
	
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
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
