
public class Main {

	public static void main(int[] args) {
		// TODO Auto-generated method stub

		Simulator s = new Simulator();
		
		//capacidade, servidores, minCh, maxCh, minSa, maxSa, simTime
		//s.run(2, 2, 2, 4, 3, 4, 100);

		//s.run(2, 2, 2, 4, 3, 4, 100000);
		s.run(args[0],args[1],args[2],args[3],args[4],args[5],args[6]);

		//s.run(3, 3, 0, 8, 0, 10, 100000);
		//System.out.println("Evento   | Fila   | Tempo   ");
		//for(ResultTable res : s.getResult()){
		//	System.out.println(res);
		//}
		
		for (int i = 0; i < s.getProbabilities().length; i++) {
			System.out.print("P("+ i + "): ");
			System.out.printf("%.2f", s.getProbabilities()[i] * 100);
			System.out.print("% \n");
		}
	}

}
