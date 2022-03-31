package com.trainingcenter.entities;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
@Proxy(lazy = false)
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private long id;
    private String name;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
    private Set<Contributor> contributorEntities = new HashSet<>();

    public Group() {

    }

    public Group(long id, String name, Set<Contributor> contributorEntities) {
        this.id = id;
        this.name = name;
        this.contributorEntities = contributorEntities;
    }

    public Group(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Contributor> getContributors() {
        return contributorEntities;
    }
/*
    public void addContributor(Contributor contributor) {
        if (contributors.add(contributor)) {
            contributor.addGroup(this);
        }
    }

 */

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contributors=" + Arrays.toString(contributorEntities.stream().map(Contributor::toString).toArray()) +
                '}';
    }
}
