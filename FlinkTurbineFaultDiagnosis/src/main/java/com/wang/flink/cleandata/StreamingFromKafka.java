package com.wang.flink.cleandata;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;

import java.util.ArrayList;
import java.util.Properties;

/**
 * 消费Kafka中得数据
 * @author 王一宁
 * @date 2020/1/2 12:12
 */
public class StreamingFromKafka {
    public static void main(String[] args) throws Exception{
        //获取环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //kafka配置
        String topic = "wang";
        Properties prop = new Properties();
        prop.setProperty("bootstrap.servers","hadoop1:9092");//多个的话可以指定
        prop.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        prop.setProperty("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        prop.setProperty("auto.offset.reset","latest");
        prop.setProperty("group.id","consumer1");

        FlinkKafkaConsumer010<String> myConsumer = new FlinkKafkaConsumer010<String>(topic, new SimpleStringSchema(), prop);
        //获取数据
        DataStream<String> text = env.addSource(myConsumer);

        //打印
        text.print().setParallelism(1);
        //执行
        //env.execute("StreamingFormCollection");
        env.execute();
    }
}
//指定参数配置
//checkpoint配置
//env.enableCheckpointing(5000);
//env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
//env.getCheckpointConfig().setMinPauseBetweenCheckpoints(500);
//env.getCheckpointConfig().setCheckpointTimeout(60000);
//env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
//env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);

