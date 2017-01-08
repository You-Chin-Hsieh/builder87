package unit;

import java.awt.Point;
import java.util.ArrayList;



import set.GameSet;

public abstract class Player extends Character implements playerSet
{
	public enum STATE{ALIVE,DEAD,INVINCIBLE};
	private STATE playerState=STATE.ALIVE;
	private int xNext;
	private int yNext;
	private String name;
	private boolean moveable=true;
	private int HP=MAXHP;
	private static int MAXHP = 600;
	private int CDtime;
	private boolean isCD=false;
	private boolean isHurt=true;
	private String imgPath;
	private int DestroyerWallSpeed=GameSet.destoryTime;
	private int ConstorWallSpeed=GameSet.constructTime;
	private ArrayList<PlayerWall> playerWallsCounter = new ArrayList<PlayerWall>() ;;
	
	public void addPlayerWAllsCounter( int x , int y )
	{
		playerWallsCounter.add(new PlayerWall(x, y)) ;
		System.out.println(playerWallsCounter);	
	}
	
	public void decreasePlayerWAllsCounter( int x , int y ) {
		int index = 0 ;
		for (int i = 0; i < playerWallsCounter.size() ; i++) 
		{
			if(playerWallsCounter.get(i).getXpos() == x && playerWallsCounter.get(i).getYpos() == y ){
				index = i ;
				break ;
			}
		}
		playerWallsCounter.remove( index ) ;
	}

	public ArrayList<PlayerWall> getPlayerWallsCounter() {
		return playerWallsCounter;
	}

	public STATE getPlayerState(){
		return playerState;
	}
	public void setPlayerState(STATE i){
		playerState=i;
	}
	public int getConstorWallSpeed(){
		System.out.println(ConstorWallSpeed);
		return ConstorWallSpeed;
	}
	public void setConstorWallSpeed(int i){
		ConstorWallSpeed=i;
	}
	public int getDestroyerWallSpeed(){
		System.out.println(DestroyerWallSpeed);
		return DestroyerWallSpeed;
	}
	public void setDestroyerWallSpeed(int i){
		DestroyerWallSpeed=i;
	}
	public boolean getIsHurt(){
		return isHurt;
	}
	public void setIsHurt(boolean i){
		isHurt=i;
	}
	public int getCDtime(){
		return CDtime;
	}
	public void setCDtime(int i){
		CDtime=i;
	}
	public boolean getIsCD(){
		return isCD;
	}
	public void setIsCD(boolean i){
		 isCD=i;
	}
	public Player(int xpos, int ypos)
	{
		super(xpos, ypos, state.PLAIN);
		speed = GameSet.PLAYERSPEED;
	}
	
	public Player(Point point)
	{
		super(point, state.PLAIN);
		speed = GameSet.PLAYERSPEED;
	}
	
	public void setPlayerName(String name)
	{
		this.name = name;
	}
	
	public String getPlayerName()
	{
		return name;
	}
	
	public String getImgPath()
	{
		return imgPath;
	}

	public int getXNext()
	{
		return xNext;
	}
	
	public void setXNext(int i)
	{
		xNext+=i;
	}
	
	public int getYNext()
	{
		return yNext;
	}
	
	public void setYNext(int i)
	{
		yNext+=i;
	}
	
	public int getHP()
	{
		return HP;
	}
	
	public void setHP(int i)
	{
		if(i > MAXHP)
			HP = 600;
		else if(i < 0)
			HP = 0;
		else
			HP = i;
	}
	
	
	public void setMoveable(boolean isTrue)
	{
		moveable=isTrue;
	}
	
	public boolean getMoveable()
	{
		return moveable;
	}
	

}
