package com.trainingcenter.repositories.security;

import com.trainingcenter.entities.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
