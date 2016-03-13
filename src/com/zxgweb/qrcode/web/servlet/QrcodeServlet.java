package com.zxgweb.qrcode.web.servlet;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxgweb.qrcode.util.QrcodeUtil;
/**
 * 二维码生成servlet<br>
 * @author lingxiaoguang
 *
 */
public class QrcodeServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
		//获取文本内容
		String str = request.getParameter("str");
		//生成文件名
		String imgName="qrcode"+UUID.randomUUID()+".jpg";
		//生成二维码图片，并写到输出流中
		OutputStream os=response.getOutputStream();
		QrcodeUtil.encoderQRCode(str, Color.BLACK,os);
	};
}
