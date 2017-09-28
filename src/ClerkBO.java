import java.util.List;

public class ClerkBO extends UserBO {
	public List<Invoice> listInvoice() throws InsufficientPrivilegeException {
		throw new InsufficientPrivilegeException("Permission Denied");
	}

	public Integer createInvoice(Invoice invoiceObject) {
		InvoiceDAO invoice = new InvoiceDAO();

		return invoice.invoiceCreation(invoiceObject);

	}

	public Boolean updateInvoiceStatus(Invoice invoiceObj, String status) throws InsufficientPrivilegeException {

		throw new InsufficientPrivilegeException("Permission Denied");

	}
	
	public Boolean invoicePayment(Invoice invoiceObj) throws InsufficientPrivilegeException {
		throw new InsufficientPrivilegeException("Permission Denied");

	}
	
}
