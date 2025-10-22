package org.idea.hackaton1.user.domain;

import lombok.RequiredArgsConstructor;
import org.idea.hackaton1.user.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
}
