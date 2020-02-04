package com.wang.flink.transformation;

import com.wang.flink.model.Turbine;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author 王一宁
 * @date 2020/1/15 10:00
 *
 * 按照多个字段进行分组
 */
public class KeyBy03 {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> lines = env.socketTextStream("192.168.52.200",8888);

        //风机编号,宁夏,1000
        //风机编号,北京,1000
        //风机编号,北京,1000
        SingleOutputStreamOperator<Tuple3<String, String, Double>> idCityMoney = lines.map(new MapFunction<String, Tuple3<String, String, Double>>() {
            @Override
            public Tuple3<String, String, Double> map(String line) throws Exception {
                //切分
                String[] fields = line.split(",");
                String id = fields[0];
                String city = fields[1];
                double money = Double.parseDouble(fields[2]);
                return Tuple3.of(id, city, money);
            }
        });

        //按照风机编号，城市分组  多个字段进行分组,最后一个字段进行聚合   没有返回打印-我，
        /**
         * 如果是自己定义的bean实体类，可以进行将字段写进去
         */
        idCityMoney.keyBy(0,1).sum(2);


        
        env.execute();
    }
}
