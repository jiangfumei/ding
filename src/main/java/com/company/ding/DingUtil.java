package com.company.ding;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class DingUtil {


    /**
     * 给钉钉群发送消息方法
     *
     * @param content 消息内容
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     */
    public static String sendByKeyword(String content, String webhook) {
        try {
            //钉钉机器人地址（配置机器人的webhook）
            //String dingUrl = "https://oapi.dingtalk.com/robot/send?access_token=4cabbc9723847cd582feee49fbdd737c2e3eae09567aa1b9cd1bb6c1e7a00322";
            String result = HttpUtil.post(webhook, content);
            return result;
        } catch (Exception e) {
            log.error("钉钉推送消息出现异常:{}", e.getMessage());
            return null;
        }

    }

    public static String sendBySign(String content, String webhook, String secret) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        try {
            String sign = genreateSign(secret);
            //钉钉机器人地址（配置机器人的webhook）
            String dingUrl = webhook + "&timestamp=" + System.currentTimeMillis() + "&sign=" + sign;
            log.info("webhook is {}", dingUrl);
            String result = HttpUtil.post(dingUrl, content);
            return result;
        } catch (Exception e) {
            log.error("钉钉推送消息出现异常:{}", e.getMessage());
            return null;
        }

    }

    public static String genreateSign(String secret) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        //String sign = new String(Base64.encodeBase64(signData));
        //新建一个Base64编码对象
        /*Base64.Encoder encoder = Base64.getEncoder();
        //把上面的字符串进行Base64加密后再进行URL编码
        String sign = URLEncoder.encode(new String(encoder.encodeToString(signData)),"UTF-8");*/

        log.info("sign is {}", sign);
        return sign;
    }

}
