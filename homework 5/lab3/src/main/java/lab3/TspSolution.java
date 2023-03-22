package lab3;

import java.util.*;

public class TspSolution {
    private int[] rowIndexes;

    public int getDimension() {
        return rowIndexes.length;
    }

    public int getRowIndex(int columnIndex) {
        return rowIndexes[columnIndex];
    }


    //? Почему protected?
    protected TspSolution(int dimension) {
        rowIndexes = new int[dimension];

        for(int i = 0; i < dimension; i++) {
            rowIndexes[i] = i;
        }
        ArrayUtil.shuffle(rowIndexes);
    }

    public TspSolution(TspSolution s) {
        int dimension = s.rowIndexes.length;
        rowIndexes = new int[dimension];
        System.arraycopy(s.rowIndexes, 0, rowIndexes, 0, dimension);
    }


    public void swapIndexes(int i, int j) {
        ArrayUtil.swap(rowIndexes, i, j);
    }

    public void insertIAfterJ(int i, int j) {
        int valToInsert = rowIndexes[i];
        ArrayUtil.moveLeft(rowIndexes, i+1);

        if(i < j) {
            j-=1;
        }

        ArrayUtil.moveRight(rowIndexes,  j+1);
        rowIndexes[j+1] = valToInsert;
    }

    public void invertRegion(int start, int end) {
//        System.out.println("inversion start, end: " + start + " ," + end);
//        System.out.println("before invertRegion: " + Arrays.toString(rowIndexes));
        int subArrayLen = end-start+1;
        for (int i = 0; i < subArrayLen / 2; i++) {
            ArrayUtil.swap(rowIndexes, start + i, end - i);
        }
//        System.out.println("after invertRegion: " + Arrays.toString(rowIndexes));
    }


    public void scrambleRegion(int start, int end) {
        int[] subArray = Arrays.copyOfRange(rowIndexes, start, end + 1);
        ArrayUtil.shuffle(subArray);
        for (int i = start; i <= end; i++) {
            rowIndexes[i] = subArray[i - start];
        }
    }


    @Override
    public String toString() {
        String representation = "";

        for(int i = 0; i < rowIndexes.length; i++) {
            representation += rowIndexes[i] + " ";
        }
        return representation;
    }

    public void checkAllRowIndexesPresent() {

        // if while iterating over this.rowIndexes we already encountered a row_index i, rowIndexEncountered[i] will
        // contain "true" otherwise - "false".
        boolean[] rowIndexEncountered = new boolean[rowIndexes.length];

        for (int i =0; i < rowIndexes.length; i++) {
            rowIndexEncountered[i] = false;
        }

        for (int i = 0; i < rowIndexes.length; i++) {
            int rowIndex = rowIndexes[i];
            if (rowIndexEncountered[rowIndex]) {
                throw new AssertionError("rowIndex " + rowIndexes + " is present at least twice in the solution");
            }
            else {
                rowIndexEncountered[rowIndex] = true;
            }
        }
    }





    public static TspSolution orderedCrossover(TspSolution p1, TspSolution p2, int start, int end) {
        TspSolution child = new TspSolution(p1);

        Set<Integer> already_used_city_indexes_set = new HashSet<Integer>();
        for (int i = start; i < end + 1; i++) {
            already_used_city_indexes_set.add(p1.getRowIndex(i));
        }

        int dimension = p1.getDimension();

        int p2End = (end + 1) % dimension;
        int childIndex, p2Index;
        p2Index = childIndex = p2End;

        do{
            int r2v = p2.getRowIndex(p2Index);
            if (!already_used_city_indexes_set.contains(r2v)) {
                child.rowIndexes[childIndex] = r2v;
                childIndex = (childIndex + 1) % dimension;
            }
            p2Index = (p2Index + 1) % dimension;
        } while (p2Index != p2End);

        return child;

    }

    public static TspSolution orderedCrossover(TspSolution p1, TspSolution p2, Random random) {
        IndexPair iPair = IndexPair.getRandomIndexPair( p1.getDimension(), random);
        return orderedCrossover(p1,  p2, iPair.smaller,  iPair.bigger);
    }






//    private int[] getInvertedSubArray(int start, int end) {
//        int subArrayLen = end-start+1;
//        int[] invertedSubArray = new int[subArrayLen];
//        for (int i = 0; i < subArrayLen; i++) {
//            invertedSubArray[subArrayLen-1-i] = rowIndexes[start + i];
//        }
//        return invertedSubArray;
//    }



//    protected TspSolution(ArrayList<Integer> route) {
//        this.route = route;
//    }
//        public void setRoute() {
//
//    }

//    public ArrayList<Integer> getRoute() {
//        return route;
//    }


//    private ArrayList<Integer> getSubArray(int start, int end) {
//        ArrayList<Integer> subArray =  new ArrayList<Integer>();
//        for (int i = start; i <= end; i++) {
//            subArray.add(rowIndexes.[i]);
//        }
//        return subArray;
//    }


}
