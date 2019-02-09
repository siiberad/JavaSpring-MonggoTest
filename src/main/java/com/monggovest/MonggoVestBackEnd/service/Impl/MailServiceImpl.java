package com.monggovest.MonggoVestBackEnd.service.Impl;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import com.monggovest.MonggoVestBackEnd.model.TransactionModel;
import com.monggovest.MonggoVestBackEnd.service.MailService;
import com.monggovest.MonggoVestBackEnd.utils.UtilsEditInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import sun.misc.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;


@Service("mailService")
public class MailServiceImpl implements MailService {

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	UtilsEditInvoice utilsEditInvoice;

	@Override
	public void sendEmail(Object object) {

		TransactionModel order = (TransactionModel) object;

		MimeMessagePreparator preparator = getContentWithAttachementMessagePreparator(order);

		try {
			mailSender.send(preparator);
			System.out.println("Message With Attachement has been sent.............................");
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}

	private MimeMessagePreparator getContentWithAttachementMessagePreparator(final TransactionModel order) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			ByteArrayOutputStream outputStream = null;
			public void prepare(MimeMessage mimeMessage) throws Exception {

				outputStream = new ByteArrayOutputStream();
				utilsEditInvoice.writePdf(outputStream);
				byte[] bytes = outputStream.toByteArray();

				DataSource dataSource = new  ByteArrayDataSource(bytes,"application/pdf");
				MimeBodyPart attachmentPart = new MimeBodyPart();
				attachmentPart.setDataHandler(new DataHandler(dataSource));

				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setSubject("Your order on Demoapp with attachement");
				helper.setFrom("customerserivces@yourshop.com");
				helper.setTo(order.getUserModel().getUserEmail());
				String content = "Dear " + order.getUserModel().getUserName()
						+ ", thank you for placing order. Your order id is " + order.getTrxInvoiceNum() + ".";

				helper.setText(content);
				helper.addAttachment("invoice-" + order.getUserModel().getUserName() + ".pdf", dataSource);
				helper.addAttachment("agreement.pdf", new ClassPathResource("CV.pdf"));
			}
		};
		return preparator;
	}
}
