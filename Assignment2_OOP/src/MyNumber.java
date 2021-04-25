public class MyNumber {
    /**
     * @author Ebrahim Shahid
     * 02/08/20
     * Object Oriented Programming Assignment 2
     * This is a class that contains 4 methods
     * Method 1 = Parse to parse strings as input and can assign string #s into 30 digit arrays
     * Method 2 = toString which displays the number using System.out.print
     * Mehtod 3 = add which sums 2 myNumbers
     * Method 4 = subtract which minuses myNumbers2 from myNumbers1
     * Additional functions - isEqualTo, isNotEqualTo, and isLessThan will be used for simple boolean comparisons
     */

    //variable declarations
    int digits[] = new int[30];
    int size;

    public void parse(String str) {
        size = str.length();
        for (int i = 0; i < size; i++) {
            digits[i] = str.charAt(i) - '0';
        }
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < size; i++) {
            s += digits[i];
        }
        return s;
    }

    public void add(MyNumber n) {
        if (n.isGreaterThan(this)) {
            addUtil(n, this);
        } else {
            addUtil(this, n);
        }
    }

    public void addUtil(MyNumber n, MyNumber n2) {
        int num1[] = n.digits;
        int num2[] = n2.digits;
        int sum[] = new int[n.size + 1];
        int i = n.size;
        int j = n2.size;
        int k = n.size - 1;


        int carry = 0;
        int s = 0;

        /**
         *Going backwards from the end of the array, we only compare the 2nd array for it's size in the add() function until array[0]
         *A while loop will get the sum of each element in both arrays
         */
        while (j > 0) {
            s = num1[i] + num2[j] + carry;
            sum[k] = (s % 10);
            // Finding carry for next sum
            carry = s / 10;
            System.out.print(sum[k] + " " + carry);
            k--;
            i--;
            j--;
        }
        k = n.size;
        //If 2nd array has less elements, carry over elements from 1st array
        while ( i >= 0) {
            //add carry to 1st array elements
            s = num1[i] + carry;
            sum[k] = (s % 10);
            carry = s / 10;
            i--;
            k--;
        }
        if(carry == 1) {
            for (int p = 1; p <= k; p++) {
                num1[p] = sum[p - 1];
            }
            num1[0] = carry;
            this.digits = num1;
            this.size = n.size + 1;
        }
        else
        {
            this.digits = sum;
        }
    }

    //subtract methods

    public void subtract(MyNumber b){
        if(this.isGreaterThan(b)){
            subtractUtil(this, b); //for this specific method, n1 and n2 are unusable as they can be consfused with the n1 and n2 generated from "this"
        }
        else
        {
            subtractUtil(b, this);
        }
    }

    public void subtractUtil(MyNumber n, MyNumber n2)
    {
        System.out.println(n + " " + n2);
        int num1[] = n.digits;
        int num2[] = n2.digits;
        int diff[] = new int[n.size];
        int i = n.size - 1, j = n2.size - 1, k = n.size - 1;

        int borrow = 0;


    /** Until we reach beginning of array.
        we are comparing only for the 2nd
        array because we have already compare
        the size of array in wrapper function.
     */
        while (j >= 0)
        {
        // If the previous digits were subtracted using borrow,
            if((num1[i] - borrow) < num2[j] ) {
                diff[k] = num1[i] + (10 - borrow) - num2[j];
                borrow = 1;
            } else {
                diff[k] = (num1[i] - borrow) - num2[j];
                borrow = 0;
            }
            i--;
            j--;
            k--;
        }

    // If second array size is less than first array size.
        while (i >= 0)
        {
            if(diff[i] == 0 && borrow != 0) {
                diff[k] = 9;
            } else {
                diff[k] = num1[i] - borrow;
                borrow = 0;
            }
        i--;
        k--;
        }
        this.digits = diff;
    }

    public boolean isEqualTo(MyNumber b) { //x = y
        for(int i=0; i<digits.length;i++) {
            if(digits[i] != b.digits[i])
                return false;
        }
        return true;
    }

    public boolean isNotEqualTo(MyNumber n) { // x != y
        return !isEqualTo(n);
    }

    public boolean isGreaterThan(MyNumber n) {
        if(this.digits.length > n.digits.length)
            return true;
        else if(this.digits.length < n.digits.length)
            return false;
        for(int i=0; i<digits.length; i++) {
            if(digits[i] < n.digits[i])
                return false;
        }
        return true;
    }

    public boolean isLessThan(MyNumber n) { // x < y
        return !isGreaterThan(n);
    }

    public static void main(String args[]) throws ArithmeticException
    { //Main function to output results for testing
        String str1 = "823141213141512";
        String str2 = "827193922219211";
        MyNumber num1 = new MyNumber();
        MyNumber num2 = new MyNumber();
        num1.parse(str1);
        num2.parse(str2);

        System.out.println("Number 1: ");
        System.out.println(num1);
        System.out.println("Number 2: ");
        System.out.println(num2);

        if(num1.isEqualTo(num2)) {
            System.out.println("Number1 and number 2 are equal!!");
        } else {
            if(num1.isGreaterThan(num2)) {
                System.out.println("Number1 is greater than number 2!!");
            } else {
                System.out.println("Number1 is lesser than number 2!!");
            }
        }

        num1.add(num2);
        System.out.println("\nAddition result: ");
        System.out.println(num1);
        num1.parse(str1);
        num1.subtract(num2);
        System.out.println("Subtraction result: ");
        System.out.println(num1);

    }
}
