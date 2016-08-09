package cn.sun.sylvanas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Controller
public class ThymeleafDemoApplication {

    @RequestMapping("/")
    public String index(Model model) {
        Student single = new Student("aa", 11);

        List<Student> students = new ArrayList<>();
        Student s1 = new Student("xx", 11);
        Student s2 = new Student("yy", 22);
        Student s3 = new Student("zz", 33);
        students.add(s1);
        students.add(s2);
        students.add(s3);

        model.addAttribute("singleStudent", single);
        model.addAttribute("student", students);

        return "index";
    }

    @RequestMapping("/animal")
    public String animal(Model model) {
        Animal singleAnimal = new Animal("小黑", 2);

        List<Animal> animals = new ArrayList<Animal>();
        Animal a1 = new Animal("aa", 3);
        Animal a2 = new Animal("bb", 3);
        Animal a3 = new Animal("cc", 3);
        animals.add(a1);
        animals.add(a2);
        animals.add(a3);

        model.addAttribute("singleAnimal", singleAnimal);
        model.addAttribute("animal", animals);

        return "animal";
    }


    public static void main(String[] args) {
        SpringApplication.run(ThymeleafDemoApplication.class, args);
    }

//    /**
//     * Tomcat的HTTP连接器
//     * 当访问8080端口时会自动跳转到8443端口
//     */
//    @Bean
//    public Connector httpConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        connector.setPort(8080);
//        connector.setSecure(false);
//        connector.setRedirectPort(8443);
//        return connector;
//    }

//    /**
//     * 配置TomcatEmbeddedServletContainerFactory
//     */
//    @Bean
//    public EmbeddedServletContainerFactory servletContainer() {
//        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection securityCollection = new SecurityCollection();
//                securityCollection.addPattern("/*");
//                securityConstraint.addCollection(securityCollection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(httpConnector());
//        return tomcat;
//    }


}
