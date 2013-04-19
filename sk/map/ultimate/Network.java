package sk.map.ultimate;

import sk.map.ultimate.teleport.Teleport;

public interface Network extends Teleport {
	
	public Network[] getDestinations();
	public boolean isSameNetwork(Teleport t);
	public boolean equals(Object o);
	
}
