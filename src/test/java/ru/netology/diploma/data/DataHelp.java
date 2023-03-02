package ru.netology.diploma.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelp {

    private DataHelp() {
    }

    @Value
    public static class UserCardInfo {
        String number;
        String month;
        String year;
        String name;
        String securityCodes;
    }

    public static String generateDate(int shift, String pattern) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern(pattern)); //pattern - "dd.MM.yyyy"
    }

    public static class Registration {
        private Registration() {
        }

        public static UserCardInfo generateUser(String locale) {
            Faker faker = new Faker(new Locale(locale));
            UserCardInfo user = new UserCardInfo("4444", "03", "23",faker.name().firstName() + " " + faker.name().lastName(), String.valueOf(faker.number().numberBetween(100, 999)));
            return user;
        }
    }
}
