package searchefficiencies;

import javax.swing.*;

/**
 * @title SearchEfficiencies
 * @author Steven Biro
 * @teacher Mr. J. Carron
 * @date 10-Apr-2015 12:19:04 PM
 * @purpose The purpose of this program is to compare different searching
 * algorithms and find the most efficient
 */
public class SearchEfficiencies {

    public static int counter = 0, counter2 = 0;

    public static void main(String[] args) {
        int number, amount, index;//generate random numbers
        long time;
//        boolean found;
        amount = Integer.parseInt(JOptionPane.showInputDialog("How many random numbers do you want to generate."));
        int nums[] = new int[amount];
        System.out.print("The unsorted list is: ");//output the unsorted list
        for (int i = 0; i < amount; i++) {
            nums[i] = (int) (Math.random() * 10001);//switched from 1000 to 10000 to show that the searches are very quick
            System.out.print(nums[i] + ", ");

        }
        number = Integer.parseInt(JOptionPane.showInputDialog("What number do you want to search for?"));//get the number to search for
        System.out.println("\n\nUnsorted linear search");
        time = System.currentTimeMillis();//get the current time
        for (int i = 0; i < nums.length; i++) {
            counter++;//count iterations and comparisons
            if (nums[i] == number) {
                break;
            }
        }
        time = System.currentTimeMillis() - time;//calculate time that the search took
        System.out.println("The unsorted linear search with a 'break' command took: " + time + " miliseconds\nit made: " + counter + " iterations and comparisons");
        time = 0;//reset values
        counter = 0;
        System.out.println("Sorting...");
        quickSort(nums, 0, nums.length - 1);
        System.out.println("Sorting done.\n\nSorted linear search");
        time = System.currentTimeMillis();//get current time
        for (int k = 0; k < nums.length; k++) {
            counter++;//counter iterations and comparisons
            counter2++;

            if (nums[k] == (number)) {
                break;
            }
            counter2++;

            if (nums[k] > number) {

                break;
            }
        }
        time = System.currentTimeMillis() - time;//calculate time the search took
        System.out.println("The sorted linear search with a 'break' command took: " + time + " miliseconds\nit made: " + counter + " iterations and " + counter2 + " comparisons");
        counter = 0;
        counter2 = 0;
        time = System.currentTimeMillis();//get time
        index = binarySearch(nums, 0, nums.length - 1, number);//start search
        counter2++;//increase comparisons counter to keep track of the final comparison

        time = System.currentTimeMillis() - time;//calculate time the search took
        System.out.println("\n\nBinary Search\nThe binary search took: " + time + " miliseconds\nit made: " + counter + " iterations and " + counter2 + " comparisons");

    }

    public static void quickSort(int nums[], int start, int end) {
        if (start < end) {//continue if the section is greater than 1
            int numb = split(nums, start, end);//find the place of a number
            quickSort(nums, start, numb - 1);//redo this with the number whoes spot was found
            quickSort(nums, numb + 1, end);

        }

    }

    public static int split(int nums[], int start, int end) {
        boolean left = true;//bolean to track which side to move
        int number = nums[start];//store number
        while (start < end) {//while the size is greater than 1
            if (left == true) {
                while ((nums[end] >= number) && (start < end)) {//while the number 
//at the end is larger or equal to the stored number and the section is greater than 1
                    end--;//make the section smaller
                }
                nums[start] = nums[end];//set the number at the start to the number at the end,because it is no longer bigger
                left = false;
            } else {
                while ((nums[start] <= number) && (start < end)) {//while the numner
//at the start is smaller or equal to the stored number and the section is larger than 1
                    start++;//make the section smaller
                }
                nums[end] = nums[start];//set the number at the start to the end, because it is no longer smaller
                left = true;
            }
        }
        nums[start] = number;//place the stored number in the correct spot
        return start;//return this spot
    }

    private static int binarySearch(int nums[], int lower, int upper, int key) {//Else statements dont need to be included becasue it is not a comparison.
        counter++;//increase iterations counter
        counter2++;//increase comparisons counter

        if (upper - lower > 1) {//if there is more than one then left in the list, search it
            counter2++;//increase comparisons counter

            if (key > (nums[(upper + lower) / 2])) {//key is larger than the number at this index
                lower = (upper + lower) / 2;//reassign lower index
                return binarySearch(nums, lower, upper, key);

            } else if (key < (nums[(upper + lower) / 2])) {//key is smaller than the number at this index
                counter2++;//increase comparisons counter
                upper = (upper + lower) / 2;//reassign upper index
                return binarySearch(nums, lower, upper, key);
            } else {
                counter2++;//increase comparisons counter

                return (upper + lower) / 2;//return index
            }
        } else {
            return -1;//return -1 if nothing is found
        }
    }
}
