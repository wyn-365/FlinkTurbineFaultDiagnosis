package com.wang.turbine.sink

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.redis.RedisSink
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig
import org.apache.flink.streaming.connectors.redis.common.mapper.{RedisCommand, RedisCommandDescription, RedisMapper}

object RedisSink {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //env.setMaxParallelism(1)

    //1.读取文件
    val inputStream = env.readTextFile("D:\\APP\\IDEA\\workplace\\HelloFlink\\src\\main\\resources\\sensor.txt")
    //2.转换操作
    val dataStream = inputStream.map(data=>{
      val dataArray = data.split(",")//trim去掉空格 有的话
      //SensorReading(dataArray(0).trim,dataArray(1).trim.toLong,dataArray(2).trim.toDouble)
    })

    //3.创建jedis连接
    val conf = new FlinkJedisPoolConfig.Builder()
        .setHost("localhost")
        .setPort(6379)
        .build()

    //3.写入redis
    //dataStream.addSink(new RedisSink(conf,new MyRedisMapper()))

    env.execute("redissink")

  }
}


//class MyRedisMapper() extends RedisMapper[SensorReading]{
//  //保存数据到redis的命令
//  override def getCommandDescription: RedisCommandDescription = {
//    //保存成把传感器id，温度值 hash表
//    new RedisCommandDescription(RedisCommand.HSET,"sensor_temperature")
//  }
//
//  //定义保存到redis的key
//  override def getKeyFromData(t: SensorReading): String = t.id
//
//  //定义保存到redis的value
//  override def getValueFromData(t: SensorReading): String = t.temperature.toString
//}