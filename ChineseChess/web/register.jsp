<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="control.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>Đăng Ký</title>
 <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Merriweather:300,400,400i|Noto+Sans:400,400i,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600" rel="stylesheet">
    <style>
  *{
    margin:0;
    padding:0;
    border:none;
    font-family: 'Open Sans', sans-serif;
}
body {
    overflow: hidden;
    background-color: #ededed;
    background: url(https://thanhviencac.files.wordpress.com/2015/06/08065-thuy-mac_wallpaper-nen-deskop-namkna-blogspot-com-_1_26.jpg);
    background-size: cover;
    font-family: sans-serif;
}
.to {
    display: grid;
    grid-template-columns: repeat(12,1fr);
    grid-template-rows: minmax(10px,auto);
}
 
.form {
    padding-top: 0px;
    border: 1px solid #80808000;
    grid-column: 6/9;
    grid-row: 3;
    height: 550px;
    width: 400px;
    display: flex;
    flex-direction: column;
    align-items: center;
    position: relative;
    border-radius: 15px;
    box-shadow: 0px 0px 14px 0px grey;
    background-color: white;
}
h2 {
    margin-top: 50px;
    margin-bottom: 30px;
}
i.fab.fa-app-store-ios {
    display: block;
    margin-bottom: 50px;
    font-size: 28px;
}
 
label {
    margin-left: -126px;
    display: block;
    font-weight: lighter;
 
}
input{
    display: block;
    border-bottom: 2px solid #00BCD4;
    margin-top: 6px;
    margin-bottom: 10px;
    outline-style: none;
}
input[type="text"] {
    padding: 5px;
    width: 70%;
}
 
input#submit {
    padding: 7px;
  
    width: 50%;
    border-radius: 10px;
    border: none;
    position: absolute;
    bottom: 30px;
    cursor: pointer;
    background: linear-gradient(to right, #fc00ff, #00dbde);
}
input#submit:hover{
 
    background: linear-gradient(to right, #fc466b, #3f5efb); 
}
h1{
    color: white;
    font-size: 50px;
     text-align: center;
     margin-left: 130px;
}
</style>

</head>
    <body>
         <h1>Game cờ tướng online</h1>
        <div class="to">
            <form class="form" action="<%=request.getContextPath()%>/register" method="post">
                <h2>Đăng ký</h2>
                <i class="fab fa-app-store-ios"></i>
                   <c:if test="${thongtinsai==true}">
                    <div style="color:red;padding-top:10px">Nhập lại mật khẩu không khớp!!!</div>  
                </c:if>
                <c:if test="${kqdangky==true}">
                    <div style="color:green">Đăng ký thành công!!!</div>  
                </c:if> 
                <label style="margin-left: -150px;">Tên tài khoản</label>
                <input type="text" name="ten">
                <label style="margin-left: -180px;">Mật khẩu</label>
                <input type="password" name="matkhau">  
                <label style="margin-left: -90px;">Nhập lại mật khẩu</label>
                <input type="password" name="matkhaunhaplai"> 
               
                <input  id="submit" type="submit" name="submit" value="Gửi">
                 <div style="font-size:15px; padding-top:90px;padding-bottom:20px">Bạn muốn đăng nhâp? &nbsp &nbsp
                <a style="font-size:20px" class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Đăng nhập</a>
            </div>
            </form>    
           
        </div>
      
    </body>
</html>
 
<body>

</html>