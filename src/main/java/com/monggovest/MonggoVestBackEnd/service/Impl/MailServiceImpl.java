package com.monggovest.MonggoVestBackEnd.service.Impl;

import javax.mail.internet.MimeMessage;

import com.monggovest.MonggoVestBackEnd.model.TransactionModel;
import com.monggovest.MonggoVestBackEnd.service.in.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailServiceImpl implements MailService {

	@Autowired
	JavaMailSender mailSender;

	@Override
	public void sendEmail(Object object) {

		TransactionModel order = (TransactionModel) object;

		MimeMessagePreparator preparator = getContentWtihAttachementMessagePreparator(order);

		try {
			mailSender.send(preparator);
			System.out.println("Message With Attachement has been sent.............................");
			preparator = getContentAsInlineResourceMessagePreparator(order);
			mailSender.send(preparator);
			System.out.println("Message With Inline Resource has been sent.........................");
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}

	private MimeMessagePreparator getContentWtihAttachementMessagePreparator(final TransactionModel order) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				helper.setSubject("Your order on Demoapp with attachement");
				helper.setFrom("customerserivces@yourshop.com");
				helper.setTo(order.getUserModel().getUserEmail());
				String content = "Dear " + order.getUserModel().getUserName()
						+ ", thank you for placing order. Your order id is " + order.getTrxInvoiceNum() + ".";

				helper.setText(content);

				// Add a resource as an attachment
				helper.addAttachment("file.txt", new ClassPathResource("linux-icon.png"));

			}
		};
		return preparator;
	}

	private MimeMessagePreparator getContentAsInlineResourceMessagePreparator(final TransactionModel order) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

				helper.setSubject("Your order on Demoapp with Inline resource");
				helper.setFrom("customerserivces@yourshop.com");
				helper.setTo(order.getUserModel().getUserEmail());

				String content = "Dear " + order.getUserModel().getUserName()
						+ ", thank you for placing order. Your order id is " + order.getTrxInvoiceNum() + ".";

				// Add an inline resource.
				// use the true flag to indicate you need a multipart message
				helper.setText("<html><body><p>" + content + "</p><img src='cid:company-logo'></body></html>", true);
				helper.addInline("company-logo", new ClassPathResource("linux-icon.png"));
			}
		};
		return preparator;
	}

}
