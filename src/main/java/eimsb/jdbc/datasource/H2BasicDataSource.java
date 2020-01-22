package eimsb.jdbc.datasource;




import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;


/*
 * Extends @org.apache.commons.dbcp.BasicDataSource DataSource
 * Adds functionality to support initialization of Database Schema
 */
public class H2BasicDataSource extends BasicDataSource {
	
	private String schemaFile;
	private boolean initializeSchema = false;


	public String getSchemaFile() {
		return schemaFile;
	}

	public void setSchemaFile(String schemaFile) {
		this.schemaFile = schemaFile;
	}

	public boolean isInitializeSchema() {
		return initializeSchema;
	}

	public void setInitializeSchema(boolean initializeSchema) {
		this.initializeSchema = initializeSchema;
	}

	/*
	 * Post-Instantiation Hook
	 * Call after Bean creation, to create/re-create the schema prior to use
	 * 
	 * Uses ResourceDatabasePopulator to initialize a schema into database.
	 */
	public void init() throws Exception {
		if(initializeSchema) {
			ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
			populator.addScript(new ClassPathResource(getSchemaFile()));
			populator.populate(getConnection());
		}
		
	}

	/*
	 * Shutdown Hook
	 */
	public void close() throws SQLException{
		shutdown(this);
		super.close();
		
	}
	
	public void shutdown(DataSource dataSource) {
		try {
			Connection connection = dataSource.getConnection();
			Statement stmt = connection.createStatement();
			stmt.execute("SHUTDOWN");
		}
		catch (SQLException ex) {
//			if (logger.isWarnEnabled()) {
//				logger.warn("Could not shutdown embedded database", ex);
//			}
		}
	}



}
