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

public class HelloWorldAction {
	
	public String execute() throws UnsupportedEncodingException, SQLException {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		String u = (String)request.getParameter("user").trim();
		String n = (String)request.getParameter("name").trim();
		
		if(u.trim().isEmpty() || n.trim().isEmpty()) {   //���u��nΪ�գ���index.jsp
			return toIndex();
		}
		else if(!validate(u, n)) { //������벻��ȷ��ȥerror.jsp
			return toError();
		}
			
		else if(exist(u, n))  //�ظ�����(id��nameȫһ��),��id��name��һ����ͬ��ע�⣺Ӣ������Сд������
			return toError(); 
		else
			return toSuccess();
	}
	
	public boolean validate(String u, String n) {   //û�б���������ж������Ƿ���ȷ
		

		String regex1 = "([Ii]\\d{6}|[Cc]\\d{7})";
		String regex2 = "[a-zA-Z\\s]+";
		if(u.matches(regex1) && n.matches(regex2))
			return true;
		else
			return false;
	}
	
	public boolean exist(String u, String n) throws SQLException { //��֤�Ƿ����,���������,����mysql
		
		Date date=new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=df.format(date);
		
		 Connection conn = null;
		 PreparedStatement ps1 = null;
		 ResultSet rs1 = null;
		 
		/* PreparedStatement ps2 = null;
		 ResultSet rs2 = null;*/
		 
		 try {
			 conn = JdbcUtils.getConnection();  //��ȡ����
			 ps1 = conn.prepareStatement("select * from employee where id=?");
			 ps1.setString(1, u);
			 rs1 = ps1.executeQuery();
			 
			/* ps2 = conn.prepareStatement("select * from employee where name=?");
			 ps2.setString(1, n);
			 rs2 = ps2.executeQuery();*/
			 
			 if(rs1.next()) { //id����,name���ùܣ�������
				 return true;	 
			 }
			 else {
				 try {
					 conn.setAutoCommit(false);  //������
					 ps1 = conn.prepareStatement("insert into employee values(?,?,?)");
					 ps1.setString(1,u);
					 ps1.setString(2,n);
					 ps1.setString(3,time);
					 ps1.executeUpdate();
					 conn.commit();  //�ύ����
					
				 } catch(SQLException e) {
		                if(conn!=null)
		                    conn.rollback();  //�ع�����
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
		return false;
	}
	
	
	public String toIndex() {
		return "index";
	}
	
	public String toSuccess() {
		ActionContext ac = ActionContext.getContext();
		ac.getSession().put("session_value", "Register success!");
		
		return "success";
	}
	
	public String toError() {
		ActionContext ac = ActionContext.getContext();
		ac.getSession().put("session_value", "Input error or Registered!");
		 //ServletActionContext.getRequest().setAttribute("session_value", "Input error or Registered!");
		return "error";
	}
	
	
}
