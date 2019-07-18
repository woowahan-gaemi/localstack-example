package io.github.woowabros.step2.service.user;

import io.github.woowabros.step2.domain.user.User;
import io.github.woowabros.step2.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDomainService {
    private final UserRepository userRepository;

    public User create(String id, String name) {
        if (userRepository.findById(id).isPresent()) {
            throw new DuplicateUserIdException(id);
        }

        User user = new User();
        user.setId(id);
        user.setName(name);
        return userRepository.save(user);
    }
}
