package cn.sap.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;










import cn.itcast.jdbc.JdbcUtils;

public class AdminPerformAction {
	private File image;
	private String imageFileName;
	public File getImage() {
		return image;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public void setImage(File image) {
		this.image = image;
	}

	public String upLoad() throws SQLException, IOException { 
		String realpath = ServletActionContext.getServletContext().getRealPath("/themes");
		System.out.println(realpath);
		if(image != null) {
			File savefile = new File(new File(realpath), "1.jpg");
			if(!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			FileUtils.copyFile(image, savefile);
		}
	    return "admin";
    }
	
	public String execute() throws Exception { 
	/*	File file=new File("c:/register.xls");  
		if(file.exists())    
		{    
		    try {    
		    	file.delete();
		    
		    } catch (Exception e) {    
		        e.printStackTrace();    
		    }    
		}*/
		Date date=new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String time=df.format(date);
		Connection conn = null;
		PreparedStatement ps = null;
		String time_tmp = null;
		 
		 try {
			conn = JdbcUtils.getConnection();
			 ps = conn.prepareStatement("select * from employee into outfile ?");
			 time_tmp = "c:/register"+"_"+time+".xls";
			 ps.setString(1, time_tmp);
			
			 ps.executeQuery(); 
			 downLoad(time_tmp, ServletActionContext.getResponse());
		} catch(SQLException e) {
			 e.printStackTrace();
          } finally {
    	 JdbcUtils.releaseConnection(conn);
     }
	 /*ActionContext ac = ActionContext.getContext();
	 ac.getSession().put("session_value1", "Export success!");*/
		 
		 
		 
	//downLoadFile(new URL("http://192.168.0.23:8080/time_tmp"),"c:\\");
		 
		
	 return "admin";
   }
	
	
	public void downLoad(String filePath, HttpServletResponse response) throws Exception {
	     // System.out.println("filePath"+filePath);
	         File f = new File(filePath);
	         if (!f.exists()) {
	             response.sendError(404, "File not found!");
	             return;
	         }
	         BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
	         byte[] buf = new byte[1024];
	         int len = 0;
	         response.reset();
	             response.setContentType("application/x-msdownload");
	             response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
	         OutputStream out = response.getOutputStream();
	         while ((len = br.read(buf)) > 0)
	             out.write(buf, 0, len);
	         br.close();
	         out.close();
	         
	         response.reset();
	     } 
	
	/* public void downLoadFile(URL theURL, String filePath) throws IOException {
		 File dirFile = new File(filePath);
	     if(!dirFile.exists()){
	        dirFile.mkdir();
	     }
	     
	     URLConnection  connection = theURL.openConnection();
	     InputStream in = connection.getInputStream();  
	     FileOutputStream os = new FileOutputStream(filePath+"\\123.png"); 
	     byte[] buffer = new byte[4 * 1024];  
	     int read;  
	     while ((read = in.read(buffer)) > 0) {  
	        os.write(buffer, 0, read);  
	     }  
	     os.close();  
	     in.close();
	 }*/
	
	public String validate() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		
		/*if(request.getHeader("referer").equals("http://localhost:8080/register/sap/admin_validate.action"))
			return "admin";
		*/
		String u = (String)request.getParameter("user").trim();
		String n = (String)request.getParameter("password").trim();
		
		
		if(u.equals("sap") && n.equals("sap")) 
			return "admin";
		else
			return "login";
		
	}
	
	public String confirmBug() throws UnsupportedEncodingException {     //解决confirm默认刷新bug
			return "admin";	
	}
	
	public String clean() throws SQLException { 
		
		 Connection conn = null;
		 Statement st = null;
		
		 try {
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			String sql = "delete from employee where 1=1";
			st.execute(sql);  
		} catch(SQLException e) {
			 e.printStackTrace();
          } finally {
    	 JdbcUtils.releaseConnection(conn);
     }
	 
	 return "admin";
   }
	
	public String changeTitleText() throws SQLException { 
		HttpServletRequest request = ServletActionContext.getRequest();
		String title_text = (String)request.getParameter("title_text").trim();
		 Connection conn = null;
		 PreparedStatement ps = null;
		
		 try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/sap","root","111111");
			
			ps = conn.prepareStatement("update data set title_text=? where id=1");
			ps.setString(1,title_text);
			ps.executeUpdate();
			
		} catch(SQLException e) {
			 e.printStackTrace();
         } finally {
   	     conn.close();
         }
	 
	 return "admin";
  }
	
	public String changeTitleColor() throws SQLException { 
		HttpServletRequest request = ServletActionContext.getRequest();
		String title_color = (String)request.getParameter("title_color").trim();
		Connection conn = null;
		PreparedStatement ps = null;
		
		 try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/sap","root","111111");
			
			ps = conn.prepareStatement("update data set title_color=? where id=1");
			ps.setString(1,title_color);
			ps.executeUpdate();
			
		} catch(SQLException e) {
			 e.printStackTrace();
         } finally {
   	     conn.close();
         }
	 
	 return "admin";
  }
	
	
	
}
