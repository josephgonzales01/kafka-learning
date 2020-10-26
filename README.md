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
   1. Execute kafka-console-producer.sh
   >```
   > $ kafka-console-producer.sh --broker-list dev_kafka:9092 --topic firstTopic acks=all
   >```
   > Note: producing a message to a non existing topic, that topic will be automatically created with a default number 
   > of partitions and replicas as described inside kafka image /opt/kafka/config/server.properties file.

#### Consume a message from a Topic
   1. Execute kafka-console-consumer.sh
   >```
   > $ kafka-console-consumer.sh --bootstrap-server dev_kafka:9092 --topic firstTopic --from-beginning --group my-consumer-app
   >```
   >Note: --from-beginning property reads all the message in which the given group (--group) haven't read before (last committed offset),
   > if no --group is set a consumer group will be automatically created with group named "console-consumer-NNNN"

#### Kafka management with CMAK
   1. go to localhost:9000
   2. Cluster -> Add Cluster
   3. Hit save and click view cluster