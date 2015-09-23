public class Main {

	/**
	 * @author Eduardo Curcino, Felipe Brizola
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] num = new int[args.length];
		for (int i = 0; i < args.length; i++) {
			num[i] = Integer.parseInt(args[i]);
		}

		Simulator s = new Simulator();

		//capacidade, servidores, minCh, maxCh, minSa, maxSa, simTime
		//caso executar pelo eclipse trocar num[x] pelo valor(int) desejado
		s.run(num[0], num[1], num[2], num[3], num[4], num[5], num[6]);

		// Exibe a tabela de saida dos resultados
		// System.out.println("Evento   | Fila   | Tempo   ");
		// for(ResultTable res : s.getResult()){
		// System.out.println(res);
		// }

		for (int i = 0; i < s.getProbabilities().length; i++) {
			System.out.print("P(" + i + "): ");
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
