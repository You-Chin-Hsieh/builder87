package unit;
//技能 : 翻牆  CD 10秒

import java.awt.Point;

import game.map.Map;

public class JumperPlayer extends Player
{

	public JumperPlayer(int xpos, int ypos) 
	{
		super(xpos, ypos);
		setPlayerName("馬子狗");
	}

	public JumperPlayer(Point point) 
	{
		super(point);
		setPlayerName("馬子狗");
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
