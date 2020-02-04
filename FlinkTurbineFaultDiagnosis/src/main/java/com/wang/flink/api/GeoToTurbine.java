package com.wang.flink.api;

import com.alibaba.fastjson.JSONObject;
import com.wang.flink.model.Turbine;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author 王一宁
 * @date 2020/1/15 19:49
 */
public class GeoToTurbine extends RichMapFunction<String, Turbine> {

    private CloseableHttpClient httpClient = null;

    @Override
    public void open(Configuration parameters) throws Exception {
        //1.创建HttpClient
        httpClient = HttpClients.createDefault();

    }

    @Override
    public Turbine map(String line) throws Exception {
        //1.切分数据
        String[] fields = line.split(",");

        String word = fields[0];
        long counts = Long.parseLong(fields[1]);
        String longitude = fields[2];
        String latitude = fields[3];

        String url = "https://restapi.ampm.com/v3/geocode/regeo?key=****" + longitude + "," + latitude;
        String province = null;
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);

        try {
            int status = response.getStatusLine().getStatusCode();
            if(status == 200){
                //获取请求的json字符串
                String result = EntityUtils.toString(response.getEntity());
                System.out.println(result);
                JSONObject jsonObject = JSONObject.parseObject(result);
                JSONObject regeocode = jsonObject.getJSONObject("regeocode");

                if(regeocode !=null && !regeocode.isEmpty()){
                    JSONObject address =  regeocode.getJSONObject("addressComponent");
                    //获取省市区
                    province = address.getString("province");
                    //String city = address.getString("city");
                    //String businessAreas = address.getString("businessAreas");
                }
            }
        }finally {
            response.close();
        }


        return Turbine.of(word,counts,Double.parseDouble(longitude),Double.parseDouble(latitude),province);
    }

    @Override
    public void close() throws Exception {
        super.close();
        httpClient.close();
    }
}
