package fi.jyu.soa.rest.security.demo4;


import fi.jyu.soa.rest.security.demo4.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

// THIS IS STILL WIP

// Examples taken from http://websystique.com/spring-boot/spring-boot-rest-api-example/
public class RestTestClient {

    public static final String REST_SERVICE_URI = "http://localhost:8080/";

    private static final RestTemplate restTemplate = new RestTemplateBuilder()
            .basicAuthorization("admin","admin").build();


    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllUsers(){
        System.out.println("Testing listAllUsers API-----------");

        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/users/", List.class);

        if(usersMap!=null){
            for(LinkedHashMap<String, Object> map : usersMap){
                System.out.println("User : _id="+map.get("_id")+", Username="+map.get("username")
                        +", Password="+map.get("password")+", Email="+map.get("email")+", Roles="+map.get("roles")
                );
            }
        }else{
            System.out.println("No user exist----------");
        }
    }

    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllLocations(){
        System.out.println("Testing listAllUsers API-----------");

        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/locations/", List.class);

        if(usersMap!=null){
            for(LinkedHashMap<String, Object> map : usersMap){
                System.out.println("Location : _id="+map.get("_id")+", Name="+map.get("name")
                        +", Location Type="+map.get("locationType")+", Slug="+map.get("slug")+", Coordinate="+map.get("Coordinate")
                        +", Operator="+map.get("operator")+", Address="+map.get("address")+", ratings="+map.get("Ratings")
                        +", Images="+map.get("images")+", Tags="+map.get("tags")
                );
            }
        }else{
            System.out.println("No user exist----------");
        }
    }

    /* POST */
    private static void createLocation() {
        System.out.println("Testing create Location API----------");
        Location location = new Location();
        location.setName("Prisma");

        //Operator operator = new Operator();
        //operator.setName("SOK");
        //Address op_address = new Address("Seppälänkatu 1", "40402", "Jyväskylä");
        //operator.setAddress(op_address);

        //Address address = new Address("Seppälänkatu 2", "254234", "Jyväskylä");
        //location.setAddress(address);
        //LocationType locationType = new LocationType("Shopping mall");
        //location.setLocationType(locationType);
        //location.setCoordinate(new Coordinate(100.0, 110.00,"http://www.prisma.fi/"));
        //location.addImage(new Image("http://localhost:8080/images/prisma.jpg", "Prisma Seppälä"));
        //Rating rating = new Rating();
        //rating.addScore(new Score("Variety", 8));
        //rating.setComment("Very nice variety of products!");
        //location.addRating(rating);
        //location.addTag("tägi");
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/locations", location, Location.class);
        System.out.println("Location : "+uri.toASCIIString());
    }

//    /* GET */
//    private static void getUser(){
//        System.out.println("Testing getUser API----------");
//        RestTemplate restTemplate = new RestTemplate();
//        User user = restTemplate.getForObject(REST_SERVICE_URI+"/user/1", User.class);
//        System.out.println(user);
//    }
//
//    /* POST */
//    private static void createUser() {
//        System.out.println("Testing create User API----------");
//        RestTemplate restTemplate = new RestTemplate();
//        User user = new User(0,"Sarah",51,134);
//        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/user/", user, User.class);
//        System.out.println("Location : "+uri.toASCIIString());
//    }
//
//    /* PUT */
//    private static void updateUser() {
//        System.out.println("Testing update User API----------");
//        RestTemplate restTemplate = new RestTemplate();
//        User user  = new User(1,"Tomy",33, 70000);
//        restTemplate.put(REST_SERVICE_URI+"/user/1", user);
//        System.out.println(user);
//    }
//
//    /* DELETE */
//    private static void deleteUser() {
//        System.out.println("Testing delete User API----------");
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.delete(REST_SERVICE_URI+"/user/3");
//    }
//
//
//    /* DELETE */
//    private static void deleteAllUsers() {
//        System.out.println("Testing all delete Users API----------");
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.delete(REST_SERVICE_URI+"/user/");
//    }

    public static void main(String args[]){
        listAllUsers();
        createLocation();
        listAllLocations();
//        getUser();
//        createUser();
//        listAllUsers();
//        updateUser();
//        listAllUsers();
//        deleteUser();
//        listAllUsers();
//        deleteAllUsers();
//        listAllUsers();
    }
}
