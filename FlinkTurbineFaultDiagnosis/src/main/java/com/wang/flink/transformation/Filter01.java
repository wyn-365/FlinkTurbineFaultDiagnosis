package com.wang.flink.transformation;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author 王一宁
 * @date 2020/1/15 9:48
 */
public class Filter01 {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<Integer> nums = env.fromElements(1,2,3,4,5,6,7,8,9);

        //实现一：ture是留下，false时过滤
        SingleOutputStreamOperator<Integer> filter1 = nums.filter(new FilterFunction<Integer>() {
            @Override
            public boolean filter(Integer value) throws Exception {
                return value % 2 != 0;
            }
        });

        //实现二：lambada表达式：filter2
        //SingleOutputStreamOperator<Integer> filter2 = nums.filter(i -> i >= 5);
        SingleOutputStreamOperator<Integer> filter2 = nums.filter(i -> {
            //换行要有return
            return i >= 5;
        });


        filter1.print();
        filter2.print();

        env.execute();
    }
}
