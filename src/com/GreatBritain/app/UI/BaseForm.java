package com.GreatBritain.app.UI;

import javax.swing.*;
import java.awt.*;

public class BaseForm extends JFrame {
    public BaseForm(){
        setTitle("Грейт Британ");
        setMinimumSize(new Dimension(1200, 800));
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("school_logo.png")).getImage());
    }
}
