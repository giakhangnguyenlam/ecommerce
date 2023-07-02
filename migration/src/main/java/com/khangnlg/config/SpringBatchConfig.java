package com.khangnlg.config;

import com.khangnlg.entities.UserEntity;
import com.khangnlg.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class SpringBatchConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final UserRepository userRepository;

    private final UserCustomFieldSetMapper userCustomFieldSetMapper;

    @Bean
    public FlatFileItemReader<UserEntity> reader(){
        FlatFileItemReader<UserEntity> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("report/user.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    @Bean
    public UserProcessor userProcessor(){
        return new UserProcessor();
    }

    @Bean
    public RepositoryItemWriter<UserEntity> itemWriter(){
        RepositoryItemWriter<UserEntity> itemWriter = new RepositoryItemWriter<>();
        itemWriter.setRepository(userRepository);
        itemWriter.setMethodName("save");
        return itemWriter;
    }

    private LineMapper<UserEntity> lineMapper() {

        DefaultLineMapper<UserEntity> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(DelimitedLineTokenizer.DELIMITER_COMMA);
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("address", "dateOfBirth", "email", "gender", "grantedAuthority", "name", "password");


        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(userCustomFieldSetMapper);

        return lineMapper;

    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("csv-step")
                .<UserEntity, UserEntity>chunk(10)
                .reader(reader())
                .processor(userProcessor())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public Job job(){
        return jobBuilderFactory.get("importUser")
                .flow(step1())
                .end()
                .build();
    }

}
