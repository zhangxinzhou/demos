package com.print;



import java.util.List;

import javax.print.event.PrintJobEvent;
import javax.print.event.PrintServiceAttributeEvent;

public class PrintResult {

	private boolean findPrinter;
	private boolean isOnLine;
	private PrintServiceAttributeEvent psae;
	private List<PrintJobEvent> pje;
	
		
	
	public boolean isFindPrinter() {
		return findPrinter;
	}
	public void setFindPrinter(boolean findPrinter) {
		this.findPrinter = findPrinter;
	}
	public boolean isOnLine() {
		return isOnLine;
	}
	public void setOnLine(boolean isOnLine) {
		this.isOnLine = isOnLine;
	}
	public PrintServiceAttributeEvent getPsae() {
		return psae;
	}
	public void setPsae(PrintServiceAttributeEvent psae) {
		this.psae = psae;
	}
	public List<PrintJobEvent> getPje() {
		return pje;
	}
	public void setPje(List<PrintJobEvent> pje) {
		this.pje = pje;
	}
	
	
	//结果
	public int getResult(){
		int result=0;
		if(!this.isOnLine){
			result=-1;                     //ping不通
		}else{
			result=pje.get(0).getPrintEventType();
		}
		return result;
	}
}
