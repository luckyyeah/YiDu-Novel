package org.yidu.novel.Listener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.springframework.web.context.WebApplicationContext;

public class JobListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
   WebApplicationContext webApplicationContext = (WebApplicationContext) arg0.getServletContext().getAttribute(
            WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
   Scheduler job = (Scheduler) webApplicationContext.getBean("schedulerFactory");

		  try {
		   if(job.isStarted()){
		    job.shutdown();
		    Thread.sleep(1000);
		   }
        // This manually deregisters JDBC driver
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		  } catch (SchedulerException e) {
		   e.printStackTrace();
		  } catch (InterruptedException e) {
		   e.printStackTrace();
		  }
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

}
