package unit;

import java.awt.Point;
import java.util.Random;
import java.util.Vector;

import javax.security.auth.DestroyFailedException;

import controller.PlayerController;
import game.map.Map;
import set.GameSet;
import unit.Character.DIRECTION;
import unit.Ghost.TYPE;

public class WallPassGhost extends Ghost{	
	
	
	public WallPassGhost(int xpos, int ypos, TYPE ghostType) {
		super(xpos, ypos, ghostType);
		// TODO Auto-generated constructor stub
	}

	public WallPassGhost(Point point,TYPE ghostType,PlayerController player1,PlayerController player2)
	{
		super(point, ghostType,player1,player2);
		setGhostType(ghostType);
		setImgPath("ghost/WallPassGhost");
	}
	
	
	@Override
	public void ghostSkill() {
		//if touch player then player die
		//pass wall free
	}
	@Override 
	public void autoMove(Map map){
		Vector<DIRECTION> Search = new Vector<DIRECTION>();
		Search.clear();
		Random randomNumber = new Random();
		int xNext = 0,yNext = 0;
		Boolean change = true;
		Search.add(DIRECTION.DOWN);
		Search.add(DIRECTION.UP);
		Search.add(DIRECTION.LEFT);
		Search.add(DIRECTION.RIGHT);
		for(int i = 0;i < 3;i++)	Search.add(super.getDirection());
		
		
		while (change){
			direction = Search.get(randomNumber.nextInt(Search.size()));
		
			xNext = getXpos();
			yNext = getYpos();
		
			/*若有玩家在視線範圍就要去追*/
			//direction = super.searchIfPlayer(map,direction);
		
			//利用更改過後的方向進行移動
		
			switch(direction)
				{
					case UP:
						if((yNext-1) > 0) { yNext -= 1; change = false;}
						break;
					case DOWN: 
						if((yNext+1) < 25) { yNext += 1; change = false;}
						break;
					case LEFT:
						if((xNext-1) > 0) { xNext -= 1; change = false;}
						break;
					default:
						if((xNext+1) > 25) { xNext += 1; change = false;}
						break;
				}
		}
			
		if(map.isWall(xNext,yNext)){
			setImgPath("ghost/WallPassGhost");
		}
			
		setXpos(xNext);
		setYpos(yNext);
	}
	
	/*若視線範圍有玩家，回傳對應之移動方向*/
	public DIRECTION searchIfPlayer(Map map,DIRECTION direction){
		switch(direction){
			case UP:case DOWN:
					if(getYpos() == 1/*playerX*/){//面向上或下時若左右有玩家
						if(getXpos() > 10/*playerX*/)
							return DIRECTION.LEFT;
						if(getXpos() < 10/*playerX*/)
							return DIRECTION.RIGHT;	
					}
				break;
			case LEFT:case RIGHT:
					if(getXpos() == 1/*playerX*/){//面向左或右時若上下有玩家
						if(getYpos() > 10/*playerX*/)
							return DIRECTION.DOWN;
						if(getYpos() < 10/*playerX*/)
							return DIRECTION.UP;	
					}
				break;
			default:
				break;
		}
		return direction;
	}
}
