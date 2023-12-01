package exercise;

import lombok.SneakyThrows;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Arrays;

public class App {

    @SneakyThrows
    public static void save(Path filePath, exercise.Car car) {
        String json = car.serialize();
        try (OutputStream output = Files.newOutputStream(filePath)) {
            output.write(json.getBytes());
        }
    }

    @SneakyThrows
    public static exercise.Car extract(Path filePath) {
        String json = new String();
        try (InputStream input = Files.newInputStream(filePath)) {
            byte[] buffer = input.readAllBytes();
            json = new String(buffer);
        }
        return exercise.Car.unserialize(json);
    }
}