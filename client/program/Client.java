package presentation;

import java.rmi.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import domaine.*;
import services.UserService;
import services.UserServiceImplService;

public class Client extends JFrame implements ActionListener
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1061309976885137631L;
private  UserService service;
 private JLabel lid;
 private JTextField chid;
 private JButton aj,qt,aff,edit,sup;
 private JPanel pan1,pan2,pan3;
 public Client(UserService service)
	{
	    this.service = service;
		lid =new JLabel("Nom Utilisateur :");
		chid= new JTextField(40);
		
		aj = new JButton("Ajouter Utilisateur");
		edit = new JButton("Modifier");
		qt = new JButton("Quitter");
		sup = new JButton("Supprimer");
		aff= new JButton("Afficher les Utilisateurs");
		aj.addActionListener(this);
		edit.addActionListener(this);
		aff.addActionListener(this);
		qt.addActionListener(this);
		sup.addActionListener(this);
		pan1=new JPanel();
		pan2=new JPanel();
		pan3=new JPanel();
		
		pan1.setLayout(new GridLayout(2,3));
		pan1.add(lid);
		pan1.add(chid);
		pan1.add(sup);
		pan1.add(edit);
		pan1.add(new JPanel());
		pan1.add(new JPanel());
		
		pan2.setLayout(new GridLayout(4,2));
		pan2.add(cbAdmins);
		pan2.add(affAdm);
		pan2.add(cbSalles);
		pan2.add(affSal);
		pan2.add(new JPanel());
		pan2.add(aff);
		
		pan3.setLayout(new GridLayout(3, 2));
		pan3.add(ajAdm);
		pan3.add(ajSal);
		pan3.add(ajServ);
		pan3.add(new JPanel());
		pan3.add(new JPanel());
		pan3.add(qt);
		
		add(pan1,BorderLayout.NORTH);
		add(pan2,BorderLayout.CENTER);
		add(pan3,BorderLayout.SOUTH);
		setTitle("Client RMI Swing");
		setSize(600,250);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
   	    setLocationRelativeTo(null);
		setVisible(true);
	}

   public void actionPerformed(ActionEvent e)
   {
	   if (e.getSource()==ajAdm){		   
			 dispose();
			 new AddUser(objetDistant);
		}
	   else if (e.getSource()==ajSal){		   
			 dispose();
			 new AjoutSalle(objetDistant);
		}
	   else if (e.getSource()==ajServ){		   
			 dispose();
			 new AjoutServeur(objetDistant);
		}
	   else
		   if (e.getSource()==aff){
				try
				{
					
			    ArrayList<Serveur> liste=objetDistant.afficherServeurs();
			          dispose();
			         new ListUsers(liste,objetDistant);
			        
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
			

           }
		   else
			   if (e.getSource()==affAdm){
					try
					{
						Administrateur admin = cbAdmins.getItemAt(cbAdmins.getSelectedIndex());
						if(admin!=null) {
							ArrayList<Serveur> liste=objetDistant.afficherServeursAdmin(admin.getNumAdmin());
					          dispose();
					         new ListUsers(liste,objetDistant);
						}else {
							JOptionPane.showMessageDialog(this, "Veuillez selectionner un Administrateur");
						}
				        
					}
					catch(Exception ex)
					{
						System.out.println(ex.getMessage());
					}
				

	           }
			   else
				   if (e.getSource()==affSal){
						try
						{
							Salle salle = cbSalles.getItemAt(cbSalles.getSelectedIndex());
							if(salle!=null)
							{
								ArrayList<Serveur> liste=objetDistant.afficherServeursSalle(salle.getNumSalle());
						        dispose();
						        new ListUsers(liste,objetDistant);
							}else {
								JOptionPane.showMessageDialog(this, "Veuillez selectionner une Salle");
							}
					        
						}
						catch(Exception ex)
						{
							System.out.println(ex.getMessage());
						}				

		           }
		   		   else 
		   			   if (e.getSource()==sup)
		   			   {
		   				   String smatr = chid.getText();
		   				   int id = (smatr.isEmpty()?-1:Integer.parseInt(smatr));
		   				   try
		   				   {
		   					   if(id>0) {
		   						   objetDistant.supprimer(id);
			   					   chid.setText("");
			   					   JOptionPane.showMessageDialog(this, "Suppression réussie");
		   					   }else {
		   						JOptionPane.showMessageDialog(this, "Veuillez renseigner un id correct");
		   					   }
		   				   }
		   				   catch(Exception ex)
		   				   {
		   					JOptionPane.showMessageDialog(this, "Une erreur s'est produite");
		   					   System.out.println(ex.getMessage());
		   				   }
		   			   }
		   			   else
				       if (e.getSource()==qt)
			        	{
			              dispose();
			               System.exit(0);
   
                         }
}
   
   
public static void main(String[] args) throws Exception
   {
	  
	   try
	   {
		   String s = JOptionPane.showInputDialog(null, "Veuillez entrer l'adresse du sereur","localhost");
		   String server = (!s.isEmpty()?s.trim():"localhost");
		   System.out.println("Connexion au serveur "+server);
		   UserServiceImplService s = new UserServiceImplService();
		   UserService service = s.getUserServiceImplPort();   
	       new Client(service);
	   }
	   catch (Exception ex)
	   {
		   JOptionPane.showMessageDialog(null, "Connexion impossible");
		   System.out.println("impossible de se connecter");
	     System.out.println(ex.getMessage());	   
	   }
        
   }
}