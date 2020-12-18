package frontend;
import backend.SoundManager;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class HowToPlayPanel extends JPanel implements ActionListener {

    private int htpPageNum = 1;
    private JButton btnBackFromHtp, btnHtpPrev, btnHtpNext;
    private JLabel lblHowToPlayBackground, htpPage1, htpPage2, htpPage3, htpPage4, htpPage5, htpPage6, htpPage7;
    private JPanel pnlMainMenu;
    private SoundManager soundManager;

    public HowToPlayPanel(JPanel pnlMainMenu, SoundManager soundManager) {
        this.pnlMainMenu = pnlMainMenu;
        this.soundManager = soundManager;
        setSize(1570, 800);

        lblHowToPlayBackground = new JLabel("");
        lblHowToPlayBackground.setIcon(new ImageIcon("./src/main/resources/images/htpBackground.png"));
        lblHowToPlayBackground.setBounds(0, -165, 1580, 1100); //1860,1200

        htpPage1 = new JLabel("");
        htpPage1.setIcon(new ImageIcon("./src/main/resources/images/htpPage1.png"));
        htpPage1.setBounds(428, -170, 1580, 1100); //1860,1200

        htpPage2 = new JLabel("");
        htpPage2.setIcon(new ImageIcon("./src/main/resources/images/htpPage2.png"));
        htpPage2.setBounds(428, -170, 1580, 1100); //1860,1200

        htpPage3 = new JLabel("");
        htpPage3.setIcon(new ImageIcon("./src/main/resources/images/htpPage3.png"));
        htpPage3.setBounds(428, -170, 1580, 1100); //1860,1200

        htpPage4 = new JLabel("");
        htpPage4.setIcon(new ImageIcon("./src/main/resources/images/htpPage4.png"));
        htpPage4.setBounds(428, -170, 1580, 1100); //1860,1200

        htpPage5 = new JLabel("");
        htpPage5.setIcon(new ImageIcon("./src/main/resources/images/htpPage5.png"));
        htpPage5.setBounds(428, -170, 1580, 1100); //1860,1200

        htpPage6 = new JLabel("");
        htpPage6.setIcon(new ImageIcon("./src/main/resources/images/htpPage6.png"));
        htpPage6.setBounds(428, -170, 1580, 1100); //1860,1200

        htpPage7 = new JLabel("");
        htpPage7.setIcon(new ImageIcon("./src/main/resources/images/htpPage7.png"));
        htpPage7.setBounds(428, -170, 1580, 1100); //1860,1200

        btnBackFromHtp = new JButton("");
        btnBackFromHtp.setBounds(35, 670, 280, 75);
        btnBackFromHtp.setContentAreaFilled(false);
        btnBackFromHtp.setBorderPainted(true);
        lblHowToPlayBackground.add(btnBackFromHtp);

        Border thick = new LineBorder(Color.red, 4);

        btnHtpPrev = new JButton("Previous Page");
        btnHtpPrev.setBounds(500, 620, 120, 50);
        btnHtpPrev.setContentAreaFilled(false);
        btnHtpPrev.setBorderPainted(true);
        btnHtpPrev.setBorder(thick);
        btnHtpPrev.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        lblHowToPlayBackground.add(btnHtpPrev);

        btnHtpNext = new JButton("Next Page");
        btnHtpNext.setBounds(900, 620, 120, 50);
        btnHtpNext.setContentAreaFilled(false);
        btnHtpNext.setBorderPainted(true);
        btnHtpNext.setBorder(thick);
        btnHtpNext.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        lblHowToPlayBackground.add(btnHtpNext);

        lblHowToPlayBackground.add(htpPage1);
        add(lblHowToPlayBackground);

        btnBackFromHtp.addActionListener(this);
        btnHtpNext.addActionListener(this);
        btnHtpPrev.addActionListener(this);
    }

    public void playSound(String soundName) {
        if(soundManager.isSoundOn()) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                FloatControl gainControl =
                        (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(soundManager.getVolume());
                clip.start();
            } catch (Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
        }
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnHtpNext)
        {
            if(htpPageNum == 1)
            {
                playSound("./src/main/resources/sounds/snd_changePage.wav");
                lblHowToPlayBackground.remove(htpPage1);
                lblHowToPlayBackground.add(htpPage2);
                htpPageNum++;
            }

            else if(htpPageNum == 2)
            {
                playSound("./src/main/resources/sounds/snd_changePage.wav");
                lblHowToPlayBackground.remove(htpPage2);
                lblHowToPlayBackground.add(htpPage3);
                htpPageNum++;
            }

            else if(htpPageNum == 3)
            {
                playSound("./src/main/resources/sounds/snd_changePage.wav");
                lblHowToPlayBackground.remove(htpPage3);
                lblHowToPlayBackground.add(htpPage4);
                htpPageNum++;
            }

            else if(htpPageNum == 4)
            {
                playSound("./src/main/resources/sounds/snd_changePage.wav");
                lblHowToPlayBackground.remove(htpPage4);
                lblHowToPlayBackground.add(htpPage5);
                htpPageNum++;
            }

            else if(htpPageNum == 5)
            {
                playSound("./src/main/resources/sounds/snd_changePage.wav");
                lblHowToPlayBackground.remove(htpPage5);
                lblHowToPlayBackground.add(htpPage6);
                htpPageNum++;
            }

            else if(htpPageNum == 6)
            {
                playSound("./src/main/resources/sounds/snd_changePage.wav");
                lblHowToPlayBackground.remove(htpPage6);
                lblHowToPlayBackground.add(htpPage7);
                htpPageNum++;
            }
        }

        if(e.getSource()==btnHtpPrev)
        {
            if(htpPageNum == 7)
            {
                playSound("./src/main/resources/sounds/snd_changePage.wav");
                lblHowToPlayBackground.remove(htpPage7);
                lblHowToPlayBackground.add(htpPage6);
                htpPageNum--;
            }

            else if(htpPageNum == 6)
            {
                playSound("./src/main/resources/sounds/snd_changePage.wav");
                lblHowToPlayBackground.remove(htpPage6);
                lblHowToPlayBackground.add(htpPage5);
                htpPageNum--;
            }

            else if(htpPageNum == 5)
            {
                playSound("./src/main/resources/sounds/snd_changePage.wav");
                lblHowToPlayBackground.remove(htpPage5);
                lblHowToPlayBackground.add(htpPage4);
                htpPageNum--;
            }

            else if(htpPageNum == 4)
            {
                playSound("./src/main/resources/sounds/snd_changePage.wav");
                lblHowToPlayBackground.remove(htpPage4);
                lblHowToPlayBackground.add(htpPage3);
                htpPageNum--;
            }

            else if(htpPageNum == 3)
            {
                playSound("./src/main/resources/sounds/snd_changePage.wav");
                lblHowToPlayBackground.remove(htpPage3);
                lblHowToPlayBackground.add(htpPage2);
                htpPageNum--;
            }

            else if(htpPageNum == 2)
            {
                playSound("./src/main/resources/sounds/snd_changePage.wav");
                lblHowToPlayBackground.remove(htpPage2);
                lblHowToPlayBackground.add(htpPage1);
                htpPageNum--;
            }
        }

        if(e.getSource() == btnBackFromHtp)
        {
            playSound("./src/main/resources/sounds/snd_howtoplay.wav");

            if(htpPageNum == 1)
            {
                lblHowToPlayBackground.remove(htpPage1);
            }

            if(htpPageNum == 2)
            {
                lblHowToPlayBackground.remove(htpPage2);
            }

            if(htpPageNum == 3)
            {
                lblHowToPlayBackground.remove(htpPage3);
            }

            if(htpPageNum == 4)
            {
                lblHowToPlayBackground.remove(htpPage4);
            }

            if(htpPageNum == 5)
            {
                lblHowToPlayBackground.remove(htpPage5);
            }

            if(htpPageNum == 6)
            {
                lblHowToPlayBackground.remove(htpPage6);
            }

            if(htpPageNum == 7)
            {
                lblHowToPlayBackground.remove(htpPage7);
            }

            setVisible(false);
            pnlMainMenu.setVisible(true);

        }

        repaint();
        revalidate();
    }
}