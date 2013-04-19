package sk.map;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.powerbot.game.api.wrappers.Tile;

public class MapData {

	private Map<Tile, byte[]> data = new HashMap<>();
	private Map<Integer, HashSet<Tile>> cols = new HashMap<>();
	private Set<Integer> objectIds = new HashSet<Integer>();

	private int version = 12;

	private static final byte[] LINE_TERM = { 0x1, 0x4F, 0x2d, 0x50, 0x34, 0x12, 0x45, 0x34 };

	private void add(Tile c) {
		HashSet<Tile> col = cols.get(c.getX());
		if (col == null) {
			col = new HashSet<>();
			col.add(c);
			cols.put(c.getX(), col);
		} else {
			col.add(c);
		}
	}

	private void remove(Tile c) {
		HashSet<Tile> col = cols.get(c.getX());
		if (col != null) {
			col.remove(c);
			if (col.size() == 0)
				cols.remove(c.getX());
		}
		data.remove(c);
	}

	public void add(Tile start, byte[][] loc) {
		int dx = 0;
		for (byte[] arr : loc) {
			Tile cur = start.derive(dx++, 0);
			data.put(cur, Arrays.copyOf(arr, arr.length));
			add(cur);
			Collection<Tile> tiles = new ArrayList<>(cols.get(cur.getX()));
			for (Tile t : tiles) {
				if (t.equals(cur))
					continue;
				Tile tmp = join(cur, t);
				if (tmp != null)
					cur = tmp;
			}
		}
	}

	public void setTile(Tile loc, byte i) {
		HashSet<Tile> tiles = cols.get(loc.getX());
		if (tiles != null) {
			for (Tile t : tiles) {
				if (t.getY() > loc.getY() || t.getPlane() != loc.getPlane())
					continue;
				byte[] arr = data.get(t);
				int ind = loc.getY() - t.getY();
				if (ind < arr.length) {
					arr[ind] = i;
					return;
				}
			}
		}
		data.put(loc, new byte[] { i });
	}

	public byte get(Tile loc) {
		HashSet<Tile> tiles = cols.get(loc.getX());
		if (tiles != null) {
			for (Tile t : tiles) {
				if (t.getY() > loc.getY() || t.getPlane() != loc.getPlane())
					continue;
				byte[] arr = data.get(t);
				if (loc.getY() - t.getY() < arr.length) {
					return arr[loc.getY() - t.getY()];
				}
			}
		}
		return 0;
	}

	public boolean contains(Tile loc) {
		HashSet<Tile> tiles = cols.get(loc.getX());
		if (tiles != null) {
			for (Tile t : tiles) {
				if (t.getY() > loc.getY() || t.getPlane() != loc.getPlane())
					continue;
				byte[] arr = data.get(t);
				if (loc.getY() - t.getY() < arr.length) {
					return true;
				}
			}
		}
		return false;
	}

	private Tile join(Tile a, Tile b) {
		if (a.getPlane() == b.getPlane() && a.getX() == b.getX()) {
			byte[] arr = data.get(a), brr = data.get(b);
			int ay = a.getY(), by = b.getY();
			if (arr != null && brr != null
					&& (ay >= by && ay <= by + brr.length || by >= ay && by <= ay + arr.length)) {
				int sy = Math.min(ay, by);
				int ey = Math.max(ay + arr.length, by + brr.length);
				byte[] narr = new byte[ey - sy];
				for (int i = 0; i < narr.length; i++) {
					int ag = get(arr, sy - ay + i), bg = get(brr, sy - by + i);
					if (bg == 0xF0000) {
						narr[i] = (byte) ag;
					} else if (ag != 0xF0000) {
						narr[i] = (byte) bg;
					} else {
						System.out.println("Error joining " + a + b);
						return null;
					}
				}
				Tile n = new Tile(a.getX(), sy, a.getPlane());
				remove(a);
				remove(b);
				data.put(n, narr);
				add(n);
				return n;
			}
		}
		return null;
	}

