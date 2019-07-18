package io.github.woowabros.step1;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Testcontainers
class LocalStackTestcontainerTest {

    @Container
    LocalStackContainer container = new LocalStackContainer()
            .withServices(LocalStackContainer.Service.S3);

    @Test
    void test() throws Exception {
        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(container.getEndpointConfiguration(LocalStackContainer.Service.S3))
                .withCredentials(container.getDefaultCredentialsProvider())
                .build();

        String bucketName = "test-s3";
        s3.createBucket(bucketName);
        log.info("버킷을 생성했습니다. bucketName={}", bucketName);

        String content = "Hello World";
        String key = "s3-key";
        s3.putObject(bucketName, key, content);
        log.info("파일을 업로드하였습니다. bucketName={}, key={}, content={}", bucketName, key, content);

        List<String> results = IOUtils.readLines(s3.getObject(bucketName, key).getObjectContent(), "utf-8");
        log.info("파일을 가져왔습니다. bucketName={}, key={}, results={}", bucketName, key, results);

        assertThat(results).hasSize(1);
        assertThat(results.get(0)).isEqualTo(content);
    }

}
