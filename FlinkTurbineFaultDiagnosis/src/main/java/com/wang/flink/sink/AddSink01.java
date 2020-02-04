package com.wang.flink.sink;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

/**
 * @author 王一宁
 * @date 2020/1/15 12:43
 */
public class AddSink01 {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> lines = env.socketTextStream("192.168.52.200",8888);

        SingleOutputStreamOperator<Tuple2<String, Integer>> wordAndOne = lines.map(new MapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(String line) throws Exception {
                String[] fields = line.split(",");
                String words = fields[0];
                int num = Integer.parseInt(fields[1]);

                return Tuple2.of(words, num);
            }
        });

        //按照单词进行分组
        KeyedStream<Tuple2<String, Integer>, Tuple> keyed = wordAndOne.keyBy(0);

        SingleOutputStreamOperator<Tuple2<String, Integer>> max = keyed.max(1);


        /**
         * 自定义sink,比如 写入数据库，磁盘等等
         * 不需要有返回就可以
         */
        max.addSink(new SinkFunction<Tuple2<String, Integer>>() {
            @Override
            public void invoke(Tuple2<String, Integer> value, Context context) throws Exception {

                System.out.println(value);

            }
        });

        /**
         * 写入磁盘
         *  如果是写入scv文件 必须时tuple格式
         */
        max.writeAsCsv("F:\\out222", FileSystem.WriteMode.OVERWRITE);



        max.print();

        env.execute();

    }
}
