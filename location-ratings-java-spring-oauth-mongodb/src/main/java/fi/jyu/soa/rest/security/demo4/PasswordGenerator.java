package fi.jyu.soa.rest.security.demo4;

public class PasswordGenerator {

    public static void main(String[] args) {
        // spring 4.0.0
        org.springframework.security.crypto.password.PasswordEncoder encoder
                = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();

        // https://stackoverflow.com/questions/25844419/spring-bcryptpasswordencoder-generate-different-password-for-same-input


        // "123456" - plain text - user input from user interface
        String passwd = encoder.encode("moderator");

        // passwd - password from database
        System.out.println(passwd); // print hash

        // true for all 5 iteration
        System.out.println(encoder.matches("moderator", passwd));

    }

}
