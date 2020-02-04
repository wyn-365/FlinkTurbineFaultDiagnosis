package com.wang.flink.generatedata;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * Created by 王一宁 on 2019/11/6.
 */
public class kafkaProducer {

    public static void main(String[] args) throws Exception{
        Properties prop = new Properties();
        //指定kafka broker地址
        prop.put("bootstrap.servers", "hadoop1:9092");
        //指定key value的序列化方式
        prop.put("key.serializer", StringSerializer.class.getName());
        prop.put("value.serializer", StringSerializer.class.getName());
        //指定topic名称
        String topic = "wang";

        //创建producer链接
        KafkaProducer<String, String> producer = new KafkaProducer<String,String>(prop);
        //创建Java IO
        InputStream file = new FileInputStream("D:\\APP\\IDEA\\workplace\\FlinkTurbineFaultDiagnosis\\src\\main\\resources\\turbine\\GW20000120160101.txt");
        InputStreamReader fileInputStream = new InputStreamReader(file);
        BufferedReader reader = new BufferedReader(fileInputStream);
        String line = null;
        while ((line = reader.readLine()) != null) {
            //生产消息
            producer.send(new ProducerRecord<String, String>(topic,line));
            Thread.sleep(1000);
        }
        reader.close();
        file.close();
        fileInputStream.close();

        //关闭链接
        producer.close();
    }





//    public static String getCurrentTime(){
//        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
//        return sdf.format(new Date());
//    }
//
//    public static String getCountryCode(){
//        String[] types = {"US","TW","HK","PK","KW","SA","IN"};
//        Random random = new Random();
//        int i = random.nextInt(types.length);
//        return types[i];
//    }
//
//
//    public static String getRandomType(){
//        String[] types = {"s1","s2","s3","s4","s5"};
//        Random random = new Random();
//        int i = random.nextInt(types.length);
//        return types[i];
//    }
//
//    public static double getRandomScore(){
//        double[] types = {0.3,0.2,0.1,0.5,0.8};
//        Random random = new Random();
//        int i = random.nextInt(types.length);
//        return types[i];
//    }
//
//    public static String getRandomLevel(){
//        String[] types = {"A","A+","B","C","D"};
//        Random random = new Random();
//        int i = random.nextInt(types.length);
//        return types[i];
//    }


}
