package com.wang.turbine.sink

import java.sql.{Connection, DriverManager, PreparedStatement}


import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}
import org.apache.flink.streaming.api.scala._

object JdbcSink {
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

    //sink
    //dataStream.addSink(new MyJdbcSink())

    env.execute("jdbcSink")

  }




//class MyJdbcSink() extends RichSinkFunction[SensorReading]{
//  //初始化连接，定义预编译器
//  var conn: Connection = _
//  var insertStmt: PreparedStatement = _
//  var updateStmt: PreparedStatement = _
//
//  override def open(parameters: Configuration): Unit = {
//    super.open(parameters)
//    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bike","root","123456")
//    insertStmt = conn.prepareStatement("insert into temperatures(sensor,temp) values (?,?)")
//    updateStmt = conn.prepareStatement("update temperatures set temp = ? where sensor = ?")
//
//  }
//
//  //执行sql
//  override def invoke(value: SensorReading, context: SinkFunction.Context[_]): Unit = {
//    //执行更新语句
//    updateStmt.setDouble(1,value.temperature)
//    updateStmt.setString(2,value.id)
//    updateStmt.execute()
//
//    //没有查询到数据 执行插入数据
//    if (updateStmt.getUpdateCount == 0){
//      insertStmt.setString(1,value.id)
//      insertStmt.setDouble(2,value.temperature)
//      insertStmt.execute()
//    }
//  }

  //关闭连接
//  override def close(): Unit = {
//    insertStmt.close()
//    updateStmt.close()
//    conn.close()
//  }
}