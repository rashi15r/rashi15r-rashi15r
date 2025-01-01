import java.util.*;
import java.util.regex.*;

class DFA {
    private Set<Integer> states;
    private Set<Character> inputSymbols;
    private Map<Integer, Map<Character, Integer>> transitions;
    private int initialState;
    private Set<Integer> acceptingStates;

    public DFA(Set<Integer> states, Set<Character> inputSymbols, Map<Integer, Map<Character, Integer>> transitions, int initialState, Set<Integer> acceptingStates) {
        this.states = states;
        this.inputSymbols = inputSymbols;
        this.transitions = transitions;
        this.initialState = initialState;
        this.acceptingStates = acceptingStates;
    }

    public boolean accepts(String string) {
        int currentState = initialState;

        for (char symbol : string.toCharArray()) {
            if (!inputSymbols.contains(symbol)) {
                return false; 
            }
            currentState = transitions.get(currentState).get(symbol);
        }

        return acceptingStates.contains(currentState);
    }
}

public class Main {

    public static DFA dfa1() {
        Set<Integer> states = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        Set<Character> inputSymbols = new HashSet<>(Arrays.asList('a', 'b'));
        Map<Integer, Map<Character, Integer>> transitions = new HashMap<>();

        transitions.put(1, Map.of('a', 2, 'b', 3));
        transitions.put(2, Map.of('a', 1, 'b', 4));
        transitions.put(3, Map.of('a', 4, 'b', 1));
        transitions.put(4, Map.of('a', 3, 'b', 2));

        int initialState = 1;
        Set<Integer> acceptingStates = new HashSet<>(Collections.singletonList(2));

        return new DFA(states, inputSymbols, transitions, initialState, acceptingStates);
    }

    public static boolean testcase1(String string) {
        followed by 11
        String pattern = "^(1|011)*$";
        return Pattern.matches(pattern, string);
    }

    public static boolean testcase2(String string) {
        
        if (string.length() < 2 || !string.chars().allMatch(c -> "abc".indexOf(c) >= 0)) {
            return false;
        }
        return string.charAt(0) == string.charAt(string.length() - 1);
    }

    public static boolean testcase3(String string) {
      
        if (string.isEmpty() || !Character.isLetter(string.charAt(0))) {
            return false;
        }
        return string.chars().allMatch(c -> Character.isLetterOrDigit(c));
    }

    public static void main(String[] args) {
        // DFA Testing
        DFA dfa = dfa1();
        List<String> testStringsDfa = Arrays.asList("aab", "abba", "bbaa", "aa");
        System.out.println("DFA Testing Results:");
        for (String s : testStringsDfa) {
            System.out.println("String '" + s + "' accepted by DFA? " + dfa.accepts(s));
        }

        // Testcase 1 Testing
        System.out.println("\nTestcase 1 Results:");
        List<String> testStringsTc1 = Arrays.asList("011", "1011", "111", "011011");
        for (String s : testStringsTc1) {
            System.out.println("String '" + s + "' valid? " + testcase1(s));
        }

        // Testcase 2 Testing
        System.out.println("\nTestcase 2 Results:");
        List<String> testStringsTc2 = Arrays.asList("ab", "aa", "bccb", "abcba");
        for (String s : testStringsTc2) {
            System.out.println("String '" + s + "' valid? " + testcase2(s));
        }

        // Testcase 3 Testing
        System.out.println("\nTestcase 3 Results:");
        List<String> testStringsTc3 = Arrays.asList("abc123", "123abc", "a1b2c3", "abc");
        for (String s : testStringsTc3) {
            System.out.println("String '" + s + "' valid? " + testcase3(s));
        }
    }
}
