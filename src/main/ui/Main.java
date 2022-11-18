package ui;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            final BucketListGUI bucketListGUI = new BucketListGUI();
            public void run() {
                bucketListGUI.createAndShowGUI();
            }
        });
    }
}

//        try {
//            new BucketListApp();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run application: file not found");
//        }