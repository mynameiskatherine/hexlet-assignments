package exercise;

import java.util.*;

import net.datafaker.Faker;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class Data {
    private static final int ITEMS_COUNT = 30;

    private static int idCounter = ITEMS_COUNT;

    public static List<Map<String, String>> getCompanies() {
        Random random = new Random(123);
        Faker faker = new Faker(new Locale("en"), random);

        List<String> ids = IntStream
            .range(1, ITEMS_COUNT + 1)
            .mapToObj(i -> Integer.toString(i))
            .collect(Collectors.toList());
        Collections.shuffle(ids, random);

        List<Map<String, String>> companies = new ArrayList<>();

        for (int i = 0; i < ITEMS_COUNT; i++) {
            Map<String, String> company = new HashMap<>();
            company.put("id", ids.get(i));
            company.put("name", faker.company().name());
            company.put("phone", faker.phoneNumber().phoneNumber());
            companies.add(company);
        }

        return companies;
    }

    public static String getNextId() {
        int id = ++idCounter;
        return Integer.toString(id);
    }

    public static Map<String, String> findCompanyById(String id) throws NoSuchElementException {
        List<Map<String, String>> list = getCompanies();
        var company = list.stream()
                .filter(m -> m.get("id").equals(id))
                .findFirst();
        try {
            return company.get();
        } catch (Exception e) {
            throw new NoSuchElementException(e);
        }
    }
}
