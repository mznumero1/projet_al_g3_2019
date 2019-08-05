package program;

import java.rmi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import domaine.*;

public class AddUser extends JFrame implements ActionListener
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1061309976885137631L;
private  DataInterface objetDistant;
 private JLabel lid,lnom,lprenom;
 private JTextField chid,chnom,chprenom;
 private JButton aj,qt;
 private JPanel pan1,pan2,pan3;
 public AddUser(DataInterface objetServeur)
	{
	    objetDistant = objetServeur;
		lid =new JLabel("Numero:");
		lnom= new JLabel("Nom:");
		lprenom= new JLabel("Prenom:");
		chid= new JTextField(40);
		chnom=new JTextField();
		chprenom = new JTextField();
		
		aj = new JButton("Enregistrer");
		qt = new JButton("Quitter");
		aj.addActionListener(this);
		qt.addActionListener(this);
		pan1=new JPanel();
		pan2=new JPanel();
		pan3=new JPanel();
	pan1.setLayout(new GridLayout(1,2));
		pan1.add(lid);
		pan1.add(chid);

		pan2.setLayout(new GridLayout(2,2));
		pan2.add(lnom);
		pan2.add(chnom);
		pan2.add(lprenom);
		pan2.add(chprenom);
		pan3.add(aj);
		pan3.add(qt);
		
		getContentPane().add(pan1,BorderLayout.NORTH);
		getContentPane().add(pan2,BorderLayout.CENTER);
		getContentPane().add(pan3,BorderLayout.SOUTH);
		setTitle("Client RMI Swing");
		setSize(600,150);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
   	    setLocationRelativeTo(null);
		setVisible(true);
	}

   public void actionPerformed(ActionEvent e)
   {
	   if (e.getSource()==aj)
		{
		   
			 Administrateur a = null; 
			 String sid=chid.getText();
	    		int id = (sid.isEmpty()?-1:Integer.parseInt(sid));
	    		String nom=chnom.getText();
	    		String prenom=chprenom.getText();
	    	    if(id>0 && !nom.isEmpty() && !prenom.isEmpty()) {
	    	    	a = new Administrateur(id, nom, prenom);
		    		try 
		    		{		    			
					objetDistant.ajouter(a);
					chid.setText("");
					chnom.setText("");
					chprenom.setText("");
					JOptionPane.showMessageDialog(this, "Enregistrement réussi");
					}
		    		catch (RemoteException ex)
		    		{
					 	System.out.println(ex.getMessage());
					 	JOptionPane.showMessageDialog(this, "Une erreur s'est produite");
					}
	    	    }else {
	    	    	if(id<0)
	    	    		JOptionPane.showMessageDialog(this, "Veuillez renseigner un id positif non nul");
	    	    	else
	    	    		JOptionPane.showMessageDialog(this, "Veuillez renseigner correctement tous les champs");
	    	    }
		}
	   else if (e.getSource()==qt){
		   dispose();
		   new Client(objetDistant);

	   }
}
}