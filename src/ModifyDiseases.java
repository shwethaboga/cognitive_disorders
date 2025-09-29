import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ModifyDiseases extends JFrame {

    private static final long serialVersionUID = 1L;
    static ModifyDiseases frame;
    private JPanel contentPane;
    private JTextField diseaseNameLbl;
    private JLabel diseaseName;
    private JTextField discriptionLbl;
    private JLabel disease;
    private JTextField medicationLbl;
    private JLabel medication;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ModifyDiseases();
					frame.setTitle("Modify Diseases Info.");
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
    public ModifyDiseases() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(110, 95, 650, 350);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        diseaseNameLbl = new JTextField();
        diseaseNameLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        diseaseNameLbl.setBounds(290, 37, 326, 40);
        contentPane.add(diseaseNameLbl);
        diseaseNameLbl.setColumns(10);

        discriptionLbl = new JTextField();
        discriptionLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        discriptionLbl.setBounds(290, 87, 326, 40);
        contentPane.add(discriptionLbl);
        diseaseNameLbl.setColumns(10);
        
        medicationLbl = new JTextField();
        medicationLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        medicationLbl.setBounds(290, 137, 326, 40);
        contentPane.add(medicationLbl);
        medicationLbl.setColumns(10);
        
        diseaseNameLbl.setText("");
		discriptionLbl.setText("");
		medicationLbl.setText("");

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
            	  String b = diseaseNameLbl.getText();
                  String t = discriptionLbl.getText();
                  String a = medicationLbl.getText();
                if(SymptomDetails.checkSymptom(b)) {
                try {
                    PreparedStatement st = con.prepareStatement("Update disease set descr:= ?,  medicine= ? where name=?");
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
					JOptionPane.showMessageDialog(ModifyDiseases.this,"Disease doesnot exist !!!");
				diseaseNameLbl.setText("");
				discriptionLbl.setText("");
				medicationLbl.setText("");
            }
 

            }
        });
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnSave.setBackground(new Color(240, 240, 240));
        btnSave.setBounds(300, 225, 130, 50);
        contentPane.add(btnSave);

        diseaseName = new JLabel("Disease name :");
        diseaseName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        diseaseName.setBounds(45, 37, 326, 40);
        contentPane.add(diseaseName);
        
        disease = new JLabel("Description :");
        disease.setFont(new Font("Tahoma", Font.PLAIN, 18));
        disease.setBounds(45, 87, 326, 40);
        contentPane.add(disease);
        
        medication = new JLabel("Suggested Medicine :");
        medication.setFont(new Font("Tahoma", Font.PLAIN, 18));
        medication.setBounds(45, 137, 326, 40);
        contentPane.add(medication);
        
        String test = diseaseNameLbl.getText();
        diseaseNameLbl.addKeyListener((KeyListener) new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                	String b = diseaseNameLbl.getText();
                    String t = discriptionLbl.getText();
                    String a = medicationLbl.getText();
              	Connection con = DB.getConnection();
              	if(SymptomDetails.checkSymptom(b)) {
              	Statement st;
  				try {
  					PreparedStatement stmt = con.prepareStatement("select name, disease, test from symptom, disease where symptom.name=? and symptom.disease_id=disease.disease_id");
  					stmt.setString(1,b);
  					ResultSet rs = stmt.executeQuery();
  					while(rs.next())   
  					{	diseaseNameLbl.setText(rs.getString(2));
  			discriptionLbl.setText(rs.getString(3));
  			medicationLbl.setText(rs.getString(4));}
  			} catch (SQLException e1) {
  				e1.printStackTrace();
  			}
  				}else {
  				JOptionPane.showMessageDialog(ModifyDiseases.this,"Symptom is not listed !!!");
  				diseaseNameLbl.setText("");
  				discriptionLbl.setText("");
  				medicationLbl.setText("");
  			}

             
            }

            }});
        
        JButton btnLoad = new JButton("Load");
        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	  String book = diseaseNameLbl.getText();
                  String title = discriptionLbl.getText();
                  String author = medicationLbl.getText();
            	Connection con = DB.getConnection();
            	if(SymptomDetails.checkSymptom(book)) {
            	Statement st;
				try {
                    PreparedStatement stmt = con.prepareStatement("select name, descr, medicine from  disease where disease.name=? ");
                    stmt.setString(1,book);
                    ResultSet rs = stmt.executeQuery();
                    while(rs.next())
                    {	diseaseNameLbl.setText(rs.getString(1));
                        discriptionLbl.setText(rs.getString(2));
                        medicationLbl.setText(rs.getString(3));}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}}else {
				JOptionPane.showMessageDialog(ModifyDiseases.this,"Dsiease name is Invalid !!!");
				diseaseNameLbl.setText("");
				discriptionLbl.setText("");
				medicationLbl.setText("");
			}

            }});
        btnLoad.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnLoad.setBackground(new Color(240, 240, 240));
        btnLoad.setBounds(150, 225, 130, 50);
        contentPane.add(btnLoad);
        
    }
}
