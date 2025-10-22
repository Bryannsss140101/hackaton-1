package org.idea.hackaton1.user.infrastructure;

import org.idea.hackaton1.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
