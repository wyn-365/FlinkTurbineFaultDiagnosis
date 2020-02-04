package com.wang.flink.sink;

import com.wang.flink.model.Turbine;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import redis.clients.jedis.Jedis;

/**
 * @author 王一宁
 * @date 2020/1/17 10:19
 */
public class MyRedisSink extends RichSinkFunction<Turbine> {
    //初始化redis连接
    private transient Jedis jedis;
    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        ParameterTool params = (ParameterTool)getRuntimeContext().getExecutionConfig().getGlobalJobParameters();
        String host = params.getRequired("redis.host");
        //String password = params.getRequired("redis.pwd");
        int db = params.getInt("redis.db",0);
        jedis = new Jedis(host, 6379, 5000);
        //jedis.auth(password);
        jedis.select(db);
    }

    @Override
    public void invoke(Turbine value, Context context) throws Exception {
        if(!jedis.isConnected()){
            jedis.connect();
        }
        //写入redis
        jedis.hset(value.word,value.province, String.valueOf(value.counts));
    }

    @Override
    public void close() throws Exception {
        super.close();
        jedis.close();
    }
}
