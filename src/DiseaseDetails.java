
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DiseaseDetails {

	public static boolean checkDisease(String SymptomName){
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
	public static int insertDisease(String symptomName, String description, String medication){
		int status=0;
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into disease(name,descr,medicine) values(?,?,?)");
			ps.setString(1,symptomName);
			ps.setString(2,description);
			ps.setString(3,medication);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}

	public static int deleteDisease(String name){
		int status=0;
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from disease where name=?");
			ps.setString(1,name);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
}
