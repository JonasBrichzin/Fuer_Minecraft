package application.pathFinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphKnoten {
	private String name;
	private Set<GraphKnoten> nachbarn = new HashSet<GraphKnoten>();
	private boolean markiert;
	private List<GraphKnoten> suchPfad = new ArrayList<GraphKnoten>();

	public List<GraphKnoten> getSuchPfad() {
		return suchPfad;
	}

	public boolean isMarkiert() {
		return markiert;
	}

	public void setMarkiert(boolean markiert) {
		this.markiert = markiert;
	}

	public GraphKnoten(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public Set<GraphKnoten> getNachbarn() {
		return nachbarn;
	}
	
	public void addNachbar(GraphKnoten nachbar) {
		nachbarn.add(nachbar);
		nachbar.nachbarn.add(this);
	}
}
