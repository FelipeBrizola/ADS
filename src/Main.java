
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Simulator s = new Simulator();
		
		//capacidade, servidores, minCh, maxCh, minSa, maxSa, simTime
		s.run(5, 2, 2, 3, 4, 5, 10);
		//s.run(args[0],args[1],args[2],args[3],args[4],args[5],args[6]);

		//s.run(3, 3, 0, 8, 0, 10, 100000);
//		System.out.println("Evento   | Fila   | Tempo   ");
//		for(ResultTable res : s.getResult()){
//			System.out.println(res);
//		}
		
		for (int i = 0; i < s.getProbabilities().length; i++) {
			System.out.print("P("+ i + "): ");
			System.out.printf("%.4f", s.getProbabilities()[i] * 100);
			System.out.print("% \n");
		}		
		System.out.print("D: ");
		System.out.printf("%.4f", s.getFlow());
		System.out.print(" cl/h \n");
		
		System.out.print("U: ");
		System.out.printf("%.4f", s.getUtilization() * 100);
		System.out.print("% \n");
		
		System.out.print("N: ");
		System.out.printf("%.4f", s.getPopulation());
		System.out.print(" cl \n");
		
		System.out.print("W: ");
		System.out.printf("%.4f", s.getResponseTime());
		System.out.print(" H \n");
		
		
	}

}
