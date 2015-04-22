package br.com.sysfar.imobileweb.jobs;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ClienteJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		try {

			URL url = new URL("http://localhost:8081/ImobileWeb/ClienteServlet");
			//URL url = new URL("http://www.sistemaimobileweb.com.br/imobileweb/ClienteServletJob");
			
			((HttpURLConnection) url.openConnection()).getResponseCode();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}

}
