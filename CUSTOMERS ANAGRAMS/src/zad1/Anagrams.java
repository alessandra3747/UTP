/**
 *
 *  @author Fus Aleksandra S30395
 *
 */

package zad1;


import java.io.File;
import java.io.IOException;
import java.util.*;


public class Anagrams {

    private Map<String,List<String>> anagrams;


    public Anagrams(String wordsFile){

        this.anagrams = new HashMap<>();

        try {

            Scanner scanner = new Scanner(new File(wordsFile));

            while (scanner.hasNext()) {

                String word = scanner.next();

                String sortedWord = sortWord(word);

                anagrams.putIfAbsent(sortedWord, new ArrayList<>());
                anagrams.get(sortedWord).add(word);

            }

            scanner.close();

        } catch(IOException e) {
            System.out.println("Error while loading file");
        }

    }

    public List<List<String>> getSortedByAnQty(){

        List<List<String>> resultAnagrams = new ArrayList<>();

        for(List<String> words : anagrams.values())
            resultAnagrams.add(words);

        resultAnagrams.sort((s1, s2) -> {
            if(Integer.compare(s1.size(), s2.size()) == 0)
                return -s1.get(0).compareTo(s2.get(0));
            return -Integer.compare(s1.size(), s2.size());
        });


        return resultAnagrams;
    }


    public String getAnagramsFor (String inputWord){

        StringBuilder result = new StringBuilder(inputWord + ": ");

        String sortedWord = sortWord(inputWord);

        for(String key : anagrams.keySet()) {

            if(key.equals(sortedWord)){
                List<String> tmp = anagrams.get(key);
                tmp.remove(inputWord);
                result.append(tmp);
            }

        }

        return result.toString();
    }

    private String sortWord(String word) {
        String sortedWord;

        char[] chars = word.toCharArray();
        Arrays.sort(chars);

        return new String(chars);
    }
}  
