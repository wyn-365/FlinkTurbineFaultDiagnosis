package com.wang.flink.cleandata;

import com.wang.flink.model.GW200001;
import com.wang.flink.sink.MySqlSink;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author 王一宁
 * @date 2020/1/14 19:54
 * 清洗风力发电机的数据
 */
public class TurbineCleanData {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> dataStreamSource = env.readTextFile("D:\\APP\\IDEA\\workplace\\FlinkTurbineFaultDiagnosis\\src\\main\\resources\\turbine\\GW20000120160101.txt");

        SingleOutputStreamOperator<GW200001> map = dataStreamSource.map(new MapFunction<String, GW200001>() {
            @Override
            public GW200001 map(String s) throws Exception {
                String[] splits = s.split("\t");
                String wt_number = splits[0];//风机编号
                String wt_date_time = splits[1];//触发时间
                float wt_wind_speed = Float.parseFloat(splits[69]);//风速
                float wt_wind_dir = Float.parseFloat(splits[80]);//风向
                float wt_temp = Float.parseFloat(splits[111]);//环境温度
                float wt_power = Float.parseFloat(splits[52]);//总发电量
                float wt_consume_power = Float.parseFloat(splits[53]);//消耗电量
                float wt_electric = Float.parseFloat(splits[54]);//通电时间
                float wt_rotor1_local = Float.parseFloat(splits[137]);//1#叶片冗余旋碥位置
                float wt_rotor2_local = Float.parseFloat(splits[138]);//2#叶片冗余旋碥位置
                float wt_rotor3_local = Float.parseFloat(splits[139]);//3#叶片冗余旋碥位置
                float wt_rotor1_speed = Float.parseFloat(splits[140]);//叶片1速度
                float wt_rotor2_speed = Float.parseFloat(splits[141]);//叶片2速度
                float wt_rotor3_speed = Float.parseFloat(splits[142]);//叶片3速度
                float wt_bianJiang1_temp = Float.parseFloat(splits[113]);//变桨电机1温度
                float wt_bianJiang2_temp = Float.parseFloat(splits[114]);//变桨电机2温度
                float wt_bianJiang3_temp = Float.parseFloat(splits[115]);//变桨电机3温度
                float wt_bianJiangGui1_temp = Float.parseFloat(splits[119]);//变桨柜1温度
                float wt_bianJiangGui2_temp = Float.parseFloat(splits[120]);//变桨柜2温度
                float wt_bianJiangGui3_temp = Float.parseFloat(splits[121]);//变桨柜3温度
                float wt_motor_speed = Float.parseFloat(splits[84]);//发电机转速
                float wt_real_power = Float.parseFloat(splits[105]);//网侧有功功率(kw)
                float wt_pianHang_rank = Float.parseFloat(splits[38]);//故障偏航等级
                float wt_pianHang_allRank = Float.parseFloat(splits[46]);//全局偏航等级
                float wt_pianHang_local = Float.parseFloat(splits[72]);//偏航位置
                float wt_control_temp = Float.parseFloat(splits[109]);//控制柜温度
                float wt_bianLiuQi_kw = Float.parseFloat(splits[107]);//变流器无功功率
                float wt_bianLiuQi_speed = Float.parseFloat(splits[158]);//变流器电机转速值
                float wt_bianLiuQi_v = Float.parseFloat(splits[161]);//变流器网侧电压
                float wt_bianLiuQi_out_temp = Float.parseFloat(splits[163]);//变流器出水口温度
                float wt_bianLiuQi_in_temp = Float.parseFloat(splits[164]);//变流器进水口温度
                float wt_bianLiuQi_out_pressure = Float.parseFloat(splits[165]);//变流器出水口压力
                float wt_jiCang_temp = Float.parseFloat(splits[112]);//机舱温度
                float wt_jiCangBianPin_temp = Float.parseFloat(splits[110]);//机舱变频柜温度

                return new GW200001(
                        wt_number,
                        wt_date_time,
                        wt_wind_speed,
                        wt_wind_dir,
                        wt_temp,
                        wt_power,
                        wt_consume_power,
                        wt_electric,
                        wt_rotor1_local,
                        wt_rotor2_local,
                        wt_rotor3_local,
                        wt_rotor1_speed,
                        wt_rotor2_speed,
                        wt_rotor3_speed,wt_bianJiang1_temp,
                        wt_bianJiang2_temp,
                        wt_bianJiang3_temp,
                        wt_bianJiangGui1_temp,
                        wt_bianJiangGui2_temp,
                        wt_bianJiangGui3_temp,wt_motor_speed,
                        wt_real_power,wt_pianHang_rank,
                        wt_pianHang_allRank,
                        wt_pianHang_local,wt_control_temp,
                        wt_bianLiuQi_kw,
                        wt_bianLiuQi_speed,
                        wt_bianLiuQi_v,
                        wt_bianLiuQi_out_temp,
                        wt_bianLiuQi_in_temp,
                        wt_bianLiuQi_out_pressure,
                        wt_jiCang_temp,
                        wt_jiCangBianPin_temp);
            }
        });

        //map.writeAsText("f:\\out222");
        //map.setParallelism(1).print();

        //插入mysql库
        map.addSink(new MySqlSink());

        env.execute("Clear-GW-data");

    }
}
