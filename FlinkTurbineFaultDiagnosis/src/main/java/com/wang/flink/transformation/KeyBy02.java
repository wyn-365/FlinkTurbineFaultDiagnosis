package com.wang.flink.transformation;

import com.wang.flink.model.Turbine;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author 王一宁
 * @date 2020/1/15 10:00
 */
public class KeyBy02 {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> lines = env.socketTextStream("192.168.52.200",8888);

        //输入一个返回一个
        SingleOutputStreamOperator<Turbine> wordAndOne = lines.map(new MapFunction<String, Turbine>() {
            @Override
            public Turbine map(String value) throws Exception {
                return null;//Turbine.of(value,1L);
            }
        });

        //根据实体类的字段进行聚合
        KeyedStream<Turbine, Tuple> keyed = wordAndOne.keyBy("word");

        //聚合
        SingleOutputStreamOperator<Turbine> sumed = keyed.sum("counts");

        keyed.print();
        sumed.print();


        env.execute();
    }
}
