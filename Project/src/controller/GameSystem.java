package controller;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;



import game.map.Map;
import set.GameSet;
import unit.Ghost;
import unit.Player;
import unit.Character.DIRECTION;
import unit.Ghost.TYPE;
import unit.InvinciblePlayer;
import unit.JumperPlayer;
import view.GameView;
import view.MapView;
import view.PlayerStateView;

public class GameSystem
{
	private Map map;
	private MapView mapView;
	private MapController mapController;
	
	private Player player1;
	private Player player2;
	private PlayerController playerController1;
	private PlayerController playerController2;
	private Thread timeThread;
	
	private Ghost ghost[];
	private GhostController ghostController[];
	
	private GameView gameView;
	private PlayerStateView playerStateView1;
	private PlayerStateView playerStateView2;
	private javazoom.jl.player.Player music = null;
	
	private static int num = 0;
	
	public GameSystem(Player player1,Player player2)
	{
		map = new Map();
		mapView = new MapView(map.getAllLocation());
		mapController = new MapController(map,mapView);
		
		
		
		playerStateView1 = new PlayerStateView("player1",player1);
		playerStateView2 = new PlayerStateView("player2",player2);
		gameView = new GameView(playerStateView1,playerStateView2,mapView);
		this.player1 = player1;
		this.player2 = player2;
		playerController1 = new PlayerController(player1,mapController,1);
		playerController2 = new PlayerController(player2,mapController,2);
		
		timeThread=new Thread(){
			@Override
			public void run() {
				ghostController = new GhostController[GameSet.GHOSTNUMBER];
				ghost = new Ghost[GameSet.GHOSTNUMBER];
				
				
				try
				{
					FileInputStream fis = new FileInputStream("Naruto.mp3");
					BufferedInputStream bis = new BufferedInputStream(fis);
				    music = new javazoom.jl.player.Player(bis);
				} catch (Exception e)
				{
					System.out.println("Problem playing file " + "Naruto.mp3");
					System.out.println(e);
				}

				// run in new thread to play in background
				new Thread() {
					public void run ()
					{
						try
						{
							music.play();
						} catch (Exception e)
						{
							System.out.println(e);
						}
					}
				}.start();

			
			
			Timer FoodGenerate1 = new Timer();
			Timer FoodGenerate2 = new Timer();

			FoodGenerate1.schedule(
					new TimerTask()
					{

						@Override
						public void run() {
							Point point = mapController.FoodGenerate();
							try {
								Thread.sleep(10000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							mapController.FoodDelete(point);
							
						}
						
						
					},5000,15000);
			
			FoodGenerate2.schedule(
					new TimerTask()
					{
						@Override
						public void run() {
							Point point = mapController.FoodGenerate();
							try {
								Thread.sleep(10000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							mapController.FoodDelete(point);
							
						}
						
						
					},10000,15000);
			
			
			Timer Reborn = new Timer();
			Reborn.schedule(
					new TimerTask()
					{
						@Override
						public void run() 
						{
							int randomNum = new Random().nextInt(GameSet.ghostLairLocation.length);
							ghostController[num] = new GhostController(mapController.mapView,map,num,playerController1,playerController2);
							num++;
						}
						
							
					},10,20000
				);
			
			
			/*Timer wallCreateAndDestory = new Timer();
			Reborn.schedule(
					new TimerTask()
					{
						@Override
						public void run() 
						{
							randomSetWall();	
						}
						
							
					},1000,1000
				);
			*/
			
			Timer refresh = new Timer();
			refresh.schedule(
					new TimerTask()
					{

						@Override
						public void run() {
							playerStateView1.repaint();
							playerStateView2.repaint();
						}
						
					},0,50);
			
			};
		};
			
	
	}
	

	public void randomSetWall()
	{
		
		int i,j;
		do
		{
			i = new Random().nextInt(GameSet.border);
			j = new Random().nextInt(GameSet.border);
		}
		while(mapController.map.isWall(i,j)==true);
		mapController.addPlayerWall(i,j,GameSet.constructTime);
		final int ii = i;
		final int jj = j;
		Timer destory = new Timer();
		destory.schedule(
				new TimerTask()
				{
					@Override
					public void run()
					{
						mapController.destoryPlayerWall(ii, jj,GameSet.constructTime);
					}
				}
				,3000);
		
	}
	
	public static void main(String args[])
	{
		Thread thread=new Thread(){
			@Override
			public void run(){
				GameSystem gameSystem = new GameSystem(new JumperPlayer(2,2),new InvinciblePlayer(21,21));
				try {
					gameSystem.timeThread.sleep(3000);
					gameSystem.timeThread.start();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//sgameSystem.timeThread.start();
			}
		};
		try {
			thread.sleep(1000);
			thread.run();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
