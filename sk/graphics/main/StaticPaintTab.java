package sk.graphics.main;

import java.awt.image.BufferedImage;

public class StaticPaintTab extends PaintTab {

	private String[] strs;

	public StaticPaintTab(String... strs) {
		this.strs = strs;
	}

	public StaticPaintTab(int h, String... strs) {
		super(h);
		this.strs = strs;
	}

	public StaticPaintTab(BufferedImage img, String... strs) {
		super(img);
		this.strs = strs;
	}

	public StaticPaintTab(BufferedImage img, int h, String... strs) {
		super(img, h);
		this.strs = strs;
	}

	@Override
	public String[] getStrings() {
		return strs;
	}

}
