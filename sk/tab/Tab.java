package sk.tab;

/**
 * Indicates a tab that can be opened
 * 
 * @author Strikeskids
 * 
 */
public interface Tab {
	/**
	 * Checks to see if this tab is currently open
	 * 
	 * @return <tt>true</tt> if the tab is open; <tt>false</tt> otherwise
	 */
	public boolean isOpen();

	/**
	 * Attempts to open this tab
	 * 
	 * @return <tt>true</tt> on success; <tt>false</tt> if the tab did not end open
	 */
	public boolean open();
}
