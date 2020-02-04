package com.wang.flink.checkpoint;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.runtime.executiongraph.failover.RestartAllStrategy;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author 王一宁
 * @date 2020/1/16 10:51
 */
public class RestartStrategies01 {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        /**
         * 只有开启了checkpoint，5s才会有重启策略，固定时间，无限重启
         * 默认把中间结果保存于JobMananger的内存
         */
        env.enableCheckpointing(5000);
        //自定义重启固定次数，和重启时间
        env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(3,2000));

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
