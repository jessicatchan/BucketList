package ui;

import model.Activity;
import model.BucketList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents a bucket list GUI
public class BucketListGUI extends JPanel implements ListSelectionListener {

    private final JList<String> bucketListJList;
    private final DefaultListModel<String> listModel;
    private BucketList bucketList;

    private static final String addString = "Add Activity";
    private static final String removeString = "Remove Activity";
    private static final String saveString = "Save";
    private static final String loadString = "Load";
    private final JButton addButton;                   // push button
    private final JButton removeButton;
    private final JTextField activityField;           // allows editing of single line of text
    private final JButton saveButton;
    private final JButton loadButton;

    private static final String JSON_STORE = "./data/bucketList.json";
    private final JsonReader jsonReader = new JsonReader(JSON_STORE);
    private final JsonWriter jsonWriter = new JsonWriter(JSON_STORE);

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public BucketListGUI() {
        super(new BorderLayout());

        listModel = new DefaultListModel<>();
        bucketList = new BucketList();

        // Create a bucket list and put it into the scroll pane
        bucketListJList = new JList<>(listModel);
        bucketListJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bucketListJList.setSelectedIndex(0);
        bucketListJList.addListSelectionListener(this);
        bucketListJList.setVisibleRowCount(10);
        JScrollPane listScrollPane = new JScrollPane(bucketListJList);

        addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);

        removeButton = new JButton(removeString);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(new RemoveListener());
        removeButton.setEnabled(false);

        saveButton = new JButton(saveString);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(new SaveListener());

        loadButton = new JButton(loadString);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(new LoadListener());

        activityField = new JTextField(15);
        activityField.addActionListener(addListener);
        activityField.getDocument().addDocumentListener(addListener);

        // Create panel using BoxLayout
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));

        buttonPane.add(loadButton);
        buttonPane.add(saveButton);

        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(activityField);

        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));

        buttonPane.add(addButton);
        buttonPane.add(removeButton);

        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);

    }

    // Represents a listener that saves the state of the bucket list
    class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(bucketList);
                jsonWriter.close();
                System.out.println("Saved bucket list to " + JSON_STORE);
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }

    // TODO
    // Represents a listener that loads data from file into the bucket list
    class LoadListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                bucketList = jsonReader.read();
                bucketList.allDescriptions();

                System.out.println("Loaded bucket list from " + JSON_STORE);
            } catch (IOException ioException) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }

    // Represents a listener that removes activity from bucket list
    class RemoveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = bucketListJList.getSelectedIndex();
            listModel.remove(index);
            bucketList.removeActivityAtIndex(index);

            int size = listModel.getSize();

            if (size == 0) {
                removeButton.setEnabled(false);
            } else {
                removeButton.setEnabled(true);
                if (index == listModel.getSize()) {
                    index--;
                }
                bucketListJList.setSelectedIndex(index);
                bucketListJList.ensureIndexIsVisible(index);
            }
        }
    }

    // Represents a listener that adds an activity to a bucket list
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private final JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String activityName = activityField.getText();

            if (activityName.equals("") || alreadyInList(activityName)) {
                Toolkit.getDefaultToolkit().beep();
                activityField.requestFocusInWindow();
                activityField.selectAll();
                return;
            }

            int index = bucketListJList.getSelectedIndex();
            if (index == -1) {
                index = 0;
            } else {
                index++;
            }

            listModel.addElement(activityName);
            bucketList.addActivity(new Activity(activityName));

            // Reset the text field
            bucketListJList.setSelectedIndex(index);
            bucketListJList.ensureIndexIsVisible(index);
        }

        protected boolean alreadyInList(String activityName) {
            return listModel.contains(activityName);
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (bucketListJList.getSelectedIndex() == -1) {
                removeButton.setEnabled(false);
            } else {
                removeButton.setEnabled(true);
            }
        }
    }

    // EFFECTS: creates a GUI and shows it
    private static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Bucket List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new BucketListGUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Splash splashScreen = new Splash(2000, "./data/tobs.jpg");
                splashScreen.showSplash();
                createAndShowGUI();
            }
        });
    }
}
