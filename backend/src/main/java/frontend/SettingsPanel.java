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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.File;

public class SettingsPanel extends JPanel implements ActionListener, ItemListener, ChangeListener {

    private JLabel lblSettingsBackground;
    private JButton btnBack, btnSave;
    private JPanel pnlMainMenu;
    private JCheckBox boxSound;
    private JSlider slider;
    private SoundManager soundManager;
    private int volume;

    public SettingsPanel(JPanel pnlMainMenu, SoundManager soundManager)
    {
        this.pnlMainMenu = pnlMainMenu;
        this.soundManager = soundManager;
        volume = soundManager.getVolume();
        setSize(1570, 800);

        lblSettingsBackground = new JLabel("");
        lblSettingsBackground.setIcon(new ImageIcon("./src/main/resources/images/settings.png"));
        lblSettingsBackground.setBounds(0, -165, 1580, 1100); //1860,1200

        btnBack = new JButton("");
        btnBack.setBounds(35, 670, 280, 70);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(true);
        lblSettingsBackground.add(btnBack);

        btnSave = new JButton("");
        btnSave.setBounds(687, 564, 180, 75);
        btnSave.setContentAreaFilled(false);
        btnSave.setBorderPainted(true);
        Border thick = new LineBorder(Color.black, 5);
        btnSave.setBorder(thick);
        lblSettingsBackground.add(btnSave);

        boxSound = new JCheckBox("");
        boxSound.setBounds(743,263,70,70);
        boxSound.setFont(new Font(Font.SERIF,Font.BOLD,40));
        boxSound.setContentAreaFilled(false);
        boxSound.setBorderPainted(true);
        Border thickbor = new LineBorder(Color.black, 3);
        boxSound.setBorder(thickbor);
        lblSettingsBackground.add(boxSound);
        boxSound.setSelected(soundManager.isSoundOn());


        slider = new JSlider(JSlider.HORIZONTAL,-40,5,-20);
        slider.setValue(soundManager.getVolume());
        slider.setBounds(688, 413, 180, 75);
        slider.setOpaque(false);
        slider.addChangeListener(new ChangeListener(){
        public void stateChanged(ChangeEvent e) {
            volume = slider.getValue();
        }
        });
        lblSettingsBackground.add(slider);

        add(lblSettingsBackground);

        btnBack.addActionListener(this);
        btnSave.addActionListener(this);
        boxSound.addItemListener(this);
        slider.addChangeListener(this);
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

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() ==  btnBack)
        {
            playSound("./src/main/resources/sounds/snd_howtoplay.wav");
            setVisible(false);
            pnlMainMenu.setVisible(true);
        }

        if (e.getSource() ==  btnSave)
        {
            if( ((LineBorder)btnSave.getBorder()).getLineColor() == Color.black )
            {
                playSound("./src/main/resources/sounds/snd_save.wav");
                soundManager.setSoundOn(boxSound.isSelected());
                soundManager.setVolume(volume);
                System.out.println(volume);
            }

            Border thick = new LineBorder(Color.green, 5);
            btnSave.setBorder(thick);
        }

        repaint();
        revalidate();
    }

    public void itemStateChanged(ItemEvent e)
    {
        if(e.getSource() == boxSound)
        {
            playSound("./src/main/resources/sounds/snd_changePage.wav");
            Border thick = new LineBorder(Color.black, 5);
            btnSave.setBorder(thick);
        }
        repaint();
        revalidate();
    }

    public void stateChanged(ChangeEvent e)
    {
        if(e.getSource()==slider)
        {
            playSound("./src/main/resources/sounds/snd_changePage.wav");
            Border thick = new LineBorder(Color.black, 5);
            btnSave.setBorder(thick);
        }
        repaint();
        revalidate();
    }
}
