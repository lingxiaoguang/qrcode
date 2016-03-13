# qrcode
在线生成二维码的小应用

### 前端页面
  * 响应式布局（百分比宽度+媒体查询）
  * 与服务器通信采用ajax的方式，比如二维码的生成和删除图片

### 后端架构
  * QrcodeServlet.java  根据文本内容生成二维码的Servlet
  * DeleteImgServlet.java 删除图片的Servlet
  * QrcodeUtil.java 二维码生成和解析的工具类
   
