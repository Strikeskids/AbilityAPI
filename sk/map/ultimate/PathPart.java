package sk.map.ultimate;

import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.Verifiable;

public interface PathPart extends Verifiable {

	/**
	 * Checks to see if this part of the path is valid, and could be ready to traverse in the future
	 */
	public boolean validate();

	/**
	 * Attempts to traverse the path part
	 */
	public void traverse();

	/**
	 * Checks to see if this part of the path is ready to traverse and not at the end
	 * 
	 * @return <tt>true</tt> if the path is ready to traverse
	 */
	public boolean ready();
	
	public Tile getDestination();

}
