<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="java.sql.*,java.io.*,java.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <link rel="shortcut icon" href="111.ico"/>
    <base href="<%=basePath%>">
    
    <title>admin</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style type="text/css">  
table {  
    border: 2px #CCCCCC solid;  
    width: 360px;  
}  
  
td,th {  
    height: 30px;  
    border: #CCCCCC 1px solid;  
}  
</style> 

<script type="text/javascript">
      function func(){
      
      	
      	 <input type="text" maxlength="100" id="name"  name="name" value =""/>
      }
      window.onload=func; 
</script> 

<script type="text/javascript">
function exportExcel()
  {
      alert("Export Successful!");
  }
  
  function cleanDatabase()
  {
  	  if(confirm("Are you sure to delete the database ?")) {
  	 	
  	 	 document.form2.action = "sap/admin_clean.action";
		 form2.submit();
		 
      } 
      else {
      document.form2.action = "sap/admin_confirmBug.action";
		 form2.submit();
      }
  }
  
    function changeTitleText()
  {
  	  if(confirm("Are you sure to change title text ?")) {
  	 	
  	 	 document.form3.action = "sap/admin_changeTitleText.action";
		 form3.submit();
		 
      } 
      else {
      	document.form3.action = "sap/admin_confirmBug.action";
		 form3.submit();
      }
      	 
  }
  
 function changeTitleColor() 
 {
 	if(confirm("Are you sure to change title color ?")) {
 	
 		/*  var myselect=document.getElementById("select"); 
 		 var index=myselect.selectedIndex;
 		 myselect.options[index].value;  */
  	 	 document.form3.action = "sap/admin_changeTitleColor.action";
		 form3.submit();
		 
      }    
      else {
      	document.form3.action = "sap/admin_confirmBug.action";
		 form3.submit();
      }
 
 }
 
 
  function changeBackgroundImage() 
 {
 	if(confirm("Are you sure to change background image ?")) {
 	
  	 	 document.form4.action = "sap/admin_upLoad.action";
		 form4.submit();
		 
      }    
      else {
       document.form4.action = "sap/admin_confirmBug.action";
		 form4.submit();
      }
 
 }
</script>

  </head>
  
  <body>
  <form name="form2" method="post" action="">
  	<input type="submit" value="EXPORT" onclick="exportExcel();form2.action='sap/admin_execute.action';form2.submit();" />
  	<input type="submit" value="CLEAN" onclick="cleanDatabase();" />
  </form>	
 <!--   <font color="blue" size=5>${sessionScope.session_value1}</font><br/> -->
 
 <form name="form3" method="post" action="">
    Title text:<input type="text" id="ti" name="title_text" size="50">
    <input type="submit" value="Save" onclick="changeTitleText();" />
    <br />
    <br />
    <br />
Title color:<select id="select" name="title_color"> 
  <option value="White" style="color:white;">White</option>
  <option value="Black" style="color:black;">Black</option>
  <option value="Red" style="color:red;">Red</option>
  <option value="Yellow" style="color:yellow;">Yellow</option>
  <option value="Pink" style="color:pink;">Pink</option>
  <option value="Green" style="color:green;">Green</option>
  <option value="Orange" style="color:orange;">Orange</option>
  <option value="Purple" style="color:purple;">Purple</option>
  <option value="Blue" style="color:blue;">Blue</option>
  <option value="Beige" style="color:beige;">Beige</option>
  <option value="Brown" style="color:brown;">Brown</option>
  <option value="Teal" style="color:teal;">Teal</option>
  <option value="Navy" style="color:navy;">Navy</option>
  <option value="Maroon" style="color:maroon;">Maroon</option>
  <option value="LimeGreen" style="color:limegreen;">LimeGreen</option>
</select>  
 <input type="submit" value="Save" onclick="changeTitleColor();" />
 </form>
 <br />
 <br />
 <br />
 <form name="form4" enctype="multipart/form-data" method="post" >
     Background image(1560px * 478px):<input type="file" name="image" style="width: 238px; ">
     <input type="submit" value="Save" onclick="changeBackgroundImage();">
</form>

<hr />

     <%  
        
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sap","root","111111");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from employee");  
        
        
        Statement st1 = conn.createStatement();
        ResultSet resultSet = st1.executeQuery("select count(*) as rowCount from employee");
        resultSet.next();
        int rowCount = resultSet.getInt("rowCount");
  
    %>  
    Current number : <input type="text" value=<%=rowCount %> readOnly="readonly" />
    <br>  
    <br>  
    <table align="center">  
        <tr>  
            <th>  
                <%  
                    out.print("ID");  
                %>  
            </th>  
            <th>  
                <%  
                    out.print("NAME");  
                %>  
            </th>  
            <th>  
                <%  
                    out.print("TIME");  
                %>  
            </th>  
        </tr>  
  
        <%  
            while (rs.next()) {  
        %>  
        <tr>  
            <td>  
                <%  
                    out.print(rs.getString(1));  
                %>  
            </td>  
            <td>  
                <%  
                    out.print(rs.getString(2));  
                %>  
            </td>  
            <td>  
                <%  
                    out.print(rs.getString(3));  
                %>  
            </td>   
        </tr>  
        <%  
            }  
        %>  
    </table> 
    
    <br />
    <br />
    <br />
    
    
    <%  
        rs.close();  
        st.close();  
        conn.close();  
    %> 
    
 
 
  </body>
</html>
