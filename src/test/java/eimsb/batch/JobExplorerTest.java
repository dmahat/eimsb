package eimsb.batch;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/eimsb-context.xml","/test-h2-config.xml"})
@DirtiesContext
public class JobExplorerTest {
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job job;
	
	@Autowired
	private JobExplorer jobExplorer;
	
	/*
	 * Example of using the JobExplorer API to get Job History
	 */
	@Test
	public void explore() {
		importProducts();
		List<JobInstance> jobInstances = jobExplorer.getJobInstances("importProducts", 0, 30);
		Assert.assertEquals(1, jobInstances.size());
		
	}



	private void importProducts() {	
			try {
				jobLauncher.run(job, new JobParametersBuilder()
					.addString("targetDirectory", "./target/test-classes/data/")
					.addString("inputFile","products.txt")
					.addString("outputFile","products-out.txt")
					.addLong("timestamp", System.currentTimeMillis())
					.toJobParameters()
				);
			} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
					| JobParametersInvalidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail(e.getMessage());
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {

			}
	}
}