	private int get(byte[] arr, int y) {
		if (y >= 0 && y < arr.length)
			return arr[y];
		return 0xF0000;
	}

	public void addObject(int id) {
		objectIds.add(id);
	}

	public Set<Integer> getObjectIds() {
		return objectIds;
	}

	public void readFrom(InputStream in) throws IOException {
		byte[] arr = new byte[32];
		int[] off = { arr.length, arr.length };
		ArrayList<Byte> bytes = new ArrayList<>();
		try {
			if (readInt(in, arr, off) < version)
				return;
			int i;
			while ((i = readInt(in, arr, off)) != -1) {
				objectIds.add(i);
			}
			while (true) {
				Tile t = new Tile(readInt(in, arr, off), readInt(in, arr, off), readInt(in, arr, off));
				int count = 0;
				while (true) {
					byte next = nextByte(in, arr, off);
					bytes.add(next);
					if (next == LINE_TERM[count]) {
						if (++count >= LINE_TERM.length) {
							for (int j = 0; j < LINE_TERM.length; j++)
								bytes.remove(bytes.size() - 1);
							break;
						}
					} else {
						count = 0;
					}
				}
				byte[] retArr = new byte[bytes.size()];
				int loc = 0;
				for (byte s : bytes) {
					retArr[loc++] = s;
				}
				data.put(t, retArr);
				add(t);
				bytes.clear();
			}
		} catch (EOFException ignored) {
		}
	}

	private byte nextByte(InputStream in, byte[] arr, int[] off) throws IOException, EOFException {
		if (off[0] >= off[1]) {
			off[1] = in.read(arr);
			off[0] = 0;
		}
		if (off[0] >= off[1]) {
			throw new EOFException();
		}
		return arr[off[0]++];
	}

	private int readInt(InputStream in, byte[] arr, int[] off) throws IOException {
		int ret = 0;
		for (int shift = 0; shift < 32; shift += 4) {
			ret |= nextByte(in, arr, off) << shift;
		}
		return ret;
	}

	public void writeTo(OutputStream os) throws IOException {
		write(os, version);
		for (int i : objectIds) {
			write(os, i);
		}
		write(os, -1);
		for (Entry<Tile, byte[]> entry : data.entrySet()) {
			byte[] arr = entry.getValue();
			Tile t = entry.getKey();
			write(os, t.getX());
			write(os, t.getY());
			write(os, t.getPlane());
			os.write(arr);
			for (byte b : LINE_TERM)
				os.write(b);
		}
	}

	private void write(OutputStream out, int i) throws IOException {
		for (int j = 0; j < 8; j++, i >>>= 4) {
			out.write(i & 0xF);
		}
	}

	public boolean equals(MapData md) {
		if (md.data.size() != this.data.size())
			return false;
		for (Tile t : md.data.keySet()) {
			if (!this.data.containsKey(t) || !Arrays.equals(md.data.get(t), this.data.get(t)))
				return false;
		}
		return true;
	}

	private static MapData stand;

	private static final String DATA_URL = ""; // TODO submit

	public static MapData get() {
		if (stand != null)
			return stand;
		stand = new MapData();
		try {
			// File main = Environment.getStorageDirectory();
			File main = new File(System.getProperty("user.home"), "RSBot2012");
			File dat = new File(main, "mapdata.in");
			if (dat.exists() && dat.canRead()) {
				BufferedInputStream stream = new BufferedInputStream(new FileInputStream(dat));
				stand.readFrom(stream);
				stream.close();
			} else {
				BufferedInputStream stream = new BufferedInputStream(new URL(DATA_URL).openStream());
				stand.readFrom(stream);
				stream.close();

				dat.mkdirs();
				dat.createNewFile();
				BufferedOutputStream save = new BufferedOutputStream(new FileOutputStream(dat));
				stand.writeTo(save);
				save.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			stand = null;
		}
		return stand;
	}
}
