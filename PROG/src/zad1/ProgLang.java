package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang {

    private Map<String, Set<String>> progsMap;
    private Map<String, Set<String>> langsMap;


    public ProgLang (String path) {

        this.progsMap = new LinkedHashMap<String, Set<String>>();
        this.langsMap = new LinkedHashMap<String, Set<String>>();

        try {
            Scanner sc = new Scanner (new File(path));

            while(sc.hasNextLine()) {

                String[] tmp = sc.nextLine().split("\t");

                Set<String> programmers = new LinkedHashSet<>(Arrays.asList(tmp).subList(1, tmp.length));

                langsMap.put(tmp[0], programmers);

                for(String programmer : programmers)
                    progsMap.computeIfAbsent(programmer, k -> new LinkedHashSet<>()).add(tmp[0]);

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public Map<String, Set<String>> getLangsMap() {
        return langsMap;
    }

    public Map<String, Set<String>> getProgsMap() {
        return progsMap;
    }


    public Map<String, Set<String>> getLangsMapSortedByNumOfProgs() {
        return sorted(langsMap, Comparator.comparing( (Map.Entry<String, Set<String>> e) -> e.getValue().size() )
                .reversed()
                .thenComparing(Map.Entry::getKey));
    }


    public Map<String, Set<String>> getProgsMapSortedByNumOfLangs() {
        return sorted(progsMap, Comparator.comparing( (Map.Entry<String, Set<String>> e) -> e.getValue().size() )
                .reversed()
                .thenComparing(Map.Entry::getKey));
    }


    public Map<String, Set<String>> getProgsMapForNumOfLangsGreaterThan(int n) {
        return filtered(progsMap, e -> e.getValue().size() > n);
    }



    public <K,V> Map<K,V> sorted (Map<K,V> inputMap, Comparator<Map.Entry<K,V>> comparator) {
        return inputMap.entrySet().stream()
                .sorted(comparator)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }


    public <K,V> Map<K,V> filtered (Map<K,V> inputMap, Predicate<Map.Entry<K,V>> predicate) {
        return inputMap.entrySet().stream()
                .filter(predicate)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }


}
