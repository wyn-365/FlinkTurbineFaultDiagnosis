package com.wang.turbine.cleandata

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.windowing.time.Time

/**
 * @author 王一宁
 * @date 2020/1/1 19:22
 *       通过socket模拟产生数据
 *       flink负责统计计算
 *       规则：实现每隔一秒对最近两秒内的数据进行计算【滑动窗口计算】
 */
object SocketWordCount {
  def main(args: Array[String]): Unit = {

    //获取端口号
    val port: Int=try {
      ParameterTool.fromArgs(args).getInt("port")
    }catch {
      case e: Exception =>{
        System.err.println("no port set, default 8888")
      }
        8888
    }

    //1.配置环境
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    //2.获取数据源
    val text = env.socketTextStream("hadoop1",port,'\n')

    //隐士转换
    import org.apache.flink.api.scala._
    //3.解析数据打平 分许 窗口计算
    val windowCounts = text.flatMap(line => line.split("\\s"))
      .map(w => WordCount(w, 1))
      .keyBy("word")
      .timeWindow(Time.seconds(2), Time.seconds(1))
      .sum("count")
    //.reduce((a,b)=>WordCount(a.word,a.count+b.count))

    windowCounts.print().setParallelism(1)//单线程
    env.execute()


    
  }


  //默认的gettter setter
  case class WordCount(word: String,count: Long)
}
