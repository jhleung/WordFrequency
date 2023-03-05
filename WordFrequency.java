import java.util.*;
public class WordFrequency {
    public static void main (String [] args) {
        System.out.println("Test case results: \n");
        testHappy();
        testHappy2();
        testEmpty();
        testNull();
        testMultiLine();

        try {
            System.out.println("\nWordFrequency of \"" + args[0] + ":\n");
            printWordFrequencies(args[0]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No input sentence given");
        }
       
    }

    public static void printWordFrequencies(String sentence) {
        mapWordToFrequency(sentence)
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .forEach(entry -> System.out.println(entry.getValue() + " " + entry.getKey() + "\n"));
    }

    /*
    * @param sentence an input in the format of a single one line string
    * @return returns a map of words and their frequency in string format
    */
    public static Map<String, Integer> mapWordToFrequency(String sentence) {
        Map<String, Integer> wordToCount = new HashMap<>();

        if (sentence != null && !sentence.equals("")) {
             Arrays.stream(sentence.split(" "))
            .forEach(word -> wordToCount.merge(word, 1, Integer::sum));
        }
       
        return wordToCount;
    }

    private static void testHappy() {
        Map<String, Integer> actual = mapWordToFrequency("the cat is in the bag");

        Map<String, Integer> expected = new HashMap();
        expected.put("the", 2);
        expected.put("cat", 1);
        expected.put("is", 1);
        expected.put("in", 1);
        expected.put("bag", 1);

        System.out.println("Happy path test case pass: " + actual.equals(expected) + "\n");

    }

    private static void testHappy2() {
        Map<String, Integer> actual = mapWordToFrequency("the cat in the hat is in the hat bag");

        Map<String, Integer> expected = new HashMap();
        expected.put("the", 3);
        expected.put("cat", 1);
        expected.put("hat", 2);
        expected.put("is", 1);
        expected.put("in", 2);
        expected.put("bag", 1);

        System.out.println("Happy path 2 test case pass: " + actual.equals(expected) + "\n");

    }

    private static void testEmpty() {
        Map<String, Integer> actual = mapWordToFrequency("");

        Map<String, Integer> expected = new HashMap();

        System.out.println("Null test case pass: " + actual.equals(expected) + "\n");

    }

    private static void testNull() {
        Map<String, Integer> actual = mapWordToFrequency(null);

        Map<String, Integer> expected = new HashMap();

        System.out.println("Empty string test case pass: " + actual.equals(expected) + "\n");

    }

    private static void testMultiLine() {
        // tests that if we input a multi line string, the map result will not correctly map words to counts
        Map<String, Integer> actual = mapWordToFrequency("the cat is in the bag\nthe dog is not");

        Map<String, Integer> expected = new HashMap();
        expected.put("the", 3);
        expected.put("dog", 1);
        expected.put("cat", 1);
        expected.put("is", 2);
        expected.put("in", 1);
        expected.put("not", 1);
        expected.put("bag", 1);

        System.out.println("Multi line string test case pass: " + !actual.equals(expected) + "\n");
    }
}