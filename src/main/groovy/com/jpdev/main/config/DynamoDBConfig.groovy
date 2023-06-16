package com.jpdev.main.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;

@Configuration
@EnableDynamoDBRepositories
        (basePackages = "com.jpdev.main.repository")
public class DynamoDBConfig {

    private static final Regions region = Regions.US_EAST_2

    @Value('${amazon.dynamodb.endpoint}')
    private String amazonDynamoDBEndpoint;

    @Value('${amazon.aws.accesskey}')
    private String amazonAWSAccessKey;

    @Value('${amazon.aws.secretkey}')
    private String amazonAWSSecretKey;


    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(amazonAWSCredentials())
                .withRegion(region)
                .build();
    }

    @Bean
    public AWSStaticCredentialsProvider amazonAWSCredentials() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey))
    }
}
