package client;

import javax.swing.*;

import java.awt.*;
import java.util.List;
import java.awt.event.*;

import services.User;
import services.UserService;
import services.UserServiceImplService;

public class Client extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1061309976885137631L;
	private  UserService service;
	private String token;
	private JLabel lid;
	private JTextField chid;
	private JButton aj,edit,qt,aff,sup;
	private JPanel pan1,pan2,pan3;
	public Client(UserService service, String token)
	{
		this.service = service;
		this.token = token;
		lid =new JLabel("ID Utilisateur :");
		chid= new JTextField(40);

		aj = new JButton("Ajouter Utilisateur");
		qt = new JButton("Quitter");
		edit = new JButton("Modifier");
		sup = new JButton("Supprimer");
		aff= new JButton("Afficher les Utilisateurs");
		aj.addActionListener(this);
		aff.addActionListener(this);
		qt.addActionListener(this);
		sup.addActionListener(this);
		edit.addActionListener(this);
		pan1=new JPanel();
		pan2=new JPanel();
		pan3=new JPanel();

		pan1.setLayout(new GridLayout(2,3));
		pan1.add(lid);
		pan1.add(new JPanel());
		pan1.add(chid);
		pan1.add(new JPanel());
		pan1.add(sup);
		pan1.add(edit);

		pan2.setLayout(new GridLayout(2,2));
		pan2.add(new JPanel());
		pan2.add(new JPanel());
		pan2.add(new JPanel());
		pan2.add(aff);

		pan3.setLayout(new GridLayout(2, 2));
		pan3.add(new JPanel());
		pan3.add(aj);
		pan3.add(new JPanel());
		pan3.add(qt);

		add(pan1,BorderLayout.NORTH);
		add(pan2,BorderLayout.CENTER);
		add(pan3,BorderLayout.SOUTH);
		setTitle("Gestion des utilisateurs");
		setSize(600,185);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==aj){		   
			dispose();
			new AddUser(service, token);
		}
		else
			if(e.getSource()==edit) {
				int id = Integer.parseInt(chid.getText());
				User u = service.getUser(id, token);
				if(u.getId()>0) {
					dispose();
					new EditUser(u, service, token);
				}else {
					JOptionPane.showMessageDialog(this, "Utilisateur non trouvé");
				}
			}
		else
			if (e.getSource()==aff){
				try
				{

					List<User> liste = service.getAllUsers(token).getItem();
					dispose();
					new ListUsers(liste,service, token);

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
							if(service.deleteUser(id, token)) {
								chid.setText("");
								JOptionPane.showMessageDialog(this, "Suppression réussie");
							}else {
								JOptionPane.showMessageDialog(this, "Erreur de suppression");
							}			   					   
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
			UserServiceImplService s = new UserServiceImplService();
			UserService service = s.getUserServiceImplPort();

			JTextField email = new JTextField();
			JPasswordField password = new JPasswordField();
			final JComponent[] inputs = new JComponent[] {
					new JLabel("Email"),
					email,
					new JLabel("Password"),
					password
			};
			String token=null;
			do {
				int result = JOptionPane.showConfirmDialog(null, inputs, "Authentification", JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					User cred = new User();
					cred.setEmail(email.getText());
					cred.setPassword(String.valueOf(password.getPassword()));
					User user = service.login(cred);
					token = user.getToken();
					if(token==null) {
						JOptionPane.showMessageDialog(null, "Erreur d'authentification");
					}else if(!user.getRole().equalsIgnoreCase("admin")) {
						JOptionPane.showMessageDialog(null, "Vous n'avez pas accès à cette application");
						token=null;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Annulation");
					System.exit(0);
				}
			}while(token==null);
			new Client(service, token);
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Connexion impossible");
			System.out.println("impossible de se connecter");
			System.out.println(ex.getMessage());	   
		}

	}
}