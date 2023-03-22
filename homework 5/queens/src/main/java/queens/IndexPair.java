package queens;

import java.util.Random;

class IndexPair {
    int i;
    int j;

    public IndexPair(int i, int j) {
        this.i = i;
        this.j = j;
    }

    static IndexPair getRandomOrderedIndexPair(int range, Random random) {
        IndexPair indexPair = getRandomIndexPair(range, random);

        if (indexPair.i < indexPair.j) {
            return indexPair;
        }

        else {
            return new IndexPair(indexPair.j, indexPair.i);
        }
    }

    static IndexPair getRandomIndexPair(int range, Random random) {
        int i = random.nextInt(range);

        int j = random.nextInt(range);

        while (j == i) {
            j = random.nextInt(range);
        }

        return new IndexPair(i,j);
    }
}