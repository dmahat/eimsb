package eimsb.batch;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import eimsb.model.Product;

/*
 * No Op Writer for debugging purposes
 */
public class NoOpWriter implements ItemWriter<Product> {
	
	private Logger logger = LoggerFactory.getLogger(eimsb.batch.NoOpWriter.class);

	@Override
	public void write(List<? extends Product> items) throws Exception {
		//Thread.sleep(5000);
		logger.debug("No Op Writer");
		//throw new RuntimeException("Failing the job");
	}

}
