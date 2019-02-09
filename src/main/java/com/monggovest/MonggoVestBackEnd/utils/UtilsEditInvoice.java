package com.monggovest.MonggoVestBackEnd.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.util.Date;

@Component
public class UtilsEditInvoice {

    public void writePdf(OutputStream outputStream) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

//        Image image = Image.getInstance ("src/pdf/java4s.png");
//        image.scaleAbsolute(120f, 60f);//image width,height

        //Inserting Table in PDF
        PdfPTable table=new PdfPTable(3);

        PdfPCell cell = new PdfPCell (new Paragraph ("Java4s.com"));

        cell.setColspan (3);
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (10.0f);
        cell.setBackgroundColor (new BaseColor(140, 221, 8));

        table.addCell(cell);

        table.addCell("Name");
        table.addCell("Address");
        table.addCell("Country");
        table.addCell("Java4s");
        table.addCell("NC");
        table.addCell("United States");
        table.setSpacingBefore(30.0f);       // Space Before table starts, like margin-top in CSS
        table.setSpacingAfter(30.0f);        // Space After table starts, like margin-Bottom in CSS

        //Inserting List in PDF
//        List list=new List(true,30);
//        list.add(new ListItem("Java4s"));
//        list.add(new ListItem("Php4s"));
//        list.add(new ListItem("Some Thing..."));

        //Text formating in PDF
        Chunk chunk=new Chunk("Welecome To Java4s Programming Blog...");
        chunk.setUnderline(+1f,-2f);//1st co-ordinate is for line width,2nd is space between
        Chunk chunk1=new Chunk("Php4s.com");
        chunk1.setUnderline(+4f,-8f);
        chunk1.setBackground(new BaseColor (17, 46, 193));

        //Now Insert Every Thing Into PDF Document
        document.open();//PDF document opened........

//        document.add(image);

        document.add(Chunk.NEWLINE);   //Something like in HTML 🙂

        document.add(new Paragraph("Dear Java4s.com"));
        document.add(new Paragraph("Document Generated On - "+new Date().toString()));

        document.add(table);

        document.add(chunk);
        document.add(chunk1);

        document.add(Chunk.NEWLINE);   //Something like in HTML 🙂

//        document.newPage();            //Opened new page
//        document.add(list);            //In the new page we are going to add list

        document.close();
    }


}
