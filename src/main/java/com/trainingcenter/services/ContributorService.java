package com.trainingcenter.services;

import com.trainingcenter.entities.Contributor;

import java.util.List;

public interface ContributorService {
    long addContributor(Contributor contributor);
    Contributor getContributorById(long id);
    void updateContributor(Contributor contributor);
    void deleteById(long id);
    List<Contributor> listContributors();
    void deleteAll();
   // Set<Group> getGroups(long contributorId);
}
