<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTPYE html>
<html>
<head>
  <title>二维码</title>
  <meta charset="UTF-8">
  <meta name="keywords" value="二维码生成，二维码解析">
  <meta name="description" value="二维码生成与解析">
  <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
  <style>
	*{margin:0;padding:0;}
	body{font-size:12px;font-family:"微软雅黑";background:lightblue;}
	
	.fl{float:left;}
	.fr{float:right;}
    .clearfix:after{content:" ";display:block;clear:both;}
	
	.main{width:90%;height:500px;background:#aaa;margin:50px auto;}
	.main .input{width:40%;margin:5%;height:400px;background:#fff;}
    .main .input textarea{width:90%;margin:5%;resize:no;padding:10px;height:300px;}
    .main .input input[type='button']{width:90%;margin:0 5% 5%;height:40px;background:lightblue;font-size:14px;color:#fff;cursor:pointer;border:none;font-weight:600;}
     .main .input input[type='button']:hover,.main .input input[type='button']:active{background:#ccc;color:#000;}
    .main .qrcode{width:40%;margin:5%;height:400px;background:#fff;}
	.main .qrcode #qrcode_img{margin:5%;width:90%;border:1px solid #ccc;height:350px;}
    .main .qrcode #qrcode_img img{max-width:90%;max-height:90%;margin:5%;}
    .main .qrcode #qrcode_img p{margin:5%;font-size:18px;text-align:center;margin-top:30%;}
	@media screen and (max-width:800px){
		.main{height:1000px;}
		.main .input{width:90%;}
		.main .qrcode{width:90%;}
	}
  </style>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css"/>
</head>
<body>
   <div class="main">
	<section class="input fl">
		<textarea id="content">请输入网址或文本。。</textarea>

		<input type="button" value="生成二维码" id="btn"/>
	</section>
	<section class="qrcode fr">
		<div id="qrcode_img">
			<p>左侧输入内容<br>点击生成二维码</p>
		</div>
	</section>
   </div>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
   <script>
		$("#btn").click(function(){
			$.ajax({
				url:"${pageContext.request.contextPath}/generate",
				data:{"str":$("#content").val()},
				success:function(data){
					$("#qrcode_img").html('<img src="${pageContext.request.contextPath}/images/'+data+'" class="animated bounceIn"/>');
					setTimeout(function(){
						deleteImg(data);
				    }, 5000)
				},
				error:function(e){
					alert("二维码生成失败！");	
				}
			 }
			);
		});
		function deleteImg(img){
			$.ajax({
				url:"${pageContext.request.contextPath}/deleteImg",
				data:{"img":img}
			});
		}
   </script>
</body>
</html>