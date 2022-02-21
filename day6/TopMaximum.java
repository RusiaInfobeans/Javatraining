package day6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopMaximum {
    public static List<Integer> getLargest(List<Integer> list,int k){
        List<Integer> li=new ArrayList<>();
        Collections.sort(list);
        for (int i=list.size()-1;i>=(list.size()-k);i--){
            li.add(list.get(i));
        }
        Collections.reverse(li);
        return  li;
    }
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(50);
        list.add(55);
        list.add(5);
        list.add(45);
        System.out.println(getLargest(list,2));
    }
}
