package com.monggovest.MonggoVestBackEnd.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.monggovest.MonggoVestBackEnd.model.AuditModel;
import com.monggovest.MonggoVestBackEnd.model.TransactionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class UtilsEditInvoice{

    public void writeInv(OutputStream outputStream, TransactionModel transactionModel) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        Image image = Image.getInstance ("src/main/resources/logo.png");
        image.setAlignment (Element.ALIGN_LEFT);
        image.scaleAbsolute(160f, 42f);

        PdfPTable irdTable = new PdfPTable(2);
        irdTable.setWidthPercentage(100);
        irdTable.setWidths(new float[] { 5,3 });
        irdTable.addCell(getIRDCell("Invoice No"));
        irdTable.addCell(getIRDCell("Invoice Date"));
        irdTable.addCell(getIRDCell(transactionModel.getTrxInvoiceNum()));
        irdTable.addCell(getPdfDate());

        PdfPTable irhTable = new PdfPTable(3);
        irhTable.setWidthPercentage(100);

        irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
        irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
        irhTable.addCell(getIRHCell("Invoice", PdfPCell.ALIGN_RIGHT));
        irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
        irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
        PdfPCell invoiceTable = new PdfPCell (irdTable);
        invoiceTable.setBorder(0);
        irhTable.addCell(invoiceTable);

        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.BOLD);
        fs.addFont(font);
        Phrase bill = fs.process("Bill To");
        Paragraph name = new Paragraph("Name    : "+transactionModel.getUserModel().getUserName());
        name.setIndentationLeft(15);
        Paragraph email = new Paragraph("Email    : "+transactionModel.getUserModel().getUserEmail());
        email.setIndentationLeft(15);
        Paragraph trx = new Paragraph("Transaction Number    : "+transactionModel.getTrxInvoiceNum());
        trx.setIndentationLeft(15);


        PdfPTable billTable = new PdfPTable(5);
        billTable.setWidthPercentage(100);
        billTable.setWidths(new float[] { 3,4,2,3,2 });
        billTable.setSpacingBefore(30.0f);
        billTable.addCell(getBillHeaderCell("Product Name"));
        billTable.addCell(getBillHeaderCell("Description"));
        billTable.addCell(getBillHeaderCell("Unit Price per Lot"));
        billTable.addCell(getBillHeaderCell("Jumlah Lot"));
        billTable.addCell(getBillHeaderCell("Amount"));

        billTable.addCell(getBillRowCell("Sapi Penggemukan Irlandia"));
        billTable.addCell(getBillRowCell("Description"));
        billTable.addCell(getBillRowCell("Rp.500.000"));
        billTable.addCell(getBillRowCell("{Jumlah Lot}"));
        billTable.addCell(getBillRowCell("{Total}"));


        PdfPTable validity = new PdfPTable(1);
        validity.setWidthPercentage(100);
        PdfPCell summaryL = new PdfPCell (validity);
        summaryL.setColspan (3);
        summaryL.setPadding (1.0f);
        billTable.addCell(summaryL);

        PdfPTable accounts = new PdfPTable(2);
        accounts.setWidthPercentage(100);
        accounts.addCell(getAccountsCell("Tax"));
        accounts.addCell(getAccountsCellR("000"));
        accounts.addCell(getAccountsCell("Total"));
        accounts.addCell(getAccountsCellR("000"));
        PdfPCell summaryR = new PdfPCell (accounts);
        summaryR.setColspan (3);
        billTable.addCell(summaryR);

        PdfPTable describer = new PdfPTable(1);
        describer.setWidthPercentage(100);
        describer.addCell(getdescCell(" "));
        describer.addCell(getdescCell("+82123 0000 0000 || MONGGO VEST PLUS || Invest Barokah"));

        document.open();//PDF document opened........

        document.add(image);
        document.add(irhTable);
        document.add(bill);
        document.add(name);
        document.add(email);
        document.add(trx);
        document.add(billTable);
        document.add(describer);

        document.close();
    }

    public static PdfPCell getIRHCell(String text, int alignment) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 16);
        /*	font.setColor(BaseColor.GRAY);*/
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setPadding(5);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    public static PdfPCell getIRDCell(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (5.0f);
        return cell;
    }
    private String getPdfDate () {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
        String strDate = dateFormat.format(date);

        PdfPCell cell = new PdfPCell (new Paragraph (strDate));
        cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
        cell.setPadding (2.0f);
        return strDate;
    }

    public static PdfPCell getBillHeaderCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell (phrase);
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (5.0f);
        return cell;
    }

    public static PdfPCell getBillRowCell(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (5.0f);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);
        return cell;
    }

    public static PdfPCell getBillFooterCell(String text) {
        PdfPCell cell = new PdfPCell (new Paragraph (text));
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (5.0f);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);
        return cell;
    }

    public static PdfPCell getValidityCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell (phrase);
        cell.setBorder(0);
        return cell;
    }

    public static PdfPCell getAccountsCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell (phrase);
        cell.setBorderWidthRight(0);
        cell.setBorderWidthTop(0);
        cell.setPadding (5.0f);
        return cell;
    }
    public static PdfPCell getAccountsCellR(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell (phrase);
        cell.setBorderWidthLeft(0);
        cell.setBorderWidthTop(0);
        cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
        cell.setPadding (5.0f);
        cell.setPaddingRight(20.0f);
        return cell;
    }

    public static PdfPCell getdescCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell (phrase);
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setBorder(0);
        return cell;
    }

}
