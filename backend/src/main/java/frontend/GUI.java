// Author: Alperen

package frontend;
import backend.GameManager;

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
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

public class GUI extends JFrame implements ActionListener {

	private JPanel pnlMainMenu, pnlStartGameMenu, pnlCredits;

	private JCheckBox boxSecretMission;

	private JButton btnStartGame, btnHowToPlay, btnQuitGame, btnCredits, btnBackFromCredits, btnBackFromStartGameMenu,btnContinueFromStartGameMenu;

	private JButton btnNumOfPlayers2, btnNumOfPlayers3, btnNumOfPlayers4;

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
	private int numOfPlayers = 4;
	private int[] playerAvatarIndexes = new int[4];
	private String[] playerNames = new String[4];
	private Color[] playerColors = new Color[4];

	private GameManager gameManager;

	private HowToPlayPanel htp;

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

		/* CREATE GAME MANAGER */
		//gameManager = new GameManager();

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

		JLabel lblSecretMission = new JLabel("Include");
		lblSecretMission.setBounds(1433,200,340,70);
		lblSecretMission.setFont(new Font(Font.SERIF,Font.BOLD,20));
		lblSecretMission.setForeground(Color.green);
		lblStartGameMenuBackground.add(lblSecretMission);

		JLabel lblSecretMission2 = new JLabel("Secret Missions");
		lblSecretMission2.setBounds(1400,230,340,70);
		lblSecretMission2.setFont(new Font(Font.SERIF,Font.BOLD,20));
		lblSecretMission2.setForeground(Color.green);
		lblStartGameMenuBackground.add(lblSecretMission2);

		boxSecretMission = new JCheckBox(" ?");
		boxSecretMission.setBounds(1430,295,70,70);
		boxSecretMission.setFont(new Font(Font.SERIF,Font.BOLD,40));
		boxSecretMission.setForeground(Color.green);
		boxSecretMission.setContentAreaFilled(false);
		boxSecretMission.setBorderPainted(true);
		Border thickbor = new LineBorder(Color.red, 3);
		boxSecretMission.setBorder(thickbor);
		lblStartGameMenuBackground.add(boxSecretMission);

		btnNumOfPlayers2 = new JButton("");
		btnNumOfPlayers2.setBounds(1270,180,95,60);
		btnNumOfPlayers2.setContentAreaFilled(false);
		btnNumOfPlayers2.setBorderPainted(true);
		lblStartGameMenuBackground.add(btnNumOfPlayers2);

		btnNumOfPlayers3 = new JButton("");
		btnNumOfPlayers3.setBounds(1270,245,95,50);
		btnNumOfPlayers3.setContentAreaFilled(false);
		btnNumOfPlayers3.setBorderPainted(true);
		lblStartGameMenuBackground.add(btnNumOfPlayers3);

		btnNumOfPlayers4 = new JButton("");
		btnNumOfPlayers4.setBounds(1270,300,95,60);
		btnNumOfPlayers4.setContentAreaFilled(false);
		btnNumOfPlayers4.setBorderPainted(true);
		Border thickBorder = new LineBorder(Color.green, 5);
		btnNumOfPlayers4.setBorder(thickBorder);
		btnNumOfPlayers4.setEnabled(false);
		lblStartGameMenuBackground.add(btnNumOfPlayers4);

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

		//############### End of textfields #######

		// ######## ACTION LISTENERS ###########

		btnCredits.addActionListener(this);
		btnBackFromCredits.addActionListener(this);
		btnQuitGame.addActionListener(this);
		btnHowToPlay.addActionListener(this);
		btnStartGame.addActionListener(this);
		btnBackFromStartGameMenu.addActionListener(this);
		btnContinueFromStartGameMenu.addActionListener(this);

		btnNumOfPlayers2.addActionListener(this);
		btnNumOfPlayers3.addActionListener(this);
		btnNumOfPlayers4.addActionListener(this);

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
			FloatControl gainControl =
					(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f);
			clip.start( );
		}
		catch(Exception ex)
		{
			System.out.println("Error with playing sound.");
			ex.printStackTrace( );
		}
	}

	// ############## End of Play Sound Method ################

	public void disableButtonsAvatar()
	{
		t1.setVisible(false);
		t2.setVisible(false);
		t3.setVisible(false);
		t4.setVisible(false);
		btnChooseColour1.setVisible(false);
		btnChooseColour2.setVisible(false);
		btnChooseColour3.setVisible(false);
		btnChooseColour4.setVisible(false);
		btnBackFromStartGameMenu.setVisible(false);

		t1.setEnabled(false);
		t2.setEnabled(false);
		t3.setEnabled(false);
		t4.setEnabled(false);
		btnChooseColour1.setEnabled(false);
		btnChooseColour2.setEnabled(false);
		btnChooseColour3.setEnabled(false);
		btnChooseColour4.setEnabled(false);

		btnChooseAvatar1.setEnabled(false);
		btnChooseAvatar2.setEnabled(false);
		btnChooseAvatar3.setEnabled(false);
		btnChooseAvatar4.setEnabled(false);

		btnContinueFromStartGameMenu.setEnabled(false);
	}

	public void enableButtonsAvatar()
	{
		t1.setVisible(true);
		t2.setVisible(true);
		t3.setVisible(true);
		t4.setVisible(true);

		btnChooseColour1.setVisible(true);
		btnChooseColour2.setVisible(true);
		btnChooseColour3.setVisible(true);
		btnChooseColour4.setVisible(true);
		btnBackFromStartGameMenu.setVisible(true);

		t1.setEnabled(true);
		t2.setEnabled(true);

		btnChooseColour1.setEnabled(true);
		btnChooseColour2.setEnabled(true);
		btnChooseAvatar1.setEnabled(true);
		btnChooseAvatar2.setEnabled(true);

		if(numOfPlayers == 3)
		{
			btnChooseColour3.setEnabled(true);
			t3.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
		}

		else if(numOfPlayers == 4)
		{
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
		}

		btnContinueFromStartGameMenu.setEnabled(true);
	}

	public void disableButtonsColor()
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

		btnContinueFromStartGameMenu.setEnabled(false);
	}

	public void enableButtonsColor()
	{
		lblStartGameMenuBackground.remove(lblSelectColourBackground);
		btnBackFromStartGameMenu.setEnabled(true);

		btnChooseColour1.setEnabled(true);
		btnChooseColour2.setEnabled(true);

		if(numOfPlayers == 3)
		{
			btnChooseColour3.setEnabled(true);
			t3.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
		}

		else if(numOfPlayers == 4)
		{
			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			t3.setEnabled(true);
			t4.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);
		}

		btnChooseAvatar1.setEnabled(true);
		btnChooseAvatar2.setEnabled(true);


		t1.setEnabled(true);
		t2.setEnabled(true);
		btnContinueFromStartGameMenu.setEnabled(true);
	}

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

			btnNumOfPlayers2.setBorder(UIManager.getBorder("Button.border"));
			btnNumOfPlayers3.setBorder(UIManager.getBorder("Button.border"));
			Border thickBorder = new LineBorder(Color.green, 5);
			btnNumOfPlayers4.setBorder(thickBorder);

			btnNumOfPlayers2.setEnabled(true);
			btnNumOfPlayers3.setEnabled(true);
			btnNumOfPlayers4.setEnabled(false);

			t3.setEnabled(true);
			t4.setEnabled(true);

			btnChooseColour3.setEnabled(true);
			btnChooseColour4.setEnabled(true);

			btnChooseAvatar3.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);

			numOfPlayers = 4;

			boxSecretMission.setSelected(false);
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

			//if(boxSecretMission.isSelected())

			if(numOfPlayers == 2)
			{
				if( c1.equals(c5) || c2.equals(c5) )
				{
					playSound("./src/main/resources/sounds/snd_error.wav");
					JOptionPane.showMessageDialog(null, "Every player must choose a color.");
				}

				else if( c1.equals(c2) )
				{
					playSound("./src/main/resources/sounds/snd_error.wav");
					JOptionPane.showMessageDialog(null, "Colors must be unique for each player.");
				}

				else if( avatarNoP1 == 0 || avatarNoP2 == 0  )
				{
					playSound("./src/main/resources/sounds/snd_error.wav");
					JOptionPane.showMessageDialog(null, "Every player must have an avatar.");
				}

				else if( avatarNoP1 == avatarNoP2  )
				{
					playSound("./src/main/resources/sounds/snd_error.wav");
					JOptionPane.showMessageDialog(null, "Avatars must be unique for each player.");
				}

				else if( s1.equals("") || s2.equals("") )
				{
					playSound("./src/main/resources/sounds/snd_error.wav");
					JOptionPane.showMessageDialog(null, "Every player must enter a nickname.");
				}

				else if( s1.equals(s2)  )
				{
					playSound("./src/main/resources/sounds/snd_error.wav");
					JOptionPane.showMessageDialog(null, "Nicknames must be unique for each player.");
				}

				else
				{
					playSound("./src/main/resources/sounds/snd_victory.wav");
					JOptionPane.showMessageDialog(null, "The game is starting with "+ numOfPlayers+" players");
					playerNames[0] = s1;
					playerNames[1] = s2;
					playerAvatarIndexes[0] = avatarNoP1;
					playerAvatarIndexes[1] = avatarNoP2;
					playerColors[0] = t1.getForeground();
					playerColors[1] = t2.getForeground();
					remove(pnlStartGameMenu);
					dispose();

					// Start the actual game

					gameManager = new GameManager(numOfPlayers, playerNames, playerAvatarIndexes,playerColors);
					Map frame = new Map(gameManager);
					frame.startTimer();
					frame.setVisible(true);
					frame.setTitle("Risk");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setPreferredSize(new Dimension(1150,830)); //1570,800
					frame.setResizable(false);
					frame.pack();

					//gameManager.startGame( numOfPlayers, playerNames, playerAvatarIndexes);

				}

			}

			else if(numOfPlayers == 3)
			{
				if( c1.equals(c5) || c2.equals(c5) || c3.equals(c5) )
				{
					playSound("./src/main/resources/sounds/snd_error.wav");
					JOptionPane.showMessageDialog(null, "Every player must choose a color.");
				}

				else if( c1.equals(c2) || c1.equals(c3) || c2.equals(c3) )
				{
					playSound("./src/main/resources/sounds/snd_error.wav");
					JOptionPane.showMessageDialog(null, "Colors must be unique for each player.");
				}

				else if( avatarNoP1 == 0 || avatarNoP2 == 0 || avatarNoP3 == 0 )
				{
					playSound("./src/main/resources/sounds/snd_error.wav");
					JOptionPane.showMessageDialog(null, "Every player must have an avatar.");
				}

				else if( avatarNoP1 == avatarNoP2 || avatarNoP1 == avatarNoP3 || avatarNoP2 == avatarNoP3 )
				{
					playSound("./src/main/resources/sounds/snd_error.wav");
					JOptionPane.showMessageDialog(null, "Avatars must be unique for each player.");
				}

				else if( s1.equals("") || s2.equals("") || s3.equals("") )
				{
					playSound("./src/main/resources/sounds/snd_error.wav");
					JOptionPane.showMessageDialog(null, "Every player must enter a nickname.");
				}

				else if( s1.equals(s2) || s1.equals(s3) || s2.equals(s3) )
				{
					playSound("./src/main/resources/sounds/snd_error.wav");
					JOptionPane.showMessageDialog(null, "Nicknames must be unique for each player.");
				}

				else
				{
					playSound("./src/main/resources/sounds/snd_victory.wav");
					JOptionPane.showMessageDialog(null, "The game is starting with "+ numOfPlayers+" players");
					playerNames[0] = s1;
					playerNames[1] = s2;
					playerNames[2] = s3;
					playerAvatarIndexes[0] = avatarNoP1;
					playerAvatarIndexes[1] = avatarNoP2;
					playerAvatarIndexes[2] = avatarNoP3;
					playerColors[0] = t1.getForeground();
					playerColors[1] = t2.getForeground();
					playerColors[2] = t3.getForeground();
					dispose();

					// Start the actual game
					gameManager = new GameManager(numOfPlayers, playerNames, playerAvatarIndexes,playerColors);
					Map frame = new Map(gameManager);
					frame.startTimer();
					frame.setVisible(true);
					frame.setTitle("Risk");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setPreferredSize(new Dimension(1150,830)); //1570,800
					frame.setResizable(false);
					frame.pack();

					//gameManager.startGame( numOfPlayers, playerNames, playerAvatarIndexes);
				}

			}

			else if(numOfPlayers == 4)
			{
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
					JOptionPane.showMessageDialog(null, "The game is starting with "+ numOfPlayers+" players");
					playerNames[0] = s1;
					playerNames[1] = s2;
					playerNames[2] = s3;
					playerNames[3] = s4;
					playerAvatarIndexes[0] = avatarNoP1;
					playerAvatarIndexes[1] = avatarNoP2;
					playerAvatarIndexes[2] = avatarNoP3;
					playerAvatarIndexes[3] = avatarNoP4;
					playerColors[0] = t1.getForeground();
					playerColors[1] = t2.getForeground();
					playerColors[2] = t3.getForeground();
					playerColors[3] = t4.getForeground();
					dispose();
					gameManager = new GameManager(numOfPlayers, playerNames, playerAvatarIndexes,playerColors);
					// Start the actual game
					Map frame = new Map(gameManager);
					frame.startTimer();
					frame.setVisible(true);
					frame.setTitle("Risk");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setPreferredSize(new Dimension(1150,830)); //1570,800
					frame.setResizable(false);
					frame.pack();

					//gameManager.startGame( numOfPlayers, playerNames, playerAvatarIndexes);
				}

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
			htp = new HowToPlayPanel(pnlMainMenu);
			pnlMainMenu.setVisible(false);
			add(htp);
		}

		if(e.getSource()==btnChooseColour1)
		{
			btnRed1.setVisible(true);
			btnBlue1.setVisible(true);
			btnYellow1.setVisible(true);
			btnGreen1.setVisible(true);
			btnCyan1.setVisible(true);
			btnPink1.setVisible(true);

			disableButtonsColor();
		}

		if(e.getSource()==btnChooseColour2)
		{
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

			disableButtonsColor();
		}

		if(e.getSource()==btnChooseColour3)
		{
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

			disableButtonsColor();
		}

		if(e.getSource()==btnChooseColour4)
		{
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

			disableButtonsColor();
		}

		if(e.getSource()==btnChooseAvatar1)
		{
			lblStartGameMenuBackground.add(lblSelectAvatarBackground);

			btnChooseAvatar1.setHorizontalAlignment(JTextField.RIGHT);
			btnChooseAvatar1.setFont(new Font(Font.SERIF,Font.BOLD,25));
			btnChooseAvatar1.setText("1");

			btnChooseAvatar1.remove(lblAlexander);
			btnChooseAvatar1.remove(lblSuleiman);
			btnChooseAvatar1.remove(lblCengiz);
			btnChooseAvatar1.remove(lblCaesar);
			btnChooseAvatar1.remove(lblNapoleon);
			btnChooseAvatar1.remove(lblHannibal);

			disableButtonsAvatar();
		}

		if(e.getSource()==btnChooseAvatar2)
		{
			lblStartGameMenuBackground.add(lblSelectAvatarBackground2);

			btnChooseAvatar2.setHorizontalAlignment(JTextField.RIGHT);
			btnChooseAvatar2.setFont(new Font(Font.SERIF,Font.BOLD,25));
			btnChooseAvatar2.setText("2");

			btnChooseAvatar2.remove(lblAlexander2);
			btnChooseAvatar2.remove(lblSuleiman2);
			btnChooseAvatar2.remove(lblCengiz2);
			btnChooseAvatar2.remove(lblCaesar2);
			btnChooseAvatar2.remove(lblNapoleon2);
			btnChooseAvatar2.remove(lblHannibal2);

			disableButtonsAvatar();
		}

		if(e.getSource()==btnChooseAvatar3)
		{
			lblStartGameMenuBackground.add(lblSelectAvatarBackground3);

			btnChooseAvatar3.setHorizontalAlignment(JTextField.RIGHT);
			btnChooseAvatar3.setFont(new Font(Font.SERIF,Font.BOLD,25));
			btnChooseAvatar3.setText("3");

			btnChooseAvatar3.remove(lblAlexander3);
			btnChooseAvatar3.remove(lblSuleiman3);
			btnChooseAvatar3.remove(lblCengiz3);
			btnChooseAvatar3.remove(lblCaesar3);
			btnChooseAvatar3.remove(lblNapoleon3);
			btnChooseAvatar3.remove(lblHannibal3);

			disableButtonsAvatar();
		}

		if(e.getSource()==btnChooseAvatar4)
		{
			lblStartGameMenuBackground.add(lblSelectAvatarBackground4);

			btnChooseAvatar4.setHorizontalAlignment(JTextField.RIGHT);
			btnChooseAvatar4.setFont(new Font(Font.SERIF,Font.BOLD,25));
			btnChooseAvatar4.setText("4");

			btnChooseAvatar4.remove(lblAlexander4);
			btnChooseAvatar4.remove(lblSuleiman4);
			btnChooseAvatar4.remove(lblCengiz4);
			btnChooseAvatar4.remove(lblCaesar4);
			btnChooseAvatar4.remove(lblNapoleon4);
			btnChooseAvatar4.remove(lblHannibal4);

			disableButtonsAvatar();
		}

		if(e.getSource()==btnRed1)
		{
			btnChooseColour1.setBackground(Color.red);
			btnRed1.setVisible(false);
			btnChooseColour1.setText("");

			if( !(btnRed1.getBackground().equals(cDef)) )
			{
				t1.setForeground(Color.red);
			}

			btnChooseAvatar1.setForeground(Color.red);
			enableButtonsColor();
		}

		if(e.getSource()==btnBlue1)
		{
			btnChooseColour1.setBackground(Color.BLUE);
			btnBlue1.setVisible(false);
			btnChooseColour1.setText("");

			if( !(btnBlue1.getBackground().equals(cDef)) )
			{
				t1.setForeground(Color.blue);
			}

			btnChooseAvatar1.setForeground(Color.blue);
			enableButtonsColor();
		}

		if(e.getSource()==btnYellow1)
		{
			btnChooseColour1.setBackground(Color.YELLOW);
			btnYellow1.setVisible(false);
			btnChooseColour1.setText("");

			if( !(btnYellow1.getBackground().equals(cDef)) )
			{
				t1.setForeground(Color.yellow);
			}

			btnChooseAvatar1.setForeground(Color.yellow);
			enableButtonsColor();
		}

		if(e.getSource()==btnGreen1)
		{
			btnChooseColour1.setBackground(Color.GREEN);
			btnGreen1.setVisible(false);
			btnChooseColour1.setText("");

			if( !(btnGreen1.getBackground().equals(cDef)) )
			{
				t1.setForeground(Color.green);
			}

			btnChooseAvatar1.setForeground(Color.green);
			enableButtonsColor();
		}

		if(e.getSource()==btnCyan1)
		{
			btnChooseColour1.setBackground(Color.CYAN);
			btnCyan1.setVisible(false);
			btnChooseColour1.setText("");

			if( !(btnCyan1.getBackground().equals(cDef)) )
			{
				t1.setForeground(Color.cyan);
			}

			btnChooseAvatar1.setForeground(Color.cyan);
			enableButtonsColor();
		}

		if(e.getSource()==btnPink1)
		{
			btnChooseColour1.setBackground(Color.PINK);
			btnPink1.setVisible(false);
			btnChooseColour1.setText("");

			if( !(btnPink1.getBackground().equals(cDef)) )
			{
				t1.setForeground(Color.pink);
			}

			btnChooseAvatar1.setForeground(Color.pink);
			enableButtonsColor();
		}

		if(e.getSource()==btnRed2)
		{
			btnChooseColour2.setBackground(Color.red);
			btnRed2.setVisible(false);
			btnChooseColour2.setText("");

			if( !(btnRed2.getBackground().equals(cDef)) )
			{
				t2.setForeground(Color.red);
			}

			btnChooseAvatar2.setForeground(Color.red);
			enableButtonsColor();
		}

		if(e.getSource()==btnBlue2)
		{
			btnChooseColour2.setBackground(Color.blue);
			btnBlue2.setVisible(false);
			btnChooseColour2.setText("");

			if( !(btnBlue2.getBackground().equals(cDef)) )
			{
				t2.setForeground(Color.blue);
			}

			btnChooseAvatar2.setForeground(Color.blue);
			enableButtonsColor();
		}

		if(e.getSource()==btnYellow2)
		{
			btnChooseColour2.setBackground(Color.yellow);
			btnYellow2.setVisible(false);
			btnChooseColour2.setText("");

			if( !(btnYellow2.getBackground().equals(cDef)) )
			{
				t2.setForeground(Color.yellow);
			}

			btnChooseAvatar2.setForeground(Color.yellow);
			enableButtonsColor();
		}

		if(e.getSource()==btnGreen2)
		{
			btnChooseColour2.setBackground(Color.green);
			btnGreen2.setVisible(false);
			btnChooseColour2.setText("");

			if( !(btnGreen2.getBackground().equals(cDef)) )
			{
				t2.setForeground(Color.green);
			}

			btnChooseAvatar2.setForeground(Color.green);
			enableButtonsColor();
		}

		if(e.getSource()==btnCyan2)
		{
			btnChooseColour2.setBackground(Color.cyan);
			btnCyan2.setVisible(false);
			btnChooseColour2.setText("");

			if( !(btnCyan2.getBackground().equals(cDef)) )
			{
				t2.setForeground(Color.cyan);
			}

			btnChooseAvatar2.setForeground(Color.cyan);
			enableButtonsColor();
		}

		if(e.getSource()==btnPink2)
		{
			btnChooseColour2.setBackground(Color.pink);
			btnPink2.setVisible(false);
			btnChooseColour2.setText("");

			if( !(btnPink2.getBackground().equals(cDef)) )
			{
				t2.setForeground(Color.pink);
			}

			btnChooseAvatar2.setForeground(Color.pink);
			enableButtonsColor();
		}

		if(e.getSource()==btnRed3)
		{
			btnChooseColour3.setBackground(Color.red);
			btnRed3.setVisible(false);
			btnChooseColour3.setText("");

			if( !(btnRed3.getBackground().equals(cDef)) )
			{
				t3.setForeground(Color.red);
			}

			btnChooseAvatar3.setForeground(Color.red);
			enableButtonsColor();
		}

		if(e.getSource()==btnBlue3)
		{
			btnChooseColour3.setBackground(Color.blue);
			btnBlue3.setVisible(false);
			btnChooseColour3.setText("");

			if( !(btnBlue3.getBackground().equals(cDef)) )
			{
				t3.setForeground(Color.blue);
			}

			btnChooseAvatar3.setForeground(Color.blue);
			enableButtonsColor();
		}

		if(e.getSource()==btnYellow3)
		{
			btnChooseColour3.setBackground(Color.yellow);
			btnYellow3.setVisible(false);
			btnChooseColour3.setText("");

			if( !(btnYellow3.getBackground().equals(cDef)) )
			{
				t3.setForeground(Color.yellow);
			}

			btnChooseAvatar3.setForeground(Color.yellow);
			enableButtonsColor();
		}

		if(e.getSource()==btnGreen3)
		{
			btnChooseColour3.setBackground(Color.green);
			btnGreen3.setVisible(false);
			btnChooseColour3.setText("");

			if( !(btnGreen3.getBackground().equals(cDef)) )
			{
				t3.setForeground(Color.green);
			}

			btnChooseAvatar3.setForeground(Color.green);
			enableButtonsColor();
		}

		if(e.getSource()==btnCyan3)
		{
			btnChooseColour3.setBackground(Color.cyan);
			btnCyan3.setVisible(false);
			btnChooseColour3.setText("");

			if( !(btnCyan3.getBackground().equals(cDef)) )
			{
				t3.setForeground(Color.cyan);
			}

			btnChooseAvatar3.setForeground(Color.cyan);
			enableButtonsColor();
		}

		if(e.getSource()==btnPink3)
		{
			btnChooseColour3.setBackground(Color.pink);
			btnPink3.setVisible(false);
			btnChooseColour3.setText("");

			if( !(btnPink3.getBackground().equals(cDef)) )
			{
				t3.setForeground(Color.pink);
			}

			btnChooseAvatar3.setForeground(Color.pink);
			enableButtonsColor();
		}

		if(e.getSource()==btnRed4)
		{
			btnChooseColour4.setBackground(Color.red);
			btnRed4.setVisible(false);
			btnChooseColour4.setText("");

			if( !(btnRed4.getBackground().equals(cDef)) )
			{
				t4.setForeground(Color.red);
			}

			btnChooseAvatar4.setForeground(Color.red);
			enableButtonsColor();
		}

		if(e.getSource()==btnBlue4)
		{
			btnChooseColour4.setBackground(Color.blue);
			btnBlue4.setVisible(false);
			btnChooseColour4.setText("");

			if( !(btnBlue4.getBackground().equals(cDef)) )
			{
				t4.setForeground(Color.blue);
			}

			btnChooseAvatar4.setForeground(Color.blue);
			enableButtonsColor();
		}

		if(e.getSource()==btnYellow4)
		{
			btnChooseColour4.setBackground(Color.yellow);
			btnYellow4.setVisible(false);
			btnChooseColour4.setText("");

			if( !(btnYellow4.getBackground().equals(cDef)) )
			{
				t4.setForeground(Color.yellow);
			}

			btnChooseAvatar4.setForeground(Color.yellow);
			enableButtonsColor();
		}

		if(e.getSource()==btnGreen4)
		{
			btnChooseColour4.setBackground(Color.green);
			btnGreen4.setVisible(false);
			btnChooseColour4.setText("");

			if( !(btnGreen4.getBackground().equals(cDef)) )
			{
				t4.setForeground(Color.green);
			}

			btnChooseAvatar4.setForeground(Color.green);
			enableButtonsColor();
		}

		if(e.getSource()==btnCyan4)
		{
			btnChooseColour4.setBackground(Color.cyan);
			btnCyan4.setVisible(false);
			btnChooseColour4.setText("");

			if( !(btnCyan4.getBackground().equals(cDef)) )
			{
				t4.setForeground(Color.cyan);
			}

			btnChooseAvatar4.setForeground(Color.cyan);
			enableButtonsColor();
		}

		if(e.getSource()==btnPink4)
		{
			btnChooseColour4.setBackground(Color.pink);
			btnPink4.setVisible(false);
			btnChooseColour4.setText("");

			if( !(btnPink4.getBackground().equals(cDef)) )
			{
				t4.setForeground(Color.pink);
			}

			btnChooseAvatar4.setForeground(Color.pink);
			enableButtonsColor();
		}

		if(e.getSource()==btnNapoleon1)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground);
			btnChooseAvatar1.add(lblNapoleon);
			avatarNoP1 = 1;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnAlexander1)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground);
			btnChooseAvatar1.add(lblAlexander);
			avatarNoP1 = 2;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnHannibal1)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground);
			btnChooseAvatar1.add(lblHannibal);
			avatarNoP1 = 3;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnCaesar1)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground);
			btnChooseAvatar1.add(lblCaesar);
			avatarNoP1 = 4;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnCengiz1)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground);
			btnChooseAvatar1.add(lblCengiz);
			avatarNoP1 = 5;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnSuleiman1)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground);
			btnChooseAvatar1.add(lblSuleiman);
			avatarNoP1 = 6;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnSuleiman2)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground2);
			btnChooseAvatar2.add(lblSuleiman2);
			avatarNoP2 = 6;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnCaesar2)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground2);
			btnChooseAvatar2.add(lblCaesar2);
			avatarNoP2 = 4;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnHannibal2)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground2);
			btnChooseAvatar2.add(lblHannibal2);
			avatarNoP2 = 3;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnCengiz2)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground2);
			btnChooseAvatar2.add(lblCengiz2);
			avatarNoP2 = 5;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnAlexander2)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground2);
			btnChooseAvatar2.add(lblAlexander2);
			avatarNoP2 = 2;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnNapoleon2)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground2);
			btnChooseAvatar2.add(lblNapoleon2);
			avatarNoP2 = 1;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnNapoleon3)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground3);
			btnChooseAvatar3.add(lblNapoleon3);
			avatarNoP3 = 1;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnSuleiman3)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground3);
			btnChooseAvatar3.add(lblSuleiman3);
			avatarNoP3 = 6;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnCaesar3)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground3);
			btnChooseAvatar3.add(lblCaesar3);
			avatarNoP3 = 4;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnCengiz3)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground3);
			btnChooseAvatar3.add(lblCengiz3);
			avatarNoP3 = 5;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnAlexander3)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground3);
			btnChooseAvatar3.add(lblAlexander3);
			avatarNoP3 = 2;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnHannibal3)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground3);
			btnChooseAvatar3.add(lblHannibal3);
			avatarNoP3 = 3;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnHannibal4)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground4);
			btnChooseAvatar4.add(lblHannibal4);
			avatarNoP4 = 3;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnNapoleon4)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground4);
			btnChooseAvatar4.add(lblNapoleon4);
			avatarNoP4 = 1;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnSuleiman4)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground4);
			btnChooseAvatar4.add(lblSuleiman4);
			avatarNoP4 = 6;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnCengiz4)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground4);
			btnChooseAvatar4.add(lblCengiz4);
			avatarNoP4 = 5;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnCaesar4)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground4);
			btnChooseAvatar4.add(lblCaesar4);
			avatarNoP4 = 4;
			enableButtonsAvatar();
		}

		if(e.getSource()==btnAlexander4)
		{
			lblStartGameMenuBackground.remove(lblSelectAvatarBackground4);
			btnChooseAvatar4.add(lblAlexander4);
			avatarNoP4 = 2;
			enableButtonsAvatar();
		}


		if(e.getSource()==btnNumOfPlayers2)
		{
			btnNumOfPlayers3.setBorder(UIManager.getBorder("Button.border"));
			btnNumOfPlayers4.setBorder(UIManager.getBorder("Button.border"));
			Border thickBorder = new LineBorder(Color.green, 5);
			btnNumOfPlayers2.setBorder(thickBorder);

			t3.setEnabled(false);
			t3.setForeground(null);
			t3.setText("");

			t4.setEnabled(false);
			t4.setForeground(null);
			t4.setText("");

			btnChooseColour3.setEnabled(false);
			btnChooseColour3.setBackground(null);
			btnChooseColour3.setText("Select Colour");

			btnChooseColour4.setEnabled(false);
			btnChooseColour4.setBackground(null);
			btnChooseColour4.setText("Select Colour");

			btnChooseAvatar3.setEnabled(false);
			btnChooseAvatar3.remove(lblAlexander3);
			btnChooseAvatar3.remove(lblSuleiman3);
			btnChooseAvatar3.remove(lblCengiz3);
			btnChooseAvatar3.remove(lblCaesar3);
			btnChooseAvatar3.remove(lblNapoleon3);
			btnChooseAvatar3.remove(lblHannibal3);
			btnChooseAvatar3.setHorizontalAlignment(JTextField.CENTER);
			btnChooseAvatar3.setText("Select Avatar");
			btnChooseAvatar3.setFont(new Font(Font.SERIF,Font.BOLD,16));
			btnChooseAvatar3.setForeground(Color.BLACK);

			btnChooseAvatar4.setEnabled(false);
			btnChooseAvatar4.remove(lblAlexander4);
			btnChooseAvatar4.remove(lblSuleiman4);
			btnChooseAvatar4.remove(lblCengiz4);
			btnChooseAvatar4.remove(lblCaesar4);
			btnChooseAvatar4.remove(lblNapoleon4);
			btnChooseAvatar4.remove(lblHannibal4);
			btnChooseAvatar4.setHorizontalAlignment(JTextField.CENTER);
			btnChooseAvatar4.setText("Select Avatar");
			btnChooseAvatar4.setFont(new Font(Font.SERIF,Font.BOLD,16));
			btnChooseAvatar4.setForeground(Color.BLACK);

			avatarNoP3 = 0;
			avatarNoP4 = 0;

			btnNumOfPlayers2.setEnabled(false);
			btnNumOfPlayers3.setEnabled(true);
			btnNumOfPlayers4.setEnabled(true);

			numOfPlayers = 2;
		}

		if(e.getSource()==btnNumOfPlayers3)
		{
			btnNumOfPlayers2.setBorder(UIManager.getBorder("Button.border"));
			btnNumOfPlayers4.setBorder(UIManager.getBorder("Button.border"));
			Border thickBorder = new LineBorder(Color.green, 5);
			btnNumOfPlayers3.setBorder(thickBorder);

			t4.setEnabled(false);
			t4.setForeground(null);
			t4.setText("");

			btnChooseColour4.setEnabled(false);
			btnChooseColour4.setBackground(null);
			btnChooseColour4.setText("Select Colour");

			btnChooseAvatar4.setEnabled(false);
			btnChooseAvatar4.remove(lblAlexander4);
			btnChooseAvatar4.remove(lblSuleiman4);
			btnChooseAvatar4.remove(lblCengiz4);
			btnChooseAvatar4.remove(lblCaesar4);
			btnChooseAvatar4.remove(lblNapoleon4);
			btnChooseAvatar4.remove(lblHannibal4);
			btnChooseAvatar4.setHorizontalAlignment(JTextField.CENTER);
			btnChooseAvatar4.setText("Select Avatar");
			btnChooseAvatar4.setFont(new Font(Font.SERIF,Font.BOLD,16));
			btnChooseAvatar4.setForeground(Color.BLACK);

			avatarNoP4 = 0;

			btnNumOfPlayers2.setEnabled(true);
			btnNumOfPlayers3.setEnabled(false);
			btnNumOfPlayers4.setEnabled(true);

			t3.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);

			numOfPlayers = 3;
		}

		if(e.getSource()==btnNumOfPlayers4)
		{
			btnNumOfPlayers2.setBorder(UIManager.getBorder("Button.border"));
			btnNumOfPlayers3.setBorder(UIManager.getBorder("Button.border"));
			Border thickBorder = new LineBorder(Color.green, 5);
			btnNumOfPlayers4.setBorder(thickBorder);

			t3.setEnabled(true);
			btnChooseColour3.setEnabled(true);
			btnChooseAvatar3.setEnabled(true);

			t4.setEnabled(true);
			btnChooseColour4.setEnabled(true);
			btnChooseAvatar4.setEnabled(true);

			btnNumOfPlayers2.setEnabled(true);
			btnNumOfPlayers3.setEnabled(true);
			btnNumOfPlayers4.setEnabled(false);

			numOfPlayers = 4;
		}




		if(e.getSource()==btnQuitGame)
		{
			playSound("snd_quitgame.wav");
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
