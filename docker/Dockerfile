# Centos based container with Java
#FROM eim/java8:latest

# Alpine Linux with OpenJDK JRE
FROM openjdk:8-jre-alpine

# Install H2
RUN \
  cd /tmp && \
  wget http://www.h2database.com/h2-2014-04-05.zip && \
  unzip h2-2014-04-05.zip && \
  rm -f h2-2014-04-05.zip && \
  mkdir -p /opt/h2 && \
  mv /tmp/h2 /opt && \
  mkdir -p /opt/h2-data

# Expose ports.
#   - 1521: H2 Server
#   -   81: H2 Console
EXPOSE 1521 81

# Define default command
CMD java -cp /opt/h2/bin/h2*.jar org.h2.tools.Server -web -webAllowOthers -webPort 81 -tcp -tcpAllowOthers -tcpPort 1521 -baseDir /opt/h2-data
