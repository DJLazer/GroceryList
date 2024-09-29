import java.awt.*;

public class CommonConstants {
    public static final Dimension GUI_SIZE = new Dimension(540, 760);

    public static final Dimension BANNER_SIZE = new Dimension(GUI_SIZE.width, 50);

    public static final Dimension TASK_PANEL_SIZE = new Dimension(GUI_SIZE.width - 30, GUI_SIZE.height - 175);

    public static final Dimension ADD_TASK_BUTTON_SIZE = new Dimension(GUI_SIZE.width, 50);

    public static final Dimension TASK_FIELD_SIZE = new Dimension((int)(TASK_PANEL_SIZE.width * 0.8), 50);

    public static final Dimension CHECKBOX_SIZE = new Dimension((int)(TASK_FIELD_SIZE.width * 0.05), 50);

    public static final Dimension DELETE_BUTTON_SIZE = new Dimension((int)(TASK_FIELD_SIZE.width * 0.12), 50);

    public static final Font TASKFIELD_FONT = new Font("Arial", Font.PLAIN, 22);
}
