package cn.sun.sylvanas.spring_boot_batch.web;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sylvanasp on 2016/8/2.
 */
@RestController
public class BatchController {

    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job importJob;

    public JobParameters jobParameters;

    @RequestMapping("/import")
    public String batchImport(String fileName) throws Exception {
        String path = fileName + ".csv";
        jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .addString("input.file.name", path)
                .toJobParameters();
        jobLauncher.run(importJob, jobParameters);
        return "ok";
    }

}
