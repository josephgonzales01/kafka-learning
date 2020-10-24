## Pre-requesite
- Java 8 or higher
- Download and Install [Docker Desktop](https://docs.docker.com/desktop/)

## Setup
  1. Clone this repository
  2. CD to repository folder and execute
      >```
      > docker-compose -f docker-compose.yml up
      >```

### Create a Topic
   1. Go inside kafka container
       >```
       > $ docker exec -it dev_kafka /bin/sh
       > $ cd opt/kafka/bin/
       >```
   2. Execute kafka-topics.sh
       >```
       > $ kafka-topics.sh --zookeeper dev_zookeeper:2181 --topic first_topic --create --partitions 2 --replication-factor 1
       >```
