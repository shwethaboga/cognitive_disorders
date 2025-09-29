import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ModifySymptoms extends JFrame {

    private static final long serialVersionUID = 1L;
    static ModifySymptoms frame;
    private JPanel contentPane;
    private JTextField symptomNameLbl;
    private JLabel symptomsName;
    private JTextField diseaseLbl;
    private JLabel disease;
    private JTextField testsLbl;
    private JLabel tests;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ModifySymptoms();
					frame.setTitle("Modify Symptoms Info.");
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
    public ModifySymptoms() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(110, 95, 650, 350);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        symptomNameLbl = new JTextField();
        symptomNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        symptomNameLbl.setBounds(290, 37, 326, 40);
        contentPane.add(symptomNameLbl);
        symptomNameLbl.setColumns(10);

        diseaseLbl = new JTextField();
        diseaseLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        diseaseLbl.setBounds(290, 87, 326, 40);
        contentPane.add(diseaseLbl);
        symptomNameLbl.setColumns(10);
        
        testsLbl = new JTextField();
        testsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        testsLbl.setBounds(290, 137, 326, 40);
        contentPane.add(testsLbl);
        testsLbl.setColumns(10);
        
        symptomNameLbl.setText("");
		diseaseLbl.setText("");
		testsLbl.setText("");

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
            	  String b = symptomNameLbl.getText();
                  String t = diseaseLbl.getText();
                  String a = testsLbl.getText();
                if(SymptomDetails.checkSymptom(b)) {
                try {
                    PreparedStatement st = con.prepareStatement("Update symptom set disease_id:= (select disease_id from disease where name=?),  test= ? where name=?");
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
					JOptionPane.showMessageDialog(ModifySymptoms.this,"Symptom doesnot exist !!!");
				symptomNameLbl.setText("");
				diseaseLbl.setText("");
				testsLbl.setText("");
            }
 

            }
        });
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnSave.setBackground(new Color(240, 240, 240));
        btnSave.setBounds(300, 225, 130, 50);
        contentPane.add(btnSave);

        symptomsName = new JLabel("Symptom name :");
        symptomsName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        symptomsName.setBounds(45, 37, 326, 40);
        contentPane.add(symptomsName);
        
        disease = new JLabel("Suspected Disease :");
        disease.setFont(new Font("Tahoma", Font.PLAIN, 18));
        disease.setBounds(45, 87, 326, 40);
        contentPane.add(disease);
        
        tests = new JLabel("Suggested Test :");
        tests.setFont(new Font("Tahoma", Font.PLAIN, 18));
        tests.setBounds(45, 137, 326, 40);
        contentPane.add(tests);
        
        String test = symptomNameLbl.getText();
        symptomNameLbl.addKeyListener((KeyListener) new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                	String b = symptomNameLbl.getText();
                    String t = diseaseLbl.getText();
                    String a = testsLbl.getText();
              	Connection con = DB.getConnection();
              	if(SymptomDetails.checkSymptom(b)) {
              	Statement st;
  				try {
  					PreparedStatement stmt = con.prepareStatement("select name, disease, test from symptom, disease where symptom.name=? and symptom.disease_id=disease.disease_id");
  					stmt.setString(1,b);
  					ResultSet rs = stmt.executeQuery();
  					while(rs.next())   
  					{	symptomNameLbl.setText(rs.getString(2));
  			diseaseLbl.setText(rs.getString(3));
  			testsLbl.setText(rs.getString(4));}
  			} catch (SQLException e1) {
  				e1.printStackTrace();
  			}
  				}else {
  				JOptionPane.showMessageDialog(ModifySymptoms.this,"Symptom is not listed !!!");
  				symptomNameLbl.setText("");
  				diseaseLbl.setText("");
  				testsLbl.setText("");
  			}

             
            }

            }});
        
        JButton btnLoad = new JButton("Load");
        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	  String book = symptomNameLbl.getText();
                  String title = diseaseLbl.getText();
                  String author = testsLbl.getText();
            	Connection con = DB.getConnection();
            	if(SymptomDetails.checkSymptom(book)) {
            	Statement st;
				try {
                    PreparedStatement stmt = con.prepareStatement("select symptom.name, disease.name, test from symptom, disease where symptom.name=? and symptom.disease_id=disease.disease_id");
                    stmt.setString(1,book);
                    ResultSet rs = stmt.executeQuery();
                    while(rs.next())
                    {	symptomNameLbl.setText(rs.getString(1));
                        diseaseLbl.setText(rs.getString(2));
                        testsLbl.setText(rs.getString(3));}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}}else {
				JOptionPane.showMessageDialog(ModifySymptoms.this,"Book ID is Invalid !!!");
				symptomNameLbl.setText("");
				diseaseLbl.setText("");
				testsLbl.setText("");
			}

            }});
        btnLoad.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnLoad.setBackground(new Color(240, 240, 240));
        btnLoad.setBounds(150, 225, 130, 50);
        contentPane.add(btnLoad);
        
    }
}
