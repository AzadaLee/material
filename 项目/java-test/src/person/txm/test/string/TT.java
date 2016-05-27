package person.txm.test.string;

import java.util.HashMap;
import java.util.Map;

public class TT {
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("str", "value");
		if(map.get("str") instanceof String)
			System.out.println(true);
		else
			System.out.println(false);
	}
}
