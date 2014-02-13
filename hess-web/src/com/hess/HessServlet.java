package com.hess;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Servlet implementation class HessServlet
 */
@WebServlet("/jtunisie")
public class HessServlet extends DispatcherServlet {
	private static final long serialVersionUID = 1L;
       
	WebApplicationContext webApplicationContext;
    /**
     * @see DispatcherServlet#DispatcherServlet()
     */
    public HessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
       
    /**
     * @see DispatcherServlet#DispatcherServlet(WebApplicationContext)
     */
    public HessServlet(WebApplicationContext webApplicationContext) {
        super(webApplicationContext);
        // TODO Auto-generated constructor stub
        this.webApplicationContext = webApplicationContext;
    }

    public void service() {
    	
    	Object bean = webApplicationContext.getBean("/jtunisie");
    
    	System.out.println("bean = " + bean.getClass().getName());
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

}
