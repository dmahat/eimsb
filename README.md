# EIMSB

A Sample Spring Batch Application that uses H2 in-memory database with an externalized file for storing Job History

## Getting Started

### Prerequisites
Prior to running any mvn commands, the H2 URL specified for the datasource bean in the file /src/test/resources/test-h2-config.xml
<property name="url" value="jdbc:log4jdbc:h2:file:/Users/enirtium/H2/test;DB_CLOSE_ON_EXIT=FALSE"> must be changed to a file in local file system.


### Installing
Clone/download source


## Running the tests
run mvn command to compile, test the code.
The Source has two unit tests that verify the config and confirm the job data is accessible via the JobExplorer APIs.

## Post test
After completion of thest, the database files can be found under the specified file. The file can be used as external file to another H2 database (say running in docker container) and Table and data can be viewed using the H2 Web Server via web browser.





## Write Up
@ See the artifacts under /docs subfolder for analysis details.

