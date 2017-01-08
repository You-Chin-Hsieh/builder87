package view;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


import controller.PlayerController;
import set.GameSet;
import unit.Player;

public class PlayerStateView extends JPanel
{
	private JLabel playerNum;
	private JLabel playerName;
	private JLabel playerHead;
	private String name;
	private ImageIcon img;
	private Player player;

	
	public PlayerStateView(String playerNum,Player player)
	{
		this.player = player;
		setSize(GameSet.playerViewWidth,GameSet.playerViewHeight);
		setLayout(null);
		setBackground(new Color(108,244,214));
		
		
		this.playerNum = new JLabel(playerNum,SwingConstants.CENTER);
		this.playerNum.setBounds(0,0,GameSet.playerViewWidth,GameSet.sectionHeight);
		add(this.playerNum);
		
		playerName = new JLabel(player.getPlayerName(),SwingConstants.CENTER);
		playerName.setBounds(0,40,GameSet.playerViewWidth,GameSet.sectionHeight);
		add(playerName);
		
		playerHead = new JLabel();
		img = new ImageIcon(player.getImgPath());
		Image imgs = (img.getImage()).getScaledInstance(GameSet.playerViewWidth,GameSet.playerViewWidth,Image.SCALE_SMOOTH);
		playerHead.setIcon(new ImageIcon(imgs));
		playerHead.setBounds(0,80,GameSet.playerViewWidth,GameSet.playerViewWidth);
		add(playerHead);
		
		
		
		
		
		this.playerNum.setFont(new Font(Font.DIALOG,Font.ITALIC,20));
		playerName.setFont(new Font(Font.DIALOG,Font.PLAIN,16));
		
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(10, 250, this.player.getHP() / 5, GameSet.hpHeight);
		g.drawString(String.format("%d",player.getHP()), 10, 280);
		
	}
	
}
