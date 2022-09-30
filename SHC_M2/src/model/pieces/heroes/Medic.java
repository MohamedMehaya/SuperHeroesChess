package model.pieces.heroes;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.game.Cell;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import exceptions.InvalidPowerTargetException;
import exceptions.InvalidPowerUseException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;

public class Medic extends ActivatablePowerHero {

	public Medic(Player player, Game game, String name) {
		super(player, game, name);
	}

	@Override
	public void moveUpRight() throws UnallowedMovementException {
		throw new UnallowedMovementException("Direction of movement UPRIGHT is not allowed for this piece", this,
				Direction.UPRIGHT);
	}

	@Override
	public void moveUpLeft() throws UnallowedMovementException {
		throw new UnallowedMovementException("Direction of movement UPLEFT is not allowed for this piece", this,
				Direction.UPLEFT);
	}

	@Override
	public void moveDownRight() throws UnallowedMovementException {
		throw new UnallowedMovementException("Direction of movement DOWNRIGHT is not allowed for this piece", this,
				Direction.DOWNRIGHT);
	}

	@Override
	public void moveDownLeft() throws UnallowedMovementException {

		throw new UnallowedMovementException("Direction of movement DOWNLEFT is not allowed for this piece", this,
				Direction.DOWNLEFT);
	}

	@Override
	public void usePower(Direction direction, Piece target, Point newPos)
			throws InvalidPowerUseException, WrongTurnException {

		super.usePower(direction, target, newPos);

		Point destination = getDirectionPos(new Point(getPosI(), getPosJ()), direction);
		adjustBounds(destination);
		Cell destinaionCell = getGame().getCellAt(destination.x, destination.y);

		if (destinaionCell.getPiece() == null) {
			if (this != null && target != null && target.getOwner() != null && target.getOwner() != getOwner()) {

				throw new InvalidPowerTargetException("Medic can not choose " + target.getName()
						+ " as a target because of incompatible target's side with the power requirement (Enemy/Ally)",
						this, target);
			}

			if (getOwner().getDeadCharacters().contains(target)) {

				destinaionCell.setPiece(target);

				if (target instanceof ActivatablePowerHero) {
					((ActivatablePowerHero) target).setPowerUsed(false);

				}
				if (target instanceof Armored)
					((Armored) target).setArmorUp(true);

				target.setPosI(destination.x);
				target.setPosJ(destination.y);
				getOwner().getDeadCharacters().remove(target);
				setPowerUsed(true);
				getGame().switchTurns();

			} else {
				throw new InvalidPowerTargetException(
						"Medic can not resurrect " + target.getName() + " that is not yet eliminated", this, target);
			}

		} else {
			throw new InvalidPowerTargetException("Medic can not place " + target.getName()
					+ " in the specified cell or direction as this cell is occupied", this, target);
		}

	}

	@Override
	public String toString() {
		return "Medic";
	}
}
