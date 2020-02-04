package com.wang.flink.cleandata;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author 王一宁
 * @date 2020/1/14 19:54
 * 清洗风力发电机中的数据tuple例子
 * 建议自定义实例，tuple最多支持25个字段
 */
public class TurbineCleanDataTuple {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> dataStreamSource = env.readTextFile("D:\\APP\\IDEA\\workplace\\FlinkTurbineFaultDiagnosis\\src\\main\\resources\\turbine\\GW20000120160101.txt");

        SingleOutputStreamOperator<Tuple4<String, String, String, String>> map = dataStreamSource.map(new MapFunction<String, Tuple4<String, String, String, String>>() {
            @Override
            public Tuple4<String, String, String, String> map(String s) throws Exception {
                String[] splits = s.split("\t");
                String field1= splits[0];
                String field2 = splits[1];
                String field3= splits[2];
                String field4= splits[3];
                return new Tuple4<>(field1, field2, field3, field4);
            }
        });

        map.setParallelism(1).print();

        env.execute("Test");

    }
}
