package ru.kashtanov.graduation_work;

import java.util.ArrayList;
import java.util.List;


public class MainTest {
    public static void main(String[] args) {
        System.out.println(getFactorial((5)));
    }
//    public static int factorial(int n){
//        int amount = 1;
//        for(int i = 1;i<n;i++){
//            amount=amount*i;
//        }return amount;
//    }
public static int getFactorial(int n) {
       if(n<=1){
           return 1;
       }
       else {
          return n*getFactorial(n-1);
       }

}

//        Fruit fruit = new Fruit();
//        Citrus citrus = new Citrus();
//        Orange orange = new Orange();
//        Mandarin mandarin = new Mandarin();
//        fruit = citrus;
//        fruit = orange;
//        fruit = mandarin;
//
//        //orange = fruit; // it is impossible
//        ArrayList<Citrus> citrusList = new ArrayList<>();
//        ArrayList<Fruit>fruitArrayList = new ArrayList<>();
//        ArrayList<Mandarin>mandarinArrayList = new ArrayList<>();
//        ArrayList<Orange>orangesList = new ArrayList<>();
//        //CONTRVARIANCE-----------------------------------
//         citrusList.add(orangesList.get(0));
//         countSomeOranges(orangesList);
//         addSomeOranges(citrusList);
//         addSomeOranges(fruitArrayList);
//         addSomeOranges();
//        //COVARIANCE---------------------------------------
//         ArrayList<? extends Citrus> newListCitruses = new ArrayList<>();
//         Fruit fruit1 = newListCitruses.get(0);
//
//      //   addSomeOranges(mandarinArrayList);    only ancestors
//
//
//    }
//
//    public static int  countSomeOranges(ArrayList<? extends Orange> arrayList){ // COVARIANCE READ ONLY given class and ancestors
//        int count = 0;
//        for(int i = 0; i<arrayList.size();i++){
//            count++;
//        }
//        Fruit fruit = arrayList.get(0);
//        Citrus citrus = arrayList.get(0);        // we can read any ancestor
////        arrayList.add(new Orange());  // it is impossible since we don't point explicit a type
//        return count;
//    }
//    public static void addSomeOranges(ArrayList<? super Orange> someFruitList){
//        for(int i = 0; i<5; i++){
//            someFruitList.add(new Orange());
//        }
//    }
//
//
//     static class Fruit{
//        String name;
//    }
//    static class Citrus extends Fruit{
//
//    }
//    static class Orange extends Citrus{
//
//    }
//    static class Mandarin extends Orange{
//
//    }
}

