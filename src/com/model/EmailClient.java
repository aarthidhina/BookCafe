package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.model.CartDao;

public class EmailClient {

	public void sendBuyerRegistrationEmail(User user, String userName) {

		String subject = "Welcome to Book Cafe - The Online Bookstore!";
		String messageText = "Hi "
				+ user.getFirstName()
				+ "!"
				+ "\n\n"
				+ "Thank you for signing up with us. We are glad to welcome you to Book Cafe - Your online bookstore. Your username is "
				+ userName
				+ ". Login in and search our pool of books and make your first purchase with us."
				+ "\n\n" + "With Lots of Book Love," + "\n"
				+ "The Book Cafe Team";
		sendEmail(user.getEmail(), subject, messageText);

	}

	public void sendSellerRegistrationEmail(User user) {

		String subject = "Thank you for Signing up with The Book Cafe - The Online Bookstore!";
		String messageText = "Hi "
				+ user.getFirstName()
				+ "!"
				+ "\n\n"
				+ "Thank you for signing up with us. We are evaluating your account."
				+ "You will recieve an email as soon as our admin makes the decision."
				+ "\n\n" + "With Lots of Book Love," + "\n"
				+ "The Book Cafe Team";
		sendEmail(user.getEmail(), subject, messageText);

	}

	public void sendSellerApproveRegistrationEmail(User user, String userName) {

		String subject = "Welcome to Book Cafe - The Online Bookstore!";
		String messageText = "Hi "
				+ user.getFirstName()
				+ "!"
				+ "\n\n"
				+ "Thank you for signing up with us. Your registration has been aproved. We are glad to welcome you to Book Cafe - Your online bookstore. Your username is "
				+ userName
				+ ". Login in and add your books to our pool of books and make your first sale."
				+ "\n\n" + "With Lots of Book Love," + "\n"
				+ "The Book Cafe Team";
		sendEmail(user.getEmail(), subject, messageText);

	}

	public void sendSellerDeclineRegistrationEmail(User user) {

		String subject = "Book Cafe - The Online Bookstore Registration Declined";
		String messageText = "Hi "
				+ user.getFirstName()
				+ "!"
				+ "\n\n"
				+ "Thank you for signing up with us. We regret to inform that your registration has been declined. Please try signing up again later."
				+ "\n\n" + "Thanks," + "\n" + "The Book Cafe Team";
		sendEmail(user.getEmail(), subject, messageText);

	}

	public void sendUpdatePriceCartEmail(int book_id) {
		System.out.println("inside sendUpdatePriceEmail");
		// get all the user_id in cart details which has book_id in it
		CartDao cartDao = new CartDao();
		AuthDAO authDao = new AuthDAO();
//		System.out.println("book_id = " + book_id);
		List<Integer> userIdList = cartDao.getUserIdbyBookId(book_id);
		// for each userId in userIdList send email
		for (int i = 0; i < userIdList.size(); i++) {
			User user = authDao.getUserById(userIdList.get(i));
			String subject = "Price drop notice!";
			String messageText = "Good News "
					+ user.getFirstName()
					+ "!"
					+ "\n\n"
					+ "The price of a book in your cart dropped! Checkout and place order soon before the price changes!"
					+ "\n\n" + "Have a great shopping," + "\n"
					+ "The Book Cafe Team";
//			System.out.println(user.getEmail()+ subject+messageText);
			sendEmail(user.getEmail(), subject, messageText);
		}

	}
	
	public void sendUpdatePriceWishListEmail(int book_id) {
//		System.out.println("inside sendUpdatePriceEmail");
		// get all the user_id in cart details which has book_id in it
		WishlistDAO wishListDao = new WishlistDAO();
		AuthDAO authDao = new AuthDAO();
//		System.out.println("book_id = " + book_id);
		List<Integer> userIdList = wishListDao.getUserIdbyBookId(book_id);
		// for each userId in userIdList send email
		for (int i = 0; i < userIdList.size(); i++) {
			User user = authDao.getUserById(userIdList.get(i));
			String subject = "Price drop notice!";
			String messageText = "Good News "
					+ user.getFirstName()
					+ "!"
					+ "\n\n"
					+ "The price of a book in your wish list dropped! Add to cart and place order soon before the price changes!"
					+ "\n\n" + "Have a great shopping," + "\n"
					+ "The Book Cafe Team";
//			System.out.println(user.getEmail()+ subject+messageText);
			sendEmail(user.getEmail(), subject, messageText);
		}

	}

	public void sendCancelPartialOrderEmail(int orderId, int book_id, User user) {
		EditBookDAO editBookDao = new EditBookDAO();
		String subject = "Order Update - Paritial Order Cancelled";
		String messageText = "Hi "
				+ user.getFirstName()
				+ "!"
				+ "\n\n"
				+ "Your order with Order Numer: "
				+ orderId
				+ " is partially cancelled for the book "
				+ editBookDao.getBookById(book_id).getTitle()
				+ ". If you did not cancel it, please contact the admin or the seller.\n\n"
				+ "The Book Cafe Team";
		sendEmail(user.getEmail(), subject, messageText);
	}

	public void sendCancelOrderEmail(int orderId, User user) {
		String subject = "Order Update - Order Cancelled";
		String messageText = "Hi "
				+ user.getFirstName()
				+ "!"
				+ "\n\n"
				+ "Your order with Order Numer: "
				+ orderId
				+ " is cancelled. If you did not cancel it, please contact the admin or the seller.\n\n"
				+ "The Book Cafe Team";
		sendEmail(user.getEmail(), subject, messageText);
	}

	public void sendCompleteOrderEmail(int orderId, User user, String status) {
		String subject = "Order Updated!";
		String messageText = "Hi " + user.getFirstName() + "!" + "\n\n"
				+ "The status of your order with Order Numer: " + orderId
				+ " is updated to " + status + ".\n\n" + "The Book Cafe Team";
		sendEmail(user.getEmail(), subject, messageText);

	}

	public void sendOrderStatusUpdateEmail(int orderId, String status,
			int bookId, User user) {
		EditBookDAO editBookDao = new EditBookDAO();
		String subject = "Order Updated!";
		String messageText = "Hi " + user.getFirstName() + "!" + "\n\n"
				+ "The status of the book "
				+ editBookDao.getBookById(bookId).getTitle()
				+ " in your order with Order Numer: " + orderId
				+ " is updated to " + status + ".\n\n" + "The Book Cafe Team";
		sendEmail(user.getEmail(), subject, messageText);

	}

	public void sendOrderPlacedEmail(int orderId, User user) {
		// Display: order no, products ordered, shipping address, phone, email,
		// total price
		OrderDAO order = new OrderDAO();
		List<ViewOrder> orderList = order.getOrderDetails(orderId);
		EditBookDAO editBookDao = new EditBookDAO();
		String bookTitle = "";
		for (int i = 0; i < orderList.size(); i++) {
			bookTitle += "\t" + editBookDao.getBookById(orderList.get(i).getBook_id()).getTitle() + "	Qty : " +orderList.get(i).getQuantity() +"\n";
		}
		String subject = "Thank you for your order!";
		String messageText = "Hi "
				+ user.getFirstName()
				+ "!"
				+ "\n\n"
				+ "Thank you for placing order with us. Your order details are as follows: "
				+ "\n\nOrder Numer: " + orderId + "\nBooks Ordered:\n"
				+ bookTitle + "Total Price : " + String.format("%.2f", orderList.get(0).getTotal_price()) 
				+ "\nShipping Address : "
				+ orderList.get(0).getShipping_addr() + ", "
				+ orderList.get(0).getShipping_addr2() + ",\n"
				+ orderList.get(0).getCity() + ", "
				+ orderList.get(0).getState() + ", "
				+ orderList.get(0).getZipcode()
				+ "\nPhone : " + orderList.get(0).getPhone()
				+ "\n\nThe Book Cafe Team";
		sendEmail(user.getEmail(), subject, messageText);

	}

	public void sendReviewEmail(User user) {
		String subject = "Review your recent order";
		String messageText = "Hi " + user.getFirstName() + "!" + "\n\n"
				+ "Thank you for placing order with us. We value your feedback. Please login to your account and review the books and seller."
				+ "\n\n" + "The Book Cafe Team";
		sendEmail(user.getEmail(), subject, messageText);
	}
	
	public void sendAccountActivatedEmail(User user) {
		String subject = "Your Book Cafe Account is activated";
		String messageText = "Hi " + user.getFirstName() + "!" + "\n\n"
				+ "Thank you for re-activating your Book Cafe Account. You can now login to your account and start buying books."
				+ "\n\n" + "The Book Cafe Team";
		sendEmail(user.getEmail(), subject, messageText);
	}
	
	public void sendEmail(String to, String subject, String messageText) {
		// Recipient's email ID needs to be mentioned.
		// String to = "aarthi264@gmail.com";

		// Sender's email ID needs to be mentioned
		String from = "bookcafeonlinebookstore@gmail.com";
		final String username = "bookcafeonlinebookstore";// change accordingly
		final String password = "dlaa9876";// change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			message.setText(messageText);

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
