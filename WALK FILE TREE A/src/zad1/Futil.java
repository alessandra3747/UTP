package zad1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class Futil {

    public static void processDir(String dirName, String resultFileName)   {

        Path startDir = Paths.get(dirName);
        Path resultFile = Paths.get(resultFileName);

        try (BufferedWriter bw = Files.newBufferedWriter(resultFile, StandardCharsets.UTF_8)){

            Files.walkFileTree(startDir, new SimpleFileVisitor<Path>() {

                public FileVisitResult visitFile(Path file, BasicFileAttributes a){

                    processFile(file, bw);

                    return FileVisitResult.CONTINUE;
                }

            });


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void processFile(Path file, BufferedWriter fw) {

        try {

            List<String> lines = Files.readAllLines(file, Charset.forName("CP1250"));

            for(String line : lines){
                fw.write(line);
            }


        } catch (IOException e) {
            throw new RuntimeException("Cannot process file " + file, e);
        }

    }

}
