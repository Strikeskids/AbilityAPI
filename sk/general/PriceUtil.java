package sk.general;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.powerbot.core.script.job.Task;

import sk.Script;

public class PriceUtil {

	private static final String[] PRICE_URL = { "http://www.tip.it/runescape/json/ge_single_item?item=XXX",
			"http://services.runescape.com/m=itemdb_rs/api/graph/XXX.json", "http://scripts.audefy.com/l/XXX",
			/*"http://craftscripts.com/price.php?geid=XXX&output=raw"*/ };
	// private static final String SEARCH_URL = "http://www.tip.it/runescape/json/ge_search?term=";

	private static Map<Integer, Integer> prices = new HashMap<>();
	private static HashSet<Integer> looking = new HashSet<>();
	private static int numRunning = 0;

	/**
	 * Gets the price for the given id. Returns -1 if looking for price, 0 if no price found, otherwise the
	 * price of the items
	 * 
	 * @param id
	 *            the id to look for
	 * @return the price of the item
	 */
	public static int getPrice(int id) {
		return getPrice(false, id);
	}

	public static int getPrice(boolean inThread, int id) {
		if (id <= 0)
			return 0;
		Integer price = prices.get(id);
		if (price == null) {
			if (looking.contains(id) || numRunning > 40 && !inThread)
				return -1;
			looking.add(id);
			Task t = new PriceTask(id);
			if (inThread) {
				t.execute();
				return getPrice(false, id);
			} else {
				Script.submit(t);
				return -1;
			}
		} else {
			return price;
		}
	}

	public static void setPrice(int id, int price) {
		looking.remove(id);
		prices.put(id, price);
	}

	private static class PriceTask extends Task {

		private final int searchId;

		public PriceTask(int id) {
			this.searchId = id;
		}

		@Override
		public void execute() {
			numRunning++;
			try {
				int skip = 2;
				int loc = (int) (Math.random() * (PRICE_URL.length - skip)) + skip;
				String data = IOUtil.readFromURL(PRICE_URL[loc].replaceAll("XXX", searchId + ""),
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:18.0) Gecko/20100101 Firefox/18.0");
				int price = 0;
				switch (loc) {
				case 0:
					Object jo = JSONUtil.parse(data);
					Object oid = JSONUtil.getFromObject(jo, "ge_id");
					Boolean bool = JSONUtil.getBoolean(oid);
					if (bool != null && !bool)
						price = 0;
					else {
						int id = JSONUtil.getInteger(JSONUtil.getFromObject(jo, "ge_id"));
						if (id == searchId)
							price = JSONUtil.getInteger(JSONUtil.getFromObject(jo, "mark_price"));
						else
							price = 0;
					}
					break;
				case 1:
					jo = JSONUtil.parse(data);
					Iterator<Entry<String, Object>> it = JSONUtil.iterateObject(JSONUtil
							.getFromObject(jo, "daily"));
					while (it.hasNext()) {
						price = JSONUtil.getInteger(it.next().getValue());
					}
					break;
				case 2:
					price = Integer.parseInt(data);
					break;
				case 3:
					try {
						price = Integer.parseInt(data);
					} catch (NumberFormatException ex) {
					}
					break;
				default:
					break;
				}
				System.out.println(price);
				setPrice(searchId, price);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				looking.remove(searchId);
				numRunning--;
			}
		}
	}

}
