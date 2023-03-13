package ru.netology.diploma.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

    private static String generateDate(int shift, String pattern) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern(pattern));
    }

    private static String generateName() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    private static String generateSecurityCode() {
        Faker faker = new Faker(new Locale("en"));
        return String.valueOf(faker.number().numberBetween(100, 999));
    }

    private static Map<String, UserCardInfo> scenarios = new HashMap<>();

    public static void creatingTestDataMap() {
        scenarios.put("scenario_01", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), generateDate(0, "yy"), "Mariy-Magdolina Pirojkova-Bulochkina", "123"));
        scenarios.put("scenario_02", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), generateDate(365 * 5, "yy"), "Mariy Pirojkova", "003"));
        scenarios.put("scenario_03", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), generateDate(0, "yy"), "Ma Pi", "100"));
        scenarios.put("scenario_04", new UserCardInfo("4444 4444 4444 4442", generateDate(30 * 3, "MM"), generateDate(365 * 3, "yy"), generateName(), generateSecurityCode()));
        scenarios.put("scenario_05", new UserCardInfo("0000 0000 0000 0000", generateDate(30 * 3, "MM"), generateDate(365 * 3, "yy"), generateName(), generateSecurityCode()));
        scenarios.put("scenario_06", new UserCardInfo(" ", generateDate(30 * 3, "MM"), generateDate(365 * 3, "yy"), generateName(), generateSecurityCode()));
        scenarios.put("scenario_07", new UserCardInfo("4444 4444", generateDate(30 * 3, "MM"), generateDate(365 * 3, "yy"), generateName(), generateSecurityCode()));
        scenarios.put("scenario_08", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), generateDate(-366, "yy"), generateName(), generateSecurityCode()));
        scenarios.put("scenario_09", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), generateDate(365 * 6, "yy"), generateName(), generateSecurityCode()));
        scenarios.put("scenario_10", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), "00", generateName(), generateSecurityCode()));
        scenarios.put("scenario_11", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), " ", generateName(), generateSecurityCode()));
        scenarios.put("scenario_12", new UserCardInfo("4444 4444 4444 4441", generateDate(-31, "MM"), generateDate(0, "yy"), generateName(), generateSecurityCode()));
        scenarios.put("scenario_13", new UserCardInfo("4444 4444 4444 4441", generateDate(31, "MM"), generateDate(356 * 5, "yy"), generateName(), generateSecurityCode()));
        scenarios.put("scenario_14", new UserCardInfo("4444 4444 4444 4441", "00", generateDate(0, "yy"), generateName(), generateSecurityCode()));
        scenarios.put("scenario_15", new UserCardInfo("4444 4444 4444 4441", "13", generateDate(0, "yy"), generateName(), generateSecurityCode()));
        scenarios.put("scenario_16", new UserCardInfo("4444 4444 4444 4441", " ", generateDate(0, "yy"), generateName(), generateSecurityCode()));
        scenarios.put("scenario_17", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), generateDate(0, "yy"), "V B", generateSecurityCode()));
        scenarios.put("scenario_18", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), generateDate(0, "yy"), "Pa$vel Krutoy", generateSecurityCode()));
        scenarios.put("scenario_19", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), generateDate(0, "yy"), "Pavel777 Krutoy", generateSecurityCode()));
        scenarios.put("scenario_20", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), generateDate(0, "yy"), "PavelKrutoy", generateSecurityCode()));
        scenarios.put("scenario_21", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), generateDate(0, "yy"), "-Pavel Krutoy", generateSecurityCode()));
        scenarios.put("scenario_22", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), generateDate(0, "yy"), "Pavel- Krutoy", generateSecurityCode()));
        scenarios.put("scenario_23", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), generateDate(0, "yy"), "Pavel -Krutoy", generateSecurityCode()));
        scenarios.put("scenario_24", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), generateDate(0, "yy"), "Pavel Krutoy-", generateSecurityCode()));
        scenarios.put("scenario_25", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), generateDate(0, "yy"), " ", generateSecurityCode()));
        scenarios.put("scenario_26", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), generateDate(0, "yy"), generateName(), "01"));
        scenarios.put("scenario_27", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), generateDate(0, "yy"), generateName(), "000"));
        scenarios.put("scenario_28", new UserCardInfo("4444 4444 4444 4441", generateDate(0, "MM"), generateDate(0, "yy"), generateName(), " "));
    }

    public static UserCardInfo generateUser(String scenario) {
        return scenarios.get(scenario);
    }

    @Value
    public static class ExpectedData {
        String newRowsDB; //комбинация для проверки была ли новая запись в таблицы БД credit_request_entity, payment_entity, order_entity (0/1 - нет/да, пример: записей не было - "000")
        String price;     // декларированная стоимость
        String status;    // принято/отклонено - "APPROVED"/"DECLINED"
    }

    public static ExpectedData validDataToBay() {
        return new ExpectedData("011", "45000", "APPROVED");
    }

    public static ExpectedData validDataOnCredit() {
        return new ExpectedData("111", "45000", "APPROVED");
    }

    public static ExpectedData notValidDataToBay() {
        return new ExpectedData("011", "0", "DECLINED");
    }

    public static ExpectedData notValidDataOnCredit() {
        return new ExpectedData("101", "", "DECLINED");
    }

    public static ExpectedData notValidInfo() {
        return new ExpectedData("000", "", "");
    }
}
