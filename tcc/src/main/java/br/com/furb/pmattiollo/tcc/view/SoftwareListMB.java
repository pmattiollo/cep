package br.com.furb.pmattiollo.tcc.view;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import br.com.furb.pmattiollo.tcc.business.SoftwareBC;
import br.com.furb.pmattiollo.tcc.domain.SoftwareEntity;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;

@ViewController
@NextView("./software_edit.jsf")
@PreviousView("./software_list.jsf")
public class SoftwareListMB extends AbstractListPageBean<SoftwareEntity, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private SoftwareBC softwareBC;
	
	@Override
	protected List<SoftwareEntity> handleResultList() {
		return this.softwareBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				softwareBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}
	
	public void postProcessXLS(Object document) {
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow header = sheet.getRow(0);
		
		HSSFCellStyle cellStyle = wb.createCellStyle();  
		cellStyle.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
			HSSFCell cell = header.getCell(i);	             
			cell.setCellStyle(cellStyle);
		}
	}
	 
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String logo = servletContext.getRealPath("") + File.separator + "images" + File.separator + "report.jpg";
		Image i = Image.getInstance(logo);
		i.setAlignment(Element.ALIGN_CENTER);
		
		Paragraph p1 = new Paragraph("List of Softwares");
		p1.setAlignment(Element.ALIGN_CENTER);
		
		Paragraph p2 = new Paragraph("---------------------------------------------------------------------------");
		p2.setAlignment(Element.ALIGN_CENTER);
		
		pdf.add(i);
		pdf.add(p1);
		pdf.add(p2);
	}

}