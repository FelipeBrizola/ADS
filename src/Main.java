
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Simulator s = new Simulator();
		
		s.run(5, 2, 3, 3, 4, 100);
		System.out.println("Evento   | Fila   | Tempo   ");
		for(ResultTable res : s.getResult()){
			System.out.println(res.getEvent()+ "   " + res.getTotalQueue() + "   " +  res.getGlobalTime());
		}
	}

}
