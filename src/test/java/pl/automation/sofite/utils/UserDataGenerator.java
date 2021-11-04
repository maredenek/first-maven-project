package pl.automation.sofite.utils;

import pl.automation.softie.components.Gender;

import java.util.Random;
import java.util.UUID;

// exercise 20
public class UserDataGenerator {

    public static String generateRandomFirstName() {
        return UUID.randomUUID().toString();
    }

    public static String generateRandomLastName() {
        return UUID.randomUUID().toString();
    }

    public static String generateRandomPassword() {
        return UUID.randomUUID().toString();
    }

    public static String generateRandomEmail() {
        return UUID.randomUUID() + "@softie.pl";
    }

    public static Gender generateRandomGender() {
        int randomNumber = new Random().nextInt(10);
        return randomNumber < 5 ? Gender.MALE : Gender.FEMALE;
/*        if(randomNumber < 5)
            return Gender.MALE;
        else
            return Gender.FEMALE;*/
    }

}
