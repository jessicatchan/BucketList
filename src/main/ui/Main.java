package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        BucketListGUI bucketListGUI = new BucketListGUI();

        bucketListGUI.createGUI();
    }
}

//        try {
//            new BucketListApp();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run application: file not found");
//        }