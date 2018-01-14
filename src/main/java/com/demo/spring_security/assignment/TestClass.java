package com.demo.spring_security.assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Parisana
 */
public class TestClass {

    public static void main(String[] args) throws IOException {
        /*BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        printHorizontalLine();
        System.out.println("Q2");
        new Q2(new int[]{1,2, 2, 1, 3, 4}).print();

        printHorizontalLine();
        System.out.println("Q3");
        new Q3(new int[]{1,2,3,4,5,6}, new int[]{4,5,6,7,8,9}).print();

        printHorizontalLine();
        System.out.println("Q4");
        new Q4(new int[]{1,2, 3, 4, 5, 7, 6, 8, 9}).print();

        printHorizontalLine();
        System.out.println("Q5");
        new Q5().print("SSUUNNIIL");

        printHorizontalLine();
        System.out.println("Q7");
        new Q7().print(6);*/

        printHorizontalLine();
        System.out.println("Q1");
        new Q1(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12}).print();

        printHorizontalLine();
        System.out.println("Q6");
        new Q6().print("SABCDASEFGHA");
        new Q6().print("aaaaabbbbbbbbbbbccccccccccccccdddddddddddddddeeeeeeeeeeefffffffffffffgggggggggggggghhhhhhhhhh" +
                "iiiiiiiiiiiiiiiijjjjjjjjjjjjjjjjkkkkkkkkkkkkkkkkklllllllllllllllllmmmmmmmmmmmmnnnnnnnnnnnnnnnnnoooooooooooooooppppppppppppppppppp" +
                "qqqqqqqqqqqqqqqrrrrrrrrrrrrrrrrrrrssssssssssssssssttttttttttttttttttttttttuuuuuuuuuuuuuuuuuuvvvvvvvvvvvvvvvvvvvvvvvvv" +
                "wwwwwwwwwwwwwwwwwwwwwwwwwxxxxxxxxxxxxxxxxxxxxxxxxyyyyyyyyyyyyyyyyyyyyyyyyyzzzzzzzzzzzzzzzzzzzzzzzzz" +
                "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111" +
                "2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222" +
                "333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333");

        printHorizontalLine();
        System.out.println("Q8");
        System.out.println("Checking for pangram (strings containing all english alphabets)");
        new Q8().print("ABCDefghijklmnopqrstuvwxyZ");
        new Q8().print("pqrstuvwxyZ");
    }

    private static void printHorizontalLine() {
        System.out.println("----------------------------------------------------------------------------------");
    }

}
class Q1{
    private int[] inputArr;
    private int size;

    public Q1(int[] integers) {
        this.inputArr= integers;
    }

    private void sortedResult(){
        size= inputArr.length;
        for (int i=2; i<size; i+=2){
            for (int j=i-2; j>=0; j-=2){
                if (inputArr[j]<inputArr[j+2]){
                    int temp= inputArr[j+2];
                    inputArr[j+2]= inputArr[j];
                    inputArr[j]= temp;
                }
            }
        }
    }
    private void sortedResult2(){
        size= inputArr.length;
//        check size is even or odd, if odd add 1.
        int theArrSize= (size&1)==1? (size/2)+1: size/2;
        int[] theArr= new int[theArrSize];
        for (int i=0, j=0; i<size; i+=2, j++){
            theArr[j] = inputArr[i];
        }
        quickSort(theArr, 0, theArrSize-1); // sort in descending order
        for (int i=0; i<theArrSize; i++){
            inputArr[i*2]= theArr[i];
        }
    }

    private void quickSort(int[] theArr, int left, int right) {
        int index= partitionAndSwap(theArr, left, right);
        if (left<index-1)
            quickSort(theArr, left, index-1);
        if(right> index)
            quickSort(theArr, index, right);
    }

    private int partitionAndSwap(int[] theArr, int left, int right) {
        int i = left, j = right;
        int tmp;
        int pivot = theArr[(left + right) / 2];
        while (i <= j) {
            while (theArr[i] > pivot)
                i++;
            while (theArr[j] < pivot)
                j--;
            if (i <= j) {
                tmp = theArr[i];
                theArr[i] = theArr[j];
                theArr[j] = tmp;
                i++;
                j--;
            }
        }
        return i;
    }
    public void print(){
        sortedResult();
        for (int e: inputArr)
            System.out.print(e+", ");
        System.out.println("\n Using Quicksort");
        sortedResult2();
        for (int e: inputArr)
            System.out.print(e+", ");
        System.out.println();
    }
}
class Q2{
    private static int[] inputArr;

    public Q2(int[] ints) {
        inputArr= ints;
    }

