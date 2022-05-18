package application.pathFinding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreitensucheGraph {
    public static List<GraphKnoten> findePfad(GraphKnoten startKnoten,
                                              GraphKnoten zielKnoten) {
        // Variable für den gefundenen kürzesten Pfad
        List<GraphKnoten> ergebnisPfad = new ArrayList<GraphKnoten>();
        Queue<GraphKnoten> q = new LinkedList<GraphKnoten>();
        q.add(startKnoten);

        // ToDo c)1: StartKnoten markieren (Methode setMarkiert verwenden)
        startKnoten.setMarkiert(true);

        while (!q.isEmpty() && ergebnisPfad.isEmpty()) {
            GraphKnoten aktuellerKnoten = q.remove();

            // ToDo c)2: Wenn es der gesuchte Knoten ist ...
            // (Name des aktuellen Knoten mit Name des zielKnoten vergleichen)
            if (aktuellerKnoten.getName().equals(zielKnoten.getName())) { // "true" ersetzen durch einen Vergleich (dummy wegen compilefehlern)

                // ToDo c)3: ... dann: ergebnisPfad = suchPfad des aktuellen Knotens
                //               plus der aktuelle Knoten selbst
                //	(Methoden ergebnisPfad.addAll(..) und ergebnisPfad.add(..) verwenden)
                ergebnisPfad.addAll(aktuellerKnoten.getSuchPfad());
                ergebnisPfad.add(aktuellerKnoten);

            } else {
                // sonst: Schleife über alle Nachbarn
                for (GraphKnoten nachbar : aktuellerKnoten.getNachbarn()) {

                    // ToDo c)4: Wenn Nachbar nicht markiert ist (Methode isMarkiert verwenden)
                    if (!nachbar.isMarkiert()) { // dummy wg compilefehlern

                        // ToDo c)5: dann füge den Nachbarn in die Schlange ein (Methode q.add(..) verwenden)
                        q.add(nachbar);

                        // ToDo c)6: und markiere den Nachbarn (Methode setMarkiert verwenden)
                        nachbar.setMarkiert(true);

                        // ToDo c)7: und füge zum Suchpfad des Nachbarn sowohl den Suchpfad des
                        // aktuellen Knotens hinzu als auch den aktuellen Knoten selbst:
                        // nachbar.getSuchPfad().addAll(...) / nachbar.getSuchPfad().add(..)
                        nachbar.getSuchPfad().addAll(aktuellerKnoten.getSuchPfad());
                        nachbar.getSuchPfad().add(aktuellerKnoten);

                    }
                }
            }
        }
        return ergebnisPfad;
    }
}
