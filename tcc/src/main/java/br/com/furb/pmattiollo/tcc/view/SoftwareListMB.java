package br.com.furb.pmattiollo.tcc.view;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import br.com.furb.pmattiollo.tcc.business.SoftwareBC;
import br.com.furb.pmattiollo.tcc.domain.SoftwareEntity;
import br.com.furb.pmattiollo.tcc.security.Identity;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;

@ViewController
@NextView("./software_edit.jsf")
@PreviousView("./software_list.jsf")
public class SoftwareListMB extends AbstractListPageBean<SoftwareEntity, Long> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Identity identity;

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
	
	public void preProcessXLS(Object document) {
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);		
		HSSFRow header = sheet.createRow(0);		
		HSSFCell cell = header.createCell(0);
		cell.setCellValue("Lisf of Sofwares");		
	}
	
	public void postProcessXLS(Object document) {
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow header = sheet.getRow(1);
		
		HSSFCellStyle cellStyle = wb.createCellStyle();  
		cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
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
		
		pdf.addTitle("List of Softwares");
		pdf.addAuthor(identity.getLoginUser());
		pdf.addCreationDate();
	}

}