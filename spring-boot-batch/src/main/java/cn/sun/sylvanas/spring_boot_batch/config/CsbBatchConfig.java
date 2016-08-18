package cn.sun.sylvanas.spring_boot_batch.config;

import cn.sun.sylvanas.spring_boot_batch.batch.CsvBeanValidator;
import cn.sun.sylvanas.spring_boot_batch.batch.CsvItemProcessor;
import cn.sun.sylvanas.spring_boot_batch.batch.CsvJobListener;
import cn.sun.sylvanas.spring_boot_batch.domain.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


/**
 * Csv数据批处理配置类
 * Created by sylvanasp on 2016/8/2.
 */
@SuppressWarnings({"SpringJavaAutowiringInspection", "Duplicates"})
//@Configuration
//@EnableBatchProcessing
public class CsbBatchConfig {

    /**
     * 定义ItemReader.
     */
    @Bean
    public ItemReader<Person> reader() throws Exception {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        // 设置需要读取文件的路径
        reader.setResource(new ClassPathResource("people.csv"));
        /**
         * 对cvs文件的数据和领域模型类做对应映射.
         */
        reader.setLineMapper(new DefaultLineMapper<Person>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"name", "age", "nation", "address"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                setTargetType(Person.class);
            }});
        }});
        return reader;
    }

    /**
     * 定义ItemProcessor
     */
    @Bean
    public ItemProcessor<Person, Person> processor() {
        // 使用自定义的ItemProcessor
        CsvItemProcessor processor = new CsvItemProcessor();
        // 指定校验器
        processor.setValidator(csvBeanValidator());
        return processor;
    }

    /**
     * 定义Validator
     */
    @Bean
    public Validator<Person> csvBeanValidator() {
        return new CsvBeanValidator<Person>();
    }

    /**
     * 定义ItemWriter
     */
    @Bean
    public ItemWriter<Person> writer(DataSource dataSource) {
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider
                (new BeanPropertyItemSqlParameterSourceProvider<Person>());
        // 设置要执行批处理的sql语句
        String sql = "insert into person " + "(id,name,age,nation,address)"
                + "values(hibernate_sequence.nextval,:name,:age,:nation,:address)";
        writer.setSql(sql);
        writer.setDataSource(dataSource);
        return writer;
    }

    /**
     * 定义JobRepository
     */
    @Bean
    public JobRepository jobRepository(DataSource dataSource,
                                       PlatformTransactionManager transactionManager)
            throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDatabaseType("mysql");
        return jobRepositoryFactoryBean.getObject();
    }

    /**
     * 定义JobLauncher
     */
    @Bean
    public JobLauncher jobLauncher(DataSource dataSource,
                                   PlatformTransactionManager transactionManager)
            throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository(dataSource, transactionManager));
        return jobLauncher;
    }

    /**
     * 定义Job
     */
    @Bean
    public Job importJob(JobBuilderFactory jobs, Step s1) {
        return jobs.get("importJob")
                .incrementer(new RunIdIncrementer())
                .flow(s1) // 为job指定step
                .end()
                .listener(csvJobListener()) // 绑定监听器
                .build();
    }

    /**
     * 定义Step
     */
    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Person> reader,
                      ItemWriter<Person> writer,
                      ItemProcessor<Person, Person> processor) {
        return stepBuilderFactory
                .get("step1")
                .<Person, Person>chunk(65000) // 批处理每次提交65000条数据.
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    /**
     * 定义自定义的Job监听器
     */
    @Bean
    public CsvJobListener csvJobListener() {
        return new CsvJobListener();
    }

}
