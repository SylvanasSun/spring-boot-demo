package cn.sun.springmvc.test;

import cn.sun.springmvc.config.MyMVCConfig;
import cn.sun.springmvc.service.DemoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @WebAppConfiguration注解在类上,用来声明加载的ApplicationContext是一个WebApplicationContext。
 * 它的属性指定的是Web资源的位置,默认为src/main/webapp
 * Created by sylvanasp on 2016/7/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyMVCConfig.class})
@WebAppConfiguration("src/main/resources")
public class TestControllerIntegrationTests {

    private MockMvc mockMvc;

    @Autowired
    private DemoService demoService;
    @Autowired
    WebApplicationContext wac;
    @Autowired
    MockHttpSession session;
    @Autowired
    MockHttpServletRequest request;

    @Before
    public void setup() {
        /**
         * MockMvc模拟MVC对象,通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化。
         */
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testNormalController() throws Exception {
        mockMvc.perform(get("/normal")) // 模拟向/normal进行get请求
                .andExpect(status().isOk()) // 预期控制返回状态为200
                .andExpect(view().name("page")) // 预期view的名称为page
                .andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp")) // 预期页面转向的真正路径
                .andExpect(model().attribute("msg", demoService.saySomething())); // 预期Model里的值
    }

    @Test
    public void testRestController() throws Exception {
        mockMvc.perform(get("/testRest")) // 模拟向/testRest进行get请求
                .andExpect(status().isOk()) // 预期控制返回状态为200
                .andExpect(content().contentType("text/plain;charset=UTF-8")) // 预期返回值的媒体类型
                .andExpect(content().string(demoService.saySomething())); // 预期返回值的内容
    }

}
