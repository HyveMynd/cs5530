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
String fid = request.getParameter("fid");
if( fid == null ){
	out.println(Helper.tablesToString(Select.from(new Feedback()).execute(con.stmt).queryResults));
%>
<h2>Enter information for the video to rate:</h2>
<form method=get action=usefulnessrating.jsp>
	<input type="text" name="fid" placeholder="Feedback ID" />
   	<input type="text" name="rating" placeholder="Rating" />
    <input type="submit" />
</form>
<%
} else {
	String rating  = request.getParameter("rating");
	Object id = session.getAttribute("id");
	out.println(Options.addRating(con.stmt, (Integer)id, Integer.parseInt(fid), rating));
	con.closeConnection();
	out.println("<a href=options.jsp >Go To Options Page</a>");
}
%>

</body>
</html>