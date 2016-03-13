package com.zxgweb.qrcode.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;

import com.swetake.util.Qrcode;
/**
 * 二维码生成解析工具类<br>
 * @author lingxiaoguang
 *
 */
public class QrcodeUtil {

	/**
	 * 生成二维码图片，写入输出流中<br>
	 * @param content 文本内容
	 * @param color 颜色
	 * @param os 输出流
	 */
	public static void encoderQRCode(String content, Color color,
			OutputStream os) {
		try {
			// 二维码编码的类
			Qrcode qrcodeHandler = new Qrcode();
			qrcodeHandler.setQrcodeErrorCorrect('M');
			qrcodeHandler.setQrcodeEncodeMode('B');
			qrcodeHandler.setQrcodeVersion(7);
			// 文本内容转字节数组
			byte[] contentBytes = content.getBytes("gb2312");
			// 准备开始画
			BufferedImage bufImg = new BufferedImage(140, 140,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, 140, 140);
			gs.setColor(color);
			// 设置偏移量 不设置可能导致解析出错
			int pixoff = 2;
			// 画二维码
			if (contentBytes.length > 0 && contentBytes.length < 120) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				System.err.println("QRCode content bytes length = "
						+ contentBytes.length + " not in [ 0,120 ]. ");
			}
			gs.dispose();
			bufImg.flush();
			// 把内存中生成的二维码图片写到流中
			ImageIO.write(bufImg, "png", os);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 生成二维码图片，写入磁盘中<br>
	 * @param content 文本内容
	 * @param color 颜色
	 * @param imgPath 文件路径
	 */
	public static void encoderQRCode(String content, Color color, String imgPath) {
		OutputStream os=null;
		try {
			os = new FileOutputStream(imgPath);
			encoderQRCode(content, color, os);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成二维码图片，写入磁盘中<br>
	 * @param content 文本内容
	 * @param imgPath 文件路径
	 */
	public static void encoderQRCode(String content, String imgPath) {
		encoderQRCode(content, Color.BLACK, imgPath);
	}

	/**
	 * 生成二维码名片，写入流中<br>
	 * 
	 * @param name 名称
	 * @param tel  电话号码
	 * @param email 电子邮件
	 * @param os  输出流
	 */
	public static void generateVcard(String name, String tel, String email,
			OutputStream os) {
		String content = "BEGIN:VCARD\n" + "VERSION:3.0\n" + "N:" + name + "\n"
				+ "TEL;VOICE:" + tel + "\n" + "EMAIL;PREF;INTERNET:" + email
				+ "\n" + "END:VCARD\n";
		encoderQRCode(content, Color.BLACK, os);
	}
	
	/**
	 * 解析二维码图片<br> 
	 * @param imgPath 文件路径
	 * @return 解析出的字符串
	 */
	public static String decoderQRCode(final String imgPath) {
		//二维码解析器
		QRCodeDecoder decoder = new QRCodeDecoder();
		//二维码图片
		QRCodeImage qrCodeImage = new QRCodeImage() {
			BufferedImage bufferedImage = null;
			{
				try {
					File file = new File(imgPath);
					bufferedImage = ImageIO.read(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			public int getWidth() {
				return bufferedImage.getWidth();
			}

			public int getPixel(int x, int y) {
				return bufferedImage.getRGB(x, y);
			}

			public int getHeight() {
				return bufferedImage.getHeight();
			}
		};
		//解析二维码图片
		byte[] decode = decoder.decode(qrCodeImage);
		return new String(decode);
	}
}
