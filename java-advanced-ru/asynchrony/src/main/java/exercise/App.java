package exercise;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String sourceFile1, String sourceFile2, String resultFile)
            throws ExecutionException, InterruptedException {
        CompletableFuture<byte[]> futureReadFile1 = CompletableFuture.supplyAsync(() -> {
            Path pathSourceFile1 = getPath(sourceFile1);
            byte[] buffer1 = new byte[0];
            try {
                buffer1 = Files.readAllBytes(pathSourceFile1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return buffer1;
        }).exceptionally(ex -> {
            System.out.println("NoSuchFileException");
            return null;
        });

        CompletableFuture<byte[]> futureReadFile2 = CompletableFuture.supplyAsync(() -> {
            Path pathSourceFile2 = getPath(sourceFile2);
            byte[] buffer2;
            try {
                buffer2 = Files.readAllBytes(pathSourceFile2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return buffer2;
        }).exceptionally(ex -> {
            System.out.println("NoSuchFileException");
            return null;
        });

        CompletableFuture<String> futureWriteFile = futureReadFile1.thenCombine(futureReadFile2, (buffer1, buffer2) -> {
            Path pathResultFile = getPath(resultFile);
            try (OutputStream out = Files.newOutputStream(pathResultFile)) {
                out.write(buffer1);
                out.write(buffer2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return resultFile;
        }).exceptionally(ex -> {
            System.out.println("Something went wrong in futureWriteFile");
            return null;
        });
        return futureWriteFile;
    }

    public static CompletableFuture<Long> getDirectorySize(String dir) {
        Path path = getPath(dir);
        CompletableFuture<Long> futureSize = CompletableFuture.supplyAsync(() -> {
            Long size = 0L;
            try {
                size = Files.walk(path, 1)
                        .filter(Files::isRegularFile)
                        .mapToLong(f -> {
                            try {
                                return Files.size(f);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .sum();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return size;
        });
        return futureSize;
    }

    private static Path getPath(String filepath) {
        return Paths.get(filepath).toAbsolutePath().normalize();
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = App.unionFiles("asynchrony/src/main/resources/file1.txt",
                "src/main/resources/file2.txt", "asynchrony/src/main/resources/dest.txt");
        CompletableFuture<Long> size = App.getDirectorySize("asynchrony/src/main/resources/");
        System.out.println(size.get());
        // END
    }
}

