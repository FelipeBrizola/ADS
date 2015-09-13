import java.util.LinkedList;
import java.util.Queue;

public class Simulator {

	public Queue<Scheduler> schedQueue = new LinkedList<Scheduler>();
	int servers = 1;
	int queue = 0;
	float globalTime = 0f;
	int eventCount = 1;

	public void run(int maxCapacity, int minArrivalCustomer, int maxArrivalCustomer,
			int minAttendanceCustomer, int maxAttendanceCustomer,
			float finalTime) {
<<<<<<< HEAD
=======

		
>>>>>>> fa35ff42fb07302b4d41553603045bb63c818366
	}

	private float getMinTime(String event) {
		// marcar como ja lido ou trocar fila por lista e remover
		float min = schedQueue.peek().getTime();
		for (Scheduler sched : schedQueue) {
			if (sched.getTime() < min && event == sched.getEvent())
				min = sched.getTime();
		}
		return min;
	}
	
	private void arrival(int minArrival, int maxArrival, int minOutput, int maxOutput) {
		//todo contabilizar tempo
		if(queue < servers){
			queue++;
			if(queue <= servers) {
				scheduleOutput(minOutput, maxOutput);
			}
		}
		scheduleArrival(minArrival, maxArrival);
	}
	
	private void output(int minOutput, int maxOutput) {
		//todo contabilizar tempo
		queue--;
		if(queue >= servers){
			scheduleOutput(minOutput, maxOutput);
		}
	}
	
	private void scheduleArrival(int minArrival, int maxArrival){
		String eventName = eventCount+"CH";
		float sortition = generatePseudoRandom(minArrival, maxArrival);
		float time = globalTime + sortition;
		Scheduler arrival = new Scheduler(eventName, time, sortition);
		schedQueue.add(arrival);
		eventCount++;
	}
	
	private void scheduleOutput(int minOutput, int maxOutput){
		String eventName = eventCount+"SA";
		float sortition = generatePseudoRandom(minOutput, maxOutput);
		float time = globalTime + sortition;
		Scheduler output = new Scheduler(eventName, time, sortition);
		schedQueue.add(output);
		eventCount++;
	}

	private float generatePseudoRandom(float init, float finish) {
		double seed = Math.random();
		return (float) (((finish - init) * seed) + init);
	}
	
}