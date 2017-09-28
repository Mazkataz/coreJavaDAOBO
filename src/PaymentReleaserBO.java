import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PaymentReleaserBO extends UserBO{

	public List<Invoice> listInvoice(){
		InvoiceDAO invoicedao = new InvoiceDAO(); 
		
		List<Invoice> list = invoicedao.getAllInvoiceList(); 
		
		for(Invoice inv : list) {
			if((inv.getStatus().equals("Pending"))) {
				list.remove(inv); 
			}
			
			return list; 
		}
		
		return list; 
		
		
	}
	
	public Integer createInvoice(Invoice invoiceObj) throws InsufficientPrivilegeException {
		throw new InsufficientPrivilegeException("Permission Denied"); 

	}
	
	public Boolean updateInvoiceStatus(Invoice invoiceObj ,String status) throws InsufficientPrivilegeException {
		throw new InsufficientPrivilegeException("Permission Denied"); 
	}
	
	public Boolean invoicePayment(Invoice invoiceObj ) {
		InvoiceDAO invoicedao = new InvoiceDAO(); 

		try {
			invoicedao.invoicePayment(invoiceObj.getId());
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
}
