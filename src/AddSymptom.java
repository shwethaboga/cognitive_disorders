import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSymptom extends JFrame {
	private static final long serialVersionUID = 1L;
	static AddSymptom frame;
	private JPanel contentPane;
	private JTextField name;
	private JTextField description;
	private JTextField disease;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AddSymptom();
					frame.setTitle("Book Info.");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddSymptom() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAddSymptom = new JLabel("Add Symptom");
		lblAddSymptom.setForeground(Color.GRAY);
		lblAddSymptom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblName = new JLabel("Symptom Name :");
		
		JLabel lblDescription = new JLabel("Description :");
		
		JLabel lblDisease = new JLabel("Disease :");
		
		name = new JTextField();
		name.setColumns(10);
		
		description = new JTextField();
		description.setColumns(10);
		
		disease = new JTextField();
		disease.setColumns(10);
		JButton btnAddChild = new JButton("Submit");
		btnAddChild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String _name= name.getText();
			String _description= description.getText();
			String _disease= disease.getText();
			if(_name.equals("") || _description.equals("")|| _disease.equals("") ) {
				JOptionPane.showMessageDialog(AddSymptom.this,"Unknown Error !!!\n TextField Cannot be Blank");
			}
			else {
			if(SymptomDetails.checkSymptom(_name)) {
				JOptionPane.showMessageDialog(AddSymptom.this,"Symptom with Same Symptom Name is present already\nInsertion Failed !!!");
			}
			else {
			int i= SymptomDetails.insertSymptom(_name, _description, _disease);
			if(i>0){
				JOptionPane.showMessageDialog(AddSymptom.this,"Symptom added successfully!");
			}else{
				JOptionPane.showMessageDialog(AddSymptom.this,"Unknown Error !!!\nInsertion not completed");
			}
			}
			}}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				WelcomePage.main(new String[]{});
				frame.dispose();
			}
			});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(150)
							.addComponent(lblAddSymptom))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblDescription)
								.addComponent(lblName)
								.addComponent(lblDisease)
								)
							.addGap(47)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								
								.addComponent(disease, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
								.addComponent(description, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
								.addComponent(name, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(125, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(50)
					.addComponent(btnAddChild)
					.addGap(30)
					.addComponent(btnBack)
					.addContainerGap())

		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblAddSymptom)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescription)
						.addComponent(description, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDisease)
						.addComponent(disease, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAddChild, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					
	)	);
		contentPane.setLayout(gl_contentPane);
	}

}
