package com.company.ding;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class BasicTestMvc {

    protected MockMvc mockMvc;
    @Autowired
    WebApplicationContext wac;
    protected ObjectMapper mapper = new ObjectMapper();

    @Before // 7 测试开始前的初始化工作
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build(); // 2
    }

}
