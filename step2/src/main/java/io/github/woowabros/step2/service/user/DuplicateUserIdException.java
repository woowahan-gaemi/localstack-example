package io.github.woowabros.step2.service.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="동일한 아이디의 사용자가 존재합니다.")
public class DuplicateUserIdException extends RuntimeException {

    public DuplicateUserIdException(String id) {
        super(id + "와 동일한 사용자가 이미 존재합니다.");
    }
}
