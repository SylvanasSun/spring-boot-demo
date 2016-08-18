package cn.sun.sylvanas.spring_boot_test;

import cn.sun.sylvanas.spring_boot_test.dao.PersonRepositroy;
import cn.sun.sylvanas.spring_boot_test.domain.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * 测试类
 * Created by sylvanasp on 2016/8/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {SpringBootTestApplication.class})
@SpringApplicationConfiguration(classes = SpringBootTestApplication.class)
@WebAppConfiguration
@Transactional
public class PersonApplicationTest {

    @Autowired
    PersonRepositroy personRepositroy;

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    String expectedJson;

    /**
     * 初始化
     */
    @Before
    public void setUp() throws JsonProcessingException {
        Person p1 = new Person("sun");
        Person p2 = new Person("sylvanas");
        personRepositroy.save(p1);
        personRepositroy.save(p2);
        // 获得期待返回的JSON字符串.
        expectedJson = Obj2Json(personRepositroy.findAll());
        // 初始化MockMvc.
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String Obj2Json(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    public void testPersonController() throws Exception {
        String url = "/person";
        // 对/person发送请求,获得请求的执行结果.
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        // 获得执行结果的状态
        int status = result.getResponse().getStatus();
        // 获得执行结果的内容
        String content = result.getResponse().getContentAsString();
        // 断言
        Assert.assertEquals("错误:正确的返回值为200", 200, status);
        Assert.assertEquals("错误:返回值和预期返回值不一致", expectedJson, content);
    }

}
