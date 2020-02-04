package com.wang.turbine.cleandata

import org.apache.flink.api.scala.ExecutionEnvironment

/**
 * //取出发电机的时间
 * //发电机各项参数：故障时间、风速、发电机转速、变流器无功功率、控制柜温度、机舱变频柜温度
 * //环境温度、机舱温度、变桨电机123温度、变桨柜123温度、叶片123速度
 * //网侧有功功率、变流器无功功率、理论有功功率
 */
object BatchWordCount {
  def main(args: Array[String]): Unit = {

    val inputPath = "D:\\APP\\IDEA\\workplace\\FlinkTurbineFaultDiagnosis\\src\\main\\resources\\turbine"
    val outPath = "D:\\APP\\IDEA\\workplace\\FlinkTurbineFaultDiagnosis\\src\\log\\result"

    //获取执行环境
    val env = ExecutionEnvironment.getExecutionEnvironment

    val text = env.readTextFile(inputPath)

    //隐士转换
    import org.apache.flink.api.scala._

    val filtered = text.map(line =>{
      val fields = line.split("\t")
      val gw = fields(0)
      val status = fields(2) //数据的可用状态
      val date01 =  fields(1) //发电机的实时时间
      val test001 = fields(8)
      (gw,status,date01,test001)

    })

    filtered.print()

    env.execute()

  }
}
