package cn.sun.spring.asyn;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by sylvanasp on 2016/7/22.
 */
@Service
public class AsynTaskService {

    @Async
    public void asynExecute(Integer i) {
        System.out.println("异步执行任务 : " + i);
    }
    @Async
    public void asynExecutePlus(Integer i) {
        System.out.println("异步执行任务 i + 1 :" + (i + 1));
    }

}
