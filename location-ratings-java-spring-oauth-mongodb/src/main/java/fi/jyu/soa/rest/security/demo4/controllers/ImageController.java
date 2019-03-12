package fi.jyu.soa.rest.security.demo4.controllers;


import fi.jyu.soa.rest.security.demo4.repositories.LocationRepository;
import fi.jyu.soa.rest.security.demo4.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("locations/{locationId}/images")
@RestController
public class ImageController {
    @Autowired
    private ImageRepository repository;

    @Autowired
    private LocationRepository locationRepository;

    // TODO

}
