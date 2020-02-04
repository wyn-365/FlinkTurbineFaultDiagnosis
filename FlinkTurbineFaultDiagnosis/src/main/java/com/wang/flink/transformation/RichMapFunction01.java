package com.wang.flink.transformation;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author 王一宁
 * @date 2020/1/14 21:32
 */
public class RichMapFunction01 {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<Integer> nums =  env.fromElements(1,2,3,4,5);

        nums.map(new RichMapFunction<Integer,Integer>(){

            //初始化连接的方法 数据库等等 map方法之前【执行一次】 还可以拿到全局的配置
            @Override
            public void open(Configuration parameters) throws Exception {
                super.open(parameters);
            }

            //销毁之前，释放资源
            @Override
            public void close() throws Exception {
                super.close();
            }

            @Override
            public Integer map(Integer value) throws Exception {
                return null;
            }
        });
    }
}
