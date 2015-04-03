package com.hofi.game.bouncyballs;

import org.jbox2d.common.Vec2;

import com.hofi.game.bouncyballs.interfaces.GameObject;

public class Arrow {

	private Sprite arrow;

	public Arrow() {
		arrow = new Sprite("arrow");
		arrow.setPosition(0, 0);
		arrow.setScale(1, 1);
		arrow.setCenter(0f, 0.5f);
		arrow.setVisible(false);
	}

	public boolean isVisible() {
		return arrow.getVisible();
	}

	public void setVisible(boolean value) {
		arrow.setVisible(value);
	}

	public void setByTouchVector(float fromX, float fromY, boolean reversed) {
		VectorData vd = Common.input.getTouchVectorData();
		if (reversed)
			arrow.setRotation(vd.angle + 180f);
		else
			arrow.setRotation(vd.angle);
		arrow.setScale(40, vd.length);
		arrow.setPosition(fromX, fromY);
	}

	public void setByTouchVector(float fromX, float fromY) {
		setByTouchVector(fromX, fromY, false);
	}

	public void setByTouchVector(Vec2 from) {
		setByTouchVector(from, false);
	}

	public void setByTouchVector(Vec2 from, boolean reversed) {
		setByTouchVector(from.x, from.y, reversed);
	}

	public void setByTouchVector(GameObject objFrom) {
		setByTouchVector(objFrom, false);
	}

	public void setByTouchVector(GameObject objFrom, boolean reversed) {
		if (objFrom == null)
			return;
		if (objFrom.getDPO() == null)
			return;
		if (objFrom.getDPO().getPhysicsObject() == null)
			return;
		setByTouchVector(
				objFrom.getDPO().getPhysicsObject().getWorldPosition(),
				reversed);
	}

	public void draw() {
		arrow.draw();
	}
}
