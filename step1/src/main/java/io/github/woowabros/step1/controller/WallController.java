package io.github.woowabros.step1.controller;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
public class WallController {

    public static final String BUCKET_WALL_CONTENT = "wall-bucket";

    private final AmazonS3 amazonS3;

    @GetMapping("/wall/{id}")
    public String get(@PathVariable String id) throws IOException {
        return String.join("", IOUtils.readLines(amazonS3.getObject(BUCKET_WALL_CONTENT, id).getObjectContent(), Charset.defaultCharset()));
    }

    @PostMapping("/wall/{id}")
    public String post(@PathVariable String id, @RequestBody PostRequest postRequest) {
        return amazonS3.putObject(BUCKET_WALL_CONTENT, id, postRequest.getContent()).getContentMd5();
    }
}
