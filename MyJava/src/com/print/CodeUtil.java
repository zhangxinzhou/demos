package com.print;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

//二维码工具栏
public class CodeUtil {

	
	private static final String FORMAT_NAME = "JPG";   //保存图片的格式
	private static final int BLACK = 0xFF000000;       //黑色
	private static final int WHITE = 0xFFFFFFFF;       //白色
	private static final String CHARSET = "UTF-8";     //编码
	
	//画图
	private static BufferedImage drawImage(BitMatrix matrix){
		int width=matrix.getWidth();
		int height=matrix.getHeight();
		BufferedImage img=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				img.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return img;
	}

	//根据图片解析二维码并返回bitmap
	private static BinaryBitmap decodeImage(BufferedImage img) {
		LuminanceSource source = new BufferedImageLuminanceSource(img);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		return bitmap;
	}
	
	//默认设置
	private static Map<EncodeHintType, Object> defaultHints(){
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  //容错率
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);                    //编码
        hints.put(EncodeHintType.MARGIN, 1);                                 //边距
        return hints;
	}
	
	/**
	 *                          自定义设置
	 * @param errorLevel        容错等级   ErrorCorrectionLevel.*
	 * @param charset           字符编码   UTF-8..
	 * @param margin            边距          数字
	 * @return                  返回Map<EncodeHintType, Object>
	 */
	private static Map<EncodeHintType, Object> customHints(EncodeHintType errorLevel,
			String charset,int margin){
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, errorLevel);               //容错率
	    hints.put(EncodeHintType.CHARACTER_SET, charset);                     //编码
	    hints.put(EncodeHintType.MARGIN, margin);                             //边距
	    return hints;
	}
	
	/**
	 *                  标准二维码
	 * @param content   二维码内容
	 * @param size      二维码尺寸
	 * @return          返回二维码的BufferedImage
	 */
	public static BufferedImage getQRcode(String content,int size){
        return getQRcode(content, size, defaultHints());
	}
	
	/**
	 *                        标准二维码
	 * @param content         二维码内容
	 * @param size            二维码的尺寸
	 * @param errorLevel      容错等级ErrorCorrectionLevel.*
	 * @param charset         字符编码  如 UTF-8..
	 * @param margin          边距          数字
	 * @return                返回二维码的BufferedImage
	 */
	public static BufferedImage getQRcode(String content,int size,EncodeHintType errorLevel,
			String charset,int margin){
        return getQRcode(content, size, customHints(errorLevel, charset, margin));
	}
	
	/**
	 *                  标准二维码
	 * @param content   二维码内容
	 * @param size      二维码内容
	 * @param hints     其他参数(容错率,字符编码,边距)
	 * @return          返回二维码的BufferedImage
	 */
	public static BufferedImage getQRcode(String content,int size,Map<EncodeHintType, Object> hints){
		BitMatrix matrix;
		try {
			matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,size, size, hints);
			return drawImage(matrix);
		} catch (WriterException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *                   中间带logo的二维码
	 * @param content    二维码的内容
	 * @param size       二维码的尺寸
	 * @param logoPath   logo地址
	 * @param logoRate   logo占百分比
	 * @return           返回带logo的二维码的BufferedImage
	 */	
	public static BufferedImage getQRcodeWithLogo(String content,int size,String logoPath,int logoRate){
        return getQRcodeWithLogo(content, size, defaultHints(), logoPath, logoRate);
	}
	
	/**
	 *                        中间带logo的二维码
	 * @param content         二维码的内容
	 * @param size            二维码的尺寸
	 * @param logoPath        logo地址
	 * @param logoRate        logo占百分比 
	 * @param errorLevel      容错等级ErrorCorrectionLevel.*
	 * @param charset         字符编码  如 UTF-8..
	 * @param margin          边距          数字
	 * @return                返回带logo的二维码的BufferedImage
	 */
	public static BufferedImage getQRcodeWithLogo(String content,int size,String logoPath,int logoRate,
			              EncodeHintType errorLevel,String charset,int margin){
        return getQRcodeWithLogo(content, size, customHints(errorLevel, charset, margin), 
        		logoPath, logoRate);
	}
	
	/**
	 *                    中间带logo的二维码
	 * @param content     二维码的内容
	 * @param size        二维码的尺寸
	 * @param hints       其他参数(容错率,字符编码,边距)
	 * @param logoPath    logo地址
	 * @param logoRate    logo占百分比
	 * @return            返回带logo的二维码的BufferedImage
	 */
	public static BufferedImage getQRcodeWithLogo(String content,int size,Map<EncodeHintType, Object> hints,
			                                      String logoPath,int logoRate){
		if(logoRate<15||logoRate>30){  //超过30不好识别,小于15又太小
			//无效的比例将采用默认值
			logoRate=20;
		}
		
        try {
			BufferedImage img=getQRcode(content,size,hints);                           //标准二维码
			BufferedImage logo=readImg(logoPath);                                     //logo原图							
			Graphics2D imgg=img.createGraphics();
						
			int logoSize=size*logoRate/100;                                     
			int logoXY=(size-logoSize)/2;
			imgg.drawImage(logo, logoXY, logoXY,logoSize,logoSize, null);
			return img;
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
	}		
	
	/**
	 *                       标准的一维码(CODE_128条形码)
	 * @param content        一维码的内容
	 * @param width          一维码的宽度
	 * @param height         一维码的高度
	 * @return               返回一维码的BufferedImage
	 */
	public static BufferedImage getBarcode(String content,int width,int height){
		try {
			content=content.toUpperCase();
			BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.CODE_128,width, height);
			BufferedImage img=drawImage(matrix);
			return img;
		} catch (WriterException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 *                       下方带内容的一维码
	 * @param content        一维码的内容
	 * @param width          一维码的宽度
	 * @param height         一维码的高度
	 * @return               返回一维码的BufferedImage
	 */
	public static BufferedImage getBarcodeWithStr(String content,int width,int height){
		try {
			content=content.toUpperCase();                        //content的内容转成大写
			int margin=10;                                        //文字与条形码的间隔
			BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.CODE_128,width, height);
			BufferedImage img=drawImage(matrix);
			BufferedImage result=new BufferedImage(img.getWidth(), img.getHeight()+margin, BufferedImage.TYPE_INT_RGB);
			Graphics2D g=result.createGraphics();
			g.drawImage(img, 0, 0, null);
			g.setColor(Color.WHITE);
			g.fillRect(0, img.getHeight(), img.getWidth(), 10);
			int strWidth=g.getFontMetrics().stringWidth(content);
			int strX=(img.getWidth()-strWidth)/2;
			int strY=img.getHeight()+margin;
			g.setColor(Color.BLACK);
			g.drawString(content, strX, strY);
			return result;
		} catch (WriterException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *                 解析二维码
	 * @param img      待解析的图片
	 * @return         返回解析后的内容(String类型)
	 */
	public static String QRdecode(BufferedImage img){
		Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);                    //编码       
		return QRdecode(img, hints);
	}
	
	/**
	 *                解析二维码
	 * @param img     待解析的图片
	 * @param hints   参数(容错率,字符编码,边距等)
	 * @return        返回解析后的内容(String类型)
	 */ 
	public static String QRdecode(BufferedImage img,Map<DecodeHintType, Object> hints){
		Result result=null;                    
        try {
			result=new MultiFormatReader().decode(decodeImage(img),hints);
			return result.getText();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *                解析一维码(条形码)
	 * @param img     待接卸的图片
	 * @return        返回解析后的内容(String类型)
	 */
	public static String Bardecode(BufferedImage img){
		try {
			Result result=new MultiFormatReader().decode(decodeImage(img));
			return result.getText();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	/**
	 *                    保存图片到指定路径(图片格式JPG)
	 * @param img         图片
	 * @param filePath    路径
	 */
	public static void saveImg(BufferedImage img,String filePath){
		File file=new File(filePath);
		if(!file.exists()){
			file.mkdirs();
		}
		String picName=System.currentTimeMillis()+".jpg";
		try {
			ImageIO.write(img, FORMAT_NAME, new File(file+"/"+picName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *                      根据路径读图片
	 * @param imgPath       图片路径
	 * @return              返回BufferedImage
	 */
	public static BufferedImage readImg(String imgPath){
		File file=new File(imgPath);
		BufferedImage img=null;
		if(file.exists()){
			try {
				img=ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		return img;
	}
}
