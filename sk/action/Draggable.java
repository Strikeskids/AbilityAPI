package sk.action;

import java.util.LinkedHashMap;
import java.util.Map;

import org.powerbot.game.api.wrappers.widget.WidgetChild;

/**
 * Something that can be dragged onto the action bar
 * 
 * @author Strikeskids
 * 
 */
public interface Draggable {

	/**
	 * Gets the id of this draggable
	 * 
	 * @return the id of the draggable
	 */
	public abstract int getId();

	/**
	 * Attempts to show the {@link WidgetChild} for this draggable
	 * 
	 * @return <tt>true</tt> if the child is visible. <tt>false</tt> otherwise
	 */
	public abstract boolean show();

	/**
	 * Checks to see if the {@link WidgetChild} of this draggable is visible
	 * 
	 * @return <tt>true</tt> if the child is visible. <tt>false</tt> otherwise
	 */
	public abstract boolean isVisible();

	/**
	 * Gets the {@link WidgetChild} of this draggable
	 * 
	 * @return The child of this draggable. Can return <tt>null</tt> if this child currently does not exist or
	 *         does not validate
	 */
	public abstract WidgetChild getChild();

	/**
	 * A map of all of the draggables available
	 */
	public static Map<Integer, Draggable> ALL = new LinkedHashMap<Integer, Draggable>(2000);
}
