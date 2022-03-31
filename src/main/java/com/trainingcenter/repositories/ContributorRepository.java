package com.trainingcenter.repositories;

import com.trainingcenter.entities.Contributor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContributorRepository extends JpaRepository<Contributor, Long> {
}
