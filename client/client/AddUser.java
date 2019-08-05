package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import services.User;
import services.UserService;

public class AddUser extends JFrame implements ActionListener
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1061309976885137631L;
	private  UserService service;
	private String token; 
	private JLabel lpwd,lnom,lemail,lrole;
	private JTextField chnom,chemail;
	private JPasswordField chpwd;
	private JComboBox<String> cbrole;
	private JButton aj,qt;
	private JPanel pan1,pan2,pan3;
	public AddUser(UserService service, String token)
	{
	    this.service = service;
	    this.token = token;
		lnom= new JLabel("Nom d'utilisateur :");
		lemail= new JLabel("Email :");
		lpwd= new JLabel("Mot de passe :");
		lrole= new JLabel("Role :");
		chnom=new JTextField();
		chemail = new JTextField();
		chpwd = new JPasswordField();
		
		String[] roles = {"Admin", "Editeur"};
		
		cbrole = new JComboBox<String>(roles);
		aj = new JButton("Enregistrer");
		qt = new JButton("Quitter");
		aj.addActionListener(this);
		qt.addActionListener(this);
		pan1=new JPanel();
		pan2=new JPanel();
		pan3=new JPanel();
	pan1.setLayout(new GridLayout(1,2));
		pan1.add(lrole);
		pan1.add(cbrole);

		pan2.setLayout(new GridLayout(3,2));
		pan2.add(lnom);
		pan2.add(chnom);
		pan2.add(lemail);
		pan2.add(chemail);
		pan2.add(lpwd);
		pan2.add(chpwd);
		pan3.add(aj);
		pan3.add(qt);
		
		getContentPane().add(pan1,BorderLayout.NORTH);
		getContentPane().add(pan2,BorderLayout.CENTER);
		getContentPane().add(pan3,BorderLayout.SOUTH);
		setTitle("Ajout Utilisateur");
		setSize(600,150);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
   	    setLocationRelativeTo(null);
		setVisible(true);
	}

   public void actionPerformed(ActionEvent e)
   {
	   if (e.getSource()==aj)
		{
		   
			 User u = new User(); 
			 String role=cbrole.getItemAt(cbrole.getSelectedIndex());
			 String email = chemail.getText();
	    	 String nom=chnom.getText();
	    	 String pwd=String.valueOf(chpwd.getPassword());
	    	    if(!role.isEmpty() && !nom.isEmpty() && !email.isEmpty() && !pwd.isEmpty()) {
	    	    	u.setEmail(email);
	    	    	u.setPassword(pwd);
	    	    	u.setRole(role);
	    	    	u.setUsername(nom);
		    		service.addUser(u, token);
					chemail.setText("");
					chnom.setText("");
					chpwd.setText("");
					JOptionPane.showMessageDialog(this, "Enregistrement réussi");
	    	    }else {
	    	    		JOptionPane.showMessageDialog(this, "Veuillez renseigner correctement tous les champs");
	    	    }
		}
	   else if (e.getSource()==qt){
		   dispose();
		   new Client(service, token);

	   }
}
}