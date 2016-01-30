package mailbox.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.*;
import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Message-Driven Bean implementation class for: MailService
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class MailService implements MessageListener {

	/**
	 * Default constructor.
	 */
	public MailService() {
	}
	// TODO Auto-generated constructor stub

	public static Session getGMailSession(String user, String pass) {
		final Properties props = new Properties();

		// Zum Empfangen
		props.setProperty("mail.pop3.host", "pop.gmail.com");
		props.setProperty("mail.pop3.user", user);
		props.setProperty("mail.pop3.password", pass);
		props.setProperty("mail.pop3.port", "995");
		props.setProperty("mail.pop3.auth", "true");
		props.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		// Zum Senden
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");

		return Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(props.getProperty("mail.pop3.user"),
						props.getProperty("mail.pop3.password"));
			}
		});

	}

	
	public static Folder openPop3InboxReadOnly( Session session )
			  throws MessagingException
			{
			  Store store = session.getStore( "pop3" );
			  store.connect();

			  Folder folder = store.getFolder( "INBOX" );
			  folder.open( Folder.READ_ONLY );

			  return folder;
			}
			public static void closeInbox( Folder folder ) throws MessagingException
			{
			  folder.close( false );
			  folder.getStore().close();
			}
	
	
	
	
	public static void postMail(Session session, String recipient, String subject, String message)
			throws MessagingException {
		Message msg = new MimeMessage(session);

		InternetAddress addressTo = new InternetAddress(recipient);
		msg.setRecipient(Message.RecipientType.TO, addressTo);

		msg.setSubject(subject);
		msg.setContent(message, "text/plain");
		Transport.send(msg);
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		// TODO Auto-generated method stub

	}

}
