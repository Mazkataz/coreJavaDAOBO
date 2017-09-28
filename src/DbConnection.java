import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
	
public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {        
        
	
	ResourceBundle rb= ResourceBundle.getBundle("mysql");
    String url = rb.getString("db.url");
    String username = rb.getString("db.username");
    String password = rb.getString("db.password");
    String driver = rb.getString("db.driver");
	
	
	//	final Properties prop = new Properties();
		//final InputStream inputStream = DbConnection.class.getClassLoader()
		//		.getResourceAsStream(
		//				"mysql");
	//	prop.load(inputStream);
		Class.forName(driver);
		final Connection connection = DriverManager.getConnection(url, 
				username, password);

		return connection;
	}
	
	
}
