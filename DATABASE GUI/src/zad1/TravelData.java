package zad1;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class TravelData {

    private ArrayList<Offer> offers;


    public TravelData(File dataDir) {

        this.offers = new ArrayList<>();

        try {

            Files.walkFileTree(dataDir.toPath(), new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if(attrs.isRegularFile()) {

                        for(String line : Files.readAllLines(file)) {
                            String[] parts = line.split("\t");
                            if(parts.length == 7)
                                offers.add(new Offer(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]));
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }

            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public List<String> getOffersDescriptionsList (String locale, String dateFormat) {
        List<String> offersDescriptions = new ArrayList<>();

        for(Offer offer : offers)
            offersDescriptions.add(offer.getDescription(locale, dateFormat));

        return offersDescriptions;
    }


    public List<Offer> getOffers () {
        return this.offers;
    }
}
