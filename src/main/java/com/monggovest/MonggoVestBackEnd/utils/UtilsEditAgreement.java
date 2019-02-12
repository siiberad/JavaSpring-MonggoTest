package com.monggovest.MonggoVestBackEnd.utils;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.OutputStream;

@Component
public class UtilsEditAgreement {
    public void writeAgree(OutputStream outputStream) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();
        document.add(Chunk.NEWLINE);
        document.close();
    }

}
