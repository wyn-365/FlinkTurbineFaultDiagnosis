package com.wang.flink.sink;

import com.wang.flink.model.Turbine;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;

/**
 * @author 王一宁
 * @date 2020/1/16 9:40
 */
public class RedisSink implements RedisMapper<Turbine> {

    //创建redis连接
    FlinkJedisPoolConfig conf = new FlinkJedisPoolConfig.Builder().setHost("127.0.0.1").build();

    /**
     * 如何调用
     * @return
     *  summed.addSink(new RedisSink<Turbine>(conf,new RedisSink()));
     *
     */


    //写入方法set 多个键匹配一个vlaue
    @Override
    public RedisCommandDescription getCommandDescription() {
        return new RedisCommandDescription(RedisCommand.HSET,"Turbine_Count");
    }

    //联合的key 风机编号+ 风机类型+ 风机经纬度
    @Override
    public String getKeyFromData(Turbine turbine) {
        return turbine.word + "_" +turbine.province +"_" + turbine.longitude;
    }

    //value String
    @Override
    public String getValueFromData(Turbine turbine) {
        return String.valueOf(turbine.counts);
    }
}
