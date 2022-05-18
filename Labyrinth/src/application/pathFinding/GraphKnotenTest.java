package application.pathFinding;

public class GraphKnotenTest {

	public static void main(String[] args) {
		// Graph aufbauen
		GraphKnoten kA=new GraphKnoten("A");
		GraphKnoten kB=new GraphKnoten("B");
		GraphKnoten kC=new GraphKnoten("C");
		GraphKnoten kD=new GraphKnoten("D");
		GraphKnoten kE=new GraphKnoten("E");
		GraphKnoten kF=new GraphKnoten("F");
		kA.addNachbar(kB);
		kA.addNachbar(kC);
		kB.addNachbar(kC);
		kC.addNachbar(kD);
		kC.addNachbar(kE);
		kE.addNachbar(kF);

		// Zugriff testen
		System.out.println("Nachbarn von " + kB + ":");
		for (GraphKnoten n : kB.getNachbarn()) {
			System.out.println(n);
		}

		// Breitensuche testen
		// ToDo d)
		System.out.println("Pfad von A nach F:"+BreitensucheGraph.findePfad(kA,kF));
	}

}
