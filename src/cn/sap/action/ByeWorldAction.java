package cn.sap.action;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.jdbc.JdbcUtils;

public class ByeWorldAction {
	public String execute() throws UnsupportedEncodingException, SQLException {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		String u = (String)request.getParameter("user").trim();
		String n = (String)request.getParameter("name").trim();
		
		if(u.trim().isEmpty() || n.trim().isEmpty()) {   //Èç¹ûuºÍnÎª¿Õ£¬»Øindex.jsp
			return toIndex();
		}
		else if(!validate(u, n)) { //Èç¹ûÊäÈë²»ÕýÈ·£¬È¥error.jsp
			return toError();
		}
		else if(exist(u, n))  //ÖØ¸´±¨Ãû(idÓënameÈ«Ò»ÖÂ)£¬×¢Òâ£ºÓ¢ÎÄÃû´óÐ¡Ð´£¡£¡£¡
			return toSuccess();
		else
			return toError();
	}
	
	public boolean validate(String u, String n) {   //Ã»ÓÐ±íµ¥£¬ÕâÀï¼òµ¥ÅÐ¶ÏÊäÈëÊÇ·ñÕýÈ·


		String regex1 = "([Ii]\\d{6}|[Cc]\\d{7})";
		String regex2 = "[a-zA-Z\\s]+";
		if(u.matches(regex1) && n.matches(regex2))
			return true;
		else
			return false;
	}
	
	public boolean exist(String u, String n) throws SQLException { //ÑéÖ¤ÊÇ·ñ´æÔÚ,Èç¹û´æÔÚ,´ÓmysqlÉ¾³ý
		 Connection conn = null;
		 PreparedStatement ps1 = null;
		 ResultSet rs1 = null;
		 
		 try {
			 conn = JdbcUtils.getConnection();  //»ñÈ¡Á¬½Ó
			 ps1 = conn.prepareStatement("select * from employee where id=? and name=?");
			 ps1.setString(1, u);
			 ps1.setString(2, n);
			 rs1 = ps1.executeQuery();
			 
			 if(!rs1.next()) { //idÇÒname¼ÇÂ¼²»´æÔÚ
				 return false;
			 }
			 else {
				 try {
					 conn.setAutoCommit(false);  //´ò¿ªÊÂÎñ
					 ps1 = conn.prepareStatement("delete from employee where id=?");
					 ps1.setString(1,u);
					 ps1.executeUpdate();
					 conn.commit();  //Ìá½»ÊÂÎñ
					
				 } catch(SQLException e) {
		                if(conn!=null)
		                    conn.rollback();  //»Ø¹öÊÂÎñ
		                e.printStackTrace();;
		         } finally {
		        	 JdbcUtils.releaseConnection(conn);
		         }
			 }
		     
		 } catch (SQLException e) {
             if(conn!=null)
            	 JdbcUtils.releaseConnection(conn);
             e.printStackTrace();;
         }
		return true;
	}
	
	
	public String toIndex() {
		return "index";
	}
	
	public String toSuccess() {
		ActionContext ac = ActionContext.getContext();
		ac.getSession().put("session_value", "Unregister success!");
		return "success";
	}
	
	public String toError() {
		ActionContext ac = ActionContext.getContext();
		ac.getSession().put("session_value", "Input error or Not Registered!");
		return "error";
	}
}
