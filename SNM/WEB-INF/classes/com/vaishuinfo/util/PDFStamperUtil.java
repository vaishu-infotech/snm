package com.vaishuinfo.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfStamper;

public class PDFStamperUtil {
	private PdfStamper stamper; 

	Font fntSmallNormal = FontFactory.getFont("Arial", 9, Font.BOLD, new Color(0,0,0));
	Color standard = new Color(255, 255, 255);
	
	public PDFStamperUtil(PdfStamper stamper, String name1, String name2, String addr1, String addr2, String hdbRef, String acctType){
		this.stamper = stamper;
	}
	public void stamp() throws IOException, DocumentException{
		PdfContentByte over = stamper.getOverContent(1);    
		stamper.close();
	}
	public Image PdfPImg(){
		InputStream in = PDFStamperUtil.class.getResourceAsStream("check5.gif");
		Image img = null;
		try {
			BufferedImage bi = ((BufferedImage)ImageIO.read(in));
			img = Image.getInstance(bi, null);
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	public PdfPCell PdfPCell(String writestr, Font fonttype, int hAlign, int vAlign, int border, Color color, int colSpan) {
		PdfPCell cell = new PdfPCell();
		cell.setPhrase(new Phrase(writestr, fonttype));
		cell.setHorizontalAlignment(hAlign);
		cell.setVerticalAlignment(vAlign);			
		cell.setBorder(border);
		cell.setBorderWidth(1);
		cell.setBackgroundColor(color);
		cell.setColspan(colSpan);
		return cell;
	}
}
