package classic_computer_science_problems.search_problems;

import org.junit.jupiter.api.Test;

import static classic_computer_science_problems.search_problems.Gene.*;
import static org.junit.jupiter.api.Assertions.*;

class GeneTest {

    @Test
    void testCodonComparison() {
        String geneStr = "ACGTGGCTCTCTAACGTACGTACGTACGGGGTTTATATATACCCTAGGACTCCCTTT";
        Gene myGene = new Gene(geneStr);
        Codon acg = new Codon("ACG");
        Codon gat = new Codon("GAT");
        assertTrue(myGene.linearContains(acg));
        assertFalse(myGene.linearContains(gat));
        assertTrue(myGene.binaryContains(acg));
        assertFalse(myGene.binaryContains(gat));
    }

}
