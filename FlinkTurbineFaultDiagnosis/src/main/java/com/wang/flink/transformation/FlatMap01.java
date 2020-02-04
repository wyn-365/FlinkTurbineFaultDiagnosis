package com.wang.flink.transformation;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Arrays;

/**
 * @author 王一宁
 * @date 2020/1/14 21:38
 * flatMap把原来的数据打开，放入一个集合中，转换成多个
 */
public class FlatMap01 {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> lines =  env.fromElements("GW001001 GW002002 GW003003","GW001001 GW002002 GW003003","GW001001 GW002002 GW003003");

        SingleOutputStreamOperator<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String line, Collector<String> out) throws Exception {
                //实现一：jdk8的流式处理 lambada表达式
                //Arrays.stream(line.split(" ")).forEach(out::collect);
                //实现二：
                //Arrays.asList(line.split(" ")).forEach(w -> out.collect(w));

                //实现三：最原始的方式
                String[] words = line.split(" ");
                for (String word : words) {
                    out.collect(word);
                }
            }
        });

        words.print();

        env.execute();
    }
}
