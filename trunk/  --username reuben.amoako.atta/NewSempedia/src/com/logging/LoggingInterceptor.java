/**
 * 
 */
package com.logging;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.TTCCLayout;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

/**
 * @author tauseefsyed
 * 
 */
public class LoggingInterceptor implements MethodBeforeAdvice,
		AfterReturningAdvice, ThrowsAdvice {
	private static Logger log = null;
	private static FileAppender fileappender = null; 
	
	public LoggingInterceptor() throws Exception{
		ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.messages");
		fileappender =new FileAppender(new TTCCLayout(),resourceBundle.getString("logfile"));
	}

	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		
		log = Logger.getLogger(arg2.getClass());
		log.addAppender(fileappender);
		log.info(new Date().getTime()+": Beginning method :"+ arg0.getName());
	}

	public void afterReturning(Object arg0, Method arg1, Object[] arg2,
			Object arg3) throws Throwable {
		log = Logger.getLogger(arg3.getClass());
		log.addAppender(fileappender);
		log.info("Ending method: ?? "+ arg1.getName());
	}

	public void afterThrowing(Method m, Object[] args, Object target,
			Throwable ex) {
		log = Logger.getLogger(target.getClass());
		log.addAppender(fileappender);
		log.info("Exception in method: " + m.getName() + " Exception is: "
				+ ex.getMessage()+"Stack Trace: "+ex.getStackTrace());
		ex.printStackTrace();		
	}

}
