package Task;

import java.util.Scanner;

public class Hello {
        public static void main(String[] args) {
            Scanner scanner=new Scanner(System.in);
            String s= scanner.nextLine();
            String s1[]=s.split(" ");
            for (int i=0;i<Integer.parseInt(s1[1]);i++){
                System.out.println(s1[0]);
            }
        }
}
