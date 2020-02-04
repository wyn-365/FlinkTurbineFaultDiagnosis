package com.wang.flink.sink;

import com.wang.flink.model.Turbine;
import com.wang.flink.utils.FlinkUtils;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;

import java.io.IOException;

/**
 * @author 王一宁
 * @date 2020/1/16 21:31
 *  * 从Kafka中消费数据，然后写入到redis中 实现exactly_once
 *  * 传值： --topics wang --group.id abc 或者路径
 *  * D:\APP\IDEA\workplace\FlinkTurbineFaultDiagnosis\src\main\resources\config.properties
 */
public class KafkaToRedis {
    public static void main(String[] args) throws Exception {
        ParameterTool parameters = ParameterTool.fromPropertiesFile("D:\\APP\\IDEA\\workplace\\FlinkTurbineFaultDiagnosis\\src\\main\\resources\\config.properties");
        DataStream<String> lines =  FlinkUtils.createKafkaStream(parameters,SimpleStringSchema.class);
        lines.print();

        //输入的时String  返回一个对象
        SingleOutputStreamOperator<Turbine> map = lines.map(new MapFunction<String, Turbine>() {
            @Override
            public Turbine map(String value) throws Exception {
                String[] fields = value.split(" ");
                String word = fields[0];
                String province = fields[1];
                long counts = Long.parseLong(fields[2]);
                return Turbine.of(word, province, counts);
            }
        });


        map.addSink(new MyRedisSink());


        //执行程序
        FlinkUtils.getEnv().execute();
    }
}
