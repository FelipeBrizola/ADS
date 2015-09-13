
public class ResultTable {

	private String event;
	private int totalQueue;
	private float globalTime;
	private float[] queueSize;
	
	public ResultTable(int queueSize) 
	{	
		this.queueSize =  new float[queueSize];
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public int getTotalQueue() {
		return totalQueue;
	}

	public void setTotalQueue(int totalQueue) {
		this.totalQueue = totalQueue;
	}

	public float getGlobalTime() {
		return globalTime;
	}

	public void setGlobalTime(float globalTime) {
		this.globalTime = globalTime;
	}

	public float[] getQueueSize() {
		return queueSize;
	}

	public void setQueueSize(float[] queueSize) {
		this.queueSize = queueSize;
	}
	
}
