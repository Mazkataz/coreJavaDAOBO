import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AuditorBO extends UserBO{

public List<Invoice> listInvoice(){
	
	InvoiceDAO invoicedao = new InvoiceDAO(); 
	
	List<Invoice> list = invoicedao.getAllInvoiceList(); 
	
	for(Invoice inv : list) {
		if(!(inv.getStatus().equals("Paid"))) {
			list.remove(inv); 
		}
		
		return list; 
	}
	
	return list; 
	
}

public Integer createInvoice(Invoice invoiceObj) throws InsufficientPrivilegeException {
	throw new InsufficientPrivilegeException("Permission Denied");

}

public Boolean updateInvoiceStatus(Invoice invoiceObj, String status) throws ClassNotFoundException, SQLException, IOException {
	InvoiceDAO invoice = new InvoiceDAO();

 invoice.updateInvoiceStatus(invoiceObj, status);
 
 return true; 
}

public Boolean invoicePayment(Invoice invoiceObj) throws InsufficientPrivilegeException {
	throw new InsufficientPrivilegeException("Permission Denied");

}

}
