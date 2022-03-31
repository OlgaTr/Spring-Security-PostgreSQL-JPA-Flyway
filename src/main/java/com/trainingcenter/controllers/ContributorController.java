package com.trainingcenter.controllers;

import com.trainingcenter.entities.Contributor;
import com.trainingcenter.services.ContributorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/contributors")
public class ContributorController {

    private final ContributorService service;

    public ContributorController(ContributorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Contributor>> listContributors() {
       try {
           return new ResponseEntity<>(service.listContributors(), HttpStatus.OK);
       } catch (Exception e) {
           throw new RuntimeException(e.getCause());
       }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contributor> getContributor(@PathVariable("id") long id) {
        return new ResponseEntity<>(service.getContributorById(id), HttpStatus.OK);
    }

    @PostMapping
    public void addContributor(@RequestBody Contributor contributor) {
        service.addContributor(contributor);
    }

    @DeleteMapping("/{id}")
    public void deleteContributor(@PathVariable("id") long id) {
        service.deleteById(id);
    }

}
