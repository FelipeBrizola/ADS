import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Simulator {

	public ArrayList<Scheduler> schedQueue = new ArrayList<Scheduler>();
	public ArrayList<ResultTable> result = new ArrayList<ResultTable>();
	int servers = 1;
	int maxCapacity;
	int queue = 0;
	float globalTime = 0f;
	int eventCount = 1;
	

	public void run(int maxCapacity, int minArrivalCustomer, int maxArrivalCustomer,
			int minAttendanceCustomer, int maxAttendanceCustomer,
			float finalTime) {
		
		this.maxCapacity = maxCapacity;

		ResultTable initial = new ResultTable(maxCapacity);
		initial.setEvent("-");
		initial.setGlobalTime(0f);
		initial.setTotalQueue(0);
		for (int i = 0; i < initial.getQueueSize().length; i++) {
			initial.setQueueValue(0, i);
		}
		result.add(initial);
		
		//Primeira chegada agendada na mao 
		schedQueue.add(new Scheduler(EventType.CH, eventCount, minArrivalCustomer, 0f));
		eventCount++;
		
		//Simulacao dentro do tempo maximo
		while(globalTime < finalTime) {
			Scheduler sc = getMinScheduler();
			
			globalTime = sc.getTime();
			
			ResultTable res = new ResultTable(maxCapacity);
			
			res.setEvent(sc.getEventNumber()+sc.getEventType().toString());
			res.setGlobalTime(globalTime);
			
			
			
			if(sc.getEventType() == EventType.CH) {
				runArrival(minArrivalCustomer, maxArrivalCustomer, minAttendanceCustomer, maxAttendanceCustomer);
				res.setTotalQueue(queue);
			} 
			else
			{
				runOutput(minAttendanceCustomer, maxAttendanceCustomer);
				res.setTotalQueue(queue);
			}
			
			result.add(res);
		}
	}
	
	private Scheduler getMinScheduler() {
		Scheduler min = new Scheduler(EventType.CH, 0, Float.MAX_VALUE, 0f);
		int removeIndex = -1;
		for (int i = 0; i < schedQueue.size(); i++) {
			Scheduler sched = schedQueue.get(i);
			if (sched.getTime() < min.getTime()) {
				min = sched;
				removeIndex = i;
			}
		}
		if(min.getTime() == Float.MAX_VALUE) {
			//gerar uma excecao personalizada caso nao encontre alguem no escalonador
			throw new NotImplementedException();
		}
		schedQueue.remove(removeIndex);
		return min;
	}

	private float getMinTime(EventType event) {
		// marcar como ja lido ou trocar fila por lista e remover
		float min = schedQueue.get(schedQueue.size()-1).getTime();
		for (Scheduler sched : schedQueue) {
			if (sched.getTime() < min && event == sched.getEventType())
				min = sched.getTime();
		}
		return min;
	}
	
	//Contabiliza tempos
	private void countingTime() {
		ResultTable currentState = result.get(result.size()-1);
				ResultTable beforeState = result.get(result.size()-2);				
		float diffTime = currentState.getGlobalTime() - beforeState.getGlobalTime(); 
		int queueIndex = beforeState.getTotalQueue();
		currentState.setQueueValue(diffTime + beforeState.getQueueValue(queueIndex), queueIndex);
	}
	
	//algoritmo de chegada
	private void runArrival(int minArrival, int maxArrival, int minOutput, int maxOutput) {
		countingTime();
		if(queue < maxCapacity){
			queue++;
			if(queue <= servers) {
				scheduleOutput(minOutput, maxOutput);
			}
		}
		scheduleArrival(minArrival, maxArrival);
	}
	
	//algoritmo de saida
	private void runOutput(int minOutput, int maxOutput) {
		countingTime();
		queue--;
		if(queue >= servers){
			scheduleOutput(minOutput, maxOutput);
		}
	}
	
	//agenda chegada
	private void scheduleArrival(int minArrival, int maxArrival){
		float sortition = generatePseudoRandom(minArrival, maxArrival);
		float time = globalTime + sortition;
		Scheduler arrival = new Scheduler(EventType.CH, eventCount, time, sortition);
		schedQueue.add(arrival);
		eventCount++;
	}

	//agenda saida
	private void scheduleOutput(int minOutput, int maxOutput){
		float sortition = generatePseudoRandom(minOutput, maxOutput);
		float time = globalTime + sortition;
		Scheduler output = new Scheduler(EventType.SA, eventCount, time, sortition);
		schedQueue.add(output);
		eventCount++;
	}
	
	//gera um pseudo aleatorio

	float[] pseudo = {0.8f, 0.2f, 0.4f, 0.7f, 0.5f, 0.3f };  	
	
	private float generatePseudoRandom(float init, float finish) {
//		double seed = Math.random();
//		return (float) (((finish - init) * seed) + init);
		float val = 0f;
		for(int i = 0; i < pseudo.length; i++){
			if(pseudo[i] > 0) {
				val = pseudo[i];
				pseudo[i] = -1f;
			}
		}
		return val;
	}
	
	public ArrayList<ResultTable> getResult(){
		return result;
	}
	
}