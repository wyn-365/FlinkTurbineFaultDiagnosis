package com.wang.flink.cleandata;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;

/**
 * 把collection作为数据源
 * @author 王一宁
 * @date 2020/1/2 12:12
 */
public class StreamingFromCollection {
    public static void main(String[] args) throws Exception{
        //获取环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        ArrayList<Integer> data = new ArrayList<>();
        data.add(10);
        data.add(20);
        data.add(40);
        data.add(10);
        //指定数据源
        DataStreamSource<Integer> collectionData = env.fromCollection(data);
        //处理数据
        SingleOutputStreamOperator<Integer> num = collectionData.map(new MapFunction<Integer, Integer>() {
            @Override
            public Integer map(Integer value) throws Exception {
                return value + 1;
            }
        });

        //输出打印
        num.print();

        //执行
        env.execute("StreamingFormCollection");
    }
}
