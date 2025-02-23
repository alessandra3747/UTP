package zad2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

public class Futil {

    public static void processDir(String dirName, String resultFileName)   {

        Path startDir = Paths.get(dirName);
        Path resultFile = Paths.get(resultFileName);

        try {
            Files.deleteIfExists(resultFile);
            Files.createFile(resultFile);


            try (Stream<Path> paths = Files.walk(startDir)
                    .filter(Files::isRegularFile)){

                paths.forEach(path -> {
                    try {
                        List<String> lines = Files.readAllLines(path, Charset.forName("CP1250"));
                        Files.write(resultFile, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        System.out.println("Error reading/writing file " + path);
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            throw new RuntimeException("Error creating/deleting res file", e);
        }

    }



}
