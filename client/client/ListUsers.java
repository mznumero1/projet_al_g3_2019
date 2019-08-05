package client;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import services.User;
import services.UserService;

public class ListUsers extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6623329687296830959L;
	private JTable table;
	UserService service;
	String token;
	private JScrollPane sc;
	private JPanel panneau1,panneau2;
	private JButton qt;

	public ListUsers(List <User> liste, UserService service, String token)
	{
		this.service = service;
		this.token = token;
		panneau1=new JPanel();
		panneau2=new JPanel();
		qt = new JButton("Quitter");
		sc  = new JScrollPane();
		table = new JTable();
		sc.setViewportView(table);
		DefaultTableModel modele = (DefaultTableModel)table.getModel();
		modele.addColumn("ID");
		modele.addColumn("Nom");
		modele.addColumn("Email");
		modele.addColumn("Role");

		int ligne=0;
		for (User emp : liste)
		{
			modele.addRow( new Object[0]);
			modele.setValueAt(String.valueOf(emp.getId()),ligne,0);
			modele.setValueAt(emp.getUsername(), ligne, 1);
			modele.setValueAt(emp.getEmail(),ligne,2);
			modele.setValueAt(emp.getRole(),ligne, 3);
			ligne++;
		}

		setTitle("Liste des utilisateurs");
		setSize(550,500);
		qt.addActionListener(this); 
		panneau1.add(sc);
		panneau2.add(qt);
		add(panneau1,BorderLayout.NORTH);
		add(panneau2,BorderLayout.SOUTH);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==qt)
		{
			dispose();
			new Client(service, token);
		}
	}
}
