/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A3Q2;

/**
 *
 * @author elder
 */
public class DoubleProbeHashMap<K, V> extends ProbeHashMap<K, V> {

	int q; // Prime number of secondary hash

	// provide same constructors as base class
	/**
	 * Creates a hash table with capacity 17, prime factor 109345121 and
	 * secondary prime factor q = 13.
	 */
	public DoubleProbeHashMap() {
		super();
		q = 13; // largest prime less than 17
	}

	/**
	 * Creates a hash table with the given capacity cap, prime factor 109345121
	 * and secondary prime factor q equal to the smallest prime less than cap.
	 * Cap must be at least 3 otherwise an Exception is thrown.
	 */
	public DoubleProbeHashMap(int cap) throws Exception {
		super(cap);
		if (cap < 3) {
			throw new Exception();
		}
		q = selectSecondaryHashPrime(cap);
	}

	/**
	 * Creates a hash table with the given capacity and prime factor, and
	 * secondary prime factor q equal to the smallest prime less than capacity.
	 * Cap must be at least 3 otherwise an Exception is thrown.
	 */
	public DoubleProbeHashMap(int cap, int p) throws Exception {
		super(cap, p);
		if (cap < 3) {
			throw new Exception();
		}
		q = selectSecondaryHashPrime(cap);
	}

	/**
	 * Updates the size of the hash table to be at least newCap and rehashes all
	 * entries. Must also update secondary prime factor q.
	 */
	protected void resize(int newCap) {
		// Implement this method. Note that you can make use of the resize
		// method of probleHashMap with a super.resize call.
		super.resize(newCap);
		q = this.selectSecondaryHashPrime(newCap);
	}

	/**
	 * Searches for an entry with key equal to k (which is known to have primary
	 * hash value h1), returning the index at which it was found, or returning
	 * -(a+1) where a is the index of the first empty or available slot that can
	 * be used to store a new such entry. Uses secondary hashing function
	 * h’(k) = q - k mod q, where the secondary prime factor q is the smallest
	 * prime less than the current capacity.
	 *
	 * @param h1
	 *            the precalculated hash value of the given key
	 * @param k
	 *            the key
	 * @return index of found entry or if not found
	 */
	protected int findSlot(int h1, K k) {
        //modify the findSlot method of probeHashMap to use double hashing
		int slotAvailable = -1, indexJ = h1, count = 0;
		int result = -(slotAvailable + 1);

		if (count > this.capacity) {

			while (count < this.capacity) {
				count++;
				this.totalProbes++;
				if (this.table[indexJ].getKey().equals(k)) {
					return indexJ;
				} else if (this.isAvailable(indexJ)) {
					if (this.table[indexJ] == null) {
						break;
					}
					if (slotAvailable == -1) {
						slotAvailable = indexJ;
					}
				}
				int h = indexJ + 1;
				indexJ = h % this.capacity;
			}
		}
		return result;

	}

	/**
	 * Returns value of secondary hash function h’(k) = q - k mod q, where k
	 * is the hash code for key*
	 */
	private int secondaryHashValue(K key) {

		int k = (q - Math.abs(key.hashCode())) % q;
		return k;
	}

	// Selects secondary hash prime to be the largest prime less than cap
	int selectSecondaryHashPrime(int cap) {
		//
		for (int i = cap - 1; i > 0; i--) {
			if (isPrime(i)) {
				return i;
			}
		}
		return 1;
	}
}
