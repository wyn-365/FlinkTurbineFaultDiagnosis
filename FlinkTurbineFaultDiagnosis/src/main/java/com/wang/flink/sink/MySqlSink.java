package com.wang.flink.sink;

import com.wang.flink.model.GW200001;
import com.wang.flink.model.Turbine;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author 王一宁
 * @date 2020/1/15 22:27
 *
 create table gw200001(
    id int(11) primary key,
    wt_number varchar(20),
    wt_date_time varchar(20),
    wt_wind_speed float,
    wt_wind_dir float,
    wt_temp float,
    wt_power float,
    wt_consume_power float,
    wt_electric float,
    wt_rotor1_local float,
    wt_rotor2_local float,
    wt_rotor3_local float,
    wt_rotor1_speed float,
    wt_rotor2_speed float,
    wt_rotor3_speed float,
    wt_bianJiang1_temp float,
    wt_bianJiang2_temp float,
    wt_bianJiang3_temp float,
    wt_bianJiangGui1_temp float,
    wt_bianJiangGui2_temp float,
    wt_bianJiangGui3_temp float,
    wt_motor_speed float,
    wt_real_power float,
    wt_pianHang_rank float,
    wt_pianHang_allRank float,
    wt_pianHang_local float,
    wt_control_temp float,
    wt_bianLiuQi_kw float,
    wt_bianLiuQi_speed float,
    wt_bianLiuQi_v float,
    wt_bianLiuQi_out_temp float,
    wt_bianLiuQi_in_temp float,
    wt_bianLiuQi_out_pressure float,
    wt_jiCang_temp float,
    wt_jiCangBianPin_temp float
);
 */
public class MySqlSink extends RichSinkFunction<GW200001> {

    //最好连接不参与序列化
    private transient Connection conn = null;

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        //创建mysql连接
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turbine?characterEncoding=UTF-8","root","123456");
        System.out.println("拿到连接了");
    }

    @Override
    public void invoke(GW200001 gw200001, Context context) throws Exception {
        //更新，插入，统计业务
        PreparedStatement pstm = null;
        try{
            pstm = conn.prepareStatement("insert into gw200001(wt_number, wt_date_time, wt_wind_speed, wt_wind_dir, wt_temp, wt_power, wt_consume_power, wt_electric, wt_rotor1_local, wt_rotor2_local, wt_rotor3_local, wt_rotor1_speed, wt_rotor2_speed,wt_rotor3_speed,wt_bianJiang1_temp,wt_bianJiang2_temp,wt_bianJiang3_temp,wt_bianJiangGui1_temp,wt_bianJiangGui2_temp,wt_bianJiangGui3_temp,wt_motor_speed,wt_real_power,wt_pianHang_rank,wt_pianHang_allRank,wt_pianHang_local,wt_control_temp,wt_bianLiuQi_kw,wt_bianLiuQi_speed,wt_bianLiuQi_v   , wt_bianLiuQi_out_temp,wt_bianLiuQi_in_temp,wt_bianLiuQi_out_pressure,wt_jiCang_temp,wt_jiCangBianPin_temp) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstm.setString(1,gw200001.wt_number);
            pstm.setString(2,gw200001.wt_date_time);
            pstm.setFloat(3,gw200001.wt_wind_speed);
            pstm.setFloat(4,gw200001.wt_wind_dir);
            pstm.setFloat(5,gw200001.wt_temp);
            pstm.setFloat(6,gw200001.wt_power);
            pstm.setFloat(7,gw200001.wt_consume_power);
            pstm.setFloat(8,gw200001.wt_electric);
            pstm.setFloat(9,gw200001.wt_rotor1_local);
            pstm.setFloat(10,gw200001.wt_rotor2_local);
            pstm.setFloat(11,gw200001.wt_rotor3_local);
            pstm.setFloat(12,gw200001.wt_rotor1_speed);
            pstm.setFloat(13,gw200001.wt_rotor2_speed);
            pstm.setFloat(14,gw200001.wt_rotor3_speed);
            pstm.setFloat(15,gw200001.wt_bianJiang1_temp);
            pstm.setFloat(16,gw200001.wt_bianJiang2_temp);
            pstm.setFloat(17,gw200001.wt_bianJiang3_temp);
            pstm.setFloat(18,gw200001.wt_bianJiangGui1_temp);
            pstm.setFloat(19,gw200001.wt_bianJiangGui2_temp);
            pstm.setFloat(20,gw200001.wt_bianJiangGui3_temp);
            pstm.setFloat(21,gw200001.wt_motor_speed);
            pstm.setFloat(22,gw200001.wt_real_power);
            pstm.setFloat(23,gw200001.wt_pianHang_rank);
            pstm.setFloat(24,gw200001.wt_pianHang_allRank);
            pstm.setFloat(25,gw200001.wt_pianHang_local);
            pstm.setFloat(26,gw200001.wt_control_temp);
            pstm.setFloat(27,gw200001.wt_bianLiuQi_kw);
            pstm.setFloat(28,gw200001.wt_bianLiuQi_speed);
            pstm.setFloat(29,gw200001.wt_bianLiuQi_v);
            pstm.setFloat(30,gw200001.wt_bianLiuQi_out_temp);
            pstm.setFloat(31,gw200001.wt_bianLiuQi_in_temp);
            pstm.setFloat(32,gw200001.wt_bianLiuQi_out_pressure);
            pstm.setFloat(33,gw200001.wt_jiCang_temp);
            pstm.setFloat(34,gw200001.wt_jiCangBianPin_temp);

            //执行sql  executeUpdate() executeQuery()
            System.out.println("执行sql");
            pstm.execute();
        }finally {
            if(pstm != null){
                pstm.close();
                System.out.println("正常关闭");
            }
        }
    }

    @Override
    public void close() throws Exception {
        super.close();
        conn.close();
        System.out.println("正常关闭了！");
    }
}
