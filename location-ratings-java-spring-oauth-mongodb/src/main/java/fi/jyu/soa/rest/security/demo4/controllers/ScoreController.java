package fi.jyu.soa.rest.security.demo4.controllers;

import fi.jyu.soa.rest.security.demo4.repositories.ScoreRepository;
import fi.jyu.soa.rest.security.demo4.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("locations/{locationId}/scores")
@RestController
public class ScoreController {
    @Autowired
    private ScoreRepository repository;

    @Autowired
    private LocationRepository locationRepository;

    // TODO

}