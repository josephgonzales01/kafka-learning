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


    public static void main(String[] args) {

        //Setup Producer config
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAPSERVER);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //Create Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        //Create Producer record
        ProducerRecord<String, String> record = new ProducerRecord<>("firstTopic", "sample message");

        //Send message - asynchronous
        producer.send(record);
        producer.close();
    }

}
