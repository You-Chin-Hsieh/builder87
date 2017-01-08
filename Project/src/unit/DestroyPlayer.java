package unit;
//技能 : 自我回復  CD 15秒   能夠回復已失去生命的70%

import java.awt.Point;

import game.map.Map;

public class DestroyPlayer extends Player 
{

	public DestroyPlayer(int xpos, int ypos) 
	{
		super(xpos, ypos);
		setPlayerName("U緊");
		
	}

	public DestroyPlayer(Point point) 
	{
		super(point);
		setPlayerName("U緊");
	}

	@Override
	public void skill(Map map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setImgPath() {
		// TODO Auto-generated method stub
		
	}

}
