package lab3;

import java.util.*;

public class TspPositionalSolution {
    private int[] route;

    public int getDimension() {
        return route.length;
    }

//    public int getRowIndex(int columnIndex) {
//        return rowIndexes[columnIndex];
//    }

//    public int[] getRowIndexes() {
//        // used in tests only
//        return rowIndexes;
//    }

    //? Почему protected?
    protected TspPositionalSolution(int dimension, Random random) {
        route = new int[dimension];

        for(int i = 0; i < dimension; i++) {
            route[i] = random.nextInt(dimension - i);
        }
    }

    public int[] getPathRepresentation() {
        int[] pathRepresentation = new int[route.length];
        List<Integer> indexes = new ArrayList<Integer>(route.length);
        for (int i = 0; i < route.length; i++) {
            indexes.add(i);
        }

        for (int i = 0; i < route.length; i++) {
            int cur_index = route[i];
            pathRepresentation[i] = indexes.get(cur_index);
            indexes.remove(cur_index);
        }

        return pathRepresentation;
    }

    protected TspPositionalSolution(int [] route) {
        // used in tests only
        this.route = route;
    }

    public void set(int i, int val) {
        route[i] = val;
    }

    public int get(int i) {
        return route[i];
    }

    public TspPositionalSolution(TspPositionalSolution s) {
        int dimension = s.route.length;
        route = new int[dimension];
        System.arraycopy(s.route, 0, route, 0, dimension);
    }

//
//
//    public void swapIndexes(int i, int j) {
//        ArrayUtil.swap(rowIndexes, i, j);
//    }
//
//    public void insertIAfterJ(int i, int j) {
//        int valToInsert = rowIndexes[i];
//        ArrayUtil.moveLeft(rowIndexes, i+1);
//
//        if(i < j) {
//            j-=1;
//        }
//
//        ArrayUtil.moveRight(rowIndexes,  j+1);
//        rowIndexes[j+1] = valToInsert;
//    }

//    public void invertRegion(int start, int end) {
////        System.out.println("inversion start, end: " + start + " ," + end);
////        System.out.println("before invertRegion: " + Arrays.toString(rowIndexes));
//        int subArrayLen = end-start+1;
//        for (int i = 0; i < subArrayLen / 2; i++) {
//            ArrayUtil.swap(rowIndexes, start + i, end - i);
//        }
////        System.out.println("after invertRegion: " + Arrays.toString(rowIndexes));
//    }
//
//
//    public void scrambleRegion(int start, int end) {
//        int[] subArray = Arrays.copyOfRange(rowIndexes, start, end + 1);
//        ArrayUtil.shuffle(subArray);
//        for (int i = start; i <= end; i++) {
//            rowIndexes[i] = subArray[i - start];
//        }
//    }


    @Override
    public String toString() {
        String representation = "";

        int[] pathRepresentation = getPathRepresentation();

        for(int i = 0; i < route.length; i++) {
            representation += pathRepresentation[i] + " ";
        }
        return representation;
    }

    public void checkSainity() {
        for (int i = 0; i < route.length; i++) {
            if(route[i] < 0 || route[i] >= route.length - i) {
                String msg = String.format("element %d in position %d is invalid", route[i], i);
                throw new AssertionError(msg);
            }
        }
    }

//
//    public static TspPositionalSolution orderedCrossover(TspPositionalSolution p1, TspPositionalSolution p2, int start, int end) {
//        TspPositionalSolution child = new TspPositionalSolution(p1);
//
//        Set<Integer> already_used_city_indexes_set = new HashSet<Integer>();
//        for (int i = start; i < end + 1; i++) {
//            already_used_city_indexes_set.add(p1.getRowIndex(i));
//        }
//
//        int dimension = p1.getDimension();
//
//        int p2End = (end + 1) % dimension;
//        int childIndex, p2Index;
//        p2Index = childIndex = p2End;
//
//        do{
//            int r2v = p2.getRowIndex(p2Index);
//            if (!already_used_city_indexes_set.contains(r2v)) {
//                child.rowIndexes[childIndex] = r2v;
//                childIndex = (childIndex + 1) % dimension;
//            }
//            p2Index = (p2Index + 1) % dimension;
//        } while (p2Index != p2End);
//
//        return child;
//
//    }
//
//    public static TspPositionalSolution orderedCrossover(TspPositionalSolution p1, TspPositionalSolution p2, Random random) {
//        IndexPair iPair = IndexPair.getRandomOrderedIndexPair( p1.getDimension(), random);
//        return orderedCrossover(p1,  p2, iPair.i,  iPair.j);
//    }
}
