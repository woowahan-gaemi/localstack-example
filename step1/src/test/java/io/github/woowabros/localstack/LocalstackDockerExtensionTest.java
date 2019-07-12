package io.github.woowabros.localstack;

import cloud.localstack.DockerTestUtils;
import cloud.localstack.docker.LocalstackDockerExtension;
import cloud.localstack.docker.annotation.LocalstackDockerProperties;
import com.amazonaws.services.s3.AmazonS3;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(LocalstackDockerExtension.class)
@LocalstackDockerProperties(services = {"s3"})
class LocalstackDockerExtensionTest {

    @Test
    void test() throws Exception {
        AmazonS3 s3 = DockerTestUtils.getClientS3();

        String bucketName = "order-test-s3";
        s3.createBucket(bucketName);
        log.info("버킷을 생성했습니다. bucketName={}", bucketName);

        String content = "Hello World";
        String key = "s3-key";
        s3.putObject(bucketName, key, content);
        log.info("컨텐츠를 업로드하였습니다. bucketName={}, key={}, content={}", bucketName, key, content);

        List<String> results = IOUtils.readLines(s3.getObject(bucketName, key).getObjectContent(), "utf-8");
        log.info("컨텐츠를 가져왔습니다. bucketName={}, key={}, results={}", bucketName, key, results);

        assertThat(results).isEqualTo(Collections.singletonList(content));
    }
}
