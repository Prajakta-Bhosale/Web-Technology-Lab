<!-- Prajakta Bhosale TECOB201-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.sql.*" %>

<html>
<body>

<%
String url="jdbc:mysql://localhost:3306/Prajakta";
String username1="root";
String password1="mysql";
response.setContentType("text/html");
String input=request.getParameter("action");
out.println(input);
String name=request.getParameter("name");
String email=request.getParameter("email");
String password=request.getParameter("password");

try
{
    Class.forName("com.mysql.jdbc.Driver");
    Connection con=DriverManager.getConnection(url,username1,password1);
    out.println("Connection successful");
    Statement st=con.createStatement();
    if(input.equals("Insert"))
    {
        int i=st.executeUpdate("insert into Student values('"+name+"','"+email+"','"+password+"')");
        if(i>0)
        {
            out.println("inserted successfully");
        }
    }
    else if(input.equals("Update"))
    {
      String query = "update Student set password= ? where name=?";
  
      PreparedStatement pst = con.prepareStatement(query);
      pst.setString(1,password);
      pst.setString(2, name);
      int i = pst.executeUpdate();
       if(i>0)
        {
            out.println("updated successfully");
        }
    }
    else if(input.equals("Delete"))
    {
        String query = "delete from Student where name=?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, name);
        int i = pst.executeUpdate();
        if(i>0)
        {
            out.println("Deleted successfully");
        }
    }
    else if(input.equals("Select"))
    {
        String query="select * from Student";
        ResultSet rs =st.executeQuery(query);
        while(rs.next())
        {
            String uname = rs.getString("name");
            out.println(uname);
            out.println("\t");
            String email1 = rs.getString("email");
            out.println(email1);
            out.println("\t");
            String pwd = rs.getString("password");
            out.println(pwd);
        }
       
       
    }
   
   
}
catch(Exception e)
{
   out.println(e);
}

%>

</body>
</html>
