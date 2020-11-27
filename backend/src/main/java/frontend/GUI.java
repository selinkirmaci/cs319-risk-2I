// Author: Alperen

package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

public class GUI extends JFrame implements ActionListener {
	
	private JFrame frame;
	
	private JPanel pnlMainMenu, pnlStartGameMenu, pnlHowToPlay, pnlCredits;
	
	private JButton btnStartGame, btnHowToPlay, btnQuitGame, btnCredits, btnBackFromCredits, btnBackFromStartGameMenu,btnContinueFromStartGameMenu;
	
	private JButton btnChooseColour1, btnChooseColour2, btnChooseColour3, btnChooseColour4;
	private JButton btnChooseAvatar1, btnChooseAvatar2, btnChooseAvatar3, btnChooseAvatar4;
	
	private JButton btnRed1, btnBlue1, btnYellow1, btnGreen1, btnCyan1, btnPink1;
	private JButton btnRed2, btnBlue2, btnYellow2, btnGreen2, btnCyan2, btnPink2;
	private JButton btnRed3, btnBlue3, btnYellow3, btnGreen3, btnCyan3, btnPink3;
	private JButton btnRed4, btnBlue4, btnYellow4, btnGreen4, btnCyan4, btnPink4;
	
	private JButton btnNapoleon1, btnAlexander1, btnHannibal1, btnCaesar1, btnCengiz1, btnSuleiman1;
	private JButton btnNapoleon2, btnAlexander2, btnSuleiman2, btnCengiz2, btnHannibal2, btnCaesar2;
	private JButton btnNapoleon3, btnAlexander3, btnSuleiman3, btnCengiz3, btnHannibal3, btnCaesar3;
	private JButton btnNapoleon4, btnAlexander4, btnSuleiman4, btnCengiz4, btnHannibal4, btnCaesar4;
	
	
	private JLabel lblSelectColourBackground, lblStartGameMenuBackground;
	private JLabel lblSelectAvatarBackground, lblSelectAvatarBackground2, lblSelectAvatarBackground3, lblSelectAvatarBackground4;
	
	private JLabel lblSuleiman, lblAlexander, lblHannibal, lblNapoleon, lblCengiz, lblCaesar;
	private JLabel lblNapoleon2, lblAlexander2, lblHannibal2, lblCaesar2, lblCengiz2, lblSuleiman2;
	private JLabel lblNapoleon3, lblAlexander3, lblHannibal3, lblCaesar3, lblCengiz3, lblSuleiman3;
	private JLabel lblNapoleon4, lblAlexander4, lblHannibal4, lblCaesar4, lblCengiz4, lblSuleiman4;
	
	private  JTextField t1, t2, t3, t4;
	
	private int avatarNoP1 = 0, avatarNoP2 = 0, avatarNoP3 = 0, avatarNoP4 = 0;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
					frame.setTitle("Risk");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setPreferredSize(new Dimension(1570,800)); //1570,800
					frame.setResizable(false);
					frame.pack();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() 
	{	
		// ###############  Main Menu Panel  ###############
		
		pnlMainMenu = new JPanel();
		pnlMainMenu.setPreferredSize(new Dimension(300,300));
		pnlMainMenu.setLayout(null);
		
		JLabel lblGameMenuBackground = new JLabel("");
		lblGameMenuBackground.setIcon(new ImageIcon("./src/main/resources/images/gameMenu.png"));
		lblGameMenuBackground.setBounds(0, -165, 1580, 1100); //1860,1200
		
		pnlMainMenu.add(lblGameMenuBackground);
		
		btnStartGame = new JButton("");
		btnStartGame.setBounds(580, 400, 400, 60);
		btnStartGame.setContentAreaFilled(false);
		btnStartGame.setBorderPainted(true);
		lblGameMenuBackground.add(btnStartGame);
		
		btnHowToPlay = new JButton("");
		btnHowToPlay.setBounds(590, 505, 400, 60);
		btnHowToPlay.setContentAreaFilled(false);
		btnHowToPlay.setBorderPainted(true);
		lblGameMenuBackground.add(btnHowToPlay);
		
		btnQuitGame = new JButton("");
		btnQuitGame.setBounds(590, 620, 400, 60);
		btnQuitGame.setContentAreaFilled(false);
		btnQuitGame.setBorderPainted(true);
		lblGameMenuBackground.add(btnQuitGame);
		
		btnCredits = new JButton("");
		btnCredits.setBounds(590, 740, 400, 60);
		
		//btnCredits.setOpaque(false);
		btnCredits.setContentAreaFilled(false);
		btnCredits.setBorderPainted(true);
		lblGameMenuBackground.add(btnCredits);
		
		pnlMainMenu.add(lblGameMenuBackground);
		
		add(pnlMainMenu);
		
		// ###############  End of Main Menu Panel  ###############
		
		
		
		// ###############  Credits Panel  ###############
		pnlCredits = new JPanel();
		pnlCredits.setPreferredSize(new Dimension(300,300));
		
		JLabel lblCreditsBackground = new JLabel("");
		lblCreditsBackground.setIcon(new ImageIcon("./src/main/resources/images/credits.png"));
		lblCreditsBackground.setBounds(0, -165, 1580, 1100); //1860,1200
		
		
		
		btnBackFromCredits = new JButton("");
		btnBackFromCredits.setBounds(35, 670, 280, 75);
		btnBackFromCredits.setContentAreaFilled(false);
		btnBackFromCredits.setBorderPainted(true);
		lblCreditsBackground.add(btnBackFromCredits);
		pnlCredits.add(lblCreditsBackground);
		
		// ###############  End of Credits Panel  ###############
		
		// ###############  Start Game Menu Panel  ###############
		
		pnlStartGameMenu = new JPanel();
		pnlStartGameMenu.setPreferredSize(new Dimension(300,300));
		
		
		lblStartGameMenuBackground = new JLabel("");
		lblStartGameMenuBackground.setIcon(new ImageIcon("./src/main/resources/images/startgamemenu.png"));
		lblStartGameMenuBackground.setBounds(0, -165, 1580, 1100); //1860,1200
		
		btnBackFromStartGameMenu = new JButton("");
		btnBackFromStartGameMenu.setBounds(25, 675, 200, 65);
		btnBackFromStartGameMenu.setContentAreaFilled(false);
		btnBackFromStartGameMenu.setBorderPainted(true);
		lblStartGameMenuBackground.add(btnBackFromStartGameMenu);
		
		btnContinueFromStartGameMenu = new JButton("");
		btnContinueFromStartGameMenu.setBounds(1210,680,340,70);
		btnContinueFromStartGameMenu.setContentAreaFilled(false);
		btnContinueFromStartGameMenu.setBorderPainted(true);
		lblStartGameMenuBackground.add(btnContinueFromStartGameMenu);
		
		btnChooseColour1 = new JButton("Select Colour");
		btnChooseColour1.setBounds(797, 220, 205, 97);
		btnChooseColour1.setFont(new Font(Font.SERIF,Font.BOLD,16));
		btnChooseColour1.setForeground(Color.BLACK);
		lblStartGameMenuBackground.add(btnChooseColour1);
		
		btnChooseColour2 = new JButton("Select Colour");
		btnChooseColour2.setBounds(797, 330, 205, 97);
		btnChooseColour2.setFont(new Font(Font.SERIF,Font.BOLD,16));
		btnChooseColour2.setForeground(Color.BLACK);
		lblStartGameMenuBackground.add(btnChooseColour2);
		
		btnChooseColour3 = new JButton("Select Colour");
		btnChooseColour3.setBounds(797, 445, 205, 97);
		btnChooseColour3.setFont(new Font(Font.SERIF,Font.BOLD,16));
		btnChooseColour3.setForeground(Color.BLACK);
		lblStartGameMenuBackground.add(btnChooseColour3);
		
		btnChooseColour4 = new JButton("Select Colour");
		btnChooseColour4.setBounds(797, 560, 205, 97);
		btnChooseColour4.setFont(new Font(Font.SERIF,Font.BOLD,16));
		btnChooseColour4.setForeground(Color.BLACK);
		lblStartGameMenuBackground.add(btnChooseColour4);
		
		btnChooseAvatar1 = new JButton("Select Avatar");
		btnChooseAvatar1.setBounds(1015, 217, 185, 104);
		btnChooseAvatar1.setFont(new Font(Font.SERIF,Font.BOLD,16));
		btnChooseAvatar1.setForeground(Color.BLACK);
		lblStartGameMenuBackground.add(btnChooseAvatar1);
		
		btnChooseAvatar2 = new JButton("Select Avatar");
		btnChooseAvatar2.setBounds(1015, 326, 185, 103);
		btnChooseAvatar2.setFont(new Font(Font.SERIF,Font.BOLD,16));
		btnChooseAvatar2.setForeground(Color.BLACK);
		lblStartGameMenuBackground.add(btnChooseAvatar2);
		
		btnChooseAvatar3 = new JButton("Select Avatar");
		btnChooseAvatar3.setBounds(1015, 440, 185, 103);
		btnChooseAvatar3.setFont(new Font(Font.SERIF,Font.BOLD,16));
		btnChooseAvatar3.setForeground(Color.BLACK);
		lblStartGameMenuBackground.add(btnChooseAvatar3);
		
		btnChooseAvatar4 = new JButton("Select Avatar");
		btnChooseAvatar4.setBounds(1015, 558, 185, 100);
		btnChooseAvatar4.setFont(new Font(Font.SERIF,Font.BOLD,16));
		btnChooseAvatar4.setForeground(Color.BLACK);
		lblStartGameMenuBackground.add(btnChooseAvatar4);
		
		
		pnlStartGameMenu.add(lblStartGameMenuBackground);
		
		// ###############  End of Start Game Menu Panel  ###############
		
		// ############### Select Colour Panel  ###############
		
		//pnlSelectColour = new JPanel(); ///
		//pnlSelectColour.setPreferredSize(new Dimension(50,50));///
		lblSelectColourBackground = new JLabel("");
		lblSelectColourBackground.setIcon(new ImageIcon("./src/main/resources/images/drawio1.png"));
		lblSelectColourBackground.setBounds(1260, -40, 1580, 1100); // 650, -165, 1580, 1100
		
		btnRed1 = new JButton("");
		btnRed1.setBounds(25, 485, 42, 42);
		btnRed1.setBackground(Color.RED);
		lblSelectColourBackground.add(btnRed1);
		btnRed1.setVisible(false);
		
		
		btnBlue1 = new JButton("");
		btnBlue1.setBounds(105, 485, 42, 42);
		btnBlue1.setBackground(Color.BLUE);
		lblSelectColourBackground.add(btnBlue1);
		btnBlue1.setVisible(false);
	
		
		btnYellow1 = new JButton("");
		btnYellow1.setBounds(185, 485, 42, 42);
		btnYellow1.setBackground(Color.YELLOW);
		lblSelectColourBackground.add(btnYellow1);
		btnYellow1.setVisible(false);
		
		btnGreen1 = new JButton("");
		btnGreen1.setBounds(25, 565, 42, 42);
		btnGreen1.setBackground(Color.GREEN);
		lblSelectColourBackground.add(btnGreen1);
		btnGreen1.setVisible(false);
		
		btnCyan1 = new JButton("");
		btnCyan1.setBounds(105, 565, 42, 42);
		btnCyan1.setBackground(Color.CYAN);
		lblSelectColourBackground.add(btnCyan1);
		btnCyan1.setVisible(false);
		
		btnPink1 = new JButton("");
		btnPink1.setBounds(185, 565, 42, 42);
		btnPink1.setBackground(Color.PINK);
		lblSelectColourBackground.add(btnPink1);
		btnPink1.setVisible(false);
		
		btnRed2 = new JButton("");
		btnRed2.setBounds(25, 485, 42, 42);
		btnRed2.setBackground(Color.RED);
		lblSelectColourBackground.add(btnRed2);
		btnRed2.setVisible(false);
		
		btnBlue2 = new JButton("");
		btnBlue2.setBounds(105, 485, 42, 42);
		btnBlue2.setBackground(Color.BLUE);
		lblSelectColourBackground.add(btnBlue2);
		btnBlue2.setVisible(false);
		
		btnYellow2 = new JButton("");
		btnYellow2.setBounds(185, 485, 42, 42);
		btnYellow2.setBackground(Color.YELLOW);
		lblSelectColourBackground.add(btnYellow2);
		btnYellow2.setVisible(false);
		
		btnGreen2 = new JButton("");
		btnGreen2.setBounds(25, 565, 42, 42);
		btnGreen2.setBackground(Color.GREEN);
		lblSelectColourBackground.add(btnGreen2);
		btnGreen2.setVisible(false);
		
		btnCyan2 = new JButton("");
		btnCyan2.setBounds(105, 565, 42, 42);
		btnCyan2.setBackground(Color.CYAN);
		lblSelectColourBackground.add(btnCyan2);
		btnCyan2.setVisible(false);
		
		btnPink2 = new JButton("");
		btnPink2.setBounds(185, 565, 42, 42);
		btnPink2.setBackground(Color.PINK);
		lblSelectColourBackground.add(btnPink2);
		btnPink2.setVisible(false);
		
		btnRed3 = new JButton("");
		btnRed3.setBounds(25, 485, 42, 42);
		btnRed3.setBackground(Color.RED);
		lblSelectColourBackground.add(btnRed3);
		btnRed3.setVisible(false);
		
		btnBlue3 = new JButton("");
		btnBlue3.setBounds(105, 485, 42, 42);
		btnBlue3.setBackground(Color.BLUE);
		lblSelectColourBackground.add(btnBlue3);
		btnBlue3.setVisible(false);
		
		btnYellow3 = new JButton("");
		btnYellow3.setBounds(185, 485, 42, 42);
		btnYellow3.setBackground(Color.YELLOW);
		lblSelectColourBackground.add(btnYellow3);
		btnYellow3.setVisible(false);
		
		btnGreen3 = new JButton("");
		btnGreen3.setBounds(25, 565, 42, 42);
		btnGreen3.setBackground(Color.GREEN);
		lblSelectColourBackground.add(btnGreen3);
		btnGreen3.setVisible(false);
		
		btnCyan3 = new JButton("");
		btnCyan3.setBounds(105, 565, 42, 42);
		btnCyan3.setBackground(Color.CYAN);
		lblSelectColourBackground.add(btnCyan3);
		btnCyan3.setVisible(false);
		
		btnPink3 = new JButton("");
		btnPink3.setBounds(185, 565, 42, 42);
		btnPink3.setBackground(Color.PINK);
		lblSelectColourBackground.add(btnPink3);
		btnPink3.setVisible(false);
		
		btnRed4 = new JButton("");
		btnRed4.setBounds(25, 485, 42, 42);
		btnRed4.setBackground(Color.RED);
		lblSelectColourBackground.add(btnRed4);
		btnRed4.setVisible(false);
		
		btnBlue4 = new JButton("");
		btnBlue4.setBounds(105, 485, 42, 42);
		btnBlue4.setBackground(Color.BLUE);
		lblSelectColourBackground.add(btnBlue4);
		btnBlue4.setVisible(false);
		
		btnYellow4 = new JButton("");
		btnYellow4.setBounds(185, 485, 42, 42);
		btnYellow4.setBackground(Color.YELLOW);
		lblSelectColourBackground.add(btnYellow4);
		btnYellow4.setVisible(false);
		
		btnGreen4 = new JButton("");
		btnGreen4.setBounds(25, 565, 42, 42);
		btnGreen4.setBackground(Color.GREEN);
		lblSelectColourBackground.add(btnGreen4);
		btnGreen4.setVisible(false);
		
		btnCyan4 = new JButton("");
		btnCyan4.setBounds(105, 565, 42, 42);
		btnCyan4.setBackground(Color.CYAN);
		lblSelectColourBackground.add(btnCyan4);
		btnCyan4.setVisible(false);
		
		btnPink4 = new JButton("");
		btnPink4.setBounds(185, 565, 42, 42);
		btnPink4.setBackground(Color.PINK);
		lblSelectColourBackground.add(btnPink4);
		btnPink4.setVisible(false);
		
		// ###############  End of Select Colour Panel  ###############
		
		// ###############  Avatar Select ###################
		
		lblSelectAvatarBackground = new JLabel("");
		lblSelectAvatarBackground.setIcon(new ImageIcon("./src/main/resources/images/avatarmenu2.png"));
		lblSelectAvatarBackground.setBounds(80, -80, 1580, 1100); // 1260 -40 1580 1100
		
		lblSelectAvatarBackground2 = new JLabel("");
		lblSelectAvatarBackground2.setIcon(new ImageIcon("./src/main/resources/images/avatarmenu2.png"));
		lblSelectAvatarBackground2.setBounds(80, -80, 1580, 1100); // 1260 -40 1580 1100
		
		lblSelectAvatarBackground3 = new JLabel("");
		lblSelectAvatarBackground3.setIcon(new ImageIcon("./src/main/resources/images/avatarmenu2.png"));
		lblSelectAvatarBackground3.setBounds(80, -80, 1580, 1100); // 1260 -40 1580 1100
		
		lblSelectAvatarBackground4 = new JLabel("");
		lblSelectAvatarBackground4.setIcon(new ImageIcon("./src/main/resources/images/avatarmenu2.png"));
		lblSelectAvatarBackground4.setBounds(80, -80, 1580, 1100); // 1260 -40 1580 1100
		
		lblSuleiman = new JLabel("");
		lblSuleiman.setIcon(new ImageIcon("./src/main/resources/images/suleiman_p.png"));
		lblSuleiman.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		lblSuleiman2= new JLabel("");
		lblSuleiman2.setIcon(new ImageIcon("./src/main/resources/images/suleiman_p.png"));
		lblSuleiman2.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		lblSuleiman3= new JLabel("");
		lblSuleiman3.setIcon(new ImageIcon("./src/main/resources/images/suleiman_p.png"));
		lblSuleiman3.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		lblSuleiman4= new JLabel("");
		lblSuleiman4.setIcon(new ImageIcon("./src/main/resources/images/suleiman_p.png"));
		lblSuleiman4.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		
		lblNapoleon = new JLabel("");
		lblNapoleon.setIcon(new ImageIcon("./src/main/resources/images/napoleon_p.png"));
		lblNapoleon.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		lblNapoleon2 = new JLabel("");
		lblNapoleon2.setIcon(new ImageIcon("./src/main/resources/images/napoleon_p.png"));
		lblNapoleon2.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		lblNapoleon3 = new JLabel("");
		lblNapoleon3.setIcon(new ImageIcon("./src/main/resources/images/napoleon_p.png"));
		lblNapoleon3.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		lblNapoleon4 = new JLabel("");
		lblNapoleon4.setIcon(new ImageIcon("./src/main/resources/images/napoleon_p.png"));
		lblNapoleon4.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		
		lblAlexander = new JLabel("");
		lblAlexander.setIcon(new ImageIcon("./src/main/resources/images/alexander_p.png"));
		lblAlexander.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		lblAlexander2 = new JLabel("");
		lblAlexander2.setIcon(new ImageIcon("./src/main/resources/images/alexander_p.png"));
		lblAlexander2.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		lblAlexander3 = new JLabel("");
		lblAlexander3.setIcon(new ImageIcon("./src/main/resources/images/alexander_p.png"));
		lblAlexander3.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		lblAlexander4 = new JLabel("");
		lblAlexander4.setIcon(new ImageIcon("./src/main/resources/images/alexander_p.png"));
		lblAlexander4.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		
		lblHannibal = new JLabel("");
		lblHannibal.setIcon(new ImageIcon("./src/main/resources/images/hannibal_p.png"));
		lblHannibal.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		lblHannibal2 = new JLabel("");
		lblHannibal2.setIcon(new ImageIcon("./src/main/resources/images/hannibal_p.png"));
		lblHannibal2.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		lblHannibal3 = new JLabel("");
		lblHannibal3.setIcon(new ImageIcon("./src/main/resources/images/hannibal_p.png"));
		lblHannibal3.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		lblHannibal4 = new JLabel("");
		lblHannibal4.setIcon(new ImageIcon("./src/main/resources/images/hannibal_p.png"));
		lblHannibal4.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		
		lblCaesar = new JLabel("");
		lblCaesar.setIcon(new ImageIcon("./src/main/resources/images/caesar_p.png"));
		lblCaesar.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		lblCaesar2 = new JLabel("");
		lblCaesar2.setIcon(new ImageIcon("./src/main/resources/images/caesar_p.png"));
		lblCaesar2.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		lblCaesar3 = new JLabel("");
		lblCaesar3.setIcon(new ImageIcon("./src/main/resources/images/caesar_p.png"));
		lblCaesar3.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		lblCaesar4 = new JLabel("");
		lblCaesar4.setIcon(new ImageIcon("./src/main/resources/images/caesar_p.png"));
		lblCaesar4.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		
		lblCengiz = new JLabel("");
		lblCengiz.setIcon(new ImageIcon("./src/main/resources/images/cengiz_p.png"));
		lblCengiz.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		lblCengiz2 = new JLabel("");
		lblCengiz2.setIcon(new ImageIcon("./src/main/resources/images/cengiz_p.png"));
		lblCengiz2.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		lblCengiz3 = new JLabel("");
		lblCengiz3.setIcon(new ImageIcon("./src/main/resources/images/cengiz_p.png"));
		lblCengiz3.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		lblCengiz4 = new JLabel("");
		lblCengiz4.setIcon(new ImageIcon("./src/main/resources/images/cengiz_p.png"));
		lblCengiz4.setBounds(80, -80, 1580, 1100); // 80 -80 1580 1100
		
		
		btnNapoleon1 = new JButton("");
		btnNapoleon1.setBounds(150, 326, 172, 172);
		btnNapoleon1.setContentAreaFilled(false);
		btnNapoleon1.setBorderPainted(true);
		lblSelectAvatarBackground.add(btnNapoleon1);
		
		btnAlexander1 = new JButton("");
		btnAlexander1.setBounds(377, 326, 172, 172);
		btnAlexander1.setContentAreaFilled(false);
		btnAlexander1.setBorderPainted(true);
		lblSelectAvatarBackground.add(btnAlexander1);
		
		btnHannibal1 = new JButton("");
		btnHannibal1.setBounds(604, 326, 172, 172);
		btnHannibal1.setContentAreaFilled(false);
		btnHannibal1.setBorderPainted(true);
		lblSelectAvatarBackground.add(btnHannibal1);
		
		btnCaesar1 = new JButton("");
		btnCaesar1.setBounds(150, 558, 172, 172);
		btnCaesar1.setContentAreaFilled(false);
		btnCaesar1.setBorderPainted(true);
		lblSelectAvatarBackground.add(btnCaesar1);
		
		btnCengiz1 = new JButton("");
		btnCengiz1.setBounds(377, 558, 172, 172);
		btnCengiz1.setContentAreaFilled(false);
		btnCengiz1.setBorderPainted(true);
		lblSelectAvatarBackground.add(btnCengiz1);

		btnSuleiman1 = new JButton("");
		btnSuleiman1.setBounds(604, 558, 172, 172);
		btnSuleiman1.setContentAreaFilled(false);
		btnSuleiman1.setBorderPainted(true);
		lblSelectAvatarBackground.add(btnSuleiman1);
		
		btnNapoleon2 = new JButton("");
		btnNapoleon2.setBounds(150, 326, 172, 172);
		btnNapoleon2.setContentAreaFilled(false);
		btnNapoleon2.setBorderPainted(true);
		lblSelectAvatarBackground2.add(btnNapoleon2);
		
		btnAlexander2 = new JButton("");
		btnAlexander2.setBounds(377, 326, 172, 172);
		btnAlexander2.setContentAreaFilled(false);
		btnAlexander2.setBorderPainted(true);
		lblSelectAvatarBackground2.add(btnAlexander2);
		
		btnHannibal2 = new JButton("");
		btnHannibal2.setBounds(604, 326, 172, 172);
		btnHannibal2.setContentAreaFilled(false);
		btnHannibal2.setBorderPainted(true);
		lblSelectAvatarBackground2.add(btnHannibal2);
		
		btnCaesar2 = new JButton("");
		btnCaesar2.setBounds(150, 558, 172, 172);
		btnCaesar2.setContentAreaFilled(false);
		btnCaesar2.setBorderPainted(true);
		lblSelectAvatarBackground2.add(btnCaesar2);
		
		btnCengiz2 = new JButton("");
		btnCengiz2.setBounds(377, 558, 172, 172);
		btnCengiz2.setContentAreaFilled(false);
		btnCengiz2.setBorderPainted(true);
		lblSelectAvatarBackground2.add(btnCengiz2);
		
		btnSuleiman2 = new JButton("");
		btnSuleiman2.setBounds(604, 558, 172, 172);
		btnSuleiman2.setContentAreaFilled(false);
		btnSuleiman2.setBorderPainted(true);
		lblSelectAvatarBackground2.add(btnSuleiman2);
		
		
		btnNapoleon3 = new JButton("");
		btnNapoleon3.setBounds(150, 326, 172, 172);
		btnNapoleon3.setContentAreaFilled(false);
		btnNapoleon3.setBorderPainted(true);
		lblSelectAvatarBackground3.add(btnNapoleon3);
		
		btnAlexander3 = new JButton("");
		btnAlexander3.setBounds(377, 326, 172, 172);
		btnAlexander3.setContentAreaFilled(false);
		btnAlexander3.setBorderPainted(true);
		lblSelectAvatarBackground3.add(btnAlexander3);
		
		btnHannibal3 = new JButton("");
		btnHannibal3.setBounds(604, 326, 172, 172);
		btnHannibal3.setContentAreaFilled(false);
		btnHannibal3.setBorderPainted(true);
		lblSelectAvatarBackground3.add(btnHannibal3);
		
		btnCaesar3 = new JButton("");
		btnCaesar3.setBounds(150, 558, 172, 172);
		btnCaesar3.setContentAreaFilled(false);
		btnCaesar3.setBorderPainted(true);
		lblSelectAvatarBackground3.add(btnCaesar3);
		
		btnCengiz3 = new JButton("");
		btnCengiz3.setBounds(377, 558, 172, 172);
		btnCengiz3.setContentAreaFilled(false);
		btnCengiz3.setBorderPainted(true);
		lblSelectAvatarBackground3.add(btnCengiz3);
		
		btnSuleiman3 = new JButton("");
		btnSuleiman3.setBounds(604, 558, 172, 172);
		btnSuleiman3.setContentAreaFilled(false);
		btnSuleiman3.setBorderPainted(true);
		lblSelectAvatarBackground3.add(btnSuleiman3);
		
		btnNapoleon4 = new JButton("");
		btnNapoleon4.setBounds(150, 326, 172, 172);
		btnNapoleon4.setContentAreaFilled(false);
		btnNapoleon4.setBorderPainted(true);
		lblSelectAvatarBackground4.add(btnNapoleon4);
		
		btnAlexander4 = new JButton("");
		btnAlexander4.setBounds(377, 326, 172, 172);
		btnAlexander4.setContentAreaFilled(false);
		btnAlexander4.setBorderPainted(true);
		lblSelectAvatarBackground4.add(btnAlexander4);
		
		btnHannibal4 = new JButton("");
		btnHannibal4.setBounds(604, 326, 172, 172);
		btnHannibal4.setContentAreaFilled(false);
		btnHannibal4.setBorderPainted(true);
		lblSelectAvatarBackground4.add(btnHannibal4);
		
		btnCaesar4 = new JButton("");
		btnCaesar4.setBounds(150, 558, 172, 172);
		btnCaesar4.setContentAreaFilled(false);
		btnCaesar4.setBorderPainted(true);
		lblSelectAvatarBackground4.add(btnCaesar4);
		
		btnCengiz4 = new JButton("");
		btnCengiz4.setBounds(377, 558, 172, 172);
		btnCengiz4.setContentAreaFilled(false);
		btnCengiz4.setBorderPainted(true);
		lblSelectAvatarBackground4.add(btnCengiz4);
		
		btnSuleiman4 = new JButton("");
		btnSuleiman4.setBounds(604, 558, 172, 172);
		btnSuleiman4.setContentAreaFilled(false);
		btnSuleiman4.setBorderPainted(true);
		lblSelectAvatarBackground4.add(btnSuleiman4);
		
		
		// ############### End of Avatar Select ###############
		
		//################# Textfields ##########################
		   
		 t1=new JTextField("");  
		 t1.setBounds(90, 235, 635, 63);  
		 lblStartGameMenuBackground.add(t1);
		 t1.setHorizontalAlignment(JTextField.CENTER);
		 t1.setFont(new Font(Font.SERIF,Font.BOLD,25));
		 
		 t1.setBackground(Color.BLACK);
		 
		 t2=new JTextField("");  
		 t2.setBounds(90, 347, 635, 69);  
		 lblStartGameMenuBackground.add(t2);
		 t2.setHorizontalAlignment(JTextField.CENTER);
		 t2.setFont(new Font(Font.SERIF,Font.BOLD,25));
		 
		 t2.setBackground(Color.BLACK);
		 
		 t3=new JTextField("");  
		 t3.setBounds(92, 460, 638, 66);  
		 lblStartGameMenuBackground.add(t3);
		 t3.setHorizontalAlignment(JTextField.CENTER);
		 t3.setFont(new Font(Font.SERIF,Font.BOLD,25));
		
		 t3.setBackground(Color.BLACK);
		 
		 t4=new JTextField("");  
		 t4.setBounds(99, 575, 635, 63);  
		 lblStartGameMenuBackground.add(t4);
		 t4.setHorizontalAlignment(JTextField.CENTER);
		 t4.setFont(new Font(Font.SERIF,Font.BOLD,25));
		
		 t4.setBackground(Color.BLACK);
		 
		
		//############### End of textfield #######
		
		
		// ######## ACTION LISTENERS ###########
		
		btnCredits.addActionListener(this);
		btnBackFromCredits.addActionListener(this);
		btnQuitGame.addActionListener(this);
		btnHowToPlay.addActionListener(this);
		btnStartGame.addActionListener(this);
		btnBackFromStartGameMenu.addActionListener(this);
		btnContinueFromStartGameMenu.addActionListener(this);
		
		btnChooseColour1.addActionListener(this);
		btnChooseColour2.addActionListener(this);
		btnChooseColour3.addActionListener(this);
		btnChooseColour4.addActionListener(this);
		
		btnChooseAvatar1.addActionListener(this);
		btnChooseAvatar2.addActionListener(this);
		btnChooseAvatar3.addActionListener(this);
		btnChooseAvatar4.addActionListener(this);
		
		btnNapoleon1.addActionListener(this);
		btnAlexander1.addActionListener(this);
		btnHannibal1.addActionListener(this);
		btnCaesar1.addActionListener(this);
		btnCengiz1.addActionListener(this);
		btnSuleiman1.addActionListener(this);
		
		btnNapoleon2.addActionListener(this);
		btnAlexander2.addActionListener(this);
		btnHannibal2.addActionListener(this);
		btnCaesar2.addActionListener(this);
		btnCengiz2.addActionListener(this);
		btnSuleiman2.addActionListener(this);
		
		btnNapoleon3.addActionListener(this);
		btnAlexander3.addActionListener(this);
		btnHannibal3.addActionListener(this);
		btnCaesar3.addActionListener(this);
		btnCengiz3.addActionListener(this);
		btnSuleiman3.addActionListener(this);
		
		btnNapoleon4.addActionListener(this);
		btnAlexander4.addActionListener(this);
		btnHannibal4.addActionListener(this);
		btnCaesar4.addActionListener(this);
		btnCengiz4.addActionListener(this);
		btnSuleiman4.addActionListener(this);
		
		
		btnRed1.addActionListener(this);
		btnBlue1.addActionListener(this);
		btnYellow1.addActionListener(this);
		btnGreen1.addActionListener(this);
		btnCyan1.addActionListener(this);
		btnPink1.addActionListener(this);
		
		btnRed2.addActionListener(this);
		btnBlue2.addActionListener(this);
		btnYellow2.addActionListener(this);
		btnGreen2.addActionListener(this);
		btnCyan2.addActionListener(this);
		btnPink2.addActionListener(this);
		
		btnRed3.addActionListener(this);
		btnBlue3.addActionListener(this);
		btnYellow3.addActionListener(this);
		btnGreen3.addActionListener(this);
		btnCyan3.addActionListener(this);
		btnPink3.addActionListener(this);
		
		btnRed4.addActionListener(this);
		btnBlue4.addActionListener(this);
		btnYellow4.addActionListener(this);
		btnGreen4.addActionListener(this);
		btnCyan4.addActionListener(this);
		btnPink4.addActionListener(this);
		
	}
	
	// ############ Method For Playing Sound Effects ###############
	
	public void playSound(String soundName)
	 {
	   try 
	   {
	    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
	    Clip clip = AudioSystem.getClip( );
	    clip.open(audioInputStream);
	    clip.start( );
	   }
	   catch(Exception ex)
	   {
	     System.out.println("Error with playing sound.");
	     ex.printStackTrace( );
	   }
	 }
	
	// ############## End of Play Sound Method ################
	
	// ############## Action Events ###########################
	
	public void actionPerformed(ActionEvent e)
	{
		JButton btnDefaultColor = new JButton("");
		Color cDef = btnDefaultColor.getBackground();
		
		if(e.getSource()==btnCredits)
		{
			
			remove(pnlMainMenu);
			add(pnlCredits);
			
			playSound("./src/main/resources/sounds/snd_howtoplay.wav");
			
			
		}
		
		if(e.getSource()==btnStartGame)
		{
			remove(pnlMainMenu);
			add(pnlStartGameMenu);
			playSound("./src/main/resources/sounds/snd_startGame.wav");
			
			
		}
		
		if(e.getSource()==btnBackFromStartGameMenu)
		{
			playSound("./src/main/resources/sounds/snd_howtoplay.wav");
			
			remove(pnlStartGameMenu);
			add(pnlMainMenu);
			btnChooseColour1.setBackground(null);
			btnChooseColour2.setBackground(null);
			btnChooseColour3.setBackground(null);
			btnChooseColour4.setBackground(null);
			
			btnChooseColour1.setText("Select Colour");
			btnChooseColour2.setText("Select Colour");
			btnChooseColour3.setText("Select Colour");
			btnChooseColour4.setText("Select Colour");
			
			btnChooseAvatar1.remove(lblAlexander);
			btnChooseAvatar1.remove(lblSuleiman);
			btnChooseAvatar1.remove(lblCengiz);
			btnChooseAvatar1.remove(lblCaesar);
			btnChooseAvatar1.remove(lblNapoleon);
			btnChooseAvatar1.remove(lblHannibal);		
			
			btnChooseAvatar2.remove(lblAlexander2);
			btnChooseAvatar2.remove(lblSuleiman2);
			btnChooseAvatar2.remove(lblCengiz2);
			btnChooseAvatar2.remove(lblCaesar2);
			btnChooseAvatar2.remove(lblNapoleon2);
			btnChooseAvatar2.remove(lblHannibal2);		
			
			btnChooseAvatar3.remove(lblAlexander3);
			btnChooseAvatar3.remove(lblSuleiman3);
			btnChooseAvatar3.remove(lblCengiz3);
			btnChooseAvatar3.remove(lblCaesar3);
			btnChooseAvatar3.remove(lblNapoleon3);
			btnChooseAvatar3.remove(lblHannibal3);	
			
			btnChooseAvatar4.remove(lblAlexander4);
			btnChooseAvatar4.remove(lblSuleiman4);
			btnChooseAvatar4.remove(lblCengiz4);
			btnChooseAvatar4.remove(lblCaesar4);
			btnChooseAvatar4.remove(lblNapoleon4);
			btnChooseAvatar4.remove(lblHannibal4);		
			
			t1.setForeground(null);
			t2.setForeground(null);
			t3.setForeground(null);
			t4.setForeground(null);
			
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t4.setText("");
			
			btnChooseAvatar1.setHorizontalAlignment(JTextField.CENTER);
			btnChooseAvatar2.setHorizontalAlignment(JTextField.CENTER);
			btnChooseAvatar3.setHorizontalAlignment(JTextField.CENTER);
			btnChooseAvatar4.setHorizontalAlignment(JTextField.CENTER);
			
			btnChooseAvatar1.setText("Select Avatar");
			btnChooseAvatar2.setText("Select Avatar");
			btnChooseAvatar3.setText("Select Avatar");
			btnChooseAvatar4.setText("Select Avatar");
			
			btnChooseAvatar1.setFont(new Font(Font.SERIF,Font.BOLD,16));
			btnChooseAvatar2.setFont(new Font(Font.SERIF,Font.BOLD,16));
			btnChooseAvatar3.setFont(new Font(Font.SERIF,Font.BOLD,16));
			btnChooseAvatar4.setFont(new Font(Font.SERIF,Font.BOLD,16));
			
			btnChooseAvatar1.setForeground(Color.BLACK);
			btnChooseAvatar2.setForeground(Color.BLACK);
			btnChooseAvatar3.setForeground(Color.BLACK);
			btnChooseAvatar4.setForeground(Color.BLACK);
			
			avatarNoP1 = 0;
			avatarNoP2 = 0;
			avatarNoP3 = 0;
			avatarNoP4 = 0;
			
		}
		
		if(e.getSource()==btnContinueFromStartGameMenu)
		{
			Color c1 = btnChooseColour1.getBackground();
			Color c2 = btnChooseColour2.getBackground();
			Color c3 = btnChooseColour3.getBackground();
			Color c4 = btnChooseColour4.getBackground();
			
			JButton x = new JButton("");
			Color c5 = x.getBackground();
			
			String s1 = t1.getText();
			String s2 = t2.getText();
			String s3 = t3.getText();
			String s4 = t4.getText();
			
			
			if( c1.equals(c5) || c2.equals(c5) || c3.equals(c5) || c4.equals(c5))
			{
				playSound("./src/main/resources/sounds/snd_error.wav");
				JOptionPane.showMessageDialog(null, "Every player must choose a color.");
				
			}
			
			
			else if( c1.equals(c2) || c1.equals(c3) || c1.equals(c4) || c2.equals(c3) || c2.equals(c4) || c3.equals(c4) )
			{
				playSound("./src/main/resources/sounds/snd_error.wav");
				JOptionPane.showMessageDialog(null, "Colors must be unique for each player.");
			}
			
			else if( avatarNoP1 == 0 || avatarNoP2 == 0 || avatarNoP3 == 0 || avatarNoP4 == 0 )
			{
				playSound("./src/main/resources/sounds/snd_error.wav");
				JOptionPane.showMessageDialog(null, "Every player must have an avatar.");
			}
			
			else if( avatarNoP1 == avatarNoP2 || avatarNoP1 == avatarNoP3 || avatarNoP1 == avatarNoP4 || avatarNoP2 == avatarNoP3 || avatarNoP2 == avatarNoP4 || avatarNoP3 == avatarNoP4 )
			{
				playSound("./src/main/resources/sounds/snd_error.wav");
				JOptionPane.showMessageDialog(null, "Avatars must be unique for each player.");
				
			}
			
			else if( s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") )
			{
				playSound("./src/main/resources/sounds/snd_error.wav");
				JOptionPane.showMessageDialog(null, "Every player must enter a nickname.");
			}
			
			else if( s1.equals(s2) || s1.equals(s3) || s1.equals(s4) || s2.equals(s3) || s2.equals(s4) || s3.equals(s4) )
			{
				playSound("./src/main/resources/sounds/snd_error.wav");
				JOptionPane.showMessageDialog(null, "Nicknames must be unique for each player.");
			}
			
			else
			{
				playSound("./src/main/resources/sounds/snd_victory.wav");
				JOptionPane.showMessageDialog(null, "Successful");
				
			}
		}
		
		if(e.getSource()==btnBackFromCredits)
		{
			playSound("./src/main/resources/sounds/snd_howtoplay.wav");
			remove(pnlCredits);
			add(pnlMainMenu);
				
		}
		
		if(e.getSource()==btnHowToPlay)
		{
			playSound("./src/main/resources/sounds/snd_howtoplay.wav");
			
				
		}
		
		if(e.getSource()==btnChooseColour1)
		{	
			lblStartGameMenuBackground.add(lblSelectColourBackground);
			
			
			btnBackFromStartGameMenu.setEnabled(false);
			btnChooseColour1.setEnabled(false);
			btnChooseColour2.setEnabled(false);
			btnChooseColour3.setEnabled(false);
			btnChooseColour4.setEnabled(false);
			
			btnChooseAvatar1.setEnabled(false);
			btnChooseAvatar2.setEnabled(false);
			btnChooseAvatar3.setEnabled(false);
			btnChooseAvatar4.setEnabled(false);
			
			t1.setEnabled(false);
			t2.setEnabled(false);
			t3.setEnabled(false);
			t4.setEnabled(false);
			
			btnRed1.setVisible(true);
			btnBlue1.setVisible(true);
			btnYellow1.setVisible(true);
			btnGreen1.setVisible(true);
			btnCyan1.setVisible(true);
			btnPink1.setVisible(true);
			
			btnContinueFromStartGameMenu.setEnabled(false);
			
			
		}
		
		if(e.getSource()==btnChooseColour2)
		{	
			lblStartGameMenuBackground.add(lblSelectColourBackground);
			btnBackFromStartGameMenu.setEnabled(false);	
			
			btnChooseColour1.setEnabled(false);
			btnChooseColour2.setEnabled(false);
			btnChooseColour3.setEnabled(false);
			btnChooseColour4.setEnabled(false);
			
			btnChooseAvatar1.setEnabled(false);
			btnChooseAvatar2.setEnabled(false);
			btnChooseAvatar3.setEnabled(false);
			btnChooseAvatar4.setEnabled(false);
			
			t1.setEnabled(false);
			t2.setEnabled(false);
			t3.setEnabled(false);
			t4.setEnabled(false);
			
			btnRed1.setVisible(false);
			btnRed2.setVisible(true);
			
			btnBlue1.setVisible(false);
			btnBlue2.setVisible(true);
			
			btnYellow1.setVisible(false);
			btnYellow2.setVisible(true);
			
			btnGreen1.setVisible(false);
			btnGreen2.setVisible(true);
			
			btnCyan1.setVisible(false);
			btnCyan2.setVisible(true);
			
			btnPink1.setVisible(false);
			btnPink2.setVisible(true);
			
			btnContinueFromStartGameMenu.setEnabled(false);
		}
		
		if(e.getSource()==btnChooseColour3)
		{	
			lblStartGameMenuBackground.add(lblSelectColourBackground);
			btnBackFromStartGameMenu.setEnabled(false);	
			
			btnChooseColour1.setEnabled(false);
			btnChooseColour2.setEnabled(false);
			btnChooseColour3.setEnabled(false);
			btnChooseColour4.setEnabled(false);
			
			btnChooseAvatar1.setEnabled(false);
			btnChooseAvatar2.setEnabled(false);
			btnChooseAvatar3.setEnabled(false);
			btnChooseAvatar4.setEnabled(false);
			
			t1.setEnabled(false);
			t2.setEnabled(false);
			t3.setEnabled(false);
			t4.setEnabled(false);
			
			btnRed1.setVisible(false);
			btnRed2.setVisible(false);
			btnRed3.setVisible(true);
			
			btnBlue1.setVisible(false);
			btnBlue2.setVisible(false);
			btnBlue3.setVisible(true);
			
			btnYellow1.setVisible(false);
			btnYellow2.setVisible(false);
			btnYellow3.setVisible(true);
			
			btnGreen1.setVisible(false);
			btnGreen2.setVisible(false);
			btnGreen3.setVisible(true);
			
			btnCyan1.setVisible(false);
			btnCyan2.setVisible(false);
			btnCyan3.setVisible(true);
			
			btnPink1.setVisible(false);
			btnPink2.setVisible(false);
			btnPink3.setVisible(true);
			
			btnContinueFromStartGameMenu.setEnabled(false);
			
			
		}
		
		if(e.getSource()==btnChooseColour4)
		{	
			lblStartGameMenuBackground.add(lblSelectColourBackground);
			btnBackFromStartGameMenu.setEnabled(false);	
			
			btnChooseColour1.setEnabled(false);
			btnChooseColour2.setEnabled(false);
			btnChooseColour3.setEnabled(false);
			btnChooseColour4.setEnabled(false);
			
			btnChooseAvatar1.setEnabled(false);
			btnChooseAvatar2.setEnabled(false);
			btnChooseAvatar3.setEnabled(false);
			btnChooseAvatar4.setEnabled(false);
			
			t1.setEnabled(false);
			t2.setEnabled(false);
			t3.setEnabled(false);
			t4.setEnabled(false);
			
			btnRed1.setVisible(false);
			btnRed2.setVisible(false);
			btnRed3.setVisible(false);
			btnRed4.setVisible(true);
			
			btnBlue1.setVisible(false);
			btnBlue2.setVisible(false);
			btnBlue3.setVisible(false);
			btnBlue4.setVisible(true);
			
			btnYellow1.setVisible(false);
			btnYellow2.setVisible(false);
			btnYellow3.setVisible(false);
			btnYellow4.setVisible(true);
			
			btnGreen1.setVisible(false);
			btnGreen2.setVisible(false);
			btnGreen3.setVisible(false);
			btnGreen4.setVisible(true);
			
			btnCyan1.setVisible(false);
			btnCyan2.setVisible(false);
			btnCyan3.setVisible(false);
			btnCyan4.setVisible(true);
			
			btnPink1.setVisible(false);
			btnPink2.setVisible(false);
			btnPink3.setVisible(false);
			btnPink4.setVisible(true);
			
			btnContinueFromStartGameMenu.setEnabled(false);
		}
		
		if(e.getSource()==btnChooseAvatar1)
		{
			lblStartGameMenuBackground.add(lblSelectAvatarBackground);
			t1.setVisible(false);
			t2.setVisible(false);
			t3.setVisible(false);
			t4.setVisible(false);
			btnChooseColour1.setVisible(false);
			btnChooseColour2.setVisible(false);
			btnChooseColour3.setVisible(false);
			btnChooseColour4.setVisible(false);
			btnBackFromStartGameMenu.setVisible(false);
			
			btnChooseAvatar1.setHorizontalAlignment(JTextField.RIGHT);
			btnChooseAvatar1.setFont(new Font(Font.SERIF,Font.BOLD,25));
			btnChooseAvatar1.setText("1");
			
			
			btnChooseAvatar1.remove(lblAlexander);
			btnChooseAvatar1.remove(lblSuleiman);
			btnChooseAvatar1.remove(lblCengiz);
			btnChooseAvatar1.remove(lblCaesar);
			btnChooseAvatar1.remove(lblNapoleon);
			btnChooseAvatar1.remove(lblHannibal);
			
			btnChooseAvatar1.setEnabled(false);
			btnChooseAvatar2.setEnabled(false);
			btnChooseAvatar3.setEnabled(false);
			btnChooseAvatar4.setEnabled(false);
			
			btnContinueFromStartGameMenu.setEnabled(false);
			
		}
		
		if(e.getSource()==btnChooseAvatar2)
		{
			lblStartGameMenuBackground.add(lblSelectAvatarBackground2);
			t1.setVisible(false);
			t2.setVisible(false);
			t3.setVisible(false);
			t4.setVisible(false);
			btnChooseColour1.setVisible(false);
			btnChooseColour2.setVisible(false);
			btnChooseColour3.setVisible(false);
			btnChooseColour4.setVisible(false);
			btnBackFromStartGameMenu.setVisible(false);
			
			btnChooseAvatar2.setHorizontalAlignment(JTextField.RIGHT);
			btnChooseAvatar2.setFont(new Font(Font.SERIF,Font.BOLD,25));
			btnChooseAvatar2.setText("2");
			
			btnChooseAvatar1.setEnabled(false);
			btnChooseAvatar2.setEnabled(false);
			btnChooseAvatar3.setEnabled(false);
			btnChooseAvatar4.setEnabled(false);
			
			btnChooseAvatar2.remove(lblAlexander2);
			btnChooseAvatar2.remove(lblSuleiman2);
			btnChooseAvatar2.remove(lblCengiz2);
			btnChooseAvatar2.remove(lblCaesar2);
			btnChooseAvatar2.remove(lblNapoleon2);
			btnChooseAvatar2.remove(lblHannibal2);		
			
			btnContinueFromStartGameMenu.setEnabled(false);
		}
		
		if(e.getSource()==btnChooseAvatar3)
		{
			lblStartGameMenuBackground.add(lblSelectAvatarBackground3);
			t1.setVisible(false);
			t2.setVisible(false);
			t3.setVisible(false);
			t4.setVisible(false);
			btnChooseColour1.setVisible(false);
			btnChooseColour2.setVisible(false);
			btnChooseColour3.setVisible(false);
			btnChooseColour4.setVisible(false);
			btnBackFromStartGameMenu.setVisible(false);
			
			btnChooseAvatar3.setHorizontalAlignment(JTextField.RIGHT);
			btnChooseAvatar3.setFont(new Font(Font.SERIF,Font.BOLD,25));
			btnChooseAvatar3.setText("3");
			
			btnChooseAvatar1.setEnabled(false);
			btnChooseAvatar2.setEnabled(false);
			btnChooseAvatar3.setEnabled(false);
			btnChooseAvatar4.setEnabled(false);
			
			btnChooseAvatar3.remove(lblAlexander3);
			btnChooseAvatar3.remove(lblSuleiman3);
			btnChooseAvatar3.remove(lblCengiz3);
			btnChooseAvatar3.remove(lblCaesar3);
			btnChooseAvatar3.remove(lblNapoleon3);
			btnChooseAvatar3.remove(lblHannibal3);		
			
			btnContinueFromStartGameMenu.setEnabled(false);
		}
		
		if(e.getSource()==btnChooseAvatar4)
		{
			lblStartGameMenuBackground.add(lblSelectAvatarBackground4);
			t1.setVisible(false);
			t2.setVisible(false);
			t3.setVisible(false);
			t4.setVisible(false);
			btnChooseColour1.setVisible(false);
			btnChooseColour2.setVisible(false);
			btnChooseColour3.setVisible(false);
			btnChooseColour4.setVisible(false);
			btnBackFromStartGameMenu.setVisible(false);
			
			btnChooseAvatar4.setHorizontalAlignment(JTextField.RIGHT);
			btnChooseAvatar4.setFont(new Font(Font.SERIF,Font.BOLD,25));
			btnChooseAvatar4.setText("4");
			
			btnChooseAvatar1.setEnabled(false);
			btnChooseAvatar2.setEnabled(false);
			btnChooseAvatar3.setEnabled(false);
			btnChooseAvatar4.setEnabled(false);
			
			btnChooseAvatar4.remove(lblAlexander4);
			btnChooseAvatar4.remove(lblSuleiman4);
			btnChooseAvatar4.remove(lblCengiz4);
			btnChooseAvatar4.remove(lblCaesar4);
			btnChooseAvatar4.remove(lblNapoleon4);
			btnChooseAvatar4.remove(lblHannibal4);		
			
			btnContinueFromStartGameMenu.setEnabled(false);
		}
		
		if(e.getSource()==btnRed1)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour1.setBackground(Color.red);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnRed1.setVisible(false);
			btnChooseColour1.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnRed1.getBackground().equals(cDef)) )
			{
				t1.setForeground(Color.red);
			}
			
			btnChooseAvatar1.setForeground(Color.red);
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
				
		}
		
		if(e.getSource()==btnBlue1)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour1.setBackground(Color.BLUE);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnBlue1.setVisible(false);
			btnChooseColour1.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnBlue1.getBackground().equals(cDef)) )
			{
				t1.setForeground(Color.blue);
			}
			
			btnChooseAvatar1.setForeground(Color.blue);
			
			btnContinueFromStartGameMenu.setEnabled(true);
				
		}
		
		if(e.getSource()==btnYellow1)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour1.setBackground(Color.YELLOW);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnYellow1.setVisible(false);
			btnChooseColour1.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnYellow1.getBackground().equals(cDef)) )
			{
				t1.setForeground(Color.yellow);
			}
				
			btnChooseAvatar1.setForeground(Color.yellow);
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
		}
		
		if(e.getSource()==btnGreen1)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour1.setBackground(Color.GREEN);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnGreen1.setVisible(false);
			btnChooseColour1.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnGreen1.getBackground().equals(cDef)) )
			{
				t1.setForeground(Color.green);
			}
			
			btnChooseAvatar1.setForeground(Color.green);
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
				
		}
		
		if(e.getSource()==btnCyan1)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour1.setBackground(Color.CYAN);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnCyan1.setVisible(false);
			btnChooseColour1.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnCyan1.getBackground().equals(cDef)) )
			{
				t1.setForeground(Color.cyan);
			}
			
			btnChooseAvatar1.setForeground(Color.cyan);
			
			btnContinueFromStartGameMenu.setEnabled(true);
				
		}
		
		if(e.getSource()==btnPink1)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour1.setBackground(Color.PINK);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnPink1.setVisible(false);
			btnChooseColour1.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnPink1.getBackground().equals(cDef)) )
			{
				t1.setForeground(Color.pink);
			}
			
			btnChooseAvatar1.setForeground(Color.pink);
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
				
		}
		
		if(e.getSource()==btnRed2)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour2.setBackground(Color.red);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnRed2.setVisible(false);
			btnChooseColour2.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnRed2.getBackground().equals(cDef)) )
			{
				t2.setForeground(Color.red);
			}
			
			btnChooseAvatar2.setForeground(Color.red);
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
				
		}
		
		if(e.getSource()==btnBlue2)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour2.setBackground(Color.blue);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnBlue2.setVisible(false);
			btnChooseColour2.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnBlue2.getBackground().equals(cDef)) )
			{
				t2.setForeground(Color.blue);
			}
			
			btnChooseAvatar2.setForeground(Color.blue);
			
			btnContinueFromStartGameMenu.setEnabled(true);
				
		}
		
		if(e.getSource()==btnYellow2)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour2.setBackground(Color.yellow);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnYellow2.setVisible(false);
			btnChooseColour2.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnYellow2.getBackground().equals(cDef)) )
			{
				t2.setForeground(Color.yellow);
			}
			
			btnChooseAvatar2.setForeground(Color.yellow);
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
				
		}
		
		if(e.getSource()==btnGreen2)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour2.setBackground(Color.green);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnGreen2.setVisible(false);
			btnChooseColour2.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnGreen2.getBackground().equals(cDef)) )
			{
				t2.setForeground(Color.green);
			}
			
			btnChooseAvatar2.setForeground(Color.green);
			
			btnContinueFromStartGameMenu.setEnabled(true);
				
		}
		
		if(e.getSource()==btnCyan2)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour2.setBackground(Color.cyan);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnCyan2.setVisible(false);
			btnChooseColour2.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnCyan2.getBackground().equals(cDef)) )
			{
				t2.setForeground(Color.cyan);
			}
			
			btnChooseAvatar2.setForeground(Color.cyan);
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
				
		}
		
		if(e.getSource()==btnPink2)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour2.setBackground(Color.pink);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnPink2.setVisible(false);
			btnChooseColour2.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnPink2.getBackground().equals(cDef)) )
			{
				t2.setForeground(Color.pink);
			}
			
			btnChooseAvatar2.setForeground(Color.pink);
			
			btnContinueFromStartGameMenu.setEnabled(true);
				
		}
		
		if(e.getSource()==btnRed3)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour3.setBackground(Color.red);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnRed3.setVisible(false);
			btnChooseColour3.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnRed3.getBackground().equals(cDef)) )
			{
				t3.setForeground(Color.red);
			}
			
			btnChooseAvatar3.setForeground(Color.red);
			
			btnContinueFromStartGameMenu.setEnabled(true);
				
		}
		
		if(e.getSource()==btnBlue3)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour3.setBackground(Color.blue);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnBlue3.setVisible(false);
			btnChooseColour3.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnBlue3.getBackground().equals(cDef)) )
			{
				t3.setForeground(Color.blue);
			}
			
			btnChooseAvatar3.setForeground(Color.blue);
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
				
		}
		
		if(e.getSource()==btnYellow3)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour3.setBackground(Color.yellow);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnYellow3.setVisible(false);
			btnChooseColour3.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnYellow3.getBackground().equals(cDef)) )
			{
				t3.setForeground(Color.yellow);
			}
			
			btnChooseAvatar3.setForeground(Color.yellow);
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
				
		}
		
		if(e.getSource()==btnGreen3)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour3.setBackground(Color.green);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnGreen3.setVisible(false);
			btnChooseColour3.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnGreen3.getBackground().equals(cDef)) )
			{
				t3.setForeground(Color.green);
			}
			
			btnChooseAvatar3.setForeground(Color.green);
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
				
		}
		
		if(e.getSource()==btnCyan3)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour3.setBackground(Color.cyan);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnCyan3.setVisible(false);
			btnChooseColour3.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnCyan3.getBackground().equals(cDef)) )
			{
				t3.setForeground(Color.cyan);
			}
			
			btnChooseAvatar3.setForeground(Color.cyan);
			
			btnContinueFromStartGameMenu.setEnabled(true);
				
		}
		
		if(e.getSource()==btnPink3)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour3.setBackground(Color.pink);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnPink3.setVisible(false);
			btnChooseColour3.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnPink3.getBackground().equals(cDef)) )
			{
				t3.setForeground(Color.pink);
			}
			
			btnChooseAvatar3.setForeground(Color.pink);
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
				
		}
		
		if(e.getSource()==btnRed4)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour4.setBackground(Color.red);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnRed4.setVisible(false);
			btnChooseColour4.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnRed4.getBackground().equals(cDef)) )
			{
				t4.setForeground(Color.red);
			}
			
			btnChooseAvatar4.setForeground(Color.red);
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
				
		}
		
		if(e.getSource()==btnBlue4)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour4.setBackground(Color.blue);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnBlue4.setVisible(false);
			btnChooseColour4.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnBlue4.getBackground().equals(cDef)) )
			{
				t4.setForeground(Color.blue);
			}
			
			btnChooseAvatar4.setForeground(Color.blue);
			
			btnContinueFromStartGameMenu.setEnabled(true);
				
		}
		
		if(e.getSource()==btnYellow4)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour4.setBackground(Color.yellow);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnYellow4.setVisible(false);
			btnChooseColour4.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnYellow4.getBackground().equals(cDef)) )
			{
				t4.setForeground(Color.yellow);
			}
			
			btnChooseAvatar4.setForeground(Color.yellow);
			
			btnContinueFromStartGameMenu.setEnabled(true);
				
		}
		
		if(e.getSource()==btnGreen4)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour4.setBackground(Color.green);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnGreen4.setVisible(false);
			btnChooseColour4.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnGreen4.getBackground().equals(cDef)) )
			{
				t4.setForeground(Color.green);
			}
			
			btnChooseAvatar4.setForeground(Color.green);
			
			btnContinueFromStartGameMenu.setEnabled(true);
				
		}
		
		if(e.getSource()==btnCyan4)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour4.setBackground(Color.cyan);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnCyan4.setVisible(false);
			btnChooseColour4.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnCyan4.getBackground().equals(cDef)) )
			{
				t4.setForeground(Color.cyan);
			}
				
			btnChooseAvatar4.setForeground(Color.cyan);
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
		}
		
		if(e.getSource()==btnPink4)
		{
			
			lblStartGameMenuBackground.remove(lblSelectColourBackground);
			btnChooseColour4.setBackground(Color.pink);
			btnBackFromStartGameMenu.setEnabled(true);
			btnChooseColour1.setEnabled(true);
			btnChooseColour2.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnPink4.setVisible(false);
			btnChooseColour4.setText("");
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			t1.setEnabled(true);
			t2.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			
			if( !(btnPink4.getBackground().equals(cDef)) )
			{
				t4.setForeground(Color.pink);
			}
			
			btnChooseAvatar4.setForeground(Color.pink);
			
			btnContinueFromStartGameMenu.setEnabled(true);
				
		}
		
		if(e.getSource()==btnNapoleon1)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar1.add(lblNapoleon);
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP1 = 1;
			
		}
		
		if(e.getSource()==btnAlexander1)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar1.add(lblAlexander);
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP1 = 2;
		    
		}
		
		if(e.getSource()==btnHannibal1)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar1.add(lblHannibal);
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP1 = 3;
		}
		
		if(e.getSource()==btnCaesar1)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar1.add(lblCaesar);
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP1 = 4;
		}
		
		if(e.getSource()==btnCengiz1)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar1.add(lblCengiz);
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP1 = 5;
		}
		
		if(e.getSource()==btnSuleiman1)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar1.add(lblSuleiman); 
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP1 = 6;
			
		}
		
		if(e.getSource()==btnSuleiman2)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground2);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar2.add(lblSuleiman2); 
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP2 = 6;
			
		}
		
		if(e.getSource()==btnCaesar2)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground2);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar2.add(lblCaesar2); 
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP2 = 4;
			
		}
		
		if(e.getSource()==btnHannibal2)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground2);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar2.add(lblHannibal2); 
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP2 = 3;
			
		}
		
		if(e.getSource()==btnCengiz2)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground2);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar2.add(lblCengiz2); 
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP2 = 5;
			
		}
		
		if(e.getSource()==btnAlexander2)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground2);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar2.add(lblAlexander2); 
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP2 = 2;
			
		}
		
		if(e.getSource()==btnNapoleon2)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground2);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar2.add(lblNapoleon2); 
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP2 = 1;
			
		}
		
		if(e.getSource()==btnNapoleon3)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground3);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar3.add(lblNapoleon3); 
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP3 = 1;
			
		}
		
		if(e.getSource()==btnSuleiman3)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground3);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar3.add(lblSuleiman3); 
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP3 = 6;
			
		}
		
		if(e.getSource()==btnCaesar3)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground3);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar3.add(lblCaesar3); 
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP3 = 4;
			
		}
		
		if(e.getSource()==btnCengiz3)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground3);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar3.add(lblCengiz3); 
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP3 = 5;
			
		}
		
		if(e.getSource()==btnAlexander3)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground3);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar3.add(lblAlexander3); 
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP3 = 2;
			
		}
		
		if(e.getSource()==btnHannibal3)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground3);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar3.add(lblHannibal3); 
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP3 = 3;
			
		}
		
		if(e.getSource()==btnHannibal4)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground4);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar4.add(lblHannibal4); 
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP4 = 3;
			
		}
		
		if(e.getSource()==btnNapoleon4)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground4);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar4.add(lblNapoleon4); 
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP4 = 1;
			
		}
		
		if(e.getSource()==btnSuleiman4)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground4);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar4.add(lblSuleiman4); 
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP4 = 6;
			
		}
		
		if(e.getSource()==btnCengiz4)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground4);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar4.add(lblCengiz4); 
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP4 = 5;
			
		}
		
		if(e.getSource()==btnCaesar4)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground4);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar4.add(lblCaesar4); 
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP4 = 4;
			
		}
		
		if(e.getSource()==btnAlexander4)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground4);
			t1.setVisible(true);
			t2.setVisible(true);
			t3.setVisible(true);
			t4.setVisible(true);
			btnChooseColour1.setVisible(true);
			btnChooseColour2.setVisible(true);
			btnChooseColour3.setVisible(true);
			btnChooseColour4.setVisible(true);
			btnBackFromStartGameMenu.setVisible(true);
			
			btnChooseAvatar1.setEnabled(true);
			btnChooseAvatar2.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
			
			btnChooseAvatar4.add(lblAlexander4); 
			
			btnContinueFromStartGameMenu.setEnabled(true);
			
			avatarNoP4 = 2;
			
		}
		
		
		
		if(e.getSource()==btnQuitGame)
		{
			playSound("./src/main/resources/sounds/snd_quitgame.wav");
			int result =JOptionPane.showConfirmDialog(null, "Quit game?");
			if(result==JOptionPane.YES_OPTION)
			{
				dispose();
			}
				
		}
		
		
		
		repaint();
		pack();
	}
	
	// ############## End of Action Events ##############
}
