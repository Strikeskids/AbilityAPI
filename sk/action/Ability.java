package sk.action;

import java.util.HashMap;

import org.powerbot.game.api.wrappers.widget.WidgetChild;

import sk.general.Completion;

/**
 * Holds information for an Ability that can be placed in the action bar. Refers to any draggable icon in the
 * interface that can be used from the action bar. Abilities should be static. When an ability is created, it
 * should be added to the {@link Ability#ALL_ABILITIES} map.
 * 
 * @author Strikeskids
 * 
 */
public interface Ability {

	/**
	 * Attempts to show the ability in its typical location (ability book, emote book)
	 * 
	 * @return <tt>true</tt> on success; <tt>false</tt> on failure
	 * @see Ability#isVisible()
	 */
	public abstract boolean show();

	/**
	 * Checks to see if the ability is visible in its typical location
	 * 
	 * @return <tt>true</tt> if the ability is visible; otherwise <tt>false</tt>
	 * @see Ability#show()
	 */
	public abstract boolean isVisible();

	/**
	 * Checks to see if the ability is available to cast, such that it is not grayed out or currently on
	 * cooldown period
	 * 
	 * @return <tt>true</tt> if the ability is available; otherwise <tt>false</tt>
	 */
	public abstract boolean available();

	/**
	 * Gets the {@link WidgetChild} that corresponds to this ability in its typical location
	 * 
	 * @return The WidgetChild that corresponds to this ability, or <tt>null</tt> if such a WidgetChild has
	 *         incorrect properties
	 */
	public abstract WidgetChild getChild();

	/**
	 * Gets a {@link Completion} that determines when the ability has been successfully cast
	 * 
	 * @return The Completion that corresponds to a successful cast of this ability or <tt>null</tt> if no
	 *         such Completion has been created
	 */
	public abstract Completion getChange();

	/**
	 * Gets the ability id in the Action Bar
	 * 
	 * @return the ability id
	 */
	public abstract int getAbilityId();

	/**
	 * A {@link HashMap} from all the ability ids to their corresponding ability
	 */
	public static HashMap<Integer, Ability> ALL_ABILITIES = new AbilityMap(2000);
	
	static class AbilityMap extends HashMap<Integer, Ability> {

		private static final long serialVersionUID = -4049206619364768012L;
		
		public AbilityMap(int size) {
			System.out.println("Starting up ability map");
		}
		
	}

}
