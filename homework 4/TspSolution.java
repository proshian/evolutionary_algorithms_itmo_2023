package lab3;

import java.util.ArrayList;
import java.util.Collections;

public class TspSolution {
    private ArrayList<Integer> route;

    protected TspSolution(int cities_num) {
        /**
         * Initialize Route attribute with an ArrayList
         * that contains a random permutation
         * of integers in range [1, cities_num]
         */

        route = new ArrayList<Integer>();

        for(int i = 0; i < cities_num; i++) {
            route.add(i);
        }

        Collections.shuffle(route);
    }


    protected TspSolution(ArrayList<Integer> route) {
        this.route = route;
    }

//        public void setRoute() {
//
//    }

    public ArrayList<Integer> getRoute() {
        return route;
    }

    public void swapIndexes(int i, int j) {
        int i_val = route.get(i);
        route.set(i, route.get(j));
        route.set(j, i_val);
    }

//    public void invertSubsolution(int i, int j) {
//    }

//    private getInvertedSubarray()


    @Override
    public String toString() {
        String representation = "";

        for(int i = 0; i < route.size(); i++) {
            representation += route.get(i) + " ";
        }

        return representation;
    }

    public int getCitiesNum() {
        return route.size();
    }




}
