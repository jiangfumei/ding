package com.company.ding;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/send")
public class DingController {

    @Value("${ding.keyword}")
    private String keyword;

    @Value("${ding.webhook}")
    private String webhook;

    @Value("${ding.secret}")
    private String secret;

    protected ObjectMapper mapper = new ObjectMapper();

    private static String line = "\n";

    @GetMapping("/{content}")
    public void sendMsg(@PathVariable String content) throws JsonProcessingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        //消息内容
        Map<String, String> contentMap = Maps.newHashMap();
        contentMap.put("content", keyword + line + content);
        //通知人
        Map<String, Object> atMap = Maps.newHashMap();
        //1.是否通知所有人
        atMap.put("isAtAll", true);
        Map<String, Object> reqMap = Maps.newHashMap();
        reqMap.put("msgtype", "text");
        reqMap.put("text", contentMap);
        reqMap.put("at", atMap);
        String sendMsg = mapper.writeValueAsString(reqMap);
        DingUtil.sendByKeyword(sendMsg, webhook);
        //DingUtil.sendBySign(sendMsg,webhook,secret);
        log.info("send msg is{}", content);
    }
}
