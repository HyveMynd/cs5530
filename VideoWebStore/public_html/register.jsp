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
<title>Login</title>
</head>
<body>
<%
Connector con = new Connector();
Customer customer = new Customer();
User user = new User();

String login = request.getParameter("login");
if( login == null ){
%>
<form method=get action="register.jsp">
	<input type="text" name="first" placeholder="First Name" />
	<input type="text" name="last" placeholder="Last Name" />
	<input type="text" name="phone" placeholder="Phone Number" />
	<input type="text" name="address" placeholder="Address" />
	<input type="text" name="login" placeholder="Login" />
	<input type="text" name="password" placeholder="Password" />
    <input type="submit" />
</form>

<%

} else {
	customer.firstName = request.getParameter("first");
	customer.lastName = request.getParameter("last");
	customer.phone = request.getParameter("phone");
	customer.address = request.getParameter("address");
	customer.login = request.getParameter("login");
	customer.password = request.getParameter("password");

	QueryResult<Customer> custInfo = Options.registerUser(con.stmt, customer);
	customer = custInfo.firstOrDefault();
	user.id = custInfo.getAutoGeneratedKey();
	user.login = customer.login;
	out.println(custInfo.resultStr);
	
	if (user.id > 0)
		out.println("<a href=options.jsp >Go To Options Page</a>");
}  // We are ending the braces for else here
%>
<script>
$.cookie("id", <%=user.id%>);
$.cookie("login", <%=user.login%>);
</script>
</body>
</html>