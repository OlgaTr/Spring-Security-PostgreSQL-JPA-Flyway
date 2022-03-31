package com.trainingcenter.services;

import com.trainingcenter.entities.Contributor;
import com.trainingcenter.entities.Group;
import com.trainingcenter.repositories.ContributorRepository;
import com.trainingcenter.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContributorServiceImpl implements ContributorService {

    private final ContributorRepository contributorRepository;
    private final GroupRepository groupRepository;

    public ContributorServiceImpl(ContributorRepository repository, GroupRepository groupRepository) {
        this.contributorRepository = repository;
        this.groupRepository = groupRepository;
    }

    public long addContributor(Contributor contributor) {
        Contributor savedContributor = contributorRepository.save(contributor);
        return savedContributor.getId();
    }

    public Contributor getContributorById(long id) {
        Optional<Contributor> optionalContributor = contributorRepository.findById(id);
        if (optionalContributor.isEmpty()) {
            throw new NullPointerException();
        } else {
            return optionalContributor.get();
        }
    }

    public void updateContributor(Contributor contributor) {
        Optional<Contributor> contributorEntity = contributorRepository.findById(contributor.getId());
        if (contributorEntity.isEmpty()) {
            throw new NullPointerException();
        } else {
            contributorRepository.save(contributor);
        }
    }

    public void deleteById(long id) {
        contributorRepository.deleteById(id);
    }

    public List<Contributor> listContributors() {
        return contributorRepository.findAll();
    }

    public void deleteAll() {
        contributorRepository.deleteAll();
    }

    private Set<Group> getGroups(long contributorId) {
        Contributor contributor = contributorRepository.getById(contributorId);
        return groupRepository.findAll().stream()
                .filter(groupEntity -> groupEntity.getContributors().contains(contributor))
//                .map(GroupMapper::asGroup)
                .collect(Collectors.toSet());
    }
}