package co.marcuss.acct.cmd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {
    /*****
     * Java-Challenge
     * {
     *   "array": [7, 6, 4, -1, 1, 2],
     *   "targetSum": 16
     * }
     * Output
     * [
     *   [7, 6, 4, -1],
     *   [7, 6, 1, 2]
     * ]
     */
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
        fourNumberSum(array, 16).forEach(e -> System.out.println(Arrays.toString(e)));
    }

    public static List<Integer[]> fourNumberSum(int[] array, int targetSum) {
        //fail fast
        if (array.length < 4) return new ArrayList<>();
        //always start by sorting things up
        Arrays.sort(array);
        List<Integer[]> solutions = new ArrayList<>();
        for (int i = 0; i < array.length - 3; i++) { //first number
            for (int j = i + 1; j < array.length - 2; j++) { //second number
                for (int k = j + 1; k < array.length - 1; k++) { //third number
                    for (int l = array.length - 1; l > k; l--) { //four number goes against the rest
                        if (array[i] + array[j] + array[k] + array[l] == targetSum) {
                            solutions.add(new Integer[]{array[i], array[j], array[k], array[l]});
                        }
                    }
                }
            }
        }

        // Write your code here.
        return solutions;
    }
}
