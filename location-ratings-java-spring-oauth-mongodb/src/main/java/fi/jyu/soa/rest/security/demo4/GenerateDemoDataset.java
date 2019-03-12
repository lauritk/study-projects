package fi.jyu.soa.rest.security.demo4;

import com.mongodb.MongoClient;
import fi.jyu.soa.rest.security.demo4.models.*;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

public class GenerateDemoDataset {

    private static PasswordEncoder passwordEncoder = passwordEncoder();
    private static Datastore datastore = datastore();

    public static Datastore datastore() {
        Morphia morphia = new Morphia();
        MongoClient mongoClient = new MongoClient();
        Datastore datastore = morphia.createDatastore(mongoClient, "demo4");
        datastore.ensureIndexes();
        return datastore;
    }

    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void addUser(String username, String password, String email, List<String> roles) {
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRoles(roles);
        datastore.save(user);
    }

    public static void addLocation(String name, String locName, Double lat, Double lon, String url, String opName,
                                   String opStreet, String opCode, String opOffice, String street, String code,
                                   String office, List<String> tags) {
        Location location = new Location();

        location.setName(name);

        LocationType locType = new LocationType();
        locType.setName(locName);
        location.setLocationType(locType);
        datastore.save(locType);

        location.setSlug("none");

        Coordinate coord = new Coordinate();
        coord.setLatitude(lat);
        coord.setLatitude(lon);
        coord.setUrl(url);
        location.setCoordinate(coord);
        datastore.save(coord);

        Operator operator = new Operator();
        operator.setName(opName);


        Address opAddress = new Address();
        opAddress.setStreetAddress(opStreet);
        opAddress.setPostalCode(opCode);
        opAddress.setPostOffice(opOffice);
        operator.setAddress(opAddress);
        location.setOperator(operator);
        datastore.save(opAddress);
        datastore.save(operator);

        Address address = new Address();
        address.setStreetAddress(street);
        address.setPostalCode(code);
        address.setPostOffice(office);
        location.setAddress(address);
        datastore.save(address);

        location.setTags(tags);

        datastore.save(location);
    }

    public static void main(String[] args) {

        // Add users for demonstration
        addUser("admin","admin","email@company.com", Arrays.asList("ADMIN", "ROLE_ADMIN"));
        addUser("user","user","email@company.com", Arrays.asList("USER", "USER_ADMIN"));
        addUser("moderator","moderator","email@company.com", Arrays.asList("MODERATOR", "USER_ADMIN"));

        // Add sample locations for demonstration
        addLocation("Prisma", "Mall", 110.0, 80.0, "www.prisma.fi", "Osuuskauppa",
                "Sepänkuja 123", "40123", "Jyväskylä", "Seppälänkatu 123", "40123", "Jyväskylä",
                Arrays.asList("mall", "groceries", "food", "goods", "family"));
        addLocation("Alko", "Alcohol retailer", 110.0, 80.0, "www.alko.fi", "Pekka Puska",
                "Arkadianmäki 123", "00000", "Helsinki", "Seppälänkatu 123", "40123", "Jyväskylä",
                Arrays.asList("alcohol", "drinks", "bad health", "dangerous"));
    }

}
