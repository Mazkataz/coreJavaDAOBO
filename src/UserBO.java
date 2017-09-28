import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class UserBO {
	
	public abstract List<Invoice> listInvoice() throws InsufficientPrivilegeException;
	public abstract Integer createInvoice(Invoice invoice) throws InsufficientPrivilegeException;
	public abstract Boolean invoicePayment (Invoice invoiceObj) throws InsufficientPrivilegeException;
	public abstract Boolean updateInvoiceStatus(Invoice invoice, String status) throws ClassNotFoundException, SQLException, IOException, InsufficientPrivilegeException;
	public static User validateUser(String username,String password) {
		
		UserDAO ud = new UserDAO(); 
		
		List<User> list = new ArrayList<User>(); 
		
		list = ud.getAllUsers(); 
		
		for(User user: list) {
			
			if(user.getUserName().equals(username) && user.getPassword().equals(password)) {
				return user; 
			}
		}
		return null; 
	}
}
