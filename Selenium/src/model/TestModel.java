package model;

public class TestModel {
	private String rowGet;   //欄位取得方式			
	private String rowName;  //欄位命名值
	private String rowType;  //欄位類型
	private String rowInput; //填入值
	private int radioValue;  //RadioGroup第幾個選項
	private double divide;   //等待時間
	private String web;      //測試網址
	private String action;   //執行動作
	
	public String getRowGet() {
		return rowGet;
	}
	public void setRowGet(String rowGet) {
		this.rowGet = rowGet;
	}
	public String getRowName() {
		return rowName;
	}
	public void setRowName(String rowName) {
		this.rowName = rowName;
	}
	public String getRowType() {
		return rowType;
	}
	public void setRowType(String rowType) {
		this.rowType = rowType;
	}
	public String getRowInput() {
		return rowInput;
	}
	public void setRowInput(String rowInput) {
		this.rowInput = rowInput;
	}
	public double getDivide() {
		return divide;
	}
	public void setDivide(double divide) {
		this.divide = divide;
	}
	public int getRadioValue() {
		return radioValue;
	}
	public void setRadioValue(int radioValue) {
		this.radioValue = radioValue;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
