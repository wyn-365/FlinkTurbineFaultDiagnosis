package com.wang.flink.utils;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author 王一宁
 * @date 2020/1/16 20:45
 * 从Kafka中消费数据，然后写入到redis中 实现exactly_once
 * 传值： --topics wang --group.id abc 或者路径
 * D:\APP\IDEA\workplace\FlinkTurbineFaultDiagnosis\src\main\resources\config.properties
 *         ParameterTool parameters = ParameterTool.fromPropertiesFile(args[0]);
 *         String groupId = parameters.get("group.id","consumer1");
 *         String topics = parameters.getRequired("topics");
 */
public class FlinkUtils {

    private static StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    public static <T> DataStream<T> createKafkaStream(ParameterTool parameters,Class<? extends DeserializationSchema<T>> clazz) throws Exception {

        //设置全局的参数
        env.getConfig().setGlobalJobParameters(parameters);

        //checkpoint配置
        env.enableCheckpointing(parameters.getLong("checkpoint.interval",5000L),CheckpointingMode.EXACTLY_ONCE);
        //env.setStateBackend(new FsStateBackend("file://D:\\APP\\IDEA\\workplace\\FlinkTurbineFaultDiagnosis\\checkpoint"));
        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        Properties prop = new Properties();
        prop.setProperty("bootstrap.servers",parameters.getRequired("bootstrap.servers"));
        prop.setProperty("group.id",parameters.getRequired("group.id"));
        prop.setProperty("auto.offset.reset",parameters.get("auto.offset.reset","earliest"));
        //不自动提交偏移量，交给flink的checkpoint处理哦
        prop.setProperty("enable.auto.commit",parameters.get("enable.auto.commit","false"));
        String topics = parameters.getRequired("topics");
        List<String> topicList = Arrays.asList(topics.split(","));

        FlinkKafkaConsumer010<T> kafkaConsumer = new FlinkKafkaConsumer010<T>(
                topicList,
                clazz.newInstance(),
                prop);

        return env.addSource(kafkaConsumer);
    }

    public static StreamExecutionEnvironment getEnv(){
        return env;
    }



}

