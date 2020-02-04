package com.wang.flink.checkpoint;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author 王一宁
 * @date 2020/1/16 10:51
 */
public class StateBackend01 {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        /**
         * 只有开启了checkpoint，5s才会有重启策略，固定时间，无限重启
         * 默认把中间结果保存于JobMananger的内存
         */
        env.enableCheckpointing(5000);

        //自定义重启固定次数，和重启时间
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(3,2000));

        //本地目录:设置状态存储的后端,知识当前的job，建议在配置文件中全局配置
        //env.setStateBackend(new FsStateBackend("file://D:\\APP\\IDEA\\workplace\\FlinkTurbineFaultDiagnosis\\checkpoint"));

        //HFDS:存储chenckpoint
        System.setProperty("HADOOP_USER_NAME","root");
        env.setStateBackend(new FsStateBackend("hdfs://hadoop1:9000/checkpoint01"));

        /**
         * 程序异常退出，或者人为取消，不删除checkpoint目录数据
         */
        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);


        DataStreamSource<String> lines = env.socketTextStream("192.168.52.200", 8888);
        SingleOutputStreamOperator<String> wangyining = lines.map(new MapFunction<String, String>() {
            @Override
            public String map(String line) throws Exception {

                if (line.startsWith("wangyining")) {
                    throw new RuntimeException("老王的程序挂了！");
                }
                return line.toUpperCase();
            }
        });
        wangyining.print();
        env.execute();

    }
}
