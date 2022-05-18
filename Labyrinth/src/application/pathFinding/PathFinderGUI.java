package application.pathFinding;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PathFinderGUI {

	private JFrame frame;
	private JTable table;
	private int colorClm = -1, colorRow = -1;
	private boolean lastSetStart = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		LabyrinthGenerieren maze=new LabyrinthGenerieren(21,21);
		maze.build();
		byte [][] labyrinth = maze.getMaze();
		System.out.println(labyrinth);

		System.exit(2);

		try {
			// Set cross-platform Java L&F (also called "Metal")
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PathFinderGUI window = new PathFinderGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PathFinderGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		table = new JTable();
		table.setShowGrid(false);

		// table.getDefaultRenderer(

		table.setDefaultRenderer(Integer.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				if ((Integer) value == 1) {
					setBackground(Color.WHITE);
					setForeground(Color.WHITE);
				} else if ((Integer) value == 2) {
					setBackground(Color.RED);
					setForeground(Color.RED);
				} else if ((Integer) value == 3 || (Integer) value == 4) {
					setBackground(Color.GREEN);
					setForeground(Color.GREEN);
				} else {
					setBackground(Color.DARK_GRAY);
					setForeground(Color.DARK_GRAY);
				}

				return this;
			}
		});

		table.setModel(new DefaultTableModel(
				new Object[][] {
						{ new Integer(1), new Integer(0), new Integer(1), new Integer(1), new Integer(1),
								new Integer(1), new Integer(1), new Integer(1), new Integer(1), new Integer(1),
								new Integer(0), new Integer(1), new Integer(1), new Integer(0), new Integer(1),
								new Integer(1), new Integer(1), new Integer(1), new Integer(1), new Integer(1),
								new Integer(1), new Integer(1), new Integer(0), new Integer(4), },
						{ new Integer(1), new Integer(1), new Integer(1), new Integer(0), new Integer(0),
								new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(
										1),
								new Integer(1), new Integer(1), new Integer(1), new Integer(1), new Integer(1),
								new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0),
								new Integer(0), new Integer(1), new Integer(1), new Integer(1), },
						{ new Integer(1), new Integer(0), new Integer(0), new Integer(0), new Integer(0),
								new Integer(1), new Integer(0), new Integer(1), new Integer(0), new Integer(1),
								new Integer(0), new Integer(0), new Integer(1), new Integer(0), new Integer(0),
								new Integer(0), new Integer(0), new Integer(1), new Integer(1), new Integer(1),
								new Integer(0), new Integer(1), new Integer(0), new Integer(0), },
						{ new Integer(1), new Integer(0), new Integer(1), new Integer(1), new Integer(1),
								new Integer(1), new Integer(0), new Integer(1), new Integer(0), new Integer(1),
								new Integer(0), new Integer(0), new Integer(1), new Integer(0), new Integer(1),
								new Integer(1), new Integer(1), new Integer(1), new Integer(0), new Integer(1),
								new Integer(0), new Integer(1), new Integer(0), new Integer(0), },
						{ new Integer(1), new Integer(0), new Integer(0), new Integer(0), new Integer(0),
								new Integer(1), new Integer(1), new Integer(1), new Integer(0), new Integer(1),
								new Integer(0), new Integer(0), new Integer(1), new Integer(0), new Integer(1),
								new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(1),
								new Integer(0), new Integer(1), new Integer(0), new Integer(0), },
						{ new Integer(1), new Integer(1), new Integer(1), new Integer(0), new Integer(0),
								new Integer(0), new Integer(1), new Integer(1), new Integer(0), new Integer(1),
								new Integer(1), new Integer(1), new Integer(1), new Integer(1), new Integer(1),
								new Integer(0), new Integer(0), new Integer(0), new Integer(1), new Integer(1),
								new Integer(0), new Integer(1), new Integer(1), new Integer(1), },
						{ new Integer(0), new Integer(0), new Integer(1), new Integer(0), new Integer(0),
								new Integer(0), new Integer(1), new Integer(0), new Integer(0), new Integer(0),
								new Integer(0), new Integer(1), new Integer(0), new Integer(0), new Integer(1),
								new Integer(0), new Integer(0), new Integer(0), new Integer(1), new Integer(0),
								new Integer(0), new Integer(0), new Integer(0), new Integer(1), },
						{ new Integer(0), new Integer(0), new Integer(1), new Integer(0), new Integer(1),
								new Integer(1), new Integer(1), new Integer(0), new Integer(0), new Integer(1),
								new Integer(1), new Integer(1), new Integer(0), new Integer(0), new Integer(1),
								new Integer(0), new Integer(1), new Integer(1), new Integer(1), new Integer(0),
								new Integer(0), new Integer(1), new Integer(1), new Integer(1), },
						{ new Integer(1), new Integer(1), new Integer(1), new Integer(0), new Integer(1),
								new Integer(0), new Integer(1), new Integer(0), new Integer(0), new Integer(1),
								new Integer(0), new Integer(0), new Integer(1), new Integer(1), new Integer(1),
								new Integer(0), new Integer(1), new Integer(0), new Integer(1), new Integer(0),
								new Integer(0), new Integer(1), new Integer(0), new Integer(0), },
						{ new Integer(1), new Integer(0), new Integer(0), new Integer(0), new Integer(1),
								new Integer(1), new Integer(1), new Integer(0), new Integer(0), new Integer(1),
								new Integer(0), new Integer(0), new Integer(1), new Integer(0), new Integer(0),
								new Integer(0), new Integer(1), new Integer(1), new Integer(1), new Integer(0),
								new Integer(0), new Integer(1), new Integer(0), new Integer(0), },
						{ new Integer(1), new Integer(0), new Integer(1), new Integer(1), new Integer(1),
								new Integer(0), new Integer(1), new Integer(0), new Integer(1), new Integer(1),
								new Integer(1), new Integer(1), new Integer(1), new Integer(0), new Integer(1),
								new Integer(1), new Integer(1), new Integer(0), new Integer(1), new Integer(0),
								new Integer(1), new Integer(1), new Integer(1), new Integer(1), },
						{ new Integer(1), new Integer(0), new Integer(1), new Integer(0), new Integer(1),
								new Integer(0), new Integer(1), new Integer(0), new Integer(0), new Integer(0),
								new Integer(1), new Integer(0), new Integer(1), new Integer(0), new Integer(1),
								new Integer(0), new Integer(1), new Integer(0), new Integer(1), new Integer(0),
								new Integer(0), new Integer(0), new Integer(1), new Integer(0), },
						{ new Integer(1), new Integer(0), new Integer(1), new Integer(1), new Integer(1),
								new Integer(1), new Integer(1), new Integer(1), new Integer(1), new Integer(1),
								new Integer(0), new Integer(1), new Integer(1), new Integer(0), new Integer(1),
								new Integer(1), new Integer(1), new Integer(0), new Integer(0), new Integer(0),
								new Integer(0), new Integer(0), new Integer(1), new Integer(0), },
						{ new Integer(1), new Integer(1), new Integer(1), new Integer(0), new Integer(0),
								new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(1),
								new Integer(1), new Integer(1), new Integer(1), new Integer(1), new Integer(1),
								new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(0),
								new Integer(0), new Integer(1), new Integer(1), new Integer(1), },
						{ new Integer(1), new Integer(0), new Integer(0), new Integer(0), new Integer(0),
								new Integer(0), new Integer(0), new Integer(1), new Integer(0), new Integer(1),
								new Integer(0), new Integer(0), new Integer(1), new Integer(0), new Integer(0),
								new Integer(0), new Integer(0), new Integer(1), new Integer(0), new Integer(1),
								new Integer(0), new Integer(1), new Integer(0), new Integer(0), },
						{ new Integer(1), new Integer(0), new Integer(1), new Integer(1), new Integer(1),
								new Integer(1), new Integer(0), new Integer(1), new Integer(0), new Integer(1),
								new Integer(0), new Integer(0), new Integer(1), new Integer(0), new Integer(1),
								new Integer(1), new Integer(1), new Integer(1), new Integer(0), new Integer(1),
								new Integer(0), new Integer(1), new Integer(0), new Integer(0), },
						{ new Integer(1), new Integer(0), new Integer(1), new Integer(0), new Integer(0),
								new Integer(1), new Integer(0), new Integer(1), new Integer(0), new Integer(1),
								new Integer(0), new Integer(0), new Integer(1), new Integer(0), new Integer(1),
								new Integer(0), new Integer(0), new Integer(0), new Integer(0), new Integer(1),
								new Integer(0), new Integer(1), new Integer(0), new Integer(0), },
						{ new Integer(1), new Integer(1), new Integer(1), new Integer(0), new Integer(0),
								new Integer(1), new Integer(1), new Integer(1), new Integer(0), new Integer(1),
								new Integer(1), new Integer(1), new Integer(1), new Integer(1), new Integer(1),
								new Integer(0), new Integer(0), new Integer(0), new Integer(1), new Integer(1),
								new Integer(0), new Integer(1), new Integer(1), new Integer(1), },
						{ new Integer(0), new Integer(0), new Integer(1), new Integer(0), new Integer(0),
								new Integer(0), new Integer(1), new Integer(0), new Integer(0), new Integer(0),
								new Integer(0), new Integer(1), new Integer(0), new Integer(0), new Integer(1),
								new Integer(0), new Integer(0), new Integer(0), new Integer(1), new Integer(0),
								new Integer(0), new Integer(0), new Integer(0), new Integer(1), },
						{ new Integer(0), new Integer(0), new Integer(1), new Integer(0), new Integer(1),
								new Integer(1), new Integer(1), new Integer(0), new Integer(0), new Integer(1),
								new Integer(1), new Integer(1), new Integer(0), new Integer(0), new Integer(1),
								new Integer(0), new Integer(1), new Integer(1), new Integer(1), new Integer(0),
								new Integer(0), new Integer(1), new Integer(1), new Integer(1), },
						{ new Integer(1), new Integer(1), new Integer(1), new Integer(0), new Integer(1),
								new Integer(0), new Integer(1), new Integer(0), new Integer(0), new Integer(1),
								new Integer(0), new Integer(0), new Integer(1), new Integer(1), new Integer(1),
								new Integer(0), new Integer(1), new Integer(0), new Integer(1), new Integer(0),
								new Integer(0), new Integer(1), new Integer(0), new Integer(0), },
						{ new Integer(1), new Integer(0), new Integer(0), new Integer(0), new Integer(1),
								new Integer(1), new Integer(1), new Integer(0), new Integer(0), new Integer(1),
								new Integer(0), new Integer(0), new Integer(1), new Integer(0), new Integer(0),
								new Integer(0), new Integer(1), new Integer(1), new Integer(1), new Integer(0),
								new Integer(0), new Integer(1), new Integer(0), new Integer(0), },
						{ new Integer(1), new Integer(0), new Integer(1), new Integer(1), new Integer(1),
								new Integer(0), new Integer(1), new Integer(0), new Integer(1), new Integer(1),
								new Integer(1), new Integer(1), new Integer(1), new Integer(0), new Integer(1),
								new Integer(1), new Integer(1), new Integer(0), new Integer(1), new Integer(0),
								new Integer(1), new Integer(1), new Integer(1), new Integer(1), },
						{ new Integer(3), new Integer(1), new Integer(1), new Integer(0), new Integer(0),
								new Integer(0), new Integer(1), new Integer(1), new Integer(1), new Integer(0),
								new Integer(1), new Integer(1), new Integer(1), new Integer(1), new Integer(1),
								new Integer(1), new Integer(1), new Integer(0), new Integer(1), new Integer(1),
								new Integer(1), new Integer(1), new Integer(1), new Integer(1), }, },
				new String[] { "New column", "New column", "New column", "New column", "New column", "New column",
						"New column", "New column", "New column", "New column", "New column", "New column",
						"New column", "New column", "New column", "New column", "New column", "New column",
						"New column", "New column", "New column", "New column", "New column", "New column" }) {
			Class[] columnTypes = new Class[] { Integer.class, Integer.class, Integer.class, Integer.class,
					Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class,
					Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class,
					Integer.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class,
					Integer.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(20);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
		table.getColumnModel().getColumn(5).setPreferredWidth(20);
		table.getColumnModel().getColumn(6).setPreferredWidth(20);
		table.getColumnModel().getColumn(7).setPreferredWidth(20);
		table.getColumnModel().getColumn(8).setPreferredWidth(20);
		table.getColumnModel().getColumn(9).setPreferredWidth(20);
		table.getColumnModel().getColumn(10).setPreferredWidth(20);
		table.getColumnModel().getColumn(11).setPreferredWidth(20);
		table.getColumnModel().getColumn(12).setPreferredWidth(20);
		table.getColumnModel().getColumn(13).setPreferredWidth(20);
		table.getColumnModel().getColumn(14).setPreferredWidth(20);
		table.getColumnModel().getColumn(15).setPreferredWidth(20);
		table.getColumnModel().getColumn(16).setPreferredWidth(20);
		table.getColumnModel().getColumn(17).setPreferredWidth(20);
		table.getColumnModel().getColumn(18).setPreferredWidth(20);
		table.getColumnModel().getColumn(19).setPreferredWidth(20);
		table.getColumnModel().getColumn(20).setPreferredWidth(20);
		table.getColumnModel().getColumn(21).setPreferredWidth(20);
		table.getColumnModel().getColumn(22).setPreferredWidth(20);
		table.getColumnModel().getColumn(23).setPreferredWidth(20);
		frame.getContentPane().add(table, BorderLayout.CENTER);

		int maxZeilen = table.getModel().getRowCount();
		int maxSpalten = table.getModel().getColumnCount();

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				Point p = evt.getPoint();
				colorRow = table.rowAtPoint(p);
				colorClm = table.columnAtPoint(p);
				if (evt.getButton() == MouseEvent.BUTTON1) {
					if ((Integer) table.getModel().getValueAt(colorRow, colorClm) == 1) {
						table.getModel().setValueAt(0, colorRow, colorClm);
					} else {
						table.getModel().setValueAt(1, colorRow, colorClm);
					}
				} else {
					if (lastSetStart) {
						for (int z = 0; z < maxZeilen; z++) {
							for (int s = 0; s < maxSpalten; s++) {
								if ((Integer) table.getModel().getValueAt(z, s) == 4
										|| (Integer) table.getModel().getValueAt(z, s) == 2) {
									table.getModel().setValueAt(1, z, s);
								}
							}
						}
						table.getModel().setValueAt(4, colorRow, colorClm);
						lastSetStart = false;
					} else {
						for (int z = 0; z < maxZeilen; z++) {
							for (int s = 0; s < maxSpalten; s++) {
								if ((Integer) table.getModel().getValueAt(z, s) == 3
										|| (Integer) table.getModel().getValueAt(z, s) == 2) {
									table.getModel().setValueAt(1, z, s);
								}
							}
						}
						table.getModel().setValueAt(3, colorRow, colorClm);
						lastSetStart = true;
					}
				}
			}
		});

		JButton btnNewButton = new JButton("Finde den Weg");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generateGraph(table.getModel());
			}
		});
		frame.getContentPane().add(btnNewButton, BorderLayout.SOUTH);

		frame.pack();

	}

	private void generateGraph(TableModel model) {
		int maxZeilen = model.getRowCount();
		int maxSpalten = model.getColumnCount();
		int startZ = -1;
		int startS = -1;
		int zielZ = -1;
		int zielS = -1;
		GraphKnoten[][] labyrinthKnoten = new GraphKnoten[maxZeilen][maxSpalten];
		for (int z = 0; z < maxZeilen; z++) {
			for (int s = 0; s < maxSpalten; s++) {
				int e = (Integer) model.getValueAt(z, s);
				if (e == 1 || e == 2 || e == 3 || e == 4) {
					if (e == 3) {
						startZ = z;
						startS = s;
					}
					if (e == 4) {
						zielZ = z;
						zielS = s;
					}
					model.setValueAt(1, z, s);
					// System.out.print("X");
					GraphKnoten tmp = labyrinthKnoten[z][s];
					if (tmp == null) {
						tmp = new GraphKnoten("Loc:" + s + "," + z);
						labyrinthKnoten[z][s] = tmp;
					}
					pruefeNachbar(labyrinthKnoten, z, s, z - 1, s, maxZeilen, maxSpalten);
					pruefeNachbar(labyrinthKnoten, z, s, z + 1, s, maxZeilen, maxSpalten);
					pruefeNachbar(labyrinthKnoten, z, s, z, s - 1, maxZeilen, maxSpalten);
					pruefeNachbar(labyrinthKnoten, z, s, z, s + 1, maxZeilen, maxSpalten);
				} else {
					// System.out.print(" ");
				}
			}
			// System.out.println();
		}

		GraphKnoten ziel;
		GraphKnoten start;
		if (zielZ == -1 || zielS == -1) {
			ziel = labyrinthKnoten[0][maxSpalten - 1];
		} else {
			ziel = labyrinthKnoten[zielZ][zielS];
		}
		if (startZ == -1 || startS == -1) {
			start = labyrinthKnoten[maxZeilen - 1][0];
		} else {
			start = labyrinthKnoten[startZ][startS];
		}

		// Aufruf der Methode aus der Übungsaufgabe
		List<GraphKnoten> path = BreitensucheGraph.findePfad(start, ziel);
		// System.out.println("Ziel: "+ziel+ "Pfad: "+path);

		for (int z = 0; z < maxZeilen; z++) {
			for (int s = 0; s < maxSpalten; s++) {
				int e = (Integer) model.getValueAt(z, s);
				if (e == 1) {
					GraphKnoten tmp = labyrinthKnoten[z][s];
					if (path.contains(tmp)) {
						// System.out.print("O");
						if (path.get(0).equals(tmp)) {
							model.setValueAt(3, z, s);
						} else if (path.get(path.size() - 1).equals(tmp)) {
							model.setValueAt(4, z, s);
						} else {
							model.setValueAt(2, z, s);
						}

					} else {
						// System.out.print("X");
					}
				} else {
					// System.out.print(" ");
				}
			}
			// System.out.println();
		}
	}

	private void pruefeNachbar(GraphKnoten[][] labyrinthKnoten, int z, int s, int nachbarZ, int nachbarS, int maxZeilen,
                               int maxSpalten) {
		if (nachbarZ >= 0 && nachbarS >= 0 && nachbarZ < maxZeilen && nachbarS < maxSpalten) {
			if (labyrinthKnoten[nachbarZ][nachbarS] != null) {
				labyrinthKnoten[z][s].addNachbar(labyrinthKnoten[nachbarZ][nachbarS]);
			}
		}
	}

}
