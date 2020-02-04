package com.wang.flink.transformation;

import org.apache.flink.api.common.functions.ReduceFunction;
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
public class Reduce01 {
    public static void main(String[] args) throws Exception{

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> lines = env.socketTextStream("192.168.52.200",8888);

        //使用lambada表达式 代替new Function
        //输入一个返回一个
        SingleOutputStreamOperator<Tuple2<String, Integer>> wordAndOne = lines.map(w -> Tuple2.of(w, 1))
                .returns(Types.TUPLE(Types.STRING,Types.INT));

        //元组也是一个特殊的集合，角标 0 开始 最大Tuple25
        KeyedStream<Tuple2<String, Integer>, Tuple> keyed = wordAndOne.keyBy(0);

        //聚合
        SingleOutputStreamOperator<Tuple2<String, Integer>> reduced = keyed.reduce(new ReduceFunction<Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> reduce(Tuple2<String, Integer> value1, Tuple2<String, Integer> value2) throws Exception {

                String key = value1.f0;
                Integer count1 = value1.f1;
                Integer count2 = value2.f1;

                Integer counts = count1 + count2;

                return Tuple2.of(key, counts);
            }
        });



        reduced.print();

        env.execute();
    }
}
