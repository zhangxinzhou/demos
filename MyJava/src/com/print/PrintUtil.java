package com.print;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.event.PrintJobEvent;
import javax.print.event.PrintJobListener;
import javax.print.event.PrintServiceAttributeEvent;
import javax.print.event.PrintServiceAttributeListener;




//打印工具类
public class PrintUtil implements Printable{

	private static PrintUtil printer=new PrintUtil(); 
	
	private String content;   
	private List<PrintEntity> pss=new ArrayList<>();
	private PrintEntity ps;
	                
	public PrintResult print(String content,String printName,String IP){
		PrintResult result=null;
		if(pingIP(IP, 1000)){
			result=print(content, printName);
		}else{
			result=new PrintResult();
			result.setOnLine(false);
		}
		return result;
	}
	
	public PrintResult print(String content,String printName){		
		this.content=content;
		PrintResult result=new PrintResult();
		List<PrintJobEvent> pjeList=new ArrayList<>();
		result.setPje(pjeList);
		result.setOnLine(false);
		result.setFindPrinter(false);
		DocFlavor flavor=DocFlavor.SERVICE_FORMATTED.PRINTABLE;
		//构建打印请求属性集 
		PrintRequestAttributeSet aset=new HashPrintRequestAttributeSet();	
		//设置:打印区域,打印份数
		aset.add(new MediaPrintableArea(0, 0, 700, 200, MediaPrintableArea.MM));
		aset.add(new Copies(1));
		//如果有名为name的打印机,则调用name,没有就初始化一个
		for (int i = 0; i < pss.size(); i++) {			
			if(pss.get(i).getPrintName().equals(printName)){
				ps=pss.get(i);
				break;
			}
		}
		
		if(ps==null){
			//初始化一个打印机
			ps=new PrintEntity(printName);
			pss.add(ps);
		}else{
			//找不到打印服务则移除
			if(getCurrentPS(printName)==null){
				pss.remove(ps);
			}			
		}
		if(ps!=null&&ps.getPrintService()!=null){
			//找到打印服务
			result.setOnLine(true);
			result.setFindPrinter(true);
			ps.countIncrement();
			//发现打印机创建打印作业
			DocPrintJob pj=ps.getPrintService().createPrintJob();
			//监听器监听打印机的属性是否发生变动,如果变动会执行attributeUpdate的方法
			ps.getPrintService().addPrintServiceAttributeListener(new PrintServiceAttributeListener() {	
				//当打印服务的属性发生变化时，PrintServiceAttributeListener.attributeUpdate()方法将会被调用。
				//服务使用PrintServiceAttributeListener接口来决定支持哪些事件。
				@Override
				public void attributeUpdate(PrintServiceAttributeEvent psae) {
					result.setPsae(psae);
				}
			});
			//监听打印任务
			pj.addPrintJobListener(new PrintJobListener() {
				
				// 要求关注(如缺纸,卡纸)打印机无法自己恢复,需要处理
				@Override
				public void printJobRequiresAttention(PrintJobEvent pje) {
					pjeList.add(pje);				
				}
				
				// 一个打印工作有可能在一个网络打印服务的缓冲队列中处于假脱机状态。此时“no more events”
				//消息虽然不能说明打印工作已经成功执行，但用户至少可以判断出该工作可能还没有失败
				@Override
				public void printJobNoMoreEvents(PrintJobEvent pje) {
					pjeList.add(pje);					
				}
				
				//失败
				@Override
				public void printJobFailed(PrintJobEvent pje) {
					pjeList.add(pje);					
				}
				
				//完成
				@Override
				public void printJobCompleted(PrintJobEvent pje) {
					pjeList.add(pje);					
				}
				
				//任务取消
				@Override
				public void printJobCanceled(PrintJobEvent pje) {
					pjeList.add(pje);					
				}
				
				//数据传输完毕
				@Override
				public void printDataTransferCompleted(PrintJobEvent pje) {
					pjeList.add(pje);					
				}
			});
			
			try {
				Doc doc=new SimpleDoc(this, flavor, null);
				pj.print(doc, aset);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return result;
	}
	
	
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if(pageIndex==0){
			Graphics2D g2d=(Graphics2D)graphics;
			g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			g2d.setColor(Color.black);
			BufferedImage img=CodeUtil.getQRcode(content, 30);
			if(ps!=null){
				g2d.drawImage(img,ps.getX(0),-2,25,25,null);                         //右起第1格
				g2d.drawImage(img,ps.getX(1),-2,25,25,null); 	                     //右起第2格
				g2d.drawImage(img,ps.getX(2),-2,25,25,null);                         //右起第3格
				g2d.drawImage(img,ps.getX(3),-2,25,25,null);    //右起第4格
				//g2d.drawString(ps.getCount().toString(), ps.getX(4), 20); //右起第5格
				g2d.rotate(-Math.PI,ps.getX(4),10);//旋转180
				g2d.drawString(ps.getCount().toString(), ps.getX(4)-26, 20);
			}
			return Printable.PAGE_EXISTS;
		}else{
			return Printable.NO_SUCH_PAGE;
		}
	}
	
	//ping方法
	private static boolean pingIP(String IP, int timeOut) {
		try {
			return InetAddress.getByName(IP).isReachable(timeOut);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//根据名称查找当前的打印服务
	private PrintService getCurrentPS(String printName){
		PrintService[] pssTemp=PrintServiceLookup.lookupPrintServices(null, null);
		PrintService psTemp=null;
		for (int i = 0; i < pssTemp.length; i++) {
			if(pssTemp[i].getName().equals(printName)){
				psTemp=pssTemp[i];
				break;
			}
		}
		return psTemp;
	}
	
	public static int UtilPrint(String content,String printName,String IP){
		return printer.print(content, printName, IP).getResult();
	}
	
	public static int UtilPrint(String content,String printName){
		return printer.print(content, printName).getResult();
	}

}
