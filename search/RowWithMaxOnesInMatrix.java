package search;

import search.OccurenceInSortedArray;

public class RowWithMaxOnesInMatrix {

    //Assumption==>All rows are sorted having only 0's and 1's

    static int rows = 4, columns = 4;
    public static void main(String[] args) {
        int mat[][] = { { 0, 0, 0, 1 },
                        { 0, 1, 1, 1 },
                        { 1, 1, 1, 1 },
                        { 0, 0, 0, 0 } };
        System.out.println("Index of row with maximum 1's is "
                + rowWithMax1s(mat));
    }

    static int rowWithMax1s(int mat[][])
    {
        // Initialize first row as row with max 1s
        int max_row_index = 0;

        // The function first() returns index of first 1 in row 0.
        // Use this index to initialize the index of leftmost 1 seen so far
        OccurenceInSortedArray occurenceInSortedArray = new OccurenceInSortedArray();
        int firstOccurence = occurenceInSortedArray.getFirstOccurence(mat[0], 0, columns -1,1);
        if (firstOccurence == -1) // if 1 is not present in first row
            firstOccurence = columns - 1;

        for (int i = 1; i < rows; i++)
        {
            // Move left until a 0 is found
            while (firstOccurence >= 0 && mat[i][firstOccurence] == 1)
            {
                firstOccurence = firstOccurence-1;  // Update the index of leftmost 1 seen so far
                max_row_index = i;  // Update max_row_index
            }
        }
        return max_row_index;
    }

}
