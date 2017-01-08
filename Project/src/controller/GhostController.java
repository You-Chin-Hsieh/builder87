package controller;
import unit.Ghost;

import java.awt.Event;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import game.map.Map;
import set.GameSet;
import unit.Boss;
import unit.Entity.state;
import unit.Ghost.TYPE;
import unit.OxygenGhost;
import unit.SpeedUpGhost;
import unit.WallPassGhost;
import view.GameView;
import view.MapView;

public class GhostController
{
	private MapController mapController;
	private Ghost ghost;
	
	GhostController(MapView mapView,Map map,int num,PlayerController player1,PlayerController player2)
	{
		mapController = new MapController(map,mapView);
		int randomNum = new Random().nextInt(GameSet.ghostLairLocation.length);
		switch(num){
			case 0:
				ghost = new Ghost(GameSet.ghostLairLocation[randomNum], TYPE.Normal,player1,player2);
				break;
			case 1:
				ghost = new OxygenGhost(GameSet.ghostLairLocation[randomNum], TYPE.Oxygen,player1,player2);
				break;
			case 2:
				ghost = new SpeedUpGhost(GameSet.ghostLairLocation[randomNum], TYPE.SpeedUp,player1,player2);
				break;
			case 3:
				ghost = new WallPassGhost(GameSet.ghostLairLocation[randomNum], TYPE.WallPass,player1,player2);
				break;
			default:
				ghost = new Boss(GameSet.ghostLairLocation[randomNum], TYPE.Boss,player1,player2);
				break;
		}
		mapView.setLabelIcon(ghost.getXpos(), ghost.getYpos(),ghost.getImgPath()+"Left.png");
		moveTimer();
	}
	
	private void moveTimer()
	{
		Timer timer = new Timer();
		GhostMove t = new GhostMove();
		timer.schedule(t ,10,300-ghost.getSpeed());
	}
	
	public class GhostMove extends TimerTask
	{
		@Override
		public void run()
		{
			if(!mapController.map.isWall(ghost.getXpos(), ghost.getYpos())) //如果不是牆壁設成地板
				mapController.mapView.setLabelIcon(ghost.getXpos(), ghost.getYpos(),"map/img/floor.jpg");
			else if(isReborn(ghost.getXpos(), ghost.getYpos())) //如果是重生點就設成重生點
				mapController.mapView.setLabelIcon(ghost.getXpos(), ghost.getYpos(),"map/img/ghostLair.gif");
			else//設成牆壁，穿牆鬼和魔王專用	
				mapController.mapView.setLabelIcon(ghost.getXpos(), ghost.getYpos(),"map/img/wall.png");
			
			ghost.autoMove(mapController.map);
			mapController.mapView.setLabelIcon(ghost.getXpos(), ghost.getYpos(),ghost.getImgPath()+"Left.png");
		
		}
		
		public Boolean isReborn(int x,int y){
			if(x == 1 && y == 1) return true;
			if(x == 1 && y == 24) return true;
			if(x == 24 && y == 1) return true;
			if(x == 24 && y == 24) return true;
			return false;
		}
	}
	
	
}
