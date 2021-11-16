package ru.netology.domain;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class Registration {
        private Registration() {
        }

        public static RegistrationByCardInfo generateByCard(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationByCardInfo(
                    faker.address().city(),
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber());
        }

        public static String getDate(int plusDays) {
            return LocalDate.now().plusDays(plusDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
    }
}

