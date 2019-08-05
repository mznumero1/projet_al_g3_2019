package program;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import domaine.*;

public class ListUsers extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6623329687296830959L;
	private JTable table;
	private JScrollPane sc;
	private JPanel panneau1,panneau2;
	private JButton qt;
    private DataInterface objetDistant;
	
	public ListUsers(ArrayList <User> liste,DataInterface objetDistant)
	{
	
		this.objetDistant=objetDistant;
		panneau1=new JPanel();
		panneau2=new JPanel();
		qt = new JButton("Quitter");
		 sc  = new JScrollPane();
		  table = new JTable();
		  sc.setViewportView(table);
		  DefaultTableModel modele = (DefaultTableModel)table.getModel();
		  modele.addColumn("Numero Serveur");
		  modele.addColumn("Nom");
		  modele.addColumn("IP");
		  modele.addColumn("Admin");
		  modele.addColumn("Salle");
		  		
		  int ligne=0;
		  for (Serveur emp : liste)
		  {
			  modele.addRow( new Object[0]);
			  modele.setValueAt(String.valueOf(emp.getNumServ()),ligne,0);
			  modele.setValueAt(emp.getNomServeur(), ligne, 1);
			  modele.setValueAt(emp.getIP(),ligne,2);
			  modele.setValueAt(emp.getAdmin(),ligne, 3);
			  modele.setValueAt(emp.getSalle(),ligne, 4);
			  ligne++;
		  }
		 
		  setTitle("Client RMI:liste des serveurs");
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
    		new Client(objetDistant);
    	}
    }
}
