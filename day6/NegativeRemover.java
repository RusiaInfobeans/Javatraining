package day6;

import java.util.ArrayList;
import java.util.List;

public class NegativeRemover {
    public static List<Integer> removeNegative(List<Integer> list){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i)<0){
                list.remove(i);
            }
        }
        return list;
    }
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        list.add(10);
        list.add(-20);
        list.add(50);
        list.add(55);
        list.add(5);
        list.add(-45);
        System.out.println(list);
        System.out.println(removeNegative(list));
    }
}
