package decl.extender;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public abstract class CustomTracker extends ServiceTracker {
	 public CustomTracker(BundleContext context, String clazz) {
		    super(context, clazz, null);
		  }

		  @Override
		  public Object addingService(ServiceReference reference) {
		    try {
		    	System.out.println("in ctrack addsvc");		          
		      serviceRegistered(reference);
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    return super.addingService(reference);
		  }

		  public abstract void serviceRegistered(ServiceReference reference) throws Exception;

		  @Override
		  public void removedService(ServiceReference reference, Object service) {
		    try {
		      serviceUnregistered(reference, service);
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    super.removedService(reference, service);
		  }

		  public abstract void serviceUnregistered(ServiceReference reference, Object service) throws Exception;
}
