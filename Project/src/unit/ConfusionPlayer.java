package unit;
// 技能 : 方向混淆  CD 15秒

import java.awt.Point;

import game.map.Map;

public class ConfusionPlayer extends Player
{
	public ConfusionPlayer(int xpos, int ypos) 
	{
		super(xpos, ypos);
		setPlayerName("G7");
	}

	public ConfusionPlayer(Point point) 
	{
		super(point);
		setPlayerName("G7");
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
