/**
 * All rights Reserved, Designed By www.iwhalecloud.com
 * Copyright (c): 2021 www.iwhalecloud.com
 * FileName: TestHttpClient
 * Author:   yida
 * Date:     2021/7/3 22:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.jike.nio.homework;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 功能描述: <br>
 * 〈5.必做〉
 *
 * @author zq
 * @date 2021/7/3
 */
public class TestHttpClient {

    public static void main(String[] args) {
        httpGet("http://127.0.0.1:8801");
    }

    public static String httpGet(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        String result = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                // 使用工具类EntityUtils，从响应中取出实体表示的内容并转换成字符串
                result = EntityUtils.toString(entity, "utf-8");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            // 关闭资源
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(result);
        return result;
    }


}
