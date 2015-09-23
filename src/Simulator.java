import java.util.ArrayList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Simulator {

	private ArrayList<Scheduler> schedQueue = new ArrayList<Scheduler>();
	private ArrayList<ResultTable> result = new ArrayList<ResultTable>();
	int servers;
	int capacity;
	int queue = 0;
	double globalTime = 0f;
	int eventCount = 1;
	int outputCount = 0;

	public void run(int maxCapacity, int maxServers, int minArrivalCustomer, int maxArrivalCustomer,
			int minAttendanceCustomer, int maxAttendanceCustomer,
			double finalTime) {
		capacity = maxCapacity;
		this.servers = maxServers;

		//Resultado para tempo 0 é adicionado manualmente
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
			
			if(sc.getTime() > finalTime) 
				break;
			
			globalTime = sc.getTime();
			
			ResultTable res = new ResultTable(maxCapacity);
			
			res.setEvent(sc.getEventNumber()+sc.getEventType().toString());
			res.setGlobalTime(globalTime);
			
			result.add(res);
			
			//se evento for chegada executa evento chegada
			if(sc.getEventType() == EventType.CH) {
				runArrival(minArrivalCustomer, maxArrivalCustomer, minAttendanceCustomer, maxAttendanceCustomer);
			} 
			else { // senao executa saida
				outputCount++;
				runOutput(minAttendanceCustomer, maxAttendanceCustomer);
			}
			res.setTotalQueue(queue);
		}
	}
	
	//calcula as probabilides marginais de cada fila
	public double[] getProbabilities() {
		double probabilities[] = new double[capacity+1];
		ResultTable res = result.get(result.size()-1);
		for (int i = 0; i < res.getQueueSize().length; i++) {
			probabilities[i] = res.getQueueValue(i) / res.getGlobalTime();
		}
		
		return probabilities;
	}
	
	//vazao
	public double getFlow() {
		double d = 0;
		double[] probabilities = this.getProbabilities(); 
		for (int i = 0; i < probabilities.length; i++) {
			d += probabilities[i] * (min(i, servers) / (outputCount/globalTime) );
		}
		
		return d;
	}
	
	//utilizacao
	public double getUtilization() {
		double u = 0;
		double[] probabilities = this.getProbabilities();
		for (int i = 0; i < probabilities.length; i++) {
			u += probabilities[i] * ( min(i, servers) / servers);
		}
		return u;
	}
	
	//populacao
	public double getPopulation() {
		double n = 0;
		double[] probabilities = this.getProbabilities();
		for (int i = 0; i < probabilities.length; i++) {
			n += probabilities[i] * i;
		}
		
		return n;
	}
	
	//tempo resposta
	public double getResponseTime() {
		return this. getPopulation() / this.getFlow();
	}
	
	private double min(int j, int c) {
		if(j < c)
			return j;
		return c;
	}
	
	//remove e retorna do escalonador o agendamento de menor tempo
	private Scheduler getMinScheduler() {
		Scheduler min = new Scheduler(EventType.CH, 0, Double.MAX_VALUE, 0f);
		int removeIndex = -1;
		for (int i = 0; i < schedQueue.size(); i++) {
			Scheduler sched = schedQueue.get(i);
			if (sched.getTime() < min.getTime()) {
				min = sched;
				removeIndex = i;
			}
		}
		if(min.getTime() == Double.MAX_VALUE) {
			//gerar uma excecao personalizada caso nao encontre alguem no escalonador
			throw new NotImplementedException();
		}
		schedQueue.remove(removeIndex);
		return min;
	}

	//Contabiliza tempos
	private void countingTime() {
		ResultTable currentState = result.get(result.size()-1);
		ResultTable beforeState = result.get(result.size()-2);
		double diffTime = currentState.getGlobalTime() - beforeState.getGlobalTime(); 
		int queueIndex = beforeState.getTotalQueue();
		
		for (int i = 0; i < currentState.getQueueSize().length; i++) {
			if(i == queueIndex){
				currentState.setQueueValue(diffTime + beforeState.getQueueValue(queueIndex), queueIndex);
			} else {
				double val = beforeState.getQueueValue(i); 
				currentState.setQueueValue(val, i);
			}
		}
	}
	
	//algoritmo de chegada
	private void runArrival(int minArrival, int maxArrival, int minOutput, int maxOutput) {
		countingTime();
		if(queue < capacity){
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
		double sortition = generatePseudoRandom(minArrival, maxArrival);
		double time = globalTime + sortition;
		Scheduler arrival = new Scheduler(EventType.CH, eventCount, time, sortition);
		schedQueue.add(arrival);
		eventCount++;
	}

	//agenda saida
	private void scheduleOutput(int minOutput, int maxOutput){
		double sortition = generatePseudoRandom(minOutput, maxOutput);
		double time = globalTime + sortition;
		Scheduler output = new Scheduler(EventType.SA, eventCount, time, sortition);
		schedQueue.add(output);
		eventCount++;
	}
	
	//gera um pseudo aleatorio	
	private double generatePseudoRandom(double init, double finish) {
		double seed = Math.random();
		return (double) (((finish - init) * seed) + init);
	}
	
	//tabela de resultado
	public ArrayList<ResultTable> getResult(){
		return result;
	}
	
}	