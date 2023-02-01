package main;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import model.TestModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.Wait;

public class ReadExcelTest2 {
	private static WebDriver driver = null;
	private List<TestModel> list = new ArrayList<TestModel>();
	private String web = "";
	public static void main(String[] args) {
		ReadExcelTest2 rt = new ReadExcelTest2();
		rt.readFile();
	}
	
	private void readFile(){
		try {
			FileInputStream file = new FileInputStream(new File("your path¡Aex¡GC:\\selenium2.xlsx"));
			XSSFWorkbook workbook1 = new XSSFWorkbook(file);
			XSSFSheet sheet1 = workbook1.getSheetAt(0);
			int line = 0;
			String fileName = "";
			String owner = "";
			double date = 0; 
			String day = "";
			Cell webCell = sheet1.getRow(0).getCell(0);
			web = webCell.getStringCellValue();
			System.out.println("web:" + web);
			System.out.println(sheet1.getPhysicalNumberOfRows());
			for (int i = 2; i < sheet1.getPhysicalNumberOfRows(); i++) {
				line = i + 1;
				TestModel model = new TestModel();
				Row row = sheet1.getRow(i);
				for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
					Cell cell = row.getCell(j);
					if(j==0){
						model.setRowGet(cell.getStringCellValue());
					}else if(j==1){
						model.setRowName(cell.getStringCellValue());
					}else if(j==2){
						model.setRowType(cell.getStringCellValue());
					}else if(j==3){
						try{
							model.setRowInput(String.valueOf(cell.getNumericCellValue()).replace(".0", ""));
						}catch(Exception e){
							model.setRowInput(cell.getStringCellValue());
						}
					}else if(j==4){
						model.setDivide(cell.getNumericCellValue());
					}else if(j==5){
						if("radio".equals(model.getRowType())){
							double d = cell.getNumericCellValue();
							int q = (int)d;
							model.setRadioValue(q);
						}
					}
					
				}
				list.add(model);
			}
			file.close();
			runTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void runTest(){
		System.setProperty("webdriver.chrome.driver","your path¡Aex¡GC:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(web);	
		for(int i=0;i<list.size();i++){
			TestModel model = list.get(i);
			try {
				doTest(model);
				long timeDivide = (long) list.get(i).getDivide()*1000;
				Thread.sleep(timeDivide);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private void doTest(TestModel model){
		if("name".equals(model.getRowGet())){
			testByName(model);
		}else if("id".equals(model.getRowGet())){
			testById(model);
		}else if("xpath".equals(model.getRowGet())){
			testByXpath(model);
		}
	}
	
	private void testByName(TestModel model){
		if("text".equals(model.getRowType())){
			driver.findElement(By.name(model.getRowName())).sendKeys(model.getRowInput());
		}else if("select".equals(model.getRowType())){
			Select dropdown1 = new Select(driver.findElement(By.name(model.getRowName())));
			dropdown1.selectByVisibleText(model.getRowInput());
		}else if("checkbox".equals(model.getRowType())){
			driver.findElements(By.name(model.getRowName())).get(model.getRadioValue()).click();
		}else if("button".equals(model.getRowType())){
			driver.findElement(By.name(model.getRowName())).click();
		}else if("radio".equals(model.getRowType())){
			driver.findElements(By.name(model.getRowName())).get(model.getRadioValue()).click();
		}
	}
	
	private void testById(TestModel model){
		if("text".equals(model.getRowType())){
			driver.findElement(By.id(model.getRowName())).sendKeys(model.getRowInput());
		}else if("select".equals(model.getRowType())){
			Select dropdown1 = new Select(driver.findElement(By.id(model.getRowName())));
			dropdown1.selectByVisibleText(model.getRowInput());
		}else if("checkbox".equals(model.getRowType())){
			driver.findElements(By.id(model.getRowName())).get(model.getRadioValue()).click();
		}else if("button".equals(model.getRowType())){
			driver.findElement(By.id(model.getRowName())).click();
		}else if("radio".equals(model.getRowType())){
			driver.findElements(By.id(model.getRowName())).get(model.getRadioValue()).click();
		}
	}
	
	private void testByXpath(TestModel model){
		if("text".equals(model.getRowType())){
			driver.findElement(By.xpath(model.getRowName())).sendKeys(model.getRowInput());
		}else if("select".equals(model.getRowType())){
			Select dropdown1 = new Select(driver.findElement(By.xpath(model.getRowName())));
			dropdown1.selectByVisibleText(model.getRowInput());
		}else if("checkbox".equals(model.getRowType())){
			driver.findElements(By.xpath(model.getRowName())).get(model.getRadioValue()).click();
		}else if("button".equals(model.getRowType())){
			driver.findElement(By.xpath(model.getRowName())).click();
		}else if("radio".equals(model.getRowType())){
			driver.findElements(By.xpath(model.getRowName())).get(model.getRadioValue()).click();
		}
	}
	
}
