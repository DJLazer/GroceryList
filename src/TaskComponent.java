import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class TaskComponent extends JPanel implements ActionListener {
    private final JCheckBox checkBox;
    private final JTextPane taskField;

    public JTextPane getTaskField() {
        return taskField;
    }

    // this panel is used so that we can make updates to the task component panel when deleting tasks
    private final JPanel parentPanel;

    public TaskComponent(JPanel parentPanel) {
        this.parentPanel = parentPanel;

        taskField = new JTextPane();

        taskField.setBorder(BorderFactory.createLineBorder(Color.black));
        taskField.setPreferredSize(CommonConstants.TASK_FIELD_SIZE);
        taskField.setContentType("text/html");
        setJTextPaneFont(taskField, CommonConstants.TASKFIELD_FONT, Color.BLACK, false);
        taskField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                taskField.setBackground(Color.white);
            }

            @Override
            public void focusLost(FocusEvent e) {
                taskField.setBackground(null);
            }
        });

        checkBox = new JCheckBox();
        checkBox.setPreferredSize(CommonConstants.CHECKBOX_SIZE);
        checkBox.addActionListener(this);

        JButton deleteButton = new JButton("X");
        deleteButton.setPreferredSize(CommonConstants.DELETE_BUTTON_SIZE);
        deleteButton.addActionListener(this);

        add(checkBox);
        add(taskField);
        add(deleteButton);
    }

    public static void setJTextPaneFont(JTextPane jtp, Font font, Color c, boolean strike) {
        // Start with the current input attributes for the JTextPane. This
        // should ensure that we do not wipe out any existing attributes
        // (such as alignment or other paragraph attributes) currently
        // set on the text area.
        MutableAttributeSet attrs = jtp.getInputAttributes();

        // Set the font family, size, and style, based on properties of
        // the Font object. Note that JTextPane supports a number of
        // character attributes beyond those supported by the Font class.
        // For example, underline, strike-through, super- and sub-script.
        StyleConstants.setFontFamily(attrs, font.getFamily());
        StyleConstants.setFontSize(attrs, font.getSize());
        StyleConstants.setItalic(attrs, (font.getStyle() & Font.ITALIC) != 0);
        StyleConstants.setBold(attrs, (font.getStyle() & Font.BOLD) != 0);

        StyleConstants.setStrikeThrough(attrs, strike);

        // Set the font color
        StyleConstants.setForeground(attrs, c);

        // Retrieve the pane's document object
        StyledDocument doc = jtp.getStyledDocument();

        // Replace the style for the entire document. We exceed the length
        // of the document by 1 so that text entered at the end of the
        // document uses the attributes.
        doc.setCharacterAttributes(0, doc.getLength() + 1, attrs, false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (checkBox.isSelected()) {
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");

//            taskField.setText("<html><s>" + taskText + "</s></html>");
            setJTextPaneFont(taskField, CommonConstants.TASKFIELD_FONT, Color.BLACK, true);
        }
        else {
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");

//            taskField.setText(taskText);
            setJTextPaneFont(taskField, CommonConstants.TASKFIELD_FONT, Color.BLACK, false);
        }

        if (e.getActionCommand().equalsIgnoreCase("X")){
            // delete component from parent panel
            parentPanel.remove(this);
            parentPanel.repaint();
            parentPanel.revalidate();
        }
    }
}
