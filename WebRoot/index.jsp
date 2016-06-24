<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="java.sql.*,java.io.*,java.util.*"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<link rel="shortcut icon" href="111.ico"/>
    <base href="<%=basePath%>">
    
    <title>register</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/login.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
	<link href="css/demo.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
	<script type="text/javascript" src="js/jquery1.42.min.js"></script>
	<script type="text/javascript" src="js/jquery.SuperSlide.js"></script>
	<script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>
	<script language="JavaScript" src="script/trim.js"></script>
	
<script type="text/javascript">

/* function bt1()
  {
      alert("Export Successful!");
  } */
</script>
  </head>
  
  <body>
     <div class="header">
  <!--<h1 class="headerLogo"><a title="报名管理系统" target="_blank" href="http://www.juheweb.com/"><img alt="logo" src="images/logo.gif" style="height: 99px; "></a></h1>
	 <div class="headerNav">
		<a target="_blank" href="http://www.sap.com/china/about/sap-labs-china.html/">SAP</a>
		<a target="_blank" href="http://www.sap.com/china/about/sap-labs-china.html/">About SAP</a>
		<a target="_blank" href="http://www.sap.com/china/about/sap-labs-china.html/">Team</a>
		<a target="_blank" href="http://www.sap.com/china/about/sap-labs-china.html/">Feedback</a>
		<a target="_blank" href="http://www.sap.com/china/about/sap-labs-china.html/">Help</a>	
	</div> -->
	<%
		String title_size = "44px";
		String title_color = null;
		String title_text = null;
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sap","root","111111");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from data where id=1"); 
        while (rs.next()) {  
			title_color = rs.getString(3);
			title_text = rs.getString(2);
		}
	 %>
	<h1 class="threed"><font style="font-size:<%=title_size %>;color:<%=title_color %>" ><nobr><%=title_text %></font></nobr></h1>
</div>

<div class="banner">

<div class="login-aside">
  <div id="o-box-up"></div>
  <div id="o-box-down"  style="table-layout:fixed;">
   <div class="error-box"></div>
   
   <form name="form1" method="post" action="">
   <div class="fm-item">
	   <label for="logonId" class="form-label">ID:</label>
	   <input type="text" maxlength="100" id="user" class="i-text" name="user">    
       
  </div>
  <br />
  <div class="fm-item">
	   <label for="logonId" class="form-label">Full Name:</label>
	   <input type="text" maxlength="100" id="name" class="i-text" name="name" value =""/> 
   
  </div>
  
 <br />
  <br />
  
  <div class="fm-item">
	  <nobr>
	   <input type="submit" value="" tabindex="4" id="send-btn" class="btn-login" onclick="form1.action='sap/party.action';form1.submit();">
	   <input type="submit" value="" tabindex="5" id="send-btn" class="btn-login1" onclick="form1.action='sap/partyoff.action';form1.submit();">  
	   </nobr>
       
  </div>
  
  </form>
  
  </div>

</div>

	<div class="bd">
		<ul>
			<li style="background:url(themes/1.jpg) center 0 no-repeat;"><a target="_blank" ></a></li>
			<li style="background:url(themes/1.jpg) center 0 no-repeat;"><a target="_blank" ></a></li>
		</ul>
	</div>

	<div class="hd"><ul></ul></div>
</div>

<%  
        rs.close();  
        st.close();  
        conn.close();  
%> 
    
<script type="text/javascript">jQuery(".banner").slide({ titCell:".hd ul", mainCell:".bd ul", effect:"fold",  autoPlay:true, autoPage:true, trigger:"click" });</script>


<div class="banner-shadow"></div>

<div class="footer">
   <p>Copyright &copy; 2016.Company name All rights reserved.</p>
</div>
  </body>
  
</html>
