package cn.sun.spring.test;

import cn.sun.spring.profile.ProfileBean;
import cn.sun.spring.profile.ProfileConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by sylvanasp on 2016/8/9.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ProfileConfig.class})
@ActiveProfiles("dev")
public class ProfileTest {

    @Autowired
    private ProfileBean profileBean;

    @Test
    public void test01() {
        System.out.println(profileBean.getMessage());
    }

}
