package com.validate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;



/**
 * Servlet implementation class ValidationServlet
 */
public class ValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
    		PrintWriter out = response.getWriter();
    		out.println("<html><body>");
    		String name = request.getParameter("tname");
    		String pass = request.getParameter("tpass"); 
    		
    	   SessionFactory factory = HibernateUtil.getSessionFactory();          
           Session session = factory.openSession();
           	   List<CredValidated> list = session.createQuery("from CredValidated", CredValidated.class).list();

        	   session.beginTransaction();
        	   CredValidated newUser = new CredValidated();
        	   newUser.setName(name);
        	   newUser.setPasscode(pass);
        	   session.close();
               
        	   boolean found = false;
        	for(CredValidated p: list) {
        		if(p.getName().equals(name) && p.getPasscode().equals(pass)) {
                    found = true;
        		}
        	}
        	if(found == true) {
        		out.println("<b>Welcome!</b><br>");
        		out.println("Wonderful to see you again " + newUser.getName()+"<br>");
        		out.println("hope you have a good day!<br>");
        		out.println("<a href='index.jsp'>Log Out</a><br>");
        	} else {
        		out.println("<b>Invalid UserName or Password</b><br>");
        		out.println("please recheck your inputs<br>");
                out.println("<a href='NewUser.jsp'>New User? Register here</a><br>");
                out.println("<a href='index.jsp'>Return</a><br>");
                out.println("<br>");
        	}
            out.println("</body></html>");       
   
    }catch (InputMismatchException ex) {
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
        out.println("an input was incorrectly typed. Please try again <br>");
        out.println("<a href='index.jsp'>Return</a><br>");
        out.println("</body></html>");
   }catch(Exception e) {
    	throw e;
    }
		
	}

	
	
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
    		PrintWriter out = response.getWriter();
    		out.println("<html><body>");
    		String name = request.getParameter("name");
    		String pass = request.getParameter("pass"); 
    		String Cpass = request.getParameter("Cpass"); 

    		
    	   SessionFactory factory = HibernateUtil.getSessionFactory();          
           Session session = factory.openSession();
           if(pass.equals(Cpass) && pass != "" && name != "") {
        	   session.beginTransaction();
        	   CredValidated newUser = new CredValidated();
        	   newUser.setName(name);
        	   newUser.setPasscode(pass);
        	   session.save(newUser);
        	   session.getTransaction().commit();
        	   session.close();
                         
               out.println("<b>Successful!</b><br>");
               out.println(String.valueOf(newUser.getName()) + " is a new user added!<br>");
               out.println("<a href='index.jsp'>Return to sign in</a><br>");
               out.println("</body></html>");
           } else {
        	   out.println("Sorry, please recomfirm your password and confirm password are the same <br>");
        	   out.println("or if any of the inputs are blank <br>");
        	   out.println("<a href='NewUser.jsp'>Return</a><br>");
        	   out.println("</body></html>");
           }
        
        
		}catch (InputMismatchException ex) {
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("an input was incorrectly typed. Please try again <br>");
			out.println("<a href='NewUser.jsp'>Return</a><br>");
			out.println("</body></html>");
		}catch(Exception e) {
			throw e;
		}
	}	
}

