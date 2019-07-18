package io.github.woowabros.testcontainer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.localstack.LocalStackContainer;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@EnableAwsLocalStack({LocalStackContainer.Service.S3})
class AwsLocalStackConfigTest {

    @Autowired
    LocalStackContainer localStackContainer;

    @Test
    void test() throws Exception {
        assertThat(localStackContainer.isRunning()).isTrue();
    }

}