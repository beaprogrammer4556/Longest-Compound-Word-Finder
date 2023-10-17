import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class LongestCompoundWordFinderr {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        String fileName = "Input_01.txt"; //for text file 1
        //String fileName = "Input_02.txt"; //for text file 2
        String longestCompoundWord = "";
        String secondLongestCompoundWord = "";
        Set<String> wordsSet = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    wordsSet.add(word);
                }
            }
        }

        for (String word : wordsSet) {
            if (isCompoundWord(word, wordsSet)) {
                if (word.length() > longestCompoundWord.length()) {
                    secondLongestCompoundWord = longestCompoundWord;
                    longestCompoundWord = word;
                } else if (word.length() > secondLongestCompoundWord.length()) {
                    secondLongestCompoundWord = word;
                }
            }
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Longest compound word: " + longestCompoundWord);
        System.out.println("Second longest compound word: " + secondLongestCompoundWord);
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds");
    }

    private static boolean isCompoundWord(String word, Set<String> wordsSet) {
        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            String suffix = word.substring(i);

            if (wordsSet.contains(prefix) && (wordsSet.contains(suffix) || isCompoundWord(suffix, wordsSet))) {
                return true;
            }
        }
        return false;
    }
}
