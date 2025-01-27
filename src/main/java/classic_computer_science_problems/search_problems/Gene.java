package classic_computer_science_problems.search_problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Gene {

    public enum Nucleotide {
        A, C, G, T;
    }
    public static class Codon implements Comparable<Codon> {

        public final Nucleotide first, second, third;
        private final Comparator<Codon> comparator = Comparator.comparing((Codon c) -> c.first)
                .thenComparing((Codon c) -> c.second)
                .thenComparing((Codon c) -> c.third);
        public Codon(String codonStr) {
            this.first = Nucleotide.valueOf(codonStr.substring(0, 1));
            this.second = Nucleotide.valueOf(codonStr.substring(1, 2));
            this.third = Nucleotide.valueOf(codonStr.substring(2, 3));
        }

        @Override
        public int compareTo(Codon o) {
            return comparator.compare(this, o);
        }

    }

    private ArrayList<Codon> codons = new ArrayList<>();

    public Gene(String geneStr) {
        for (int i = 0; i < geneStr.length() - 3; i += 3) {
            codons.add(new Codon(geneStr.substring(i, i + 3)));
        }
    }

    public boolean linearContains(Codon key) {
        for (Codon codon : codons) {
            if (codon.compareTo(key) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean binaryContains(Codon key) {
        //copy original codons to avoid modifying the original codons
        ArrayList<Codon> sortedCodons = new ArrayList<>(codons);
        //sort the codons
        Collections.sort(sortedCodons);
        //binary search start
        int low = 0;
        int high = sortedCodons.size() - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            int comparison = sortedCodons.get(middle).compareTo(key);
            if (comparison < 0) { // key is before the middle element
                low = middle + 1;
            } else if (comparison > 0) { //key is after the middle element
                high = middle - 1;
            } else { // middle codon is equal to key
                return true;
            }
        }
        return false;
    }
}
