package io.github.woowabros.step2.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import io.github.woowabros.testcontainer.EnableAwsLocalStack;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.localstack.LocalStackContainer;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.DYNAMODB;

@Profile("local")
@EnableAwsLocalStack({DYNAMODB})
@EnableDynamoDBRepositories(basePackages = {"io.github.woowabros.step2.domain"})
@Configuration
public class DynamoDBLocalConfig {

    @Bean
    public AmazonDynamoDB amazonDynamoDB(LocalStackContainer localStackContainer) {
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(localStackContainer.getEndpointConfiguration(DYNAMODB))
                .withCredentials(localStackContainer.getDefaultCredentialsProvider())
                .build();
    }
}
