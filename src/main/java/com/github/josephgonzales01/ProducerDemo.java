package com.github.josephgonzales01;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@Slf4j
public class ProducerDemo {

  public static final String BOOTSTRAPSERVER = "localhost:9092";
  private static final String TOPIC_FIRSTTOPIC = "firstTopic";
  private static KafkaProducer<String, String> producer;


  static {
    //Setup Producer config
    Properties properties = new Properties();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAPSERVER);
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    producer = new KafkaProducer<>(properties);
  }


  public static void main(String[] args) {
    produceMessage(TOPIC_FIRSTTOPIC, "hello world");

    for (int i = 1; i <= 10; i++) {
      produceMessageWithCallback(TOPIC_FIRSTTOPIC, "sample message " + i + 10);
    }

    close();
  }

  private static void produceMessage(final String topic, final String message) {
    //Create Producer record
    ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);

    //Send message - asynchronous
    producer.send(record);
  }

  private static void produceMessageWithCallback(final String topic, final String message) {
    //Create Producer record
    ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);

    //Send message - asynchronous with callback that details the calls status(recordMetadata) or error(exception)
    producer.send(record, (recordMetadata, e) -> {
      //check if the call failed
      if (e == null) {
        log.info("Received metadata. \n Topic:{} | Partition:{} | Offset:{} | Timestamp:{}",
            recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset(),
            recordMetadata.timestamp());
      } else {
        log.error("Error while producing a message: {}", e.getMessage());
      }
    });

  }

  private static void close() {
    producer.flush();
    producer.close();
  }


}
