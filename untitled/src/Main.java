import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setExtendedState(Frame.MAXIMIZED_BOTH);
        mainWindow.setVisible(true);
        System.out.println();


    }

}