package decl.client;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.osgi.util.tracker.ServiceTracker;

import com.caucho.hessian.server.HessianServlet;

public class HttpActivator  {
	
		 private Client consumer;
		 
		 ServiceTracker httpTracker;

		 BundleContext context;
		 
		 HttpService httpService;
		 
		 public HttpActivator(BundleContext bundleContext) {

			 
		    	this.context = bundleContext;
        		
		    	TestServlet test = new TestServlet(); 
        		try {
					httpService.registerServlet("/test", test, null, null);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NamespaceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		    	/*
		    	 httpTracker = new ServiceTracker(bundleContext, HessianServlet.class.getName(), null) {
			   	      
			   		  public void removedService(ServiceReference reference, Object service) {
			   	        // HTTP service is no longer available, unregister our resources...
			   	        try {
			  // 	           ((IService) service).unregister("/static");
			   	        	((HttpService) (service)).unregister((String) reference.getProperty("url"));
			   	        } catch (IllegalArgumentException exception) {
			   	           // Ignore; servlet registration probably failed earlier on...
			   	        }
			   	      }

			   	      public Object addingService(ServiceReference reference) {
			   	        // HTTP service is available, register our resources...
			   	   	  
			   	    	  
			   	    	ServiceReference httpRef = this.context.getServiceReference(HttpService.class.getName());
			   	    	  
			   	    	httpService = (HttpService) this.context.getService(httpRef);
			   	    	  
			   	        Servlet httpServlet = (Servlet) this.context.getService(reference);
			   	        String url = (String) reference.getProperty("url");
				        try {
				        	System.out.println(" http = " + httpService);
				        	if(httpService != null)
				        		httpService.registerServlet(url, httpServlet, null, null);
				        	
				        	System.out.println(" http reg test= " + httpService);
				        	
				        		TestServlet test = new TestServlet(); 
				        		httpService.registerServlet("/test", test, null, null);
				        	
				        	} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				     //   consumer.startTimer();
				        
			   	      
			   	        return httpService;
			   	      }
			   	    };
			   	    // start tracking all HTTP services...
			   	    httpTracker.open();
		    }
		    
		    @Override
		    public void stop(BundleContext bundleContext) throws Exception {
		        consumer.stopTimer();
		    }
		    */
		 }
		 
		    public void setHttpService(HttpService httpService) throws Exception {
		        System.out.println("http registered");	
		        this.httpService = httpService;
		   //     this.servletsTracker.open();
		    }

		    public void unsetHttpService(ServiceReference reference) {
		        this.httpTracker.close();
		    }
		    
	}

