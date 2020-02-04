package com.wang.flink.model;

/**
 * @author 王一宁
 * @date 2020/1/15 10:24
 *
 * Tuple25 不够用的话，自己定义成公开的就可以了
 *  元组可以用角标，自己定义的类型可以使用【字段名称】，非常不错
 */
public class Turbine {
    public String word;
    public Long counts;

    public double longitude;
    public double latitude;
    
    public String province;

    public Turbine(String word, Long counts, double longitude, double latitude) {
        this.word = word;
        this.counts = counts;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Turbine(String word, long counts, String province) {
        this.word = word;
        this.counts = counts;
        this.province = province;
    }

    public static  Turbine of (String word, Long counts,double longitude,double latitude){
        return new Turbine(word,counts,longitude,latitude);
    }

    public static  Turbine of (String word, Long counts,double longitude,double latitude,String province){
        return new Turbine(word,counts,longitude,latitude,province);
    }

    public static Turbine of(String word, String province, long counts) {
        return new Turbine(word,counts,province);
    }


    @Override
    public String toString() {
        return "Turbine{" +
                "word='" + word + '\'' +
                ", counts=" + counts +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    public Turbine() {
    }

    public Turbine(String word, Long counts,double longitude,double latitude,String province) {
        this.word = word;
        this.counts = counts;
        this.latitude = latitude;
        this.longitude = longitude;
        this.province = province;
    }

//    public String getWord() {
//        return word;
//    }
//
//    public void setWord(String word) {
//        this.word = word;
//    }
//
//    public Long getCounts() {
//        return counts;
//    }
//
//    public void setCounts(Long counts) {
//        this.counts = counts;
//    }
}
