package decl.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.osgi.service.log.LogService;

import decl.api.IService;

public class Client implements ActionListener {
	private IService service;
	private final Timer timer;
	LogService logService;
		
	
	public LogService getlogService() {
		return logService;
	}

	public void setlogService(LogService logService) {
		this.logService = logService;
	}
	
	public Client(IService service) throws Exception {
		// TODO Auto-generated method stub
		this.service = service;	
		timer = new Timer(1000, this);
	   	
	}

	public Client(IService service, LogService logService) throws Exception {
		// TODO Auto-generated method stub
		this.service = service;	
		this.logService = logService;
		timer = new Timer(1000, this);
	   	
	}

	  public void startTimer(){
	        timer.start();
	    }
	 
	    public void stopTimer() {
	        timer.stop();
	    }
	 
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	if(this.logService != null)
	    		logService.log(LogService.LOG_INFO, "heehaw Iam logging");
	       System.out.println( service.execute("ramone"));
	    }
	    

}