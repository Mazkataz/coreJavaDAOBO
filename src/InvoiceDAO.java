import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceDAO {

	Integer invoiceCreation(Invoice inv) {

		Connection conn = null;
		Integer id = null;

		PreparedStatement stmt = null;
		try {
			conn = DbConnection.getConnection();
			stmt = conn.prepareStatement(
					"INSERT into Invoice (invoice_number, status, amount, created_date, user_id) values (?, ?, ?, ?, ?)");

			stmt.setString(1, inv.getInvoiceNumber());
			stmt.setString(2, inv.getStatus());
			stmt.setInt(3, inv.getAmount());
			stmt.setLong(4, inv.getCreatedBy().getId());
			stmt.setDate(5, (java.sql.Date) inv.getCreatedDate());

			stmt.setInt(1, inv.getId());

			stmt.executeUpdate();

			ResultSet rs_key = stmt.getGeneratedKeys();

			if (rs_key.first()) {
				id = rs_key.getInt("id");
			}

			return id;

		} catch (SQLException | ClassNotFoundException | IOException e) {
			System.out.println("sql db error");
			e.printStackTrace();
		}
		return id;

	}

	List<Invoice> getAllInvoiceList() {

		Connection conn = null;
		List<Invoice> list = null;

		try {
			conn = DbConnection.getConnection();

			PreparedStatement stmt = conn.prepareStatement("Select * from invoice");

			ResultSet rs = stmt.executeQuery();
			list = new ArrayList<Invoice>();

			while (rs.next()) {
				Invoice invoice = null;

				Integer id = rs.getInt("id");
				String invoicenumber = rs.getString("invoice_number");
				String status = rs.getString("status");
				Integer amount = rs.getInt("amount");
				Date cd = rs.getDate("created_date");
				Integer userId = rs.getInt("user_id");
		

				//need method to get user from userId for constructor
				invoice = new Invoice(id, invoicenumber, status, amount, cd);

				list.add(invoice);

			}

		} catch (ClassNotFoundException | SQLException | IOException e) {
			System.out.println("an error has occured");
			e.printStackTrace();
		}

		return list;
	}
	Connection conn = null;

	public void updateInvoiceStatus(Invoice invoiceObj, String status) throws SQLException, ClassNotFoundException, IOException {

		try {
			conn = DbConnection.getConnection();

			PreparedStatement stmt = conn.prepareStatement("SET status = ? from invoice where invoiceId = ?");
			stmt.setString(1,status) ;
			stmt.setInt(2, invoiceObj.getId());
			
			 stmt.executeUpdate();

		stmt.close(); 
		
		} finally {
			conn.close(); 
		
		}
	}
		
	

	void invoicePayment(Integer Id ) throws SQLException, ClassNotFoundException, IOException {
		Connection conn = null;

		try {
			conn = DbConnection.getConnection();

			PreparedStatement stmt = conn.prepareStatement("SET status = ? from invoice where invoiceId = ?");
			stmt.setString(1, "Paid");
			stmt.setInt(2,Id);
			
			 stmt.executeUpdate();

		stmt.close(); 
		
		} finally {
			conn.close(); 
		
		}
	}
}
