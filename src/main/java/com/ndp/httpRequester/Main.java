package com.ndp.httpRequester;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ndp.httpRequester.config.PropertyLoader;
import com.ndp.httpRequester.http.HttpRequester;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

/**
 * Created by prabhathd on 11/6/16.
 */
public class Main {
    public static void main(String[] args){
        PropertyLoader prop = PropertyLoader.getInstace();
        try{

            int count = Integer.parseInt(prop.getProperty("count"));
            Long interval = Long.parseLong(prop.getProperty("interval"));
            String url = prop.getProperty("url");
            System.out.println("URL:"+url);
            System.out.println("Interval:"+interval);
            System.out.println("Request Count:"+count);
            for(int i=0;i<count;i++){
                HttpRequester httpRequester = new HttpRequester();
                HttpResponse httpResponse = httpRequester.get(url);
                HttpEntity entity = httpResponse.getEntity();
                String entityString = EntityUtils.toString(entity);
                String messege = "";
                if(httpResponse.getStatusLine().getStatusCode() == 200){
                    if(entityString != null && !entityString.equals("")){
                        try {
                            JsonElement jsonElement = new JsonParser().parse(entityString);
                            messege = jsonElement.getAsJsonObject().get("status").toString();
                        }catch(Exception e){
                            messege = entityString;
                        }
                    }
                }else{
                    messege = "Request return http error:"+httpResponse.getStatusLine().getStatusCode();
                }
                System.out.println((i+1) + "-" + messege);
                Thread.sleep(interval);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
