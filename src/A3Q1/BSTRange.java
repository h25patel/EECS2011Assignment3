package A3Q1;

/**
 * Extends the TreeMap class to allow convenient access to entries within a
 * specified range of key values (findAllInRange).
 * 
 * @author jameselder
 */
public class BSTRange<K, V> extends TreeMap<K, V> {

	/*
	 * Returns the lowest (deepest) position in the subtree rooted at pos that
	 * is a common ancestor to positions with keys k1 and k2, or to the
	 * positions they would occupy were they present.
	 */
	protected Position<Entry<K, V>> findLowestCommonAncestor(K k1, K k2, Position<Entry<K, V>> pos) {

		if (pos.getElement() != null && this.compare(k2, pos.getElement()) < 0) {
			pos = this.findLowestCommonAncestor(k1, k2, left(pos));
			return pos;
		} else if (pos.getElement() != null && this.compare(k1, pos.getElement()) > 0) {
			pos = this.findLowestCommonAncestor(k1, k2, right(pos));
			return pos;
		} else {
			return pos;
		}
	}

	/*
	 * Finds all entries in the subtree rooted at pos with keys of k or greater
	 * and copies them to L, in non-decreasing order.
	 */
	protected void findAllAbove(K k, Position<Entry<K, V>> pos, PositionalList<Entry<K, V>> L) {
		if (pos.getElement() != null && this.compare(k, pos.getElement()) <= 0) {
			this.findAllAbove(k, left(pos), L);
			L.addLast(pos.getElement());
			this.findAllAbove(k, right(pos), L);
		} else if (pos.getElement() != null && this.compare(k, pos.getElement()) > 0) {
			this.findAllAbove(k, right(pos), L);
		} else {
			return;
		}
	}

	/*
	 * Finds all entries in the subtree rooted at pos with keys of k or less and
	 * copies them to L, in non-decreasing order.
	 */
	protected void findAllBelow(K k, Position<Entry<K, V>> pos, PositionalList<Entry<K, V>> L) {
		if (pos.getElement() != null && this.compare(k, pos.getElement()) >= 0) {
			this.findAllBelow(k, left(pos), L);
			L.addLast(pos.getElement());
			this.findAllBelow(k, right(pos), L);
		} else if (pos.getElement() != null && this.compare(k, pos.getElement()) < 0) {
			this.findAllBelow(k, left(pos), L);
		} else {
			return;
		}
	}

	/*
	 * Returns all entries with keys no less than k1 and no greater than k2, in
	 * non-decreasing order.
	 */
	public PositionalList<Entry<K, V>> findAllInRange(K k1, K k2) {
		Position<Entry<K, V>> rootPos = this.findLowestCommonAncestor(k1, k2, this.root());
		PositionalList<Entry<K, V>> list = new LinkedPositionalList<Entry<K, V>>();
		if (rootPos.getElement() != null) {
			this.findAllAbove(k1, left(this.root()), list);
			list.addLast(rootPos.getElement());
			this.findAllBelow(k2, right(rootPos), list);
			return list;
		} else {
			return list;
		}
	}
}
