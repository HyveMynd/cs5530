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
String submitted = request.getParameter("submitted");
if( submitted == null ){
%>
<h2>Enter the search Query:</h2>
<form method=get action=searchmovies.jsp>
	<input type="text" name=actor placeholder="Actor Name" />
	<select name=op1>
		<option value="and" selected>AND</option>
		<option value="or">OR</option>
	</select>
   	<input type="text" name=director placeholder="Director Name" />
   	<select name=op2>
		<option value="and" selected>AND</option>
		<option value="or">OR</option>
	</select>
   	<input type="text" name=title placeholder="Title" />
   	<select name=op3>
		<option value="and" selected>AND</option>
		<option value="or">OR</option>
	</select>
	<input type="text" name=rating placeholder="Rating" />
   	<h5>Search by:</h5>
   	<input type="radio" name="searchby" value="1" selected/>Year<br>
	<input type="radio" name="searchby" value="2" />Average Feedback Score<br>
	<input type="radio" name="searchby" value="3" />Average Trusted Feedback Score<br>
    <input type="button" value="Submit" onclick="submitForm()" />
    <input type="hidden" name=submitted id=formSub />
</form>
<%
} else {	
	 String actor = request.getParameter("actor");
	String director = request.getParameter("director");
	String title = request.getParameter("title");
	String rating = request.getParameter("rating");
	String op1 = request.getParameter("op1");
	String op2 = request.getParameter("op2");
	String op3 = request.getParameter("op3");

	 int order = Integer.parseInt(request.getParameter("searchby"));
	 out.println(Options.movieSearch(con.stmt, order, actor, director, title, rating, op1, op2, op3));
	con.closeConnection();
	out.println("<a href=options.jsp >Go To Options Page</a>");
}
%>
<script>
	function submitForm(){
		$("#formSub").val("yes");
		$("form").submit();
	}
</script>
</body>
</html>