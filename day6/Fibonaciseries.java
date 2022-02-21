package day6;

import java.util.Scanner;

public class Fibonaciseries {
    static  int n1=0,n2=1,n3=0;
    public static void printFibonacciRecusive(int count){
        if(count>0){
            n3 = n1 + n2;
            n1 = n2;
            n2 = n3;
            System.out.print(" "+n3);
            printFibonacciRecusive(count-1);
        }
    }
    public  static void printFiboIterative(int count){
        System.out.println();
        System.out.print(0+" "+1);
        int j1=0,j2=1,j3=0;
        for(int i=2;i<count;++i)
        {
            j3=j1+j2;
            System.out.print(" "+j3);
            j1=j2;
            j2=j3;
        }
    }

    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        int count=scanner.nextInt();
        System.out.print(n1+" "+n2);
        printFibonacciRecusive(count-2);
        printFiboIterative(count);

    }
}
