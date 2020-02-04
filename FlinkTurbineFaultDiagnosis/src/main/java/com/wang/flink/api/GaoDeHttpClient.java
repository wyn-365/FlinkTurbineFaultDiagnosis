package com.wang.flink.api;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author 王一宁
 * @date 2020/1/15 19:27
 */
public class GaoDeHttpClient {
    public static void main(String[] args) throws Exception {
        double longitude = 116.311805;
        double latitude = 40.311805;
        String url = "https://restapi.ampm.com/v3/geocode/regeo?key=" + longitude + "," + latitude;

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);

        try{
            int status = response.getStatusLine().getStatusCode();
            String province = null;
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
            System.out.println(province);
        }finally {
            response.close();
        }

    }
}
