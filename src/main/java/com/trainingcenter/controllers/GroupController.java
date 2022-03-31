package com.trainingcenter.controllers;

import com.trainingcenter.entities.Group;
import com.trainingcenter.services.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/groups")
public class GroupController {

    private final GroupService service;

    public GroupController(GroupService service) {
        this.service = service;
    }

    @GetMapping
    public List<Group> listGroups() {
        return service.getAllGroups();
    }

    @GetMapping("/{id}")
    public Group getGroup(@PathVariable("id") long id) {
        return service.getGroupById(id);
    }

    @PostMapping
    public void addGroup(@RequestBody Group group) {
        service.addGroup(group);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable("id") long id) {
        service.deleteGroup(id);
    }

    @PutMapping("/{groupId}/{contributorId}")
    public void addContributorToGroup(@PathVariable("groupId") long groupId,
                                      @PathVariable("contributorId") long contributorId) {
        service.addContributor(contributorId, groupId);
    }
}
