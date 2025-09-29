import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class ChangePassword extends JFrame {

    private static final long serialVersionUID = 1L;
    static ChangePassword frame;
    private JPanel contentPane;
    private JPasswordField PasswordField;
    private JLabel lblEnterOldPassword;
    private JPasswordField PasswordField1;
    private JLabel lblEnterNewPassword;
    private JPasswordField PasswordField2;
    private JLabel lblReEnterNewPassword;

    /**
     * Launch the application.
     */
  
    /**
     * Create the frame.
     */
    public ChangePassword(String name) {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(110, 95, 650, 350);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        PasswordField = new JPasswordField();
        PasswordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        PasswordField.setBounds(290, 37, 326, 40);
        contentPane.add(PasswordField);
        PasswordField.setColumns(10);

        PasswordField1 = new JPasswordField();
        PasswordField1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        PasswordField1.setBounds(290, 87, 326, 40);
        contentPane.add(PasswordField1);
        PasswordField.setColumns(10);
        
        PasswordField2 = new JPasswordField();
        PasswordField2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        PasswordField2.setBounds(290, 137, 326, 40);
        contentPane.add(PasswordField2);
        PasswordField2.setColumns(10);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 frame.dispose();
            }});
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnBack.setBackground(new Color(240, 240, 240));
        btnBack.setBounds(400, 225, 170, 50);
        contentPane.add(btnBack);
        
        JButton btnSearch = new JButton("Save");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String old = String.valueOf(PasswordField.getPassword());
                String pnew = String.valueOf(PasswordField1.getPassword());
                String renew = String.valueOf(PasswordField2.getPassword());
                if(Logindetails.checkpassword(name, old)) {
                	if(pnew.equals(renew)) {
                try {
                    Connection con = DB.getConnection();

                    PreparedStatement st = con.prepareStatement("Update librarian set password=? where id=?");

                    st.setString(1, renew);
                    st.setString(2, name);
                    st.executeUpdate();
                    System.out.println("updated password for id : " + name);
                    JOptionPane.showMessageDialog(btnSearch, "Password has been Changed Successfully\nLogin Again :)");
                    frame.dispose();
                    Login.main(new String[]{});

                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }else {
            	
					JOptionPane.showMessageDialog(ChangePassword.this, "Both Passwords doesn't match \nEnter again :-<");
					PasswordField1.setText("");
					PasswordField2.setText("");
					}
				}
				else{
					JOptionPane.showMessageDialog(ChangePassword.this,"Current Password is Incorrect !!!");
				PasswordField.setText("");
				PasswordField1.setText("");
				PasswordField2.setText("");
            };
 

            }
        });
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnSearch.setBackground(new Color(240, 240, 240));
        btnSearch.setBounds(210, 225, 170, 50);
        contentPane.add(btnSearch);

        lblEnterOldPassword = new JLabel("Enter Current Password :");
        lblEnterOldPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterOldPassword.setBounds(45, 37, 326, 40);
        contentPane.add(lblEnterOldPassword);
        
        lblEnterNewPassword = new JLabel("Enter New Password :");
        lblEnterNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEnterNewPassword.setBounds(45, 87, 326, 40);
        contentPane.add(lblEnterNewPassword);
        
        lblReEnterNewPassword = new JLabel("Re-enter New Password :");
        lblReEnterNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblReEnterNewPassword.setBounds(45, 137, 326, 40);
        contentPane.add(lblReEnterNewPassword);
    }
}