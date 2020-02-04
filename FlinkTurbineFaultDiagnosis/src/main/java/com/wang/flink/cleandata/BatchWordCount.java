package com.wang.flink.cleandata;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;


/**
 * @author 王一宁
 * @date 2020/1/1 21:00
 * 批处理
 */
public class BatchWordCount {
    public static void main(String[] args) throws Exception{
        //获取配置
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        //获取数据源
        String inputPath = "F:\\input01";
        String outPath = "F:\\output01\\result";

        DataSource<String> text = env.readTextFile(inputPath);
        AggregateOperator<Tuple2<String, Integer>> counts = text.flatMap(new Tokenizer()).groupBy(0).sum(1);


        //保存文件
        counts.writeAsCsv(outPath,"\n"," ").setParallelism(1);

        //执行
        env.execute("batch wordcount");
    }

    public static class Tokenizer implements FlatMapFunction<String, Tuple2<String,Integer>>{
        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
            String[] tokens = value.toLowerCase().split("\\W+");
            for (String token : tokens) {
                if (token.length()>0){
                    out.collect(new Tuple2<String,Integer>(token,1));
                }
            }

        }
    }
}
