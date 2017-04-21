package com.print;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class PrintEntity {

	
	//private static int[] X={523,430,335,240,145,50};              //第n格的x坐标
	private static int[] X={100,134,168,202,236};                   //第n格的x坐标
	private static int LineNum=5;                                 
	private String printName;
	private PrintService printService;
	private Integer count;
	
	
	public PrintEntity() {
		super();
	}
	public PrintEntity(String printName){
		super();
		this.printName=printName;
		this.count=0;
		PrintService[] ps=PrintServiceLookup.lookupPrintServices(null, null);
		for (int i = 0; i < ps.length; i++) {
			if(ps[i].getName().equals(printName)){
				this.printService=ps[i];
				break;
			}
		}
	}
	public PrintEntity(String printName, PrintService printService, Integer count) {
		super();
		this.printName = printName;
		this.printService = printService;
		this.count = count;
	}
    

	public String getPrintName() {
		return printName;
	}
	public void setPrintName(String printName) {
		this.printName = printName;
	}
	public PrintService getPrintService() {
		return printService;
	}
	public void setPrintService(PrintService printService) {
		this.printService = printService;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "PrintEntity [printName=" + printName + ", printService=" + printService + ", count=" + count + "]";
	}
	public boolean isNewLine(){
		return (this.count%LineNum)==0;
	}
	public int getX(){
		return X[(this.count%LineNum)];
	}
	public int getX(int num){
		return X[num];
	}
	public void countIncrement(){
		count++;
	}
	public void init(){
		this.count=0;
	}
}
