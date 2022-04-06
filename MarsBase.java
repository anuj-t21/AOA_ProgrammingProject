
import java.util.*;

public class MarsBase {

    public static int[] task1(int[] A, int n) {
        int max_sum = Integer.MIN_VALUE;
        int left_index = 0, right_index = 0;
        for (int i = 0; i < n; i++) {
            // Loop for traversing in array
            for (int j = 0; j < n; j++) {
                // Loop for traversing for selecting a range ahead of i
                int current_sum = 0;
                for (int k = i; k <= j; k++) {
                    // Loop for ttraversing between i & j
                    current_sum += A[k]; // Calculation sum from i to j
                    if (max_sum < current_sum) {
                        // Updating max_sum and assigning indexes
                        max_sum = current_sum;
                        left_index = i;
                        right_index = k;
                    }
                }

            }
        }
        int[] result = { (left_index + 1), (right_index + 1), max_sum };
        return result;
    }

    public static int[] task2(int[] A, int n) {
        int max_sum = Integer.MIN_VALUE;
        int left_index = 0, right_index = 0;
        for (int i = 0; i < n; i++) {
            // Loop for traversing in array
            int current_sum = 0;
            for (int j = i; j < n; j++) {
                // Loop for traversing from i to last index
                current_sum += A[j]; // Calculating sum
                if (max_sum < current_sum) {
                    // Updating max_sum and assigning indexes
                    max_sum = current_sum;
                    left_index = i;
                    right_index = j;
                }
            }
        }

        int[] result = { (left_index + 1), (right_index + 1), max_sum };
        return result;
    }

    // Global variabl for 3A to be used in an outside recursive function
    private static int max_sum_3a;
    private static int left_index_3a;
    private static ArrayList<Integer> memo_3a;

    private static int memoizationTask3A(int[] A, int i, int n) {
        if (i == n) {
            // Terminating the recursive function here
            return 0;
        }

        if (memo_3a.get(i) != null) {
            return memo_3a.get(i);
        }

        // Starting recursion and assigning max in temp and setting it in memo
        int temp = Math.max(A[i], A[i] + memoizationTask3A(A, i + 1, n));
        memo_3a.set(i, temp);

        if (max_sum_3a < temp) {
            // Comparing the max_sum with temp and assigning
            max_sum_3a = temp;
            left_index_3a = i;
        }

        return temp;
    }

    public static int[] task3A(int[] A, int n) {
        int right_index_3a;

        // Generating an ArrayList
        memo_3a = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            memo_3a.add(null);
        }

        max_sum_3a = A[0];

        // Calling the recursive function
        memoizationTask3A(A, 0, n);

        right_index_3a = left_index_3a;
        for (int i = left_index_3a; i < n; i++) {
            // Loop from left index to n; for assigning right index value
            if (memo_3a.get(i) < 0) {
                break;
            } else {
                right_index_3a = i;
            }
        }

        int[] result = { (left_index_3a + 1), (right_index_3a + 1), max_sum_3a };
        return result;

    }

    public static int[] task3B(int[] A, int n) {

        // Initialising an array for dp
        int[] dp = new int[n];
        dp[0] = A[0];
        int max_sum = dp[0];

        int left_index = 0, right_index = 0;
        int left_index_temp = 0;

        for (int i = 1; i < n; i++) {
            // Loop for traversing in array
            if (dp[i - 1] > 0) {
                // Calculating dp from previous value of dp if its positive
                dp[i] = dp[i - 1] + A[i];
            } else {
                dp[i] = A[i];
                left_index_temp = i;
            }

            if (dp[i] > max_sum) {
                // Updating max_sum and assigning indexes
                left_index = left_index_temp;
                max_sum = dp[i];
                right_index = i;
            }
        }
        int[] result = { (left_index + 1), (right_index + 1), max_sum };
        return result;
    }

    public static void task4(int[][] ar, int row, int column) {
        // initializing the variable maxsum which stores the latest maxsum
        // for a given submatrix

        int maxsum = Integer.MIN_VALUE;
        int rstartm = 0;
        int cstartm = 0;
        int rendm = 0;
        int cendm = 0;

        // calculation of the maxsum
        // Iterating through every possible submatrix and calculating its sum
        // to find the maximum sum.

        for (int e = 0; e < row; e++) { // iterating through rows

            for (int f = 0; f < column; f++) {// iterating through columns

                for (int g = e; g < row; g++) {// loops for finding submatrices between index e and f

                    for (int h = f; h < column; h++) {
                        int sum = 0;
                        // loops for calculting the sum of the submatrix
                        for (int i = e; i <= g; i++) {
                            for (int j = f; j <= h; j++) {
                                // calculate the sum of current submatrix
                                sum += ar[i][j];
                            }
                        }

                        // if current sum is greater than max then update max
                        if (sum > maxsum) {
                            maxsum = sum;
                            rstartm = e;
                            cstartm = f;
                            rendm = g;
                            cendm = h;

                        }
                    }
                }
            }
        }

        // return the sum
        System.out.println((rstartm + 1) + " " + (cstartm + 1) + " " + (rendm + 1) + " " + (cendm + 1) + " " + maxsum);

    }

    static int[][] extraSpaceTask6(int[][] ar, int row, int column) {
        int[][] aux = new int[row][column];

        for (int y = 0; y < row; y++) {
            for (int z = 0; z < column; z++) {
                aux[y][z] = 0;// initializing elements to 0
            }
            // calculating prefix sum
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (j == 0) {
                        aux[i][j] = ar[i][j];
                    } else {
                        aux[i][j] = ar[i][j] + aux[i][j - 1];
                    }

                }
            }

        }
        return aux;
    }

    public static void task5(int[][] ar, int row, int column) {

        int[][] auxmat = new int[row + 1][column + 1];

        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= column; j++) {
                if (i == 0 || j == 0) {
                    auxmat[i][j] = 0;
                } else {
                    auxmat[i][j] = auxmat[i - 1][j] + auxmat[i][j - 1] - auxmat[i - 1][j - 1] + ar[i - 1][j - 1];
                }
            }
        }

        int maxsum = Integer.MIN_VALUE;
        int rstartm = 0;
        int rendm = 0;
        int cstartm = 0;
        int cendm = 0;

        for (int i = 0; i < row; i++) {
            for (int j = i; j < row; j++) {
                for (int m = 0; m < column; m++) {
                    for (int n = m; n < column; n++) {

                        int current_sum = auxmat[j + 1][n + 1] - auxmat[j + 1][m]
                                - auxmat[i][n + 1] + auxmat[i][m];

                        if (current_sum > maxsum) {
                            maxsum = current_sum;
                            rstartm = i;
                            rendm = j;
                            cstartm = m;
                            cendm = n;
                        }
                    }
                }
            }
        }

        System.out.println((rstartm + 1) + " " + (cstartm + 1) + " " + (rendm + 1) + " " + (cendm + 1) + " " + maxsum);

    }

    public static void task6(int[][] ar, int row, int column) {
        int[][] aux_mat = extraSpaceTask6(ar, row, column);

        int maxsum = Integer.MIN_VALUE;
        int rstartm = 0;
        int cstartm = 0;
        int rendm = 0;
        int cendm = 0;

        // populating the array that is going to passed to the max_sub_array function
        for (int a = 0; a < column; a++) {
            for (int b = a; b < column; b++) {
                Vector<Integer> temp_vector = new Vector<Integer>(); // initializing an int Vector
                for (int c = 0; c < row; c++) {
                    int temp = 0;

                    if (a == 0) {
                        temp = aux_mat[c][b];
                    } else {
                        temp = aux_mat[c][b] - aux_mat[c][a - 1]; // calculating the sum between indices a-1 and b
                    }

                    temp_vector.add(temp);
                }

                int temp_array[] = new int[temp_vector.size()];
                for (int i = 0; i < temp_vector.size(); i++) {
                    temp_array[i] = temp_vector.get(i);
                }

                int final_matrix[] = task3B(temp_array, temp_array.length);

                // calculating the maxsum
                if (final_matrix[2] > maxsum) {
                    maxsum = final_matrix[2];
                    rstartm = (final_matrix[0] - 1);
                    cstartm = a;
                    rendm = (final_matrix[1] - 1);
                    cendm = b;
                }
            }
        }
        System.out.println((rstartm + 1) + " " + (cstartm + 1) + " " + (rendm + 1) + " " + (cendm + 1) + " " + maxsum);
    }

    public static void main(String args[]) {

        // Running Program for Task 1, 2, 3A, 3B
        if (args[0].equals("1") || args[0].equals("2") || args[0].equals("3A") || args[0].equals("3a")
                || args[0].equals("3B") || args[0].equals("3b")) {

            int n;
            System.out.println("Enter input size: ");
            Scanner sc = new Scanner(System.in);
            n = sc.nextInt();
            int[] array = new int[n];

            System.out.println("Enter array: ");
            for (int i = 0; i < n; i++) {
                // Reading array elements from the user
                array[i] = sc.nextInt();
            }

            int[] result = new int[3];
            if (args[0].equals("1")) {
                result = task1(array, n);
            } else if (args[0].equals("2")) {
                result = task2(array, n);
            } else if (args[0].equals("3A") || args[0].equals("3a")) {
                result = task3A(array, n);
            } else if (args[0].equals("3B") || args[0].equals("3b")) {
                result = task3B(array, n);
            }

            System.out.println(result[0] + " " + result[1] + " " + result[2]);

        } else if (args[0].equals("4") || args[0].equals("5") || args[0].equals("6")) { // Running Program for Task 4, 5, 6

            Scanner scan = new Scanner(System.in);

            int row = scan.nextInt();

            int column = scan.nextInt();
            int[][] matrix = new int[row][column];

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    matrix[i][j] = scan.nextInt();
                }
            }

            if (args[0].equals("4")) {
                task4(matrix, row, column);
            } else if (args[0].equals("5")) {
                task5(matrix, row, column);
            } else if (args[0].equals("6")) {
                task6(matrix, row, column);
            }
        }
    }
}
