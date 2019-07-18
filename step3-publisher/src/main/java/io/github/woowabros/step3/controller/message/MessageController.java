package io.github.woowabros.step3.controller.message;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final QueueMessagingTemplate sqsTemplate;

    @PostMapping("/message")
    public void create(@RequestBody MessageSendRequest messageSendRequest) {
        sqsTemplate.convertAndSend("test-message", messageSendRequest.getMessage());
    }

}
