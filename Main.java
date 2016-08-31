/*	Program name:	Lab 02 Furniture Manufacture Inventory
	Programmer:		Marcus Ross
	Date Due:		12 Feb, 2014
	Description:	This program takes inventory data as input from a file upon opening, allows a user to add to inventory or sell from inventory, shows a receipt upon selling, and stores inventory data in the same file upon closing.	*/

package lab02;

import lab02.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		new Win();
	}
}

class Win extends Frame implements WindowListener {
	private static final String FILE_NAME = "inventory.txt"; // simpler to alter if a constant
	private ArrayList<Furnit> invList;

	public Win() {

		setResizable(false);

		addWindowListener(this);
		setLocationRelativeTo(null);
		load();
	}

	private void load() {
		removeAll();
		setSize(200, 175);
		setTitle("Furniture Inventory");

		Panel panel = new Panel(new GridLayout(2,1));
		Button btnAdd = new Button("Add Inventory");
		Button btnSale = new Button("Process Sale");

		btnAdd.addActionListener(new Add());
		btnSale.addActionListener(new Sale());

		panel.add(btnAdd);
		panel.add(btnSale);
		add(panel);

		setVisible(true);
	}

	private class Add implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			removeAll();
			setTitle("Add Inventory");

			SpringLayout layout = new SpringLayout();
			Panel panel = new Panel(layout);
			Label errorL = new Label("", Label.CENTER);
			Label idL = new Label("Item ID", Label.CENTER);
			TextField idF = new TextField();
			Label qtyL = new Label("Quantity", Label.CENTER);
			TextField qtyF = new TextField();
			Button okB = new Button("OK");
			Button cancelB = new Button("Cancel");

			okB.addActionListener(new AddOk(idF, qtyF, errorL));
			cancelB.addActionListener(new AddOk(idF, qtyF, errorL));

			panel.add(errorL);	panel.add(idL);	panel.add(idF);	panel.add(qtyL);
			panel.add(qtyF);	panel.add(okB);	panel.add(cancelB);
			add(panel);

			errorL.setForeground(Color.red);

			layout.putConstraint("VerticalCenter", idL, -30, "VerticalCenter", panel);
			layout.putConstraint("VerticalCenter", idF, -30, "VerticalCenter", panel);
			layout.putConstraint("West", idL, 25, "West", panel);
			layout.putConstraint("West", idF, 10, "East", idL);
			layout.putConstraint("East", idF, -25, "East", panel);
			layout.putConstraint("VerticalCenter", qtyL, 0, "VerticalCenter", panel);
			layout.putConstraint("VerticalCenter", qtyF, 0, "VerticalCenter", panel);
			layout.putConstraint("West", qtyL, 25, "West", panel);
			layout.putConstraint("West", qtyF, 10, "East", qtyL);
			layout.putConstraint("East", qtyF, -25, "East", panel);
			layout.putConstraint("VerticalCenter", okB, 30, "VerticalCenter", panel);
			layout.putConstraint("VerticalCenter", cancelB, 30, "VerticalCenter", panel);
			layout.putConstraint("West", okB, 50, "West", panel);
			layout.putConstraint("East", cancelB, -50, "East", panel);
			layout.putConstraint("North", errorL, 5, "North", panel);
			layout.putConstraint("HorizontalCenter", errorL, 0, "HorizontalCenter", panel);
			layout.putConstraint("West", errorL, 5, "West", panel);
			layout.putConstraint("East", errorL, 5, "East", panel);

			setVisible(true);
		}
	}

	private class AddOk implements ActionListener {
		private TextField idF, qtyF;
		private Label errorL;
		public AddOk(TextField idF, TextField qtyF, Label errorL) {
			this.idF = idF;
			this.qtyF = qtyF;
			this.errorL = errorL;
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("OK")) {
				for(Furnit furnitCur:invList) 
					if(furnitCur.getId().equals(idF.getText())) {
						try {
							furnitCur.modQty(Integer.parseInt(qtyF.getText()));
							load();
						} catch(NumberFormatException | QtyException q) {
							errorL.setText("Invalid quantity");
							return;
						}
					}
				errorL.setText("Item not found");
			} else
				load();
		}
	}

	private class Sale implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			removeAll();
			setTitle("Process Sale");

			SpringLayout layout = new SpringLayout();
			Panel panel = new Panel(layout);
			Label errorL = new Label("", Label.CENTER);
			Label idL = new Label("Item ID", Label.CENTER);
			TextField idF = new TextField();
			Label qtyL = new Label("Quantity", Label.CENTER);
			TextField qtyF = new TextField();
			Button okB = new Button("OK");
			Button cancelB = new Button("Cancel");

			okB.addActionListener(new SaleOk(idF, qtyF, errorL));
			cancelB.addActionListener(new SaleOk(idF, qtyF, errorL));

			panel.add(errorL);	panel.add(idL);	panel.add(idF);	panel.add(qtyL);
			panel.add(qtyF);	panel.add(okB);	panel.add(cancelB);
			add(panel);

			errorL.setForeground(Color.red);

			layout.putConstraint("VerticalCenter", idL, -30, "VerticalCenter", panel);
			layout.putConstraint("VerticalCenter", idF, -30, "VerticalCenter", panel);
			layout.putConstraint("West", idL, 25, "West", panel);
			layout.putConstraint("West", idF, 10, "East", idL);
			layout.putConstraint("East", idF, -25, "East", panel);
			layout.putConstraint("VerticalCenter", qtyL, 0, "VerticalCenter", panel);
			layout.putConstraint("VerticalCenter", qtyF, 0, "VerticalCenter", panel);
			layout.putConstraint("West", qtyL, 25, "West", panel);
			layout.putConstraint("West", qtyF, 10, "East", qtyL);
			layout.putConstraint("East", qtyF, -25, "East", panel);
			layout.putConstraint("VerticalCenter", okB, 30, "VerticalCenter", panel);
			layout.putConstraint("VerticalCenter", cancelB, 30, "VerticalCenter", panel);
			layout.putConstraint("West", okB, 50, "West", panel);
			layout.putConstraint("East", cancelB, -50, "East", panel);
			layout.putConstraint("North", errorL, 5, "North", panel);
			layout.putConstraint("HorizontalCenter", errorL, 0, "HorizontalCenter", panel);
			layout.putConstraint("West", errorL, 5, "West", panel);
			layout.putConstraint("East", errorL, 5, "East", panel);

			setVisible(true);
		}
	}

	private class SaleOk implements ActionListener {
		private TextField idF, qtyF;
		private Label errorL;
		public SaleOk(TextField idF, TextField qtyF, Label errorL) {
			this.idF = idF;
			this.qtyF = qtyF;
			this.errorL = errorL;
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("OK")) {
				for(Furnit furnitCur:invList) 
					if(furnitCur.getId().equals(idF.getText())) {
						try {
							furnitCur.modQty(-Integer.parseInt(qtyF.getText()));
							receipt(Integer.parseInt(qtyF.getText()), furnitCur);
						} catch(NumberFormatException | QtyException q) {
							errorL.setText("Invalid quantity");
							return;
						}
					}
				errorL.setText("Item not found");
			} else
				load();
		}
	}

	private void receipt(int qty, Furnit furnit) {
		setVisible(false);
		setSize(200, 255);
		removeAll();
		setTitle("Sale Receipt");

		double total = qty * furnit.getPrice();

		SpringLayout layout = new SpringLayout();
		Panel panel = new Panel(layout);
		TextField qtyF = new TextField(Integer.toString(qty));
		TextField totalF = new TextField("$" + Double.toString(total));
		TextField idF = new TextField(furnit.getId());
		TextField noteF = new TextField(furnit.getNote());
		Label qtyL = new Label("Quantity");
		Label totalL = new Label("Total");
		Label idL = new Label("Item");
		Label noteL = new Label("Item Description");
		Button okB = new Button("OK");

		qtyF.setEditable(false);	totalF.setEditable(false);
		idF.setEditable(false);	noteF.setEditable(false);
		okB.addActionListener(new rcptOk());

		panel.add(qtyL);	panel.add(totalL);	panel.add(idL);	panel.add(noteL);
		panel.add(qtyF);	panel.add(totalF);	panel.add(idF);	panel.add(noteF);
		panel.add(okB);

		add(panel);

		layout.putConstraint("West", idL, 25, "West", panel);
		layout.putConstraint("North", idL, 5, "North", panel);
		layout.putConstraint("West", idF, 25, "West", panel);
		layout.putConstraint("East", idF, -25, "East", panel);
		layout.putConstraint("North", idF, 0, "South", idL);
		layout.putConstraint("West", noteL, 25, "West", panel);
		layout.putConstraint("North", noteL, 0, "South", idF);
		layout.putConstraint("West", noteF, 25, "West", panel);
		layout.putConstraint("East", noteF, -25, "East", panel);
		layout.putConstraint("North", noteF, 0, "South", noteL);
		layout.putConstraint("West", qtyL, 25, "West", panel);
		layout.putConstraint("North", qtyL, 0, "South", noteF);
		layout.putConstraint("West", qtyF, 25, "West", panel);
		layout.putConstraint("East", qtyF, -25, "East", panel);
		layout.putConstraint("North", qtyF, 0, "South", qtyL);
		layout.putConstraint("West", totalL, 25, "West", panel);
		layout.putConstraint("North", totalL, 0, "South", qtyF);
		layout.putConstraint("West", totalF, 25, "West", panel);
		layout.putConstraint("East", totalF, -25, "East", panel);
		layout.putConstraint("North", totalF, 0, "South", totalL);
		layout.putConstraint("South", okB, -5, "South", panel);
		layout.putConstraint("HorizontalCenter", okB, 0, "HorizontalCenter", panel);

		setVisible(true);
	}

	private class rcptOk implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			load();
		}
	}

	private void invLoad() { // load inventory from file when opening
		Scanner invFRead;
		int qty;
		double price;
		String id, note;

		invList = new ArrayList<Furnit>();

		try {
			invFRead = new Scanner(new BufferedReader(new FileReader(new File(FILE_NAME))));
		} catch(FileNotFoundException e) {
			System.out.println("Inventory file not found");
			System.exit(1);
			return;
		}

		try {
			while(invFRead.hasNext()) {
				note = invFRead.nextLine();
				id = invFRead.next();
				price = invFRead.nextDouble();
				qty = invFRead.nextInt();
				invFRead.nextLine();
				invList.add(new Furnit(id, price, qty, note));
			}
		} catch(InputMismatchException e) {
			System.out.println("Inventory file incorrectly formatted");
			System.exit(3);
			return;
		}

		invFRead.close();
	}

	private void invSave() { // save inventory to file when closing
		PrintWriter invFWrite;

		try {
			invFWrite = new PrintWriter(new BufferedWriter(new FileWriter(new File(FILE_NAME), false)));
		} catch(FileNotFoundException e) {
			System.out.println("Inventory file not found");
			System.exit(1);
			return;
		} catch(IOException e) {
			System.out.println("Inventory file i/o error");
			System.exit(2);
			return;
		}

		for(Furnit furnitCur:invList) {
			invFWrite.printf("%s\n%s %.2f %d\n", furnitCur.getNote(), furnitCur.getId(), furnitCur.getPrice(), furnitCur.getQty());
		}

		invFWrite.close();
	}

	public void windowClosing(WindowEvent e) {
		invSave();
		setVisible(false);
		dispose();
		System.exit(0);
	}
	public void windowOpened(WindowEvent e) {
		invLoad();
	}
	public void windowClosed(WindowEvent e) { }
	public void windowIconified(WindowEvent e) { }
	public void windowDeiconified(WindowEvent e) { }
	public void windowActivated(WindowEvent e) { }
	public void windowDeactivated(WindowEvent e) { }
}