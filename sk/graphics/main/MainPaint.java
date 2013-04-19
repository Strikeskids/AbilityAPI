package sk.graphics.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.game.api.util.Time;

import sk.Script;
import sk.Universal;
import sk.graphics.Anchor;
import sk.graphics.Paintable;
import sk.graphics.RectangleUtil;
import sk.graphics.TextUtil;

public class MainPaint implements Paintable {

	private List<PaintTab> tabs = Collections.synchronizedList(new ArrayList<PaintTab>());
	private Rectangle[] head = null;
	private int curTab = -1;
	private int defaultTab = -1;

	public MainPaint() {

	}

	public MainPaint(PaintTab... tabs) {
		add(tabs);
	}

	public void add(PaintTab tab) {
		if (tab == null)
			return;
		this.tabs.add(tab);
		head = null;
	}

	public void add(PaintTab... tabs) {
		if (tabs == null)
			return;
		for (PaintTab tab : tabs) {
			add(tab);
		}
	}

	public void remove(PaintTab tab) {
		if (this.tabs.remove(tab))
			head = null;
	}

	public Rectangle[] buildHeaders() {
		if (head == null) {
			Rectangle full = RectangleUtil.build(centerSide, new Dimension(tabHeader.width,
					(tabHeader.height + spacing) * tabs.size() - spacing), Anchor.LEFT);
			if (full.y < 50 + spacing)
				full.y = 60 + spacing;
			Point top = full.getLocation();
			Rectangle[] rect = new Rectangle[tabs.size() + 1];
			for (int i = 0; i < rect.length; i++) {
				Point loc = new Point(top);
				loc.translate(0, (tabHeader.height + spacing) * i);
				rect[i] = new Rectangle(loc, tabHeader);
			}
			return head = rect;
		}
		return head;
	}

	public void move(Point loc) {
		if (loc == null) {
			setCurrentTab(defaultTab);
			return;
		}
		if (getTabBackground(curTab).contains(loc))
			return;
		Rectangle[] rects = buildHeaders();
		for (int i = 1; i < rects.length; i++) {
			if (rects[i].contains(loc)) {
				setCurrentTab(i - 1);
				return;
			}
		}
		setCurrentTab(defaultTab);
	}

	public void click(Point loc) {
		Rectangle[] rects = buildHeaders();
		for (int i = 0; i < rects.length; i++) {
			if (rects[i].contains(loc)) {
				if (i == defaultTab)
					defaultTab = -1;
				else
					defaultTab = i;
				break;
			}
		}
		if (curTab != -1) {
			Rectangle rect = getMainRect(curTab);
			if (rect.contains(loc)) {
				tabs.get(curTab).click(new Point(loc.x - rect.x, loc.y - rect.y));
			}
		}
	}

	public Shape getTabBackground(int tabNum) {
		Area ret = new Area();
		if (tabNum >= tabs.size() || tabNum < 0)
			return ret;

		Rectangle head = buildHeaders()[tabNum + 1];
		ret.add(new Area(head));

		ret.add(new Area(new Rectangle(head.x + head.width, head.y, spacing, head.height)));
		ret.add(new Area(getMainRect(tabNum)));
		return ret;
	}

	public Rectangle getMainRect(int tabNum) {
		if (tabNum >= tabs.size() || tabNum < 0)
			return null;

		Rectangle head = buildHeaders()[tabNum + 1];
		Point centerMain = new Point(head.getLocation());
		centerMain.translate(tabHeader.width + spacing, 0);

		return RectangleUtil.build(centerMain, new Dimension(MAIN_WIDTH, tabs.get(tabNum).getSize()),
				Anchor.TOP_LEFT);
	}

	@Override
	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Rectangle[] rects = buildHeaders();

		g.setColor(TEXT_COLOR);
		String topLine = Script.getName() + " by Strikeskids";
		String botLine = (Universal.testing ? "00:00:00 STATUS" : Time.format(Script.getElapsed()) + " "
				+ Script.getStatus());

		Point headerLoc = new Point(rects[0].getLocation());
		headerLoc.translate(tabHeader.width+spacing,0);
		
		TextUtil.drawTextFromPoint(g, headerLoc, topLine + "\n" + botLine, Anchor.TOP_LEFT);

		int loc = -1;
		for (PaintTab tab : tabs) {

			Rectangle head = rects[++loc + 1];

			if (loc == curTab) {
				g.setPaint(BACKGROUND);
				g.fill(getTabBackground(loc));

				Rectangle main = getMainRect(loc);

				Graphics2D tmp = (Graphics2D) g1.create(main.x, main.y, main.width, main.height);
				tmp.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				tab.paint(tmp);
				tmp.dispose();
			} else {
				g.setPaint(UNSELECTED_BACKGROUND);
				g.fill(head);
			}

			BufferedImage img = tab.getImage();
			if (img == null)
				img = defaultImage;

			g.drawImage(img, head.x + (tabHeader.width - tabHeaderImage.width) / 2, head.y
					+ (tabHeader.height - tabHeaderImage.height) / 2, tabHeaderImage.width, tabHeaderImage.height,
					null);
		}
	}

	public void setCurrentTab(int tab) {
		this.curTab = tab;
	}

	private static final Point centerSide = new Point(20, 170);
	private static final Dimension tabHeader = new Dimension(30, 30);
	private static final Dimension tabHeaderImage = new Dimension(20, 20);
	private static final int spacing = 10;

	public static final int MAIN_WIDTH = 250;

	public static final Color BACKGROUND = new Color(0, 0, 0, 100);
	public static final Color UNSELECTED_BACKGROUND = new Color(100, 100, 100, 100);
	public static final Color TEXT_COLOR = Color.white;
	private static final BufferedImage defaultImage = new BufferedImage(tabHeaderImage.width,
			tabHeaderImage.height, BufferedImage.TYPE_INT_ARGB);

	public static final Font FONT = new Font("Courier New", 0, 10), TITLE = new Font("Arial", 0, 14);

	static {
		// Graphics2D g = defaultImage.createGraphics();
		// g.setStroke(new BasicStroke(3));
		// g.setColor(Color.RED);
		// g.drawLine(0, 0, tabHeaderImage.width, tabHeaderImage.height);
		// g.drawLine(0, tabHeaderImage.height, tabHeaderImage.width, 0);
		// g.dispose();
	}

}
