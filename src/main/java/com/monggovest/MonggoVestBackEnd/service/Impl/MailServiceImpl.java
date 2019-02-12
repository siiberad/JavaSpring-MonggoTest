package com.monggovest.MonggoVestBackEnd.service.Impl;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import com.monggovest.MonggoVestBackEnd.model.TransactionModel;
import com.monggovest.MonggoVestBackEnd.service.MailService;
import com.monggovest.MonggoVestBackEnd.utils.UtilsEditAgreement;
import com.monggovest.MonggoVestBackEnd.utils.UtilsEditInvoice;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.*;



@Service("mailService")
public class MailServiceImpl implements MailService {

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	UtilsEditInvoice utilsEditInvoice;

	@Autowired
	UtilsEditAgreement utilsEditAgreement;

	@Override
	public void sendEmail(Object object) {

		TransactionModel transactionModel = (TransactionModel) object;

		MimeMessagePreparator preparator = getContentWithAttachementMessagePreparator(transactionModel);

		try {
			mailSender.send(preparator);
			System.out.println("Email Send .....");
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}

	private MimeMessagePreparator getContentWithAttachementMessagePreparator(final TransactionModel transactionModel) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			ByteArrayOutputStream outputStream = null;
			public void prepare(MimeMessage mimeMessage) throws Exception {

				outputStream = new ByteArrayOutputStream();

				utilsEditInvoice.writeInv(outputStream, transactionModel);
				byte[] inv = outputStream.toByteArray();

				utilsEditAgreement.writeAgree(outputStream);
				byte[] agree = outputStream.toByteArray();

				DataSource invDataSource = new  ByteArrayDataSource(inv,"application/pdf");
				MimeBodyPart attachmentInv = new MimeBodyPart();
				attachmentInv.setDataHandler(new DataHandler(invDataSource));

				DataSource agreeDataSource = new  ByteArrayDataSource(agree,"application/pdf");
				MimeBodyPart attachmentAgree = new MimeBodyPart();
				attachmentAgree.setDataHandler(new DataHandler(agreeDataSource));

				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setSubject("This is you Invoice and Agreement Letter");
				helper.setFrom("mvestplus@gmail.com");
				helper.setTo(transactionModel.getUserModel().getUserEmail());
				String content = "Dear " + transactionModel.getUserModel().getUserName()
						+ ", thank you for placing order. Your order id is " + transactionModel.getTrxInvoiceNum() + ".";

				helper.setText(content);
				helper.addAttachment("invoice-" + transactionModel.getUserModel().getUserName() + ".pdf", invDataSource);
				helper.addAttachment("agreement-" + transactionModel.getUserModel().getUserName() + ".pdf", agreeDataSource);
//				helper.addAttachment("agreement.pdf", new ClassPathResource("CV.pdf"));
			}
		};
		return preparator;
	}

}
