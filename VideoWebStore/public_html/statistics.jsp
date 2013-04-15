<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page language="java" import="cs5530.*" %>
<%@ page language="java" import="databaseOps.*" %>
<%@ page language="java" import="dbModels.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="jquery.cookie.js"></script>
<title>User Record</title>
</head>
<body>
<%
Connector con = new Connector();
String video = request.getParameter("videoCount");
if( video == null ){
%>
<h2>Enter information for the video to rate:</h2>
<form method=get action=statistics.jsp>
	<input type="text" name=videoCount placeholder="Video Count" />
   	<input type="text" name="directorCount" placeholder="Director Count" />
    <input type="text" name="actorCount" placeholder="Actor Count" />
    <input type="submit" />
</form>
<%
} else {
	 int videoCount = Integer.parseInt(request.getParameter("videoCount"));
	 int directorCount	 = Integer.parseInt(request.getParameter("directorCount"));
	 int actorCount = Integer.parseInt(request.getParameter("actorCount"));
	 String result = "<div style='font-size:large;' >";
	 result += Options.getStatistics(con.stmt, videoCount, directorCount, actorCount);
	 result += "</div>";
	 out.println(result);
	con.closeConnection();
	out.println("<a href=options.jsp >Go To Options Page</a>");
}
%>

</body>
</html>