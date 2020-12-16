package frontend;

import backend.Card;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SecretMissionFrame extends JFrame implements ActionListener
{
    ImageIcon secretMission;
    private JLabel picPlace;
    public SecretMissionFrame(int secretMissionNumber)
    {
        setLayout(null);
        setPreferredSize(new Dimension(400,630));
        setBounds(105,105,200,400);
        setBackground(Color.lightGray);
        secretMission = new ImageIcon("./src/main/resources/images/secretmissions/secret_mission_"+secretMissionNumber+".jpg");
        picPlace = new JLabel();
        picPlace.setIcon(secretMission);
        picPlace.setBounds(0,0,400,600);
        add(picPlace);
        pack();

    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
    }
}
