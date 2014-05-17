package lab9;

public class MapListItem<K extends Comparable<K>, V> {
	K key;
	V value;
	MapListItem<K, V> next;
		
	/**
		 * Creates a single list item.
		 * @param number the value to be held in the item
		 * @param next a reference to the next item in the list
		 */
		MapListItem(K key, V value, MapListItem<K,V> next) {
			this.key = key;
			this.value   = value;
			this.next = next;
		}
		
}
