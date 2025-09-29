
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ChildDetails {

	public static boolean checkChild(String childName){
		boolean status=false;
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from child where name=?");
			ps.setString(1,childName);
		    ResultSet rs=ps.executeQuery();
			status=rs.next();

			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static int insertChild(String childName, String age, String symptom){
		int status=0;
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into child(name,age,symptom) values(?,?,?)");
			ps.setString(1,childName);
			ps.setString(2,age);
			ps.setString(3,symptom);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}

	public static int deleteChild(String name){
		int status=0;
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from child where name=?");
			ps.setString(1,name);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
}
