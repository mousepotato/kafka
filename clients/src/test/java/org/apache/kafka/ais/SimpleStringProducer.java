/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.kafka.ais;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

/*
 * @author: shuangjiang.li
 * @date: 2/18/18 21:08
 */
public class SimpleStringProducer {

    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "100.67.148.133:8080"); //es-na61-a-01.na61
        // // props.put("bootstrap.servers", "100.67.148.156:8080");
        // props.put("bootstrap.servers", "10.218.146.129:9092"); // tbc
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 10; i++) {
            System.out.println("ib = " + i);
            ProducerRecord<String, String> record = new ProducerRecord<>("VIP_test1", "1", "value-" + i);
            // ProducerRecord<String, String> record = new ProducerRecord<>("demo", "1", "value-" + i);
            System.out.println(record.toString());
            // producer.send(record);
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    System.out.println(metadata.topic());
                    System.out.println(metadata.partition());
                }
            });
            Thread.sleep(250);
        }

        producer.close();
    }
}