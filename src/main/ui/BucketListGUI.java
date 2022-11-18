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
import java.util.List;

// Represents a bucket list GUI
public class BucketListGUI extends JPanel implements ListSelectionListener {

    private JList<String> bucketListJList;
    private final DefaultListModel<String> listModel;

    private static final String addString = "Add Activity";
    private static final String removeString = "Remove Activity";
    private static final String saveString = "Save";
    private static final String loadString = "Load";
    private JButton removeButton;
    private JButton addButton;
    private JButton loadButton;
    private JButton saveButton;
    private AddListener addListener;
    private JTextField activityField;
    private JScrollPane listScrollPane;

    private static final String JSON_STORE = "./data/bucketList.json";
    private final JsonReader jsonReader = new JsonReader(JSON_STORE);
    private final JsonWriter jsonWriter = new JsonWriter(JSON_STORE);

    // EFFECTS: produces a bucket list GUI using Java Swing library
    public BucketListGUI() {
        super(new BorderLayout());

        listModel = new DefaultListModel<>();

        createBucketList();
        createButtons();
        createActivityField();
        createPanel();
    }

    // MODIFIES: this
    // EFFECTS: Creates a bucket list and puts it into a scroll pane
    public void createBucketList() {
        bucketListJList = new JList<>(listModel);
        bucketListJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bucketListJList.setSelectedIndex(0);
        bucketListJList.addListSelectionListener(this);
        bucketListJList.setVisibleRowCount(10);
        listScrollPane = new JScrollPane(bucketListJList);
    }

    // MODIFIES: this
    // EFFECTS: Creates an add, remove, save and load button with commands
    public void createButtons() {
        addButton = new JButton(addString);
        addListener = new AddListener(addButton);
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
    }

    // MODIFIES: this
    // EFFECTS: Creates a text field
    public void createActivityField() {
        activityField = new JTextField(15);
        activityField.addActionListener(addListener);
        activityField.getDocument().addDocumentListener(addListener);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel with BoxLayout
    public void createPanel() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));

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

        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        ImageIcon image = new ImageIcon("./data/image.png");
        JLabel imageLabel = new JLabel(image);
        JPanel imagePane = new JPanel();
        imagePane.add(imageLabel);

        add(imagePane, BorderLayout.NORTH);
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    // Represents a listener that saves the state of the bucket list
    class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(toBucketList(listModel));
                jsonWriter.close();
                System.out.println("Saved bucket list to " + JSON_STORE);
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }

        // EFFECTS: converts default list model to a bucket list object
        public BucketList toBucketList(DefaultListModel<String> list) {
            BucketList bucketList = new BucketList();
            int index = 0;

            while (index < list.getSize()) {
                Activity activity = new Activity(list.getElementAt(index));
                bucketList.addActivity(activity);
                index++;
            }
            return bucketList;
        }
    }

    // Represents a listener that loads data from file into the bucket list
    class LoadListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                BucketList bucketList = jsonReader.read();
                List<String> listOfDescr = bucketList.allDescriptions();

                for (String s: listOfDescr) {
                    if (!listModel.contains(s)) {
                        listModel.addElement(s);
                    }
                }
                System.out.println("Loaded bucket list from " + JSON_STORE);
            } catch (IOException ioException) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }

    // Represents a listener that removes an activity from the bucket list
    class RemoveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = bucketListJList.getSelectedIndex();
            listModel.remove(index);

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

    // Represents a listener that adds an activity to the bucket list
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private final JButton button;

        // EFFECTS: constructs a listener with a button
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

            bucketListJList.setSelectedIndex(index);
            bucketListJList.ensureIndexIsVisible(index);
        }

        // EFFECTS: returns true if the activityName is already in the list model, false otherwise
        private boolean alreadyInList(String activityName) {
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

        // MODIFIES: this
        // EFFECTS: enables button if it is not already enabled, does nothing is button is already enabled
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        // MODIFIES: this
        // EFFECTS: if the text field is empty, does not enable button. Else, enables button.
        // True if text field is empty, false otherwise.
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
            removeButton.setEnabled(true);
            if (bucketListJList.getSelectedIndex() == -1) {
                removeButton.setEnabled(false);
            }
        }
    }

    // EFFECTS: creates a GUI and shows it
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Bucket List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent newContentPane = new BucketListGUI();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
