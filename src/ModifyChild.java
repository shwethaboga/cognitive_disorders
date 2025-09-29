import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ModifyChild extends JFrame {

    private static final long serialVersionUID = 1L;
    static ModifyChild frame;
    private JPanel contentPane;
    private JTextField childNameLbl;
    private JLabel childName;
    private JTextField ageLbl;
    private JLabel age;
    private JTextField symptomLbl;
    private JLabel symptom;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ModifyChild();
					frame.setTitle("Modify Child Info.");
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
    public ModifyChild() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(110, 95, 650, 350);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        childNameLbl = new JTextField();
        childNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        childNameLbl.setBounds(290, 37, 326, 40);
        contentPane.add(childNameLbl);
        childNameLbl.setColumns(10);

        ageLbl = new JTextField();
        ageLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        ageLbl.setBounds(290, 87, 326, 40);
        contentPane.add(ageLbl);
        childNameLbl.setColumns(10);
        
        symptomLbl = new JTextField();
        symptomLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        symptomLbl.setBounds(290, 137, 326, 40);
        contentPane.add(symptomLbl);
        symptomLbl.setColumns(10);
        
        childNameLbl.setText("");
		ageLbl.setText("");
		symptomLbl.setText("");

		 Connection con = DB.getConnection();
		 
		 JButton btnBack = new JButton("Back");
	        btnBack.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	frame.dispose();
	            	ManageSymptoms.main(new String[]{});
	            }});
	        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        btnBack.setBackground(new Color(240, 240, 240));
	        btnBack.setBounds(450, 225, 130, 50);
	        contentPane.add(btnBack);
        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	  String b = childNameLbl.getText();
                  String t = ageLbl.getText();
                  String a = symptomLbl.getText();
                if(SymptomDetails.checkSymptom(b)) {
                try {
                    PreparedStatement st = con.prepareStatement("Update child set age=?, symptom_id:= (select symptom_id from symptom where name=?) where name=?");
                    st.setString(1, t);
                    st.setString(2, a);
                    st.setString(3, b);
                    st.executeUpdate();
                  System.out.println("updated info for : " + b);
                    JOptionPane.showMessageDialog(btnSave, "Data Updated Successfully :)");

                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }}
				else{
					JOptionPane.showMessageDialog(ModifyChild.this,"Child doesnot exist !!!");
				childNameLbl.setText("");
				ageLbl.setText("");
				symptomLbl.setText("");
            }
 

            }
        });
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnSave.setBackground(new Color(240, 240, 240));
        btnSave.setBounds(300, 225, 130, 50);
        contentPane.add(btnSave);

        childName = new JLabel("Child name :");
        childName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        childName.setBounds(45, 37, 326, 40);
        contentPane.add(childName);
        
        age = new JLabel("Age :");
        age.setFont(new Font("Tahoma", Font.PLAIN, 18));
        age.setBounds(45, 87, 326, 40);
        contentPane.add(age);
        
        symptom = new JLabel("Symptom :");
        symptom.setFont(new Font("Tahoma", Font.PLAIN, 18));
        symptom.setBounds(45, 137, 326, 40);
        contentPane.add(symptom);

        
        JButton btnLoad = new JButton("Load");
        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	  String book = childNameLbl.getText();
                  String title = ageLbl.getText();
                  String author = symptomLbl.getText();
            	Connection con = DB.getConnection();
            	if(SymptomDetails.checkSymptom(book)) {
            	Statement st;
				try {
                    PreparedStatement stmt = con.prepareStatement("select child.name, age, symptom.name from child, disease where child.name=? and child.symptom_id=symptom.symptom_id");
                    stmt.setString(1,book);
                    ResultSet rs = stmt.executeQuery();
                    while(rs.next())
                    {	childNameLbl.setText(rs.getString(1));
                        ageLbl.setText(rs.getString(2));
                        symptomLbl.setText(rs.getString(3));}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}}else {
				JOptionPane.showMessageDialog(ModifyChild.this,"Child is Invalid !!!");
				childNameLbl.setText("");
				ageLbl.setText("");
				symptomLbl.setText("");
			}

            }});
        btnLoad.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnLoad.setBackground(new Color(240, 240, 240));
        btnLoad.setBounds(150, 225, 130, 50);
        contentPane.add(btnLoad);
        
    }
}
