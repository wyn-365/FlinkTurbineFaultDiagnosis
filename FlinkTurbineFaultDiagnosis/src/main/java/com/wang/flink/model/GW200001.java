package com.wang.flink.model;

/**
 * @author 王一宁
 * @date 2020/1/23 12:58
 * 共计34个字段
 */
public class GW200001 {
    public int id;
    public String wt_number;        //风机编号
    public String wt_date_time;     //触发时间

    public float wt_wind_speed;     //风速
    public float wt_wind_dir;       //风向
    public float wt_temp;           //环境温度

    public float wt_power;          //总发电量
    public float wt_consume_power;  //消耗电量
    public float wt_electric;       //通电时间


    /**
     * 风轮系统
     */
    public float wt_rotor1_local;   //1#叶片冗余旋碥位置
    public float wt_rotor2_local;   //2#叶片冗余旋碥位置
    public float wt_rotor3_local;   //3#叶片冗余旋碥位置
    public float wt_rotor1_speed;   //叶片1速度
    public float wt_rotor2_speed;   //叶片2速度
    public float wt_rotor3_speed;   //叶片3速度

    /**
     * 变桨系统
     */
    public float wt_bianJiang1_temp;    //变桨电机1温度
    public float wt_bianJiang2_temp;    //变桨电机2温度
    public float wt_bianJiang3_temp;    //变桨电机3温度
    public float wt_bianJiangGui1_temp; //变桨柜1温度
    public float wt_bianJiangGui2_temp; //变桨柜2温度
    public float wt_bianJiangGui3_temp; //变桨柜3温度

    /**
     * 传动链系统
     */
    public float wt_motor_speed;        //发电机转速
    public float wt_real_power;         //网侧有功功率(kw)

    /**
     * 偏航系统
     */
    public float wt_pianHang_rank;      //故障偏航等级
    public float wt_pianHang_allRank;   //全局偏航等级
    public float wt_pianHang_local;     //偏航位置

    /**
     * 电气系统
     */
    public float wt_control_temp;               //控制柜温度
    public float wt_bianLiuQi_kw;               //变流器无功功率
    public float wt_bianLiuQi_speed;            //变流器电机转速值
    public float wt_bianLiuQi_v;                //变流器网侧电压
    public float wt_bianLiuQi_out_temp;         //变流器出水口温度
    public float wt_bianLiuQi_in_temp;          //变流器进水口温度
    public float wt_bianLiuQi_out_pressure;     //变流器出水口压力
    public float wt_jiCang_temp;                //机舱温度
    public float wt_jiCangBianPin_temp;         //机舱变频柜温度

    public GW200001(String wt_number, String wt_date_time, float wt_wind_speed, float wt_wind_dir, float wt_temp, float wt_power, float wt_consume_power, float wt_electric, float wt_rotor1_local, float wt_rotor2_local, float wt_rotor3_local, float wt_rotor1_speed, float wt_rotor2_speed, float wt_rotor3_speed, float wt_bianJiang1_temp, float wt_bianJiang2_temp, float wt_bianJiang3_temp, float wt_bianJiangGui1_temp, float wt_bianJiangGui2_temp, float wt_bianJiangGui3_temp, float wt_motor_speed, float wt_real_power, float wt_pianHang_rank, float wt_pianHang_allRank, float wt_pianHang_local, float wt_control_temp, float wt_bianLiuQi_kw, float wt_bianLiuQi_speed, float wt_bianLiuQi_v, float wt_bianLiuQi_out_temp, float wt_bianLiuQi_in_temp, float wt_bianLiuQi_out_pressure, float wt_jiCang_temp, float wt_jiCangBianPin_temp) {
        this.wt_number = wt_number;
        this.wt_date_time = wt_date_time;
        this.wt_wind_speed = wt_wind_speed;
        this.wt_wind_dir = wt_wind_dir;
        this.wt_temp = wt_temp;
        this.wt_power = wt_power;
        this.wt_consume_power = wt_consume_power;
        this.wt_electric = wt_electric;
        this.wt_rotor1_local = wt_rotor1_local;
        this.wt_rotor2_local = wt_rotor2_local;
        this.wt_rotor3_local = wt_rotor3_local;
        this.wt_rotor1_speed = wt_rotor1_speed;
        this.wt_rotor2_speed = wt_rotor2_speed;
        this.wt_rotor3_speed = wt_rotor3_speed;
        this.wt_bianJiang1_temp = wt_bianJiang1_temp;
        this.wt_bianJiang2_temp = wt_bianJiang2_temp;
        this.wt_bianJiang3_temp = wt_bianJiang3_temp;
        this.wt_bianJiangGui1_temp = wt_bianJiangGui1_temp;
        this.wt_bianJiangGui2_temp = wt_bianJiangGui2_temp;
        this.wt_bianJiangGui3_temp = wt_bianJiangGui3_temp;
        this.wt_motor_speed = wt_motor_speed;
        this.wt_real_power = wt_real_power;
        this.wt_pianHang_rank = wt_pianHang_rank;
        this.wt_pianHang_allRank = wt_pianHang_allRank;
        this.wt_pianHang_local = wt_pianHang_local;
        this.wt_control_temp = wt_control_temp;
        this.wt_bianLiuQi_kw = wt_bianLiuQi_kw;
        this.wt_bianLiuQi_speed = wt_bianLiuQi_speed;
        this.wt_bianLiuQi_v = wt_bianLiuQi_v;
        this.wt_bianLiuQi_out_temp = wt_bianLiuQi_out_temp;
        this.wt_bianLiuQi_in_temp = wt_bianLiuQi_in_temp;
        this.wt_bianLiuQi_out_pressure = wt_bianLiuQi_out_pressure;
        this.wt_jiCang_temp = wt_jiCang_temp;
        this.wt_jiCangBianPin_temp = wt_jiCangBianPin_temp;

    }


    public static GW200001 of(int id, String wt_number, String wt_date_time,
                              float wt_wind_speed, float wt_wind_dir, float wt_temp,
                              float wt_power, float wt_consume_power, float wt_electric,
                              float wt_rotor1_local, float wt_rotor2_local,
                              float wt_rotor3_local, float wt_rotor1_speed,
                              float wt_rotor2_speed, float wt_rotor3_speed,
                              float wt_bianJiang1_temp, float wt_bianJiang2_temp,
                              float wt_bianJiang3_temp, float wt_bianJiangGui1_temp,
                              float wt_bianJiangGui2_temp, float wt_bianJiangGui3_temp,
                              float wt_motor_speed, float wt_real_power,
                              float wt_pianHang_rank, float wt_pianHang_allRank,
                              float wt_pianHang_local, float wt_control_temp,
                              float wt_bianLiuQi_kw, float wt_bianLiuQi_speed,
                              float wt_bianLiuQi_v, float wt_bianLiuQi_out_temp,
                              float wt_bianLiuQi_in_temp, float wt_bianLiuQi_out_pressure,
                              float wt_jiCang_temp, float wt_jiCangBianPin_temp) {
        return new GW200001(id,  wt_number,  wt_date_time,
                wt_wind_speed,  wt_wind_dir,  wt_temp,
                wt_power,  wt_consume_power,  wt_electric,
                wt_rotor1_local,  wt_rotor2_local,
                wt_rotor3_local,  wt_rotor1_speed,
                wt_rotor2_speed,  wt_rotor3_speed,
                wt_bianJiang1_temp,  wt_bianJiang2_temp,
                wt_bianJiang3_temp,  wt_bianJiangGui1_temp,
                wt_bianJiangGui2_temp,  wt_bianJiangGui3_temp,
                wt_motor_speed,  wt_real_power,
                wt_pianHang_rank,  wt_pianHang_allRank,
                wt_pianHang_local,  wt_control_temp,
                wt_bianLiuQi_kw,  wt_bianLiuQi_speed,
                wt_bianLiuQi_v,  wt_bianLiuQi_out_temp,
                wt_bianLiuQi_in_temp,  wt_bianLiuQi_out_pressure,
                wt_jiCang_temp,  wt_jiCangBianPin_temp);
    }



    public GW200001() {
    }

    public GW200001(int id, String wt_number, String wt_date_time,
                    float wt_wind_speed, float wt_wind_dir, float wt_temp,
                    float wt_power, float wt_consume_power, float wt_electric,
                    float wt_rotor1_local, float wt_rotor2_local,
                    float wt_rotor3_local, float wt_rotor1_speed,
                    float wt_rotor2_speed, float wt_rotor3_speed,
                    float wt_bianJiang1_temp, float wt_bianJiang2_temp,
                    float wt_bianJiang3_temp, float wt_bianJiangGui1_temp,
                    float wt_bianJiangGui2_temp, float wt_bianJiangGui3_temp,
                    float wt_motor_speed, float wt_real_power,
                    float wt_pianHang_rank, float wt_pianHang_allRank,
                    float wt_pianHang_local, float wt_control_temp,
                    float wt_bianLiuQi_kw, float wt_bianLiuQi_speed,
                    float wt_bianLiuQi_v, float wt_bianLiuQi_out_temp,
                    float wt_bianLiuQi_in_temp, float wt_bianLiuQi_out_pressure,
                    float wt_jiCang_temp, float wt_jiCangBianPin_temp) {
        this.id = id;
        this.wt_number = wt_number;
        this.wt_date_time = wt_date_time;
        this.wt_wind_speed = wt_wind_speed;
        this.wt_wind_dir = wt_wind_dir;
        this.wt_temp = wt_temp;
        this.wt_power = wt_power;
        this.wt_consume_power = wt_consume_power;
        this.wt_electric = wt_electric;
        this.wt_rotor1_local = wt_rotor1_local;
        this.wt_rotor2_local = wt_rotor2_local;
        this.wt_rotor3_local = wt_rotor3_local;
        this.wt_rotor1_speed = wt_rotor1_speed;
        this.wt_rotor2_speed = wt_rotor2_speed;
        this.wt_rotor3_speed = wt_rotor3_speed;
        this.wt_bianJiang1_temp = wt_bianJiang1_temp;
        this.wt_bianJiang2_temp = wt_bianJiang2_temp;
        this.wt_bianJiang3_temp = wt_bianJiang3_temp;
        this.wt_bianJiangGui1_temp = wt_bianJiangGui1_temp;
        this.wt_bianJiangGui2_temp = wt_bianJiangGui2_temp;
        this.wt_bianJiangGui3_temp = wt_bianJiangGui3_temp;
        this.wt_motor_speed = wt_motor_speed;
        this.wt_real_power = wt_real_power;
        this.wt_pianHang_rank = wt_pianHang_rank;
        this.wt_pianHang_allRank = wt_pianHang_allRank;
        this.wt_pianHang_local = wt_pianHang_local;
        this.wt_control_temp = wt_control_temp;
        this.wt_bianLiuQi_kw = wt_bianLiuQi_kw;
        this.wt_bianLiuQi_speed = wt_bianLiuQi_speed;
        this.wt_bianLiuQi_v = wt_bianLiuQi_v;
        this.wt_bianLiuQi_out_temp = wt_bianLiuQi_out_temp;
        this.wt_bianLiuQi_in_temp = wt_bianLiuQi_in_temp;
        this.wt_bianLiuQi_out_pressure = wt_bianLiuQi_out_pressure;
        this.wt_jiCang_temp = wt_jiCang_temp;
        this.wt_jiCangBianPin_temp = wt_jiCangBianPin_temp;
    }

    @Override
    public String toString() {
        return "GW200001{" +
                "id=" + id +
                ", wt_number='" + wt_number + '\'' +
                ", wt_date_time='" + wt_date_time + '\'' +
                ", wt_wind_speed='" + wt_wind_speed + '\'' +
                ", wt_wind_dir='" + wt_wind_dir + '\'' +
                ", wt_temp='" + wt_temp + '\'' +
                ", wt_power='" + wt_power + '\'' +
                ", wt_consume_power='" + wt_consume_power + '\'' +
                ", wt_electric='" + wt_electric + '\'' +
                ", wt_rotor1_local='" + wt_rotor1_local + '\'' +
                ", wt_rotor2_local='" + wt_rotor2_local + '\'' +
                ", wt_rotor3_local='" + wt_rotor3_local + '\'' +
                ", wt_rotor1_speed='" + wt_rotor1_speed + '\'' +
                ", wt_rotor2_speed='" + wt_rotor2_speed + '\'' +
                ", wt_rotor3_speed='" + wt_rotor3_speed + '\'' +
                ", wt_bianJiang1_temp='" + wt_bianJiang1_temp + '\'' +
                ", wt_bianJiang2_temp='" + wt_bianJiang2_temp + '\'' +
                ", wt_bianJiang3_temp='" + wt_bianJiang3_temp + '\'' +
                ", wt_bianJiangGui1_temp='" + wt_bianJiangGui1_temp + '\'' +
                ", wt_bianJiangGui2_temp='" + wt_bianJiangGui2_temp + '\'' +
                ", wt_bianJiangGui3_temp='" + wt_bianJiangGui3_temp + '\'' +
                ", wt_motor_speed='" + wt_motor_speed + '\'' +
                ", wt_real_power='" + wt_real_power + '\'' +
                ", wt_pianHang_rank='" + wt_pianHang_rank + '\'' +
                ", wt_pianHang_allRank='" + wt_pianHang_allRank + '\'' +
                ", wt_pianHang_local='" + wt_pianHang_local + '\'' +
                ", wt_control_temp='" + wt_control_temp + '\'' +
                ", wt_bianLiuQi_kw='" + wt_bianLiuQi_kw + '\'' +
                ", wt_bianLiuQi_speed='" + wt_bianLiuQi_speed + '\'' +
                ", wt_bianLiuQi_v='" + wt_bianLiuQi_v + '\'' +
                ", wt_bianLiuQi_out_temp='" + wt_bianLiuQi_out_temp + '\'' +
                ", wt_bianLiuQi_in_temp='" + wt_bianLiuQi_in_temp + '\'' +
                ", wt_bianLiuQi_out_pressure='" + wt_bianLiuQi_out_pressure + '\'' +
                ", wt_jiCang_temp='" + wt_jiCang_temp + '\'' +
                ", wt_jiCangBianPin_temp='" + wt_jiCangBianPin_temp + '\'' +
                '}';
    }
}
