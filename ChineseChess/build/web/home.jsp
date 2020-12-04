<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="control.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

<title>Trang Chủ</title>
<style>
    * {
  box-sizing: border-box;
  font-family: Arial, Helvetica, sans-serif;
}

body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

/* Style the top navigation bar */
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
}

li {
  float: left;
}

li a, .dropbtn {
  display: inline-block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover, .dropdown:hover .dropbtn {
  background-color: red;
}

li.dropdown {
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
}

.dropdown-content a:hover {background-color: #f1f1f1;}

.dropdown:hover .dropdown-content {
  display: block;
}
/* Style the content */
.content {
  background-color: #ddd;
  padding: 10px;
  height: 580px; /* Should be removed. Only for demonstration */
}

/* Style the footer */
.footer {
  background-color: #f1f1f1;
  padding: 10px;
}
</style>
</head>
<body>

<ul>
  <li><a class="nav-link" href="<%=request.getContextPath()%>/home.jsp">Trang chủ</a></li>
 
  <li><a class="nav-link" href="#news">Chơi cờ</a></li>
  <li class="dropdown">
    <a class="nav-link" href="javascript:void(0)" class="dropbtn">Bảng xếp hạng</a>
    <div class="dropdown-content">
      <a href="<%=request.getContextPath()%>/rankscore">Tổng điểm</a>
      <a href="#">Trung bình điểm đối thủ</a>
      <a href="<%=request.getContextPath()%>/rankmovewin">Trung bình nước đi thắng</a>
      <a href="<%=request.getContextPath()%>/rankmoveloss">Trung bình nước đi thua</a>
    </div>
  </li>
  <li>&nbsp  &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp 
         &nbsp &nbsp &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp  &nbsp  &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp &nbsp &nbsp  
         &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp 
         &nbsp &nbsp &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  </li>
 <li><a class="nav-link" href="<%=request.getContextPath()%>/logout">Đăng xuất</a></li>
</ul>
<div class="content">
    <h2>Xin chào kiện tướng </h2>
    <div style="float:left; width:50%;">
        &nbsp  &nbsp Cờ tướng (Tiếng Trung: 象棋), hay còn gọi là cờ Trung Hoa (Tiếng Trung: 中國象棋), 
        là một trò chơi trí tuệ dành cho hai người. Đây là loại cờ phổ biến nhất tại các nước như Trung Quốc, 
        Việt Nam, Đài Loan và Singapore và nằm trong cùng một thể loại cờ với cờ vua, shogi, janggi.
        <div> &nbsp  &nbsp Trò chơi này mô phỏng cuộc chiến giữa hai quốc gia, với mục tiêu là bắt được Tướng của đối phương.
       Các đặc điểm khác biệt của cờ tướng so với các trò chơi cùng họ là: các quân đặt ở giao điểm các đường thay vì đặt vào ô,
       quân Pháo phải nhảy qua 1 quân khi ăn quân, các khái niệm sông và cung nhằm giới hạn các quân Tướng, Sĩ và Tượng.</div>
       
    </div>
    <image style="float:right"src="https://zigavn.com/wp-content/uploads/2018/04/co-tuong-co-xua-e1530975435856.jpg">
</div>
<div class="footer">
    <center style="color: black">Game Cờ Tướng Thi Đấu Đối Kháng Online</center>
</div>

</body>
</html>