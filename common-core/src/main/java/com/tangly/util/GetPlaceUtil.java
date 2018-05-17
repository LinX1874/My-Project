package com.tangly.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import sun.net.util.IPAddressUtil;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;


/**
 * 通过IP获取地理位置
 */
public class GetPlaceUtil {

    private final static String BAIDU_API = "http://api.map.baidu.com/location/ip?ak=F454f8a5efe5e577997931cc01de3974&ip=";

    /**
     * 通过IP地址获取地址
     * @param ip
     * @return
     */
    public static String getPlace(String ip) {
        if (internalIp(ip)) {
            return "局域网内部";
        }
        JSONObject json;
        try {
            json = readJsonFromUrl(BAIDU_API + ip);
            System.out.println(json.toString());
            return (String) ((JSONObject) json.get("content")).get("address");
        } catch (Exception e) {
            return "未知地区";
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = JSONObject.parseObject(jsonText);
            return json;
        } finally {
            is.close();
            // System.out.println("同时 从这里也能看出 即便return了，仍然会执行finally的！");
        }
    }

    public static void main(String[] args) throws IOException, JSONException {
        System.out.println(getPlace("117.28.195.4"));
    }

    /**
     * 判断IP是否是局域网
     * 10.0.0.0/8：10.0.0.0～10.255.255.255
     * 172.16.0.0/12：172.16.0.0～172.31.255.255
     * 192.168.0.0/16：192.168.0.0～192.168.255.255
     *
     * @param ip
     * @return
     */
    public static boolean internalIp(String ip) {
        try {
            byte[] addr = IPAddressUtil.textToNumericFormatV4(ip);
            final byte b0 = addr[0];
            final byte b1 = addr[1];
            //10.x.x.x/8
            final byte SECTION_1 = 0x0A;
            //172.16.x.x/12
            final byte SECTION_2 = (byte) 0xAC;
            final byte SECTION_3 = (byte) 0x10;
            final byte SECTION_4 = (byte) 0x1F;
            //192.168.x.x/16
            final byte SECTION_5 = (byte) 0xC0;
            final byte SECTION_6 = (byte) 0xA8;
            switch (b0) {
                case SECTION_1:
                    return true;
                case SECTION_2:
                    if (b1 >= SECTION_3 && b1 <= SECTION_4) {
                        return true;
                    }
                case SECTION_5:
                    switch (b1) {
                        case SECTION_6:
                            return true;
                        default:
                            return false;
                    }
                default:
                    return false;
            }
        } catch (Exception e) {
            return true;
        }
    }
}