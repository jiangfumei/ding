package com.company.ding;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DingControllerTest extends BasicTestMvc {

    public static String line = "\n";

    @Test
    public void sendMsg() throws Exception {
        mockMvc.perform(get("/send/test")
        )
                .andDo(print())
                .andExpect(status().isOk());
    }
/*

    @Test
    public void send() throws Exception {
        //消息内容
        Map<String, String> contentMap = Maps.newHashMap();
        contentMap.put("content", "告警" + line + "success");
        //通知人
        Map<String, Object> atMap = Maps.newHashMap();
        //1.是否通知所有人
        atMap.put("isAtAll", true);
        //2.通知具体人的手机号码列表
        List<String> mobileList = new ArrayList<>();
        mobileList.add("178******62");
        atMap.put("atMobiles", mobileList);
        Map<String, Object> reqMap = Maps.newHashMap();
        reqMap.put("msgtype", "text");
        reqMap.put("text", contentMap);
        reqMap.put("at", atMap);
        String contens = JSON.toJSONString(reqMap);

        String result = DingUtil.sendByKeyword(contens);

        mockMvc.perform(post("/send/warn")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mapper.writeValueAsString(reqMap))
        )
                .andDo(print())
                .andExpect(status().isOk());
        System.out.print(result);
    }
*/

}