package hessclient;

import java.net.MalformedURLException;

import org.osgi.framework.ServiceException;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.caucho.hessian.client.HessianProxyFactory;

import decl.api.IService;

public class HessClient {
 	
	 public static void main(String[] args) throws MalformedURLException, ServiceException {
		 		 
	//	 FileSystemXmlApplicationContext ctx = 
		//	      new FileSystemXmlApplicationContext("c:/users/omprakash/workspaceg/hess.client/bin/META-INF/spring/applicationContext.xml");
			
		 //	System.out.println(" props = " + ctx.getBean("props"));
		 	
	        HessianProxyFactory factory = new HessianProxyFactory();
	        IService service = (IService) factory.create(IService.class, "http://localhost:8080/jtunisie");
	        System.out.println(service.execute("omar"));
	        
		 	
		 	/*
		 	IService service = (IService) ctx.getBean("helloWorldService");
		 	 
		    String welcomeMessage = service.execute("dees");
		 
		    System.out.println(welcomeMessage);
		    */
	    }

	
}
