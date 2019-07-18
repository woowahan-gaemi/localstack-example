package io.github.woowabros.step1.controller;

import com.amazonaws.services.s3.AmazonS3;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Tag("integration")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = LocalStackS3Config.class)
class WallControllerIntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    AmazonS3 amazonS3;

    @BeforeEach
    void init() {
        amazonS3.createBucket(WallController.BUCKET_WALL_CONTENT);
    }

    @Test
    void test() throws Exception {
        String id = "gaemi";
        String content = "Hello World";
        PostRequest postRequest = new PostRequest();
        postRequest.setContent(content);

        String createResponse = testRestTemplate.postForObject("/wall/{id}", postRequest, String.class, id);
        log.info("createResponse={}", createResponse);
        assertThat(createResponse).isNotBlank();

        String getResponse = testRestTemplate.getForObject("/wall/{id}", String.class, id);
        log.info("getResponse={}", getResponse);
        assertThat(getResponse).isEqualTo(content);
    }
}