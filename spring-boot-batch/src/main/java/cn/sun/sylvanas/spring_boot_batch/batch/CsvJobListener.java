package cn.sun.sylvanas.spring_boot_batch.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * 自定义Job监听器
 * Created by sylvanasp on 2016/8/2.
 */
public class CsvJobListener implements JobExecutionListener {

    private long startTime;
    private long endTime;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();
        System.out.println("任务处理开始.");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        endTime = System.currentTimeMillis();
        System.out.println("任务处理结束!");
        System.out.println("耗时:" + (endTime - startTime) + "ms");
    }
}
