package ua.edu.ucu.autocomplete;

import ua.edu.ucu.collections.Queue;
import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;


/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int sizeAdded = 0;
        for (String string: strings) {
            String[] splittedString = string.split("\\s+");
            for (String str: splittedString) {
                if (str.length() > 2) {
                    Tuple t = new Tuple(str, str.length());
                    trie.add(t);
                    sizeAdded++;
                }
            }
        }
        return sizeAdded;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        Iterable<String> prefixIterator = wordsWithPrefix(pref);

        Queue queue = new Queue();
        for (String word: prefixIterator) {
            if (word.length() < pref.length() + k) {
                queue.enqueue(word);
            }
        }
        return queue;
    }

    public int size() {
        return trie.size();
    }
}
