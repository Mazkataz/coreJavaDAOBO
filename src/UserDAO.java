import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	
	
	public List<User> getAllUsers() {

		Connection conn = null;
		List<User> list = null;

		try {
			conn = DbConnection.getConnection();

			PreparedStatement stmt = conn.prepareStatement("select * from user");

			ResultSet rs = stmt.executeQuery();
			list = new ArrayList<User>();

			while (rs.next()) {
				User user = null;

				
				 Integer id = rs.getInt("id");;
				String username = rs.getString("username");
				 String	password = rs.getString("password");
				 String	address = rs.getString("address");
				 String	role = rs.getString("address");
				
				
				

				user = new User(id, username, password, address,role );

				list.add(user);
				
			}

		} catch (ClassNotFoundException | SQLException | IOException e) {
			System.out.println("an error has occured");
			e.printStackTrace();
		}

		return list;
	}

	
}
