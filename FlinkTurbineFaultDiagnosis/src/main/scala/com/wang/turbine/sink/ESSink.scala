package com.wang.turbine.sink

import java.util


import org.apache.flink.api.common.functions.RuntimeContext
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.elasticsearch.{ElasticsearchSinkFunction, RequestIndexer}
import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink
import org.apache.http.HttpHost
import org.elasticsearch.client.Requests

object ESSink {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setMaxParallelism(1)

    //1.读取文件
    val inputStream = env.readTextFile("D:\\APP\\IDEA\\workplace\\HelloFlink\\src\\main\\resources\\sensor.txt")
    //2.转换操作
    val dataStream = inputStream.map(data=>{
      val dataArray = data.split(",")//trim去掉空格 有的话
      //SensorReading(dataArray(0).trim,dataArray(1).trim.toLong,dataArray(2).trim.toDouble)
    })

    //3.写入es
//    val httpHosts = new util.ArrayList[HttpHost]()
//    httpHosts.add(new HttpHost("hadoop1",9200))
//
//    val esSinkBuiilder = ElasticsearchSink.Builder[SensorReading](
//      httpHosts,
//      new ElasticsearchSinkFunction[SensorReading] {
//        override def process(element: SensorReading, ctx: RuntimeContext, indexer: RequestIndexer): Unit = {
//          println("saving data:"+element)
//
//          //包装秤一个map或者jsonObject
//          val json = new util.HashMap[String,String]()
//          json.put("sensor_id",element.id)
//          json.put("temperature",element.temperature.toString)
//          json.put("ts",element.timestamp.toString)
//
//          //request准备发送数据
//          val indexRequest = Requests.indexRequest()
//            .index("sensor")
//            .`type`("readingdata")
//            .source(json)
//
//          //利用index发送请求，写入数据
//          indexer.add(indexRequest)
//          println("data saved successfully")
//
//
//        }
//      }
//    )

    //4.Sink
    //dataStream.addSink(esSinkBuiilder.build())

    env.execute("es")
  }

}
