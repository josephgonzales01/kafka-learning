## Pre-requesite
- Java 8 or higher
- Download and Install [Docker Desktop](https://docs.docker.com/desktop/)

## Setup
  1. Clone this repository
  2. CD to repository folder and execute
  >```
  > docker-compose -f docker-compose.yml up
  >```

#### Create a Topic
   1. Go inside kafka container
   >```
   > $ docker exec -it dev_kafka /bin/sh
   > $ cd opt/kafka/bin/
   >```
   2. Execute kafka-topics.sh
   >```
   > $ kafka-topics.sh --zookeeper dev_zookeeper:2181 --topic first_topic --create --partitions 2 --replication-factor 1
   >```

#### Produce a message to a Topic
   2. Execute kafka-console-producer.sh
   >```
   > $ kafka-console-producer.sh --broker-list dev_kafka:9092 --topic secondTopic acks=all
   >```
   > Note: producing a message to a non existing topic, that topic will be automatically created with a default number 
   > of partition and replicas described in /opt/kafka/config/server.properties file.

#### Consume a message from a Topic
   2. Execute kafka-console-consumer.sh
   >```
   > $ kafka-console-consumer.sh --bootstrap-server dev_kafka:9092 --topic --from-beginning
   >```
