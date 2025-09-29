
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SymptomDetails {

	public static boolean checkSymptom(String SymptomName){
		boolean status=false;
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from symptom where name=?");
			ps.setString(1,SymptomName);
		    ResultSet rs=ps.executeQuery();
			status=rs.next();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static int insertSymptom(String symptomName, String description, String disease){
		int status=0;
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into symptom(name,test,disease_id) values(?,?,(select disease_id from disease where name=?))");
			ps.setString(1,symptomName);
			ps.setString(2,description);
			ps.setString(3,disease);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}

	public static int deleteSymptom(String name){
		int status=0;
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from symptom where name=?");
			ps.setString(1,name);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
}
