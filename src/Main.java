import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		User user;
		UserBO userBO;
		String status;
		Boolean logout;
		Invoice invoice = null;
		Integer statusId;
		Integer invoiceId;
		String newLine = "\n";
		String invoiceDetails;
		List<Invoice> invoiceList;
		StringBuilder mainMenu = new StringBuilder();
		StringBuilder userMenu = new StringBuilder();
		StringBuilder auditorMenu = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		mainMenu.append("1. Login").append(newLine).append("2. Exit").append(newLine).append("Enter the choice:");
		userMenu.append("1. Create invoice").append(newLine).append("2. Update invoice").append(newLine)
				.append("3. Invoice Payment").append(newLine).append("4. Logout").append(newLine)
				.append("Enter the choice:");
		auditorMenu.append("1. Approved").append(newLine).append("2. Deny");
		List<String> auditorList = new ArrayList<String>() {
			{
				add("Approved");
				add("Deny");
			}
		};
		while (true) {
			System.out.println(mainMenu.toString());
			int choice = new Integer(br.readLine());
			switch (choice) {
			case 1:
				System.out.println("Enter the username:");
				String userName = br.readLine();
				System.out.println("Enter the password:");
				String password = br.readLine();

				user = UserBO.validateUser(userName, password);
				userBO = Main.findRole(user);

				if (user != null) {
					logout = false;

					while (true) {
						System.out.println(userMenu.toString());
						int userChoice = new Integer(br.readLine());
						switch (userChoice) {
						case 1:
							try {
								System.out.println("Enter the invoice detail:");
								invoiceDetails = br.readLine();

								String[] invSplit = invoiceDetails.split(",");
								invoice = new Invoice();

								invoice.setInvoiceNumber(invSplit[0]);

								invoice.setAmount((Integer.parseInt(invSplit[1])));

								invoice.setCreatedBy(user);

								invoice.setCreatedDate(new Date());

								userBO.createInvoice(invoice);

							} catch (Exception e) {
								System.out.println(e.toString());
							}
							break;
						case 2: //
							try {
								invoiceList = userBO.listInvoice();

								if (!invoiceList.isEmpty()) {
									System.out.format("%-5s %-15s %-10s %-15s %-15s %s\n", "Id", "Invoice number",
											"Amount", "Status", "Created by", "Created on");
									for (Invoice inv : invoiceList) {
										System.out.println(inv.getInvoiceNumber() + inv.getAmount() + inv.getStatus()
												+ inv.getCreatedBy() + inv.getCreatedDate());

									}
									System.out.println("Enter the id to update the status:");
									invoiceId = new Integer(br.readLine());
									
									boolean in = false;
									
									for (Invoice inv : invoiceList) {
										if(inv.getId() == invoiceId) {in = true; invoice = inv;} 
									}
									
									if (in == true) {
										System.out.println(auditorMenu.toString());
										System.out.println("Enter the status number:");
										statusId = new Integer(br.readLine());

									if(statusId==1) { status = "APPROVED";}
									else status = "DENY"; 
									
									userBO.updateInvoiceStatus(invoice, status);
									
									} else {
										System.out.println("Invoice Id is invalid");
									}
								}
							} catch (Exception e) {
								System.out.println(e.toString());
							}
							break;
						case 3: // invoice payment
							try {
								invoiceList = userBO.listInvoice();

								if (!invoiceList.isEmpty()) {
									System.out.format("%-5s %-15s %-10s %-15s %-15s %s\n", "Id", "Invoice number",
											"Amount", "Status", "Created by", "Created on");

									for (Invoice inv : invoiceList) {
										System.out.println(inv.getInvoiceNumber()  +inv.getAmount() + inv.getStatus()+inv.getCreatedBy() + inv.getCreatedDate());

									}

									System.out.println("Enter the id to Pay:");
									invoiceId = new Integer(br.readLine());
									
									boolean in = false;
									
									for (Invoice inv : invoiceList) {
										if(inv.getId() == invoiceId) {in = true; invoice = inv;} 
									}

									if (in == true) {

									
										userBO.invoicePayment(invoice);
									
									
									
									} else {
										System.out.println("Invoice Id is invalid");
									}
								}
							} catch (Exception e) {
								System.out.println(e.toString());
							}
							break;
						case 4:
							logout = true;
							break;
						}
						if (logout)
							break;
					}
				} else
					System.out.println("Invalid username or password");
				break;
			case 2:
				System.exit(0);
			}
		}
	}

	public static UserBO findRole(User user) {
		String role = user.getRole();
		if (role.equalsIgnoreCase("clerk")) {
			ClerkBO cb = new ClerkBO();
			return cb;
		}
		if (role.equalsIgnoreCase("payment releaser")) {
			PaymentReleaserBO pr = new PaymentReleaserBO();
			return pr;
		}
		if (role.equalsIgnoreCase("auditor")) {
			AuditorBO ab = new AuditorBO();
			return ab;
		}

		// fill code here.
		return null;
	}
}
