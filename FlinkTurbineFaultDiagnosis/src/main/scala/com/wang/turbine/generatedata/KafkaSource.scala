package com.wang.turbine.generatedata

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer010, FlinkKafkaConsumer011}

/**
 * 读取kafka的数据
 */

object KafkaSource {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    //1.读取kafka的数据
    val properties = new Properties()
    properties.setProperty("bootstrap.servers","hadoop1:9092")
    properties.setProperty("group.id","consumer1")
    properties.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
    properties.setProperty("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
    properties.setProperty("auto.offset.reset","latest")

    //2.创建kafka的消费者
    val stream1 = env.addSource(new FlinkKafkaConsumer010[String]("wang",new SimpleStringSchema(),properties))


    stream1.print().setParallelism(1)

    env.execute("source test")
  }

}
