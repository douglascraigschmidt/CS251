import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class DynamicBindingTest {
	public static abstract class SimpleAbstractMap<K, V> {
		public abstract Set<Map.Entry<K, V>> entrySet();
		public V put(K key, V value) {
			throw new UnsupportedOperationException();
		}
	}
	
	public static class SimpleHashMap<K, V> extends SimpleAbstractMap<K, V> {
		private HashMap<K, V> mMap = new HashMap<>();
		public Set<Map.Entry<K, V>> entrySet() {
			System.out.println("SimpleHashMap.entrySet()");
			return mMap.entrySet();
		}
		public V put(K key, V value) {
			System.out.println("SimpleHashMap.put");
			return mMap.put(key, value);
		}
	}
	
	public static class SimpleTreeMap<K, V> extends SimpleAbstractMap<K, V> {
		private TreeMap<K, V> mMap = new TreeMap<>();
		public Set<Map.Entry<K, V>> entrySet() {
			System.out.println("SimpleTreeMap.entrySet()");
			return mMap.entrySet();
		}
		public V put(K key, V value) {
			System.out.println("SimpleTreeMap.put");
			return mMap.put(key, value);
		}
	}
	
	private static class SimpleConcurrentHashMap<K, V> extends SimpleAbstractMap<K, V> {
		private ConcurrentHashMap<K, V> mMap = new ConcurrentHashMap<>();

		public Set<Map.Entry<K, V>> entrySet() {
			System.out.println("SimpleConcurrentHashMap.entrySet()");
			return mMap.entrySet();
		}
		public V put(K key, V value) {
			System.out.println("SimpleConcurrentHashMap.put()");
			return mMap.put(key, value);
		}
	}
	
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
	
	public static void main(String[] args) {
		SimpleAbstractMap<String, Integer> map = makeMap(args[0]);

		map.put("I", 1);
		map.put("am", 2); 
		map.put("Ironman", 7);

		for (Map.Entry<String, Integer> s : map.entrySet())
		  System.out.println
		    ("key = "
		     + s.getKey()
		     + " value = "
		     + s.getValue());

	}
}
