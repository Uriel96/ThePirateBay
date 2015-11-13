package com.Team.GameName.Characters;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.Team.GameName.Utilities.Controller;

public abstract class Enemy extends Character{
	
	private float x;
	
	public Enemy(Image image, float positionX, float positionY, int width, int height, int spriteX, int spriteY, int frames, int duration) throws SlickException {
		x = super.getPositionX();
	}

	@Override
	public void init() throws SlickException {

	}

	@Override
	public void Render(Graphics g) throws SlickException {
		g.setColor(Color.red);
		g.drawRect(super.positionX+5, super.positionY-11, 21, 6);
		g.setColor(Color.green);
		g.fillRect(super.positionX+5, super.positionY-10, (float) (super.health*0.2), 5);
		g.setColor(Color.white);
	}

	@Override
	public void Update(Controller controller, int delta) throws SlickException {
		
		
	}
	
	public void Update2(Controller controller, int delta, MainCharacter Monillo) throws SlickException {
		if (checkSight(controller,Monillo) == true)
		{
			chase(controller, delta, Monillo);
			
		}
		else
		{
			returnPosition(controller,delta);
		}
		
	}
	
	@Override
	void applyDamage(Graphics g) throws SlickException {
		
	}
	
	public boolean checkSight(Controller controller, MainCharacter Monillo){
		
		boolean OnSight = false;
		boolean CheckRange = false;
		OnSight = controller.doRayCast(Monillo);
		CheckRange = controller.checkRange(Monillo);
		if (OnSight && CheckRange)
		{
			return true;
		}
		return false;
	}
	
	public void chase(Controller controller, float delta, MainCharacter Monillo) throws SlickException{
		if(controller.getRange(this,Monillo) > 5)
		{
			if(Monillo.getPositionX() < this.positionX){
			this.move(controller, delta, Direction.Left);
			}
			if(Monillo.getPositionX() > this.positionX){
			this.move(controller, delta, Direction.Right);
			}
		}
	}
	
	
	public void returnPosition(Controller controller, float delta) throws SlickException{
		
		if(Math.abs(x - this.positionX) < 5)
		{
			this.move(controller, delta, Direction.Left);
			
		}
		if(Math.abs(x - this.positionX) > 5)
		{
			this.move(controller, delta, Direction.Right);
			
		}
		
	}
	
	public void dead(){
		
		
	}
	

		
	
	
}