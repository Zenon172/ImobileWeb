package br.com.sysfar.imobileweb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import br.com.sysfar.imobileweb.jobs.ClienteJob;

@WebServlet(value="/InicialServlet", loadOnStartup=1)
public class InicialServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    public InicialServlet() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	
    	System.out.println("Funcionou");
    	
    	SchedulerFactory sf = new StdSchedulerFactory();
    	Scheduler sched;
    	
		try {
			
			sched = sf.getScheduler();
			
			JobDetail job = JobBuilder.newJob(ClienteJob.class).withIdentity("clienteJob", "cliente").build();
	    	
			CronTrigger trigger = TriggerBuilder.newTrigger()
					 .withIdentity("clienteTrigger", "cliente")
					 .withSchedule(CronScheduleBuilder.cronSchedule("0 45 19 * * ? *"))
					 .build();
			
	    	sched.scheduleJob(job, trigger);
	    	     
	    	sched.start();
			
		} catch (SchedulerException e) {

			e.printStackTrace();
			
		}
    	 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
