import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class illustrates how inheritance and dynamic binding works in
 * Java.  It uses a simplified hierarchy of *Map classes that are
 * implemented by the corresponding "real" Java *Map classes.
 */
public class DynamicBindingTest {
    /**
     * Superclass for the hierarchy.
     */
    public static abstract class SimpleAbstractMap<K, V> {
        // This is an abstract method - *must* be overridden by
        // concrete subclasses.
        public abstract Set<Map.Entry<K, V>> entrySet();

        // This is a non-abstract method - *may* be overridden by
        // concrete subclasses.
        public V put(K key, V value) {
            throw new UnsupportedOperationException();
        }
    }
	
    /**
     * One subclass in the hierarchy.
     */
    public static class SimpleHashMap<K, V> extends SimpleAbstractMap<K, V> {
        // Concrete state.
        private HashMap<K, V> mMap = new HashMap<>();

        // Override the superclass method.
        public Set<Map.Entry<K, V>> entrySet() {
            System.out.println("SimpleHashMap.entrySet()");
            return mMap.entrySet();
        }

        // Override the superclass method.
        public V put(K key, V value) {
            System.out.println("SimpleHashMap.put");
            return mMap.put(key, value);
        }
    }
	
    /**
     * One subclass in the hierarchy.
     */
    public static class SimpleTreeMap<K, V> extends SimpleAbstractMap<K, V> {
        // Concrete state.
        private TreeMap<K, V> mMap = new TreeMap<>();

        // Override the superclass method.
        public Set<Map.Entry<K, V>> entrySet() {
            System.out.println("SimpleTreeMap.entrySet()");
            return mMap.entrySet();
        }

        // Override the superclass method.
        public V put(K key, V value) {
            System.out.println("SimpleTreeMap.put");
            return mMap.put(key, value);
        }
    }
	
    /**
     * One subclass in the hierarchy.
     */
    private static class SimpleConcurrentHashMap<K, V> extends SimpleAbstractMap<K, V> {
        // Concrete state.
        private ConcurrentHashMap<K, V> mMap = new ConcurrentHashMap<>();

        // Override the superclass method.
        public Set<Map.Entry<K, V>> entrySet() {
            System.out.println("SimpleConcurrentHashMap.entrySet()");
            return mMap.entrySet();
        }

        // Override the superclass method.
        public V put(K key, V value) {
            System.out.println("SimpleConcurrentHashMap.put()");
            return mMap.put(key, value);
        }
    }
	
    /**
     * Factory method that creates the designated @a mapType.
     */
    private static SimpleAbstractMap<String, Integer> makeMap(String mapType) {
        if (mapType.equals("HashMap"))
            return new SimpleHashMap<String, Integer>();
        else if (mapType.equals("TreeMap"))
            return new SimpleTreeMap<String, Integer>();
        else if (mapType.equals("ConcurrentHashMap"))
            return new SimpleConcurrentHashMap<String, Integer>();
        else
            throw new IllegalArgumentException();
    }
	
    /**
     * Main entry point into the test program.
     */
    public static void main(String[] args) {
        // Factory method makes the appropriate type of Map subclass.
        SimpleAbstractMap<String, Integer> map = makeMap(args[0]);

        // Add some elements to the Map.
        map.put("I", 1);
        map.put("am", 2); 
        map.put("Ironman", 7);

        // Print out the key/values pairs in the Map.
        for (Map.Entry<String, Integer> s : map.entrySet())
            System.out.println
                ("key = "
                 + s.getKey()
                 + " value = "
                 + s.getValue());
    }
}
