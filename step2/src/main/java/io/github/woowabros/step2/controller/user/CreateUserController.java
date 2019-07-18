package io.github.woowabros.step2.controller.user;

import io.github.woowabros.step2.domain.user.User;
import io.github.woowabros.step2.service.user.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateUserController {

    private final UserDomainService userDomainService;

    @PostMapping("/user/{id}")
    public User create(@PathVariable String id, @RequestBody CreateUserRequest createUser) {
        return userDomainService.create(id, createUser.getName());
    }


}
