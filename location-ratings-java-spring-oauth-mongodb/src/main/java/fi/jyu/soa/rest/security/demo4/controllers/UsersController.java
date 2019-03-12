package fi.jyu.soa.rest.security.demo4.controllers;


import com.mongodb.WriteResult;
import fi.jyu.soa.rest.security.demo4.models.Users;
import fi.jyu.soa.rest.security.demo4.repositories.UsersRepository;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MODERATOR')")
    @GetMapping
    @SuppressWarnings("unchecked")
    public List<Users> listUsers(@RequestParam(required = false, value = "roles") List<String> roles) {
        Map<String, List<String>> filter = new HashMap<>();
        if (roles != null) {
            filter.put("roles", roles);
        }
        return repository.getCollection(filter);
    }

    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MODERATOR')")
    @GetMapping("{id}")
    public Users getUserById(@PathVariable("id") ObjectId id) {
        Users user = (Users) repository.read(id);
        return user;
    }

    @PreAuthorize("hasRole('ADMIN') or hasAuthority('MODERATOR')")
    @GetMapping("username/{username}")
    public Users findByUsername(@PathVariable("username") String username) {
        return repository.findByUsername(username);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public Users modifyUserById(@PathVariable("id") ObjectId id,
                              @Valid @RequestBody Users user) {
        try {
            Users old = (Users) repository.read(id);
            user.set_id(old.get_id());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return repository.update(user);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @SuppressWarnings("unchecked")
    public Users createUser(@Valid @RequestBody Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Key<Users> key = repository.create(user);
        ObjectId id = new ObjectId(key.getId().toString());
        return (Users) repository.read(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    @SuppressWarnings("unchecked")
    public WriteResult deleteUser(@PathVariable("id") ObjectId id) {
        return repository.delete(getUserById(id));
    }
}
