package br.com.sysfar.imobileweb.jobs;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ClienteJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		try {

			Logger.getLogger(this.getClass().getName()).info("Executando Cliente Job");
			
			//URL url = new URL("http://localhost:8081/ImobileWeb/ClienteServlet");
			URL url = new URL("http://localhost:8094/imobileweb/ClienteServlet");
			
			((HttpURLConnection) url.openConnection()).getResponseCode();
			
		} catch (IOException e) {
			
			Logger.getLogger(this.getClass().getName()).info(e.getMessage());
			
		}
		
	}

}
