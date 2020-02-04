package com.wang.turbine.cleandata

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.environment.CheckpointConfig
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer010, FlinkKafkaConsumer011}

/**
 * 创建kafka的消费者
  * Created by 王一宁 on 2019/10/23.
 *
  */
object StreamingKafkaSourceScala {

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    //隐式转换
    import org.apache.flink.api.scala._


    //checkpoint配置
    env.enableCheckpointing(5000);
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
    env.getCheckpointConfig.setMinPauseBetweenCheckpoints(500);
    env.getCheckpointConfig.setCheckpointTimeout(60000);
    env.getCheckpointConfig.setMaxConcurrentCheckpoints(1);
    env.getCheckpointConfig.enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);

    //设置statebackend

    //env.setStateBackend(new RocksDBStateBackend("hdfs://hadoop100:9000/flink/checkpoints",true));

    val topic = "wang"
    val prop = new Properties()
    prop.setProperty("bootstrap.servers","hadoop1:9092")
    prop.setProperty("group.id","con1")

    //消费者
    val myConsumer = new FlinkKafkaConsumer010[String](topic,new SimpleStringSchema(),prop)
    val text = env.addSource(myConsumer)


    text.print()


    env.execute("StreamingFromCollectionScala")



  }

}
