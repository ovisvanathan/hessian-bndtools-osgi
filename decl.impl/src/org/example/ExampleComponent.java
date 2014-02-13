package org.example;

import com.caucho.hessian.server.HessianServlet;

import decl.api.IService;

public class ExampleComponent extends HessianServlet implements IService {

	// TODO: class provided by template
	public String execute(String name) {
		
		return "hello " + name;
	}

}