package com.zxgweb.qrcode.web.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 删除图片文件的Servlet<br>
 * @author lingxiaoguang
 *
 */
public class DeleteImgServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取文件名
		String img = request.getParameter("img");
		//删除文件
		File file = new File(request.getServletContext().getRealPath("/images/"+img));
		file.delete();
		response.getWriter().write("success");
	}
}

