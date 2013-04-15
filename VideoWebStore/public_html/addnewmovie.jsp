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
<title>Ordering</title>
</head>
<body>
<%
Connector con = new Connector();
Video video = new Video();
String isbn = request.getParameter("isbn");
if( isbn == null ){
%>
<h2>Enter the new video information:</h2>
<form method=get action=addnewmovie.jsp>
	<input type="text" name="isbn" placeholder="ISBN" />
   	<input type="text" name="title" placeholder="Title" />
   	<input type="text" name="subject" placeholder="Subject" />
   	<input type="text" name="actor" placeholder="Actor Name" />
   	<input type="text" name="director" placeholder="Director" />
   	<input type="text" name="rating" placeholder="Rating" />
   	<input type="text" name="price" placeholder="Price" />
   	<input type="text" name="format" placeholder="Format" />
     <input type="text" name="yop" placeholder="Year of Production" />
    <input type="submit" />
</form>
<%
} else {
	 video.ISBN = request.getParameter("isbn");
	 video.Title = request.getParameter("title");
	 video.Subject= request.getParameter("subject");
	 video.Rating = request.getParameter("rating");
	 video.Price = request.getParameter("price");
	 video.Format = request.getParameter("format");
	 video.YearOfProduction = request.getParameter("yop");
	 video.ActorName = request.getParameter("actor");
	 video.Director = request.getParameter("director");

	 out.println(Options.addVideo(con.stmt, video));
	 out.println("<a href=options.jsp >Go To Options</a>");
	con.closeConnection();
}
%>

</body>
</html>