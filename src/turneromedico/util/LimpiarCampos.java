package turneromedico.util;

import javax.swing.*;
import java.awt.*;

public class LimpiarCampos {

    public static void limpiarCampos(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            } else if (component instanceof Container) {
                limpiarCampos((Container) component);
            }
        }
    }
}
