package com.trainingcenter.services;

import com.trainingcenter.entities.Contributor;
import com.trainingcenter.entities.Group;
import com.trainingcenter.repositories.ContributorRepository;
import com.trainingcenter.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final ContributorRepository contributorRepository;

    public GroupServiceImpl(GroupRepository repository, ContributorRepository contributorRepository) {
        this.groupRepository = repository;
        this.contributorRepository = contributorRepository;
    }

    public long addGroup(Group group) {
        Group savedGroup = groupRepository.save(group);
        return savedGroup.getId();
    }

    public Group getGroupById (long id) {
        Optional<Group> group = groupRepository.findById(id);
        if (group.isEmpty()) {
            throw new NullPointerException();
        } else {
            return group.get();
        }
    }

    public void updateGroup(Group group) {
        Optional<Group> groupEntity = groupRepository.findById(group.getId());
        if (groupEntity.isEmpty()) {
            throw new NullPointerException();
        } else {
            groupRepository.save(group);
        }
    }

    public void deleteGroup(long id) {
        groupRepository.deleteById(id);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public long addContributor(long contributorId, long groupId) {
        Optional<Contributor> optionalContributor = contributorRepository.findById(contributorId);
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalContributor.isEmpty() || optionalGroup.isEmpty()) {
            throw new NullPointerException();
        } else {
            Contributor contributor = optionalContributor.get();
            Group group = optionalGroup.get();
            group.getContributors().add(contributor);
            contributor.getGroups().add(group);
            Group savedGroup = groupRepository.saveAndFlush(group);
            System.out.println("************************" + savedGroup);
            return savedGroup.getId();
        }
    }
}
