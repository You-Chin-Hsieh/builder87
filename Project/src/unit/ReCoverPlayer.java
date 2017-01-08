package unit;
//技能 : 自我回復  CD 15秒

import java.awt.Point;

import game.map.Map;

public class ReCoverPlayer extends Player
{

	public ReCoverPlayer(int xpos, int ypos) 
	{
		super(xpos, ypos);
		setPlayerName("99");
		
	}

	public ReCoverPlayer(Point point) 
	{
		super(point);
		setPlayerName("99");
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
