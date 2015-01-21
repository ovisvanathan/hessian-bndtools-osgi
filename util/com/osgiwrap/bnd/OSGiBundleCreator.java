package com.osgiwrap.bnd;

/*******************************************************************************
 * Copyright (c) 2014 Omprakash Visvanathan.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the LGPL.
  * 
 * This file is part of the Colander Business Rules Project of Meghrules.com.
 * Copyright (C) 2012-2020 Meghrules.com
 * 
 ******************************************************************************/
 
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.BuildListener;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.selectors.FileSelector;

import aQute.bnd.ant.WrapTask;
import aQute.libg.qtokens.QuotedTokenizer;


public class OSGiBundleCreator  {

    
	private final static String DEFAULT_BUILD_FILE = "build.xml";

	
    public static final Category logger = Logger.getInstance(Javac.class);
	    
	private final static String ANT_HOME = System.getenv("ANT_HOME");

	
	private Project project = null;
	
    String baseDir;

    String jarsDir, defnDir, destDir;
    
    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }


	public OSGiBundleCreator() throws Exception {
	
		System.out.println("Starting Test Runner ....");
		//TODO:Remove this. Its for test only.
		
		System.out.println("Checking ANT_HOME ...");
		if(ANT_HOME == null){
			throw new Exception("ANT_HOME is not set. Please set environment variable ANT_HOME");
		}
		
	} 

	
	public OSGiBundleCreator(String jarsDir, String defnDir, String destDir) throws Exception {
		
		System.out.println("Starting Test Runner ....");
		//TODO:Remove this. Its for test only.
		
		System.out.println("Checking ANT_HOME ...");
		if(ANT_HOME == null){
			throw new Exception("ANT_HOME is not set. Please set environment variable ANT_HOME");
		}

		this.jarsDir =  jarsDir;
		this.defnDir = defnDir;
		this.destDir = destDir;	
		

	} 

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		System.out.println("Starting Test Runner ....");
		//TODO:Remove this. Its for test only.
		
		System.out.println("Checking ANT_HOME ...");
		if(ANT_HOME == null){
			throw new Exception("ANT_HOME is not set. Please set environment variable ANT_HOME");
		}

		if(args.length != 3) {
			System.out.println("Incorrect no of Params ...");
			System.out.println("Usage is java OSGIBundleCreator <jarsdir> <definitionsdir> <destinationdir> ");
			System.exit(1);
		}
		
		
		OSGiBundleCreator antCompilerAdapter = new OSGiBundleCreator(args[0], args[1], args[2]);
		System.out.println("Initializing Ant Project ...");
		
		antCompilerAdapter.compile();
	}

	public void compile(final String packageName, final String srcFolder, final String binFolder, final String bcp) {		
		System.out.println("dummy compile");		
	}

	public void compile() {
		
	try
	{
	
	//	project = new Project();
		project = getProject();
		System.out.println("GP done = ");

		project.addTaskDefinition("bndwrap", aQute.bnd.ant.WrapTask.class);
		
		WrapTask wrapTask = (WrapTask) project.createTask("bndwrap");
		Target target = new Target();
		target.setName("jarwrap");
		target.setProject(project);
	    target.addTask(wrapTask);
		
	    wrapTask.setProject(project);
	   	    
	    File destDir = new File(this.destDir);
	    
	    if(!destDir.exists())
	    	destDir.mkdirs();
	    
		wrapTask.setOutput(destDir);
		
		wrapTask.setDefinitions(new File(this.defnDir));		
						
		FileSet jarSet = new FileSet();

		jarSet.setDir(new File(this.jarsDir));
		
	    FileSelector selector = new FileSelector() {
	    
			public boolean isSelected(File baseDir, String fileName, File file)
				throws BuildException {

				if (fileName.endsWith(".jar") || fileName.endsWith(".war")) {
					return true;
				}
				else {
					return false;
				}
			}
	    };
	    

	    jarSet.add(selector);

		wrapTask.addConfiguredFileSet(jarSet);

	  //  jarSet.setVerbose(true);
	    
	    try {
	    	wrapTask.execute();
	    }
	    catch (Exception x) {
	    	logger.error("cannot compile sources", x);
			System.out.println("cannot compile sources ");
			x.printStackTrace();

	    }

	}   catch (Exception x) {
		x.printStackTrace();
	    	logger.error("cannot compile sources", x);
	    }

	}

	public static File getFile(File base, String file) {
		File f = new File(file);
		if (!f.isAbsolute()) {
			int n;

			f = base.getAbsoluteFile();
			while ((n = file.indexOf('/')) > 0) {
				String first = file.substring(0, n);
				file = file.substring(n + 1);
				if (first.equals(".."))
					f = f.getParentFile();
				else
					f = new File(f, first);
			}
			f = new File(f, file);
		}
		try {
			return f.getCanonicalFile();
		}
		catch (IOException e) {
			return f.getAbsoluteFile();
		}
	}
	
	protected Project getProject() {
		Project project = new Project();
	
		project.addBuildListener(new BuildListener() {

			public void buildFinished(BuildEvent arg0) {
		//		logger.info("finished build");
				System.out.println("finished build");

			}

			public void buildStarted(BuildEvent arg0) {
		//		logger.info("starting build");
				System.out.println("starting build");

			}

			public void messageLogged(BuildEvent e) {
				if (e.getException() != null) {
					System.out.println(e.getMessage());

					logger.error(e.getMessage(), e.getException());
				} else {
					// TODO use log priority in message
					System.out.println(e.getMessage());
					logger.error(e.getMessage());
				}
			}

			public void targetFinished(BuildEvent e) {
		//		logger.error("target finished: " + e.getTarget());
				System.out.println("target finished: " + e.getTarget());
				System.out.println(e.getMessage());
				logger.error(e.getMessage());
			}

			public void targetStarted(BuildEvent e) {
				logger.error("target started: " + e.getTarget());
				logger.error(e.getMessage());
				System.out.println("target started: " + e.getTarget());
				System.out.println(e.getMessage());

			}

			public void taskFinished(BuildEvent e) {
				logger.error("task finished: " + e.getTask());
				logger.error(e.getMessage());
				System.out.println("task finished: " + e.getTask());
				System.out.println(e.getMessage());

			}

			public void taskStarted(BuildEvent e) {
				logger.info("task started: " + e.getTask());
				logger.info(e.getMessage());
				System.out.println("task started:  = " + e.getTask());
				System.out.println(e.getMessage());

			}
		});	

		try {
			project.init();
		}
		catch (Throwable t) {
			t.printStackTrace();
		}

		return project;
	}

	
	private void setProperties(Map<String,String> propertyMap,boolean overridable ) throws Exception{
		if(project == null){
			throw new Exception("Properties can not be set. Please initialize the project first.");
		}
		
		if(propertyMap == null){
			throw new Exception("Properties map provided was null");
		}
		
		Set propKeys = propertyMap.keySet();
		Iterator iterator = propKeys.iterator();
		
		while (iterator.hasNext()) {
			String propertyKey = (String) iterator.next();
			String propertyValue = propertyMap.get(propertyKey);
			
			if(propertyValue == null) continue;
			
			if(overridable){
				project.setProperty(propertyKey,propertyValue);
			} else {
				project.setUserProperty(propertyKey,propertyValue);
			}
		}
		project.setSystemProperties();
	}

	private void runTarget(String targetName) throws Exception{
		if(project == null){
			throw new Exception("Properties can not be set. Please initialize the project first.");
		}
		
		if(targetName == null){
			targetName = project.getDefaultTarget();
		}
		
		if(targetName == null){
			throw new Exception("Target Not found :"+targetName);
		}

		project.executeTarget(targetName);
	}


}


