<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="control.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>


<title>Xếp hạng trung bình thắng </title>
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
#players {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#players td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#players tr:nth-child(even){background-color: #f2f2f2;}

#players tr:hover {background-color: cornsilk;}

#players th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4CAF50;
  color: white;
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
    <h2>Bảng xếp trung bình số nước đi trận thắng tăng dần</h2>
    <table id="players">

                    <tbody>
                        <!--   for (Todo todo: todos) {  -->
                        <thead>
                            <tr>
                                <th>Hạng</th>
                                <th>Tên  người chơi</th>
                                <th>Số trận thắng</th>
                                <th>Tổng số nước đi trận thắng</th>
                                <th>Trung bình số nước đi trận thắng</th>
                                <th>Xem thông tin</th>
                            </tr>
                        </thead>
                        <c:forEach var="player" items="${listrankmovewin}">    
                            <tr>
                                <td>
                                    <c:out value="${player.rank}"></c:out>
                                </td>
                                <td>
                                    <c:out value="${player.ten}" />
                                </td>
                               <td>
                                    <c:out value="${player.sotranthang}" />
                                </td>
                                <td>
                                    <c:out value="${player.nuocdithang}" />
                                </td>
                                <td style="color: red">
                                    <c:out value="${player.trungbinhnuocdithang}" />
                                </td>
                                <td style="text-decoration: underline"><a href="inforplayer?id=<c:out value='${player.id}' />">Chi tiết</a></td>
                            </tr>
                        </c:forEach> 
    </tbody>
    </table>
</div>
<div class="footer">
    <center style="color: black"> Game Cờ Tướng Thi Đấu Đối Kháng Online</center>
</div>
</body>
</html>