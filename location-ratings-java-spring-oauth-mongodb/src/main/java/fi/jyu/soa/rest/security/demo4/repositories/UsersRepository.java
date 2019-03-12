package fi.jyu.soa.rest.security.demo4.repositories;

import fi.jyu.soa.rest.security.demo4.models.Users;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepository extends BaseRepository {

    public UsersRepository() {
        super(Users.class);
    }

    @SuppressWarnings("unchecked")
    public Users update(Users user) {
        UpdateOperations<Users> operations = super.createOperations();

        if (user.getUsername() != null) {
            operations.set("username", user.getUsername());
        }

        if (user.getPassword() != null) {
            operations.set("password", user.getPassword());
        }

        if (user.getEmail() != null) {
            operations.set("email", user.getEmail());
        }

        if (user.getRoles() != null) {
            operations.set("roles", user.getRoles());
        }

        UpdateResults results = super.update(user, operations);

        return (Users) super.read(user.get_id());
    }

    public Users findByUsername(String username) {

        Query<Users> query = super.getDatastore().createQuery(Users.class).field("username").equal(username);
        Users result = query.get();
        System.out.println(result.getUsername());
        System.out.println(result.get_id());

        return result;
    }


}
