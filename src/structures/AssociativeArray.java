package structures;

import static java.lang.reflect.Array.newInstance;

/**
 * A basic implementation of Associative Arrays with keys of type K
 * and values of type V. Associative Arrays store key/value pairs
 * and permit you to look up values by key.
 *
 * @author Madel Sibal
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the associative array (the number of key/value pairs).
   */
  int size;

  /**
   * The array of key/value pairs.
   */
  KVPair<K, V> pairs[];

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty associative array.
   */
  @SuppressWarnings({ "unchecked" })
  public AssociativeArray() {
    // Creating new arrays is sometimes a PITN.
    this.pairs = (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(),
        DEFAULT_CAPACITY);
    this.size = 0;
  } // AssociativeArray()

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Create a copy of this AssociativeArray.
   */
  public AssociativeArray<K, V> clone() {
    AssociativeArray<K, V> clone = new AssociativeArray<>();
    clone.size = this.size;
    clone.pairs = new KVPair[this.pairs.length];
    for (int i = 0; i < this.size; i++) {
      clone.pairs[i] = this.pairs[i].clone();
    }
    return clone;
  } // clone()

  /**
   * Convert the array to a string.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder("{");
    for (int i = 0; i < size; i++) {
      if (i > 0) {
        sb.append(", ");
      }
      sb.append(pairs[i].key.toString()).append(": ").append(pairs[i].value.toString());
    }
    sb.append("}");
    return sb.toString();
  } // toString()

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Set the value associated with key to value. Future calls to
   * get(key) will return value.
   */
  public void set(K key, V value) {
    int index = find(key);
    if (index != -1) {
      pairs[index].value = value; // Update the value if the key exists.
    } else {
      if (size == pairs.length) {
        expand();
      }
      pairs[size++] = new KVPair<>(key, value);
    }
  } // set(K,V)

  /**
   * Get the value associated with key.
   *
   * @throws KeyNotFoundException when the key does not appear in the associative
   *                              array.
   */
  public V get(K key) throws KeyNotFoundException {
    int index = find(key);
    if (index != -1) {
      return pairs[index].value;
    } else {
      throw new KeyNotFoundException("Key not found: " + key);
    }
  } // get(K)

  /**
   * Determine if key appears in the associative array.
   */
  public boolean hasKey(K key) {
    return find(key) != -1;
  } // hasKey(K)

  /**
   * Remove the key/value pair associated with a key. Future calls
   * to get(key) will throw an exception. If the key does not appear
   * in the associative array, does nothing.
   */
  public void remove(K key) {
    int index = find(key);
    if (index != -1) {
      for (int i = index; i < size - 1; i++) {
        pairs[i] = pairs[i + 1];
      }
      size--;
    }
  } // remove(K)

  /**
   * Determine how many values are in the associative array.
   */
  public int size() {
    return this.size;
  } // size()

  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expand the underlying array.
   */
  private void expand() {
    this.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length * 2);
  } // expand()

  /**
   * Find the index of the first entry in `pairs` that contains key.
   * If no such entry is found, returns -1.
   */
  private int find(K key) {
    for (int i = 0; i < size; i++) {
      if (pairs[i].key.equals(key)) {
        return i;
      }
    }
    return -1; // Key not found.
  } // find(K)

} // class AssociativeArray
