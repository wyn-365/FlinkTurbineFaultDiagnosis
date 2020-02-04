package com.wang.flink.transformation;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamContextEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author 王一宁
 * @date 2020/1/14 21:18
 */
public class Map01 {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Integer> nums =  env.fromElements(1,2,3,4,5);
        //实现一：map 方法做映射，输入输出一直
        SingleOutputStreamOperator<Integer> result1 = nums.map(new MapFunction<Integer, Integer>() {
            @Override
            public Integer map(Integer value) throws Exception {
                return value * 2;
            }
        });

        //实现二：lambada 表达式的应用
        SingleOutputStreamOperator<Integer> result2 = nums.map(i -> i * 2);

        //sink
        result1.print();
        result2.print();

        env.execute();

    }
}
