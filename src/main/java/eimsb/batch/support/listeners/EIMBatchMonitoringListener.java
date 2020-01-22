package eimsb.batch.support.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/*
 * Listener to log Job Execution Events to log
 */
public class EIMBatchMonitoringListener implements JobExecutionListener{

	private Logger logger = LoggerFactory.getLogger(EIMBatchMonitoringListener.class);
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		logger.info("Started job "+jobExecution);
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		logger.info("Ended job "+jobExecution);
		if(BatchStatus.FAILED == jobExecution.getStatus()) {
			logger.error(jobExecution.getJobId() + "FAILED" , (jobExecution.getAllFailureExceptions()).get(0));
		}
		else if(BatchStatus.ABANDONED == jobExecution.getStatus()) {
			logger.warn(jobExecution.getJobId() + " was abondoned" );
		}
		
	}

}
