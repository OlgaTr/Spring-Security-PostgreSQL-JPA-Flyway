package com.trainingcenter.services;


import com.trainingcenter.entities.Group;

import java.util.List;

public interface GroupService {
    long addGroup(Group group);
    Group getGroupById (long id);
    void updateGroup(Group group);
    void deleteGroup(long id);
    List<Group> getAllGroups();
    long addContributor(long contributorId, long groupId);
}
