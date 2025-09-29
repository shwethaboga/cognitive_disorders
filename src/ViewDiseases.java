import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

    public class ViewDiseases extends JFrame {

        private JPanel contentPane;
        private JTable table;
        static ViewDiseases frame = new ViewDiseases();


        public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        frame.setTitle("Diseases");
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
        public ViewDiseases() {
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setBounds(100, 100, 450, 300);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            contentPane.setLayout(new BorderLayout(0, 0));
            setContentPane(contentPane);
            JButton btnBack = new JButton("Back");
            btnBack.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Menu.main(new String[]{});
                    frame.dispose();
                }
            });
            btnBack.setFont(new Font("Tahoma", Font.PLAIN, 13));

            String data[][]=null;
            String column[]=null;
            try{
                Connection con=DB.getConnection();
                PreparedStatement ps=con.prepareStatement("select * from disease",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                ResultSet rs=ps.executeQuery();

                ResultSetMetaData rsmd=rs.getMetaData();
                int cols=rsmd.getColumnCount();
                column=new String[cols];
                for(int i=1;i<=cols;i++){
                    column[i-1]=rsmd.getColumnName(i);
                }

                rs.last();
                int rows=rs.getRow();
                rs.beforeFirst();

                data=new String[rows][cols];
                int count=0;
                while(rs.next()){
                    for(int i=1;i<=cols;i++){
                        data[count][i-1]=rs.getString(i);
                    }
                    count++;
                }
                con.close();
            }catch(Exception e){System.out.println(e);}

            table = new JTable(data,column);
            table.setDefaultEditor(Object.class, null);
            JScrollPane sp=new JScrollPane(table);

            contentPane.add(sp, BorderLayout.CENTER);
        }

    }


