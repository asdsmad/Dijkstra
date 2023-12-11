
public class Town implements Comparable<Town> {
	private String name;

	/**
	 * Constructor. Requires town's name.
	 * @param name - town name
	 */
	public Town(String name) {
		this.name = name;
	}

	/**
	 * Copy constructor.
	 * @param templateTown - an instance of Town
	 */
	public Town(Town templateTown) {
		name = templateTown.name;
	}

	/**
	 * Returns the town's name
	 * @return town's name
	 */
	public String getName() {
		return name;
	}

	@Override
	/**
	 * Compare to method
	 * @return 0 if names are equal, a positive or negative number if the names are not equal
	 */
	public int compareTo(Town o) {
		return name.compareTo(o.name);
	}

	/**
	 * To string method
	 * @return the town name
	 */
	public String toString() {
		return name;
	}

	/**
	 * @return the hashcode for the name of the town
	 */
	public int hashCode() {
		int code = name.hashCode();
		return code;
	}

	/**
	 * @return true if the town names are equal, false if not
	 */
	public boolean equals(Object obj) {
		// if(obj instanceof Town)
		if (obj instanceof Town) {
			Town town = (Town) obj;
			return town.name.equals(name);
		}
		return false;
		
	}

}
