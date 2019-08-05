package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import services.User;
import services.UserService;

public class EditUser extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1061309976885137631L;
	private UserService service;
	private String token;
	private User user;
	private JLabel lid,lnom,lemail,lrole;
	private JTextField chnom,chemail;
	private JTextField chid;
	private JComboBox<String> cbrole;
	private JButton aj,qt;
	private JPanel pan1,pan2,pan3;
	public EditUser(User user, UserService service, String token)
	{
		this.user = user;
		this.service = service;
		this.token = token;
		lnom= new JLabel("Nom d'utilisateur :");
		lemail= new JLabel("Email :");
		lid= new JLabel("ID :");
		lrole= new JLabel("Role :");
		chnom=new JTextField(user.getUsername());
		chemail = new JTextField(user.getEmail());
		chid = new JTextField(String.valueOf(user.getId()));
		chid.setEditable(false);

		String[] roles = {"Admin", "Editeur"};

		cbrole = new JComboBox<String>(roles);
		cbrole.setSelectedItem(user.getRole());
		aj = new JButton("Enregistrer");
		qt = new JButton("Quitter");
		aj.addActionListener(this);
		qt.addActionListener(this);
		pan1=new JPanel();
		pan2=new JPanel();
		pan3=new JPanel();
		pan1.setLayout(new GridLayout(2,2));
		pan1.add(lid);
		pan1.add(chid);
		pan1.add(new JPanel());
		pan1.add(new JPanel());

		pan2.setLayout(new GridLayout(3,2));
		pan2.add(lrole);
		pan2.add(cbrole);
		pan2.add(lnom);
		pan2.add(chnom);
		pan2.add(lemail);
		pan2.add(chemail);
		pan3.add(aj);
		pan3.add(qt);

		getContentPane().add(pan1,BorderLayout.NORTH);
		getContentPane().add(pan2,BorderLayout.CENTER);
		getContentPane().add(pan3,BorderLayout.SOUTH);
		setTitle("Ajout Utilisateur");
		setSize(600,200);
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
			if(!role.isEmpty() && !nom.isEmpty() && !email.isEmpty()) {
				u.setId(user.getId());
				u.setEmail(email);
				u.setRole(role);
				u.setUsername(nom);
				if(service.updateUser(u, token)){
					JOptionPane.showMessageDialog(this, "Mise à jour réussi");
				}else {
					JOptionPane.showMessageDialog(this, "Echec de l'enregistrement");
				}
				
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