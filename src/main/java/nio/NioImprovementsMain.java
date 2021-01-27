package nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

public class NioImprovementsMain {

    public static void main(String[] args) throws IOException {
        String homePath = System.getProperty("user.home");
        Path path = Paths.get(homePath);
        printFileProperties(path);
        createFiles(homePath);
        walkFiles(path);
        findFiles(path);
    }

    private static void printFileProperties(Path path) {
        System.out.println(String.format("Properties for '%s'", path));
        System.out.println(String.format("  exists: %s", Files.exists(path)));
        System.out.println(String.format("  isExecutable: %s", Files.isExecutable(path)));
        System.out.println(String.format("  isDirectory: %s", Files.isDirectory(path)));
        System.out.println(String.format("  isRegularFile: %s", Files.isRegularFile(path)));
        System.out.println("------------------------------------------------------------------");
        System.out.println();
    }

    private static void createFiles(String homePath) throws IOException {
        // Create directory
        String directoryPath = homePath + "/myDirectory_" + UUID.randomUUID().toString();
        Path directory = Paths.get(directoryPath);
        Files.createDirectory(directory);
        printFileProperties(directory);

        // Create a file
        String filePath = directoryPath + "/myfile_" + UUID.randomUUID().toString() + ".txt";
        Path file = Paths.get(filePath);
        Files.createFile(file);
        Files.write(file, "First line in a file\n".getBytes());
        Files.write(file, "Second line in a file\n".getBytes(), StandardOpenOption.APPEND);
        printFileProperties(file);

        // Copy the file
        String filePath2 = directoryPath + "/myfile_" + UUID.randomUUID().toString() + ".txt";
        Path file2 = Paths.get(filePath2);
        Files.copy(file, file2);
        Files.write(file2, "Third line in a file".getBytes(), StandardOpenOption.APPEND);
        printFileProperties(file);

        // Read copied file
        printFileContent(file2);

        // Delete all files under directory
        try (Stream<Path> stream = Files.list(directory)) {
            stream.forEach(subPath -> {
                try {
                    Files.delete(subPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        // Delete directory
        Files.delete(directory);
    }

    private static void printFileContent(Path file) throws IOException {
        System.out.println(String.format("Content of file %s:", file));
        try (Stream<String> stream = Files.lines(file)) {
            stream.map(String::toUpperCase)
                    .forEach(System.out::println);
        }
        System.out.println();
    }

    private static void walkFiles(Path basePath) throws IOException {
        System.out.println(String.format("Walk through all files under'%s', not starting with '.':", basePath));
        try (Stream<Path> stream = Files.walk(basePath, 1)) {
            stream.filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(Objects::nonNull)
                    .filter(path -> !path.startsWith("."))
                    .forEach(System.out::println);
        }
        System.out.println();
    }

    private static void findFiles(Path basePath) throws IOException {
        System.out.println(String.format("Find all files under'%s', not starting with '.':", basePath));
        BiPredicate<Path, BasicFileAttributes> matcher = (path, fileAttrs) -> Files.isRegularFile(path) && fileAttrs.size() > 100_000;
        try (Stream<Path> stream = Files.find(basePath, 1, matcher)) {
            stream.filter(Files::isRegularFile)
                    .forEach(System.out::println);
        }
        System.out.println();
    }
}
