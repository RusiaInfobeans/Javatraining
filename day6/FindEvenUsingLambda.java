package day6;

import java.util.ArrayList;
import java.util.List;

public class FindEvenUsingLambda {
    public static void findEven(List<Integer> list){
        list.stream().filter(n -> n%2==0).forEach(System.out::println);
    }
    public static void main(String[] args) {
      List<Integer> list=new ArrayList<Integer>();
      list.add(10);
        list.add(20);
        list.add(21);
        list.add(3);
        list.add(4);
        findEven(list);
    }
}
