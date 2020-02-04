package com.wang.turbine.sink

import java.util.Properties


import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer011, FlinkKafkaProducer011}

/**
 * 1.读取本地数据写入到kafka
 * 2.读取kafuka的数据消费kafka数据  管道
 */
object KafkaSink {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setMaxParallelism(1)

    //1.读取kafka的数据
    val properties = new Properties()
    properties.setProperty("bootstrap.servers","hadoop1:9092")
    properties.setProperty("group.id","consumer1")
    properties.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
    properties.setProperty("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
    properties.setProperty("auto.offset.reset","latest")

    //2.创建kafka的消费者
    val inputStream = env.addSource(new FlinkKafkaConsumer011[String]("sensor",new SimpleStringSchema(),properties))


    //3.读取文件
    //val streamFromFile = env.readTextFile("D:\\APP\\IDEA\\workplace\\HelloFlink\\src\\main\\resources\\sensor.txt")

    val dataStream = inputStream.map(data=>{
      val dataArray = data.split(",")//trim去掉空格 有的话
      //SensorReading(dataArray(0).trim,dataArray(1).trim.toLong,dataArray(2).trim.toDouble)
        .toString//方便序列化输出
    })

    //Sink连接kafka
    //dataStream.addSink(new FlinkKafkaProducer011[String]("hadoop1:9092","KafkaSink",new SimpleStringSchema()))
    dataStream.print()

    env.execute("kafka-sink")
  }
}
