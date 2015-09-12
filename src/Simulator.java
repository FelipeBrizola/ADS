import java.util.LinkedList;
import java.util.Queue;

public class Simulator {

	public Queue<Scheduler> schedQueue = new LinkedList<Scheduler>();

	public void run(int maxCapacity, int minArrivalCustomer, int maxArrivalCustomer,
			int minAttendanceCustomer, int maxAttendanceCustomer,
			float finalTime) {

	}

	public float getMinTime(String event) {
		// marcar como ja lido ou trocar fila por lista e remover
		float min = schedQueue.peek().getTime();
		for (Scheduler sched : schedQueue) {
			if (sched.getTime() < min && event == sched.getEvent())
				min = sched.getTime();
		}
		return min;
	}

	private float generatePseudoRandom(float init, float finish) {
		double seed = Math.random();
		return (float) (((finish - init) * seed) + init);
	}

}