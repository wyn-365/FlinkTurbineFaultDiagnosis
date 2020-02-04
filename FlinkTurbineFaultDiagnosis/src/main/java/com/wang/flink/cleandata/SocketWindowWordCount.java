package com.wang.flink.cleandata;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * @author 王一宁
 * @date 2020/1/1 19:22
 * 通过socket模拟产生数据
 * flink负责统计计算
 * 规则：实现每隔一秒对最近两秒内的数据进行计算【滑动窗口计算】
 */
public class SocketWindowWordCount {
    public static void main(String[] args) throws Exception{
        //动态获取端口号
        int port;
        try {
            ParameterTool parameterTool = ParameterTool.fromArgs(args);
            port = parameterTool.getInt("port");
        }catch (Exception e){
            port = 9000;
        }

        //获取配置
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //指定数据源
        String hostname = "yum";
        //表示换行结束
        String delimiter = "\n";
        //获取到socket的数据
        DataStreamSource<String> text = env.socketTextStream(hostname, port, delimiter);
        //a b c d ...
        // a 1
        // b 1
        SingleOutputStreamOperator<WordCount> windowCounts = text.flatMap(new FlatMapFunction<String, WordCount>() {
            @Override
            public void flatMap(String value, Collector<WordCount> out) throws Exception {
                String[] splits = value.split("\\s");
                for (String word : splits) {
                    out.collect(new WordCount(word, 1L));
                }
            }
        }).keyBy("word")
                .timeWindow(Time.seconds(2), Time.seconds(1)) //时间窗口 和时间间隔
                .sum("count");//sum 或者reduce 都可以
//                .reduce(new ReduceFunction<WordCount>(){
//                    @Override
//                    public WordCount reduce(WordCount a, WordCount b) throws Exception {
//                        return new WordCount(a.word,a.count+b.count);
//                    }
//                });


        //数据打印控制台
        windowCounts.print().setParallelism(1);

        //执行程序
        env.execute("socket window count");

    }

    public static class WordCount{
        public String word;
        public long count;

        public WordCount(){

        }
        public WordCount(String word,long count){
            this.word = word;
            this.count = count;

        }

        @Override
        public String toString() {
            return "WordCount{" +
                    "word='" + word + '\'' +
                    ", count=" + count +
                    '}';
        }
    }
}
