package com.wang.turbine.generatedata


import java.util.Properties

import com.wang.turbine.config.ConfigurationManager
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

import scala.io.Source

object MockRealTimeData2 {

  /**
   * 创建kafka生产者 读取文件生产数据
   * @param broker
   * @return
   */
  def createKafkaProducer(broker: String): KafkaProducer[String, String] = {
    // 创建配置对象
    val prop = new Properties()
    // 添加配置
    prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, broker)
    prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")

    // 根据配置创建Kafka生产者
    new KafkaProducer[String, String](prop)
  }

  val source = Source.fromFile("D:\\APP\\IDEA\\workplace\\spark-commerce\\mock\\turbine_data\\WT00100-dex10mr.csv", "UTF-8")
  val lineIterator = source.getLines
  val lines =lineIterator.toArray


  def main(args: Array[String]): Unit = {
    // 获取配置文件commerce.properties中的Kafka配置参数
    val broker = ConfigurationManager.config.getString("kafka.broker.list")
    val topic = ConfigurationManager.config.getString("kafka.topics")

    // 创建Kafka消费者
    val kafkaProducer = createKafkaProducer(broker)

      // 随机产生实时数据并通过Kafka生产者发送到Kafka集群中
      for (item <- lines) {
        kafkaProducer.send(new ProducerRecord[String, String](topic, item))
        Thread.sleep(1000)
      }


  }
}
