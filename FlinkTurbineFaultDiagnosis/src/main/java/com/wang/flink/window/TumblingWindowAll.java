package com.wang.flink.window;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.AllWindowedStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

/**
 * @author 王一宁
 * @date 2020/1/15 15:40
 */
public class TumblingWindowAll {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> lines = env.socketTextStream("192.168.52.200",8888);

        //把传进来的数据String换成int类型
        SingleOutputStreamOperator<Integer> nums = lines.map(new MapFunction<String, Integer>() {
            @Override
            public Integer map(String value) throws Exception {
                return Integer.parseInt(value);
            }
        });


        //不分组 5s 中聚合一次
        AllWindowedStream<Integer, TimeWindow> window = nums.timeWindowAll(Time.seconds(5));

        //窗口中聚合
        SingleOutputStreamOperator<Integer> sumed = window.sum(0);
        sumed.print();

        env.execute();

    }
}
