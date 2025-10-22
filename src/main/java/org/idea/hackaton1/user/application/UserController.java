package org.idea.hackaton1.user.application;

import lombok.RequiredArgsConstructor;
import org.idea.hackaton1.user.domain.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class UserController {
    private final UserService userService;
}
