package com.pluralsight.ui;

import javax.swing.*;
import java.awt.*;

public class CheckoutDialog {
    public static boolean showCheckout(Component parent, String orderSummary) {
        JTextArea ta = new JTextArea(orderSummary);
        ta.setEditable(false);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(ta);
        scroll.setPreferredSize(new Dimension(600, 400));

        final JOptionPane pane = new JOptionPane(scroll, JOptionPane.PLAIN_MESSAGE,
                JOptionPane.YES_NO_OPTION, null,
                new Object[]{"Place Order", "Cancel"}, "Place Order");

        JDialog dialog = pane.createDialog(parent, "Review Order");
        dialog.setModal(true);
        dialog.setVisible(true);

        Object chosen = pane.getValue();
        return "Place Order".equals(chosen);
    }
}
