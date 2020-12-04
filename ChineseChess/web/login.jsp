<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Trang Đăng Nhập</title>  
        <style>
    body{
    margin: 0;
    padding: 0;
    background: url(https://thanhviencac.files.wordpress.com/2015/06/08065-thuy-mac_wallpaper-nen-deskop-namkna-blogspot-com-_1_26.jpg);
    background-size: cover;
    font-family: sans-serif;
}

.loginbox{
    width: 320px;
    height: 420px;
    background: #000;
    color: #fff;
    top: 50%;
    left: 50%;
    position: absolute;
    transform: translate(-50%,-50%);
    box-sizing: border-box;
    padding: 70px 30px;
}

.avatar{
    width: 100px;
    height: 100px;
    border-radius: 50%;
    position: absolute;
    top: -50px;
    left: calc(50% - 50px);
}

h2{
    margin: 0;
    padding: 0 0 20px;
    text-align: center;
    font-size: 22px;
}

.loginbox p{
    margin: 0;
    padding: 0;
    font-weight: bold;
}

.loginbox input{
    width: 100%;
    margin-bottom: 20px;
}

.loginbox input[type="text"], input[type="password"]
{
    border: none;
    border-bottom: 1px solid #fff;
    background: transparent;
    outline: none;
    height: 40px;
    color: #fff;
    font-size: 16px;
}
.loginbox input[type="submit"]
{
    border: none;
    outline: none;
    height: 40px;
    background: #fb2525;
    color: #fff;
    font-size: 18px;
    border-radius: 20px;
}
.loginbox input[type="submit"]:hover
{
    cursor: pointer;
    background: #ffc107;
    color: #000;
}
.loginbox a{
    text-decoration: none;
    font-size: 12px;
    line-height: 20px;
    color: darkgrey;
}

.loginbox a:hover
{
    color: #ffc107;
}
h1{
    color: white;
    font-size: 50px;
     text-align: center;
}
</style>
    </head>
    <body>
           <h1>Game cờ tướng online</h1>
        <div class="loginbox">
         
            <h2>Đăng Nhập</h2>
            <img src="https://anhdep.tv/attachments/d729e7ff09ebee15f2009bba9b2be257-jpeg.2529/" class="avatar">
            <form action="<%=request.getContextPath()%>/login" method="post" >   
                <p>Tên tài khoản:<p>
                    <input type="text" placeholder="Nhập tên tài khoản của bạn..." name="tentaikhoan"/>    
                <p>Mật khẩu:</p>
                <input type="password" placeholder="Nhập mật khẩu của bạn..." name="matkhau" />
                <input type="submit" value="Đăng nhập"/>
            </form>
            <div style="font-size:15px">Bạn chưa có tài khoản? &nbsp &nbsp
                <a style="font-size:20px" class="nav-link" href="<%=request.getContextPath()%>/register.jsp">Đăng kí</a>
            </div>
        </div> 
                
        
    </body>
</html>