package ui;

import model.Activity;
import model.BucketList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a bucket list GUI
public class BucketListGUI extends JPanel implements ListSelectionListener {

    private JList list;
    private DefaultListModel bucketList;

    private static final String addString = "Add Activity";
    private static final String removeString = "Remove Activity";
    private JButton removeButton;           // push button
    private JTextField activity;            // allows editing of single line of text

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public BucketListGUI() {
        super(new BorderLayout());

        bucketList = new BucketList();
        // Create a bucket list and put it into the scroll pane
        list = new JList(bucketList);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPanel = new JScrollPane(list);

        JButton addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.setEnabled(false);

        removeButton = new JButton(removeString);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(new RemoveListener());

        activity = new JTextField();
        activity.addActionListener(addListener);
        activity.getDocument().addDocumentListener(addListener);
//        String activityName = bucketList.getElementAt(list.getSelectedIndex()).toString(); // TODO

        // Create a panel that uses BoxLayout
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(removeButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(activity);
        buttonPane.add(addButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(listScrollPanel, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }


    class RemoveListener implements ActionListener {

        // TODO: specification
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            bucketList.remove(index);

            int size = bucketList.getSize();

            if (size == 0) {
                removeButton.setEnabled(false);
            } else {
                if (index == bucketList.getSize()) {
                    index--;
                }
            }
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
    }

    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = true;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        // EFFECTS: invoked when an action occurs
        @Override
        public void actionPerformed(ActionEvent e) {
            String activityName = activity.getText();

            //User didnt type in a unique activity
            if (activityName.equals("") || alreadyInList(activityName)) {
                Toolkit.getDefaultToolkit().beep();
                activity.requestFocusInWindow();
                activity.selectAll();
                return;
            }

            int index = list.getSelectedIndex();
            if (index == -1) {
                index = 0;
            } else {
                index++;
            }

            bucketList.addElement(activity.getText()); // add to end

            // Reset text field
            activity.requestFocusInWindow();
            activity.setText("");

            // Select new activity and make it visible
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        protected boolean alreadyInList(String activity) {
            return bucketList.contains(activity);
        }

        // EFFECTS: Gives notification that there was an insert into the document.
        // The range given by the DocumentEvent bounds the freshly inserted region.
        @Override
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }



        // EFFECTS: Gives notification that a portion of the document has been removed.
        // The range is given in terms of what the view last saw (that is, before updating sticky positions).
        @Override
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        // EFFECTS: Gives notification that an attribute or set of attributes changed.
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

    // EFFECTS: Called whenever the value of the selection changes.
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (list.getSelectedIndex() == -1) {
                removeButton.setEnabled(false);
            } else {
                removeButton.setEnabled(true);
            }
        }
    }

    // EFFECTS: create and show GUI
    public static void createGUI() {
        JFrame frame = new JFrame("Bucket List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create and set up the content panel
        JComponent newContentPane = new BucketListGUI();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        // display the window
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }
}
