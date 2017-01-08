package unit;
//技能 : 無敵模式 CD 18秒

import java.awt.Point;

import game.map.Map;

public class InvinciblePlayer extends Player 
{

	public InvinciblePlayer(int xpos, int ypos) 
	{
		super(xpos, ypos);
		setPlayerName("歐老大");
	}

	public InvinciblePlayer(Point point) 
	{
		super(point);
		setPlayerName("歐老大");
		
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
