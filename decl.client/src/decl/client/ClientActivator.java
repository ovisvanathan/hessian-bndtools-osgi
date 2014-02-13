package decl.client;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;

import decl.api.IService;

public class ClientActivator  implements BundleActivator  {
	 private Client consumer;
	 LogService logService;
	 ServiceTracker apiTracker;

	 ServiceTracker logServiceTracker;
	 BundleContext context;
	 
	    @Override
	    public void start(BundleContext bundleContext) throws Exception {
	      /*
	    	ServiceReference reference = 
	        		bundleContext.getServiceReference(IService.class.getName());
	 
	        consumer = new Client((IService) bundleContext.getService(reference));
	        consumer.startTimer();
		*/	    	
	    	this.context = bundleContext;
	    	
	    	 apiTracker = new ServiceTracker(bundleContext, IService.class.getName(), null) {
	   	      
	   		  public void removedService(ServiceReference reference, Object service) {
	   	        // HTTP service is no longer available, unregister our resources...
	   	        try {
	  // 	           ((IService) service).unregister("/static");
	   	        } catch (IllegalArgumentException exception) {
	   	           // Ignore; servlet registration probably failed earlier on...
	   	        }
	   	      }

	   	      public Object addingService(ServiceReference reference) {
	   	        // HTTP service is available, register our resources...
	   	        IService apiService = (IService) this.context.getService(reference);
		        try {
					consumer = new Client(apiService);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        consumer.startTimer();
		        
	   	        /*
	   	        TestServlet testservlet = new TestServlet(); 
	   	        try {
	   	          httpService.registerServlet("/felixhttp", testservlet, null, null);
	   	        } catch (Exception exception) {
	   	          exception.printStackTrace();
	   	        }
	   	        */
	   	        return apiService;
	   	      }
	   	    };
	   	    // start tracking all HTTP services...
	   	    apiTracker.open();
	   	    
	       
	   	 logServiceTracker = new ServiceTracker(bundleContext, LogService.class.getName(), null) {
	   	      
	   	      public Object addingService(ServiceReference reference) {
	   	        // HTTP service is available, register our resources...
	   	        LogService logService = (LogService) this.context.getService(reference);
		        try {
					if(consumer != null)
						consumer.setlogService(logService);
					
					
					logService.log(LogService.LOG_INFO, "logger started");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	   	        return logService;
	   	      }
	   	    };
	   	    // start tracking all HTTP services...
	   	 logServiceTracker.open();
            
	    }
	 
	    @Override
	    public void stop(BundleContext bundleContext) throws Exception {
	        consumer.stopTimer();
	    }
}