    public void print(){
        result();
        System.out.print(inputArr[0]+", ");
        for (int i=1; i< inputArr.length; i++){
            inputArr[i]=inputArr[i]+inputArr[i-1];
            System.out.print(inputArr[i]+ ", ");
        }
        System.out.println();
    }
    private void result(){
        Map<Integer, Integer> map= new TreeMap<>();
        Arrays.stream(inputArr).forEach(value -> {
            if (map.containsKey(value))
                map.put(value, map.get(value)+1);
            else map.put(value, 1);
        });
        inputArr= new int[map.size()];
        Iterator<Integer> iterator = map.keySet().iterator();
        int i=0;
        while (iterator.hasNext()){
            inputArr[i++]+= map.get(iterator.next());
        }
    }
}
class Q3{
    private static int[] inputArr1;
    private static int[] inputArr2;
    private static Set<Integer> resultSet;

    public Q3(int[] ints1, int[] ints2) {
        inputArr1= ints1;
        inputArr2= ints2;
    }
    public void print(){
        result();
        System.out.println(resultSet);
    }
    public void result(){
        int i=0;
        int j=0;
        int size1= inputArr1.length;
        int size2= inputArr2.length;
        resultSet = new LinkedHashSet<>();
        while (i < size1 && j < size2)
            resultSet.add(inputArr1[i]<inputArr2[j]? inputArr1[i++]:inputArr2[j++]);
        while (i<size1){
            resultSet.add(inputArr1[i++]);
        }
        while (j<size2){
            resultSet.add(inputArr2[j++]);
        }
    }
}
class Q4{
    private static int[] inputArr;

    public Q4(int[] ints) {
        inputArr= ints;
    }

    public void print(){
        System.out.println(result());
    }
    private String result(){
        BitSet bitSet= new BitSet(9);
        Arrays.stream(inputArr).forEach(value -> {
            bitSet.set(value-1);
        });
//        System.out.println(bitSet);
        if (bitSet.cardinality()==9)
            return "UNIQUE";
        else return "NOTUNIQUE";
    }
}
class Q5{
    public void print(String s){
        result(s);
    }
    public void result(String s){
        findString(s.trim());
    }

    private void findString(String string) {
        StringBuilder stringBuilder= new StringBuilder();
        int len= string.length();
        if (string.equals("")){
            System.out.print(" empty");
            return;
        }
        if (len<=1) {
            System.out.println(string);
            return;
        }
        for (int i=0; i<len;){
            int j=i+1;
            int count=1;
            for (; j<len; j++){
                if (string.charAt(i)==string.charAt(j))
                    count++;
                else break;
            }
            if ((count&1)==1){
                stringBuilder.append(string.charAt(i));
            }
            i=j;
        }
        String nextString= stringBuilder.toString();
        if (string.equals(nextString)) {
            System.out.print(nextString);
            return;
        }
        else System.out.print(string+" --> ");
        findString(nextString);
    }
}
class Q6{

    public void print(String s){
        int result= result(s);
        System.out.println(result<2?"All characters are Unique, Sorry try another string!": result-2);
    }
    private static int result(String sentence) {
    // Store already checked characters
        BitSet bitSet= new BitSet(200);
        int slength= sentence.length();
        int maxLength=0;
        int maxLengthPossible;
        for (int i=0; i<slength; i++){
            maxLengthPossible= slength-i;
            char leftChar= sentence.charAt(i);
            if (bitSet.get(leftChar))
                continue;
            bitSet.set(leftChar);
            for (int j=slength-1; j>i; j--){
                if (sentence.charAt(j)==leftChar){
                    int currentLength= j+1-i; // to match with maxLengthPossible
                    if (currentLength>maxLength)
                        maxLength= currentLength;
                    else continue;
                    if (maxLength==maxLengthPossible)
                        return maxLength;
                }
            }
        }
        return maxLength;
    }
}

class Q7{
    public void print(int n){
        staircase(n);
    }

    private void staircase(int n) {
        System.out.println("Left aligned: ");
        for (int i=1; i<n+1; i++){
            for (int j=0; j<i; j++)
                System.out.print("# ");
            System.out.println();
        }
        System.out.println("Right aligned: ");
        for (int i=1; i<n+1; i++){
            for (int k=n+1; k>i; k--)
                System.out.print("  ");
            for (int j=0; j<i; j++)
                System.out.print("# ");
            System.out.println();
        }
    }
}
class Q8{
    public void print(String sentence){
        System.out.println(isPangram(sentence)?"Pangram":"Not Pangram");
    }

    private boolean isPangram(String sentence) {
        BitSet bitSet= new BitSet(26);
        for (int i=0; i<sentence.length(); i++){
            char c = sentence.charAt(i);
            if (c >=97 && c <=122) {
                bitSet.set((c -19) %26);
            }else if (c >=65 && c <=90) {
                bitSet.set((c -13) %26);
            }
            if (bitSet.cardinality()==26)
                return true;
        }
        System.out.println(bitSet);
        return false;
    }
}
