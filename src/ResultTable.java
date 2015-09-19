
public class ResultTable {

	private String event;
	private int totalQueue;
	private double globalTime;
	private double[] queueSize;
	
	public ResultTable(int queueSize) 
	{	
		this.queueSize =  new double[queueSize+1];
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

	public double getGlobalTime() {
		return globalTime;
	}

	public void setGlobalTime(double globalTime) {
		this.globalTime = globalTime;
	}

	public double[] getQueueSize() {
		return queueSize;
	}

	public void setQueueSize(double[] queueSize) {
		this.queueSize = queueSize;
	}
	
	public double getQueueValue(int index){
		return this.queueSize[index];
	}
	public void setQueueValue(double value, int index){
		this.queueSize[index] = value;
	}
	
	@Override
	public String toString(){
		String q = "";
		for (int i = 0; i < queueSize.length; i++) {
			String format = String.format("%.2f", queueSize[i]);
			q += "|" + format; 
		}
		return "EV:" + this.event + " F:" + this.totalQueue + " T" + String.format("%.2f", this.globalTime) + " Q:"+q;
	}
}
