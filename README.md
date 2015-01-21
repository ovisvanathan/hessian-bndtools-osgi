hessian-bndtools-OSGI
================

This repository contains several bndtools projects that explain how to consume OSGi declarative services from an external java application

The bndtools run descriptor is not in working state. It is better to copy the jars in the generated directory and deploy them to a OSGi container and run the code.

The code is based on the hessian project available at http://hessian.caucho.com/.

The basis for this code is the jroller article available at http://www.jroller.com/ouertani/entry/hessian_extender_on_osgi_container

An OSGI bundlor utility class is also available in the util folder that runs the BndWrap ANT Task from java.The usage is 

"java OSGIBundleCreator <jarsdir> <definitionsdir> <destinationdir>"

License
=======
This software is made available under "LGPL" 

Committers
==========
Om Visvanathan (OVisvanathan@meghrules.com)

/*******************************************************************************
 * Copyright (c) 2014 Omprakash Visvanathan.
 * All rights reserved. 
 * 
 * This file is part of the Colander Business Rules Project of Meghrules.com.
 * Copyright (C) 2012-2020 Meghrules.com. Meghrules.com, Meghrules.in, Meghrules.io
 * and other internet sites as well as Mamallansoft.com are owned by 
 * Om Visvanathan Techmologies llc. 
 * 
 ******************************************************************************/


