<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="control.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>


<title>Chi tiết người chơi</title>
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
/*table, td, th, tr {  
  border: 2px solid white;
  text-align: left;
}*/
table {
  border-collapse: collapse;
  width: 40%;
}

th,tr, td {
  padding: 15px;
  text-align: left;
  border:2px solid black;
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
      <a class="nav-link"href="#">Trung bình nước đi thua</a>
    </div>
  </li>
  <li>&nbsp  &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp 
         &nbsp &nbsp &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp  &nbsp  &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp &nbsp &nbsp  
         &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp 
         &nbsp &nbsp &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  </li>
  <li><a class="nav-link" href="<%=request.getContextPath()%>/logout">Đăng xuất</a></li>
</ul>
 
<div class="content">
  <image style="float:right; width:55%"src="https://zigavn.com/wp-content/uploads/2018/04/Kho-s%C3%A1ch-h%E1%BB%8Dc-c%E1%BB%9D-t%C6%B0%E1%BB%9Bng-online-kyvuong-e1530975773231.jpg">
    <h2>Thông tin người chơi
    </h2>
      
    <table>
        
                    <tbody>
                        <tr>
                            <th>Tên </th>
                            <th style="color: green">                                      
                                <c:out value="${player.ten}" />
                            </th>
                        </tr>
                        <tr>
                            <th> Số trận thắng</th>          
                            <td>
                                <c:out value="${player.sotranthang}" />
                            </td>
                            </th>
                        </tr>
                        <tr>
                            <th> Số trận thua</th>
                            <td>                                      
                                <c:out value="${player.sotranthua}" />
                            </td>
                        </tr>
                        <tr>
                            <th> Số trận hòa</th>
                            <td>                                      
                                <c:out value="${player.sotranhoa}" />
                            </td>
                        </tr>
                        <tr>
                            <th>Tổng điểm </th>
                            <th style="color: red">                                      
                                <c:out value="${player.diem}" />
                            </th>
                        </tr>
                    </tbody>    
                </table>                 
                            
</div>

<div class="footer">
    <center style="color: black">Game Cờ Tướng Thi Đấu Đối Kháng Online</center>
</div>
</body>

</html>