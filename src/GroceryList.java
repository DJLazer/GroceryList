import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GroceryList implements ActionListener {
    private JFrame frame;

    // taskPanel will act as the container for the taskcomponentpanel
    // taskComponentPane; will store all the taskcomponents
    private JPanel taskPanel, taskComponentPanel;

    public GroceryList() {
        initialize();

        addGuiComponents();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Grocery List App");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(CommonConstants.GUI_SIZE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
    }

    private void addGuiComponents() {
        // banner text
        JLabel bannerLabel = new JLabel("Grocery List");
        bannerLabel.setFont(new Font("Arial", Font.BOLD, 36));
        bannerLabel.setBounds(
                (CommonConstants.GUI_SIZE.width - bannerLabel.getPreferredSize().width)/2,
                15,
                CommonConstants.BANNER_SIZE.width,
                CommonConstants.BANNER_SIZE.height
        );

        // taskPanel
        taskPanel = new JPanel();

        // taskComponentPanel
        taskComponentPanel = new JPanel();
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
        taskPanel.add(taskComponentPanel);

        // add scrolling to taskPanel
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBounds(8, 70, CommonConstants.TASK_PANEL_SIZE.width, CommonConstants.TASK_PANEL_SIZE.height);
        scrollPane.setMaximumSize(CommonConstants.TASK_PANEL_SIZE);
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // make scroll faster
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        // add task button
        JButton addTaskButton = new JButton("Add Item");
        addTaskButton.setFont(new Font("Arial", Font.BOLD, 22));
        addTaskButton.setBounds(-5, CommonConstants.GUI_SIZE.height - 88, CommonConstants.ADD_TASK_BUTTON_SIZE.width, CommonConstants.ADD_TASK_BUTTON_SIZE.height);
        addTaskButton.addActionListener(this);

        // add to frame
        frame.add(bannerLabel);
        frame.add(scrollPane);
        frame.add(addTaskButton);
    }

    public void show() {
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equalsIgnoreCase("Add Item")) {
            // create a task component
            TaskComponent taskComponent = new TaskComponent(taskComponentPanel);
            taskComponentPanel.add(taskComponent);



            // make the previous task appear disable
//            if (taskComponentPanel.getComponentCount() > 1) {
//                TaskComponent previousTask = (TaskComponent) taskComponentPanel.getComponent(
//                        taskComponentPanel.getComponentCount() - 2);
//                previousTask.getTaskField().setBackground(null);
//            }

            // make the task field request focus after creation

            taskComponent.getTaskField().requestFocus();
            frame.repaint();
            frame.revalidate();
        }
    }
}
