package sk.graphics.time;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Number {

	private BufferedImage img;
	private int num;
	private final int numDigits;
	private final Digit[] digits;

	public Number(int digits, Color c) {
		if (digits > 6)
			digits = 6;
		else if (digits <= 0)
			digits = 8;
		this.numDigits = digits;
		this.digits = new Digit[numDigits];
		for (int i = 0; i < numDigits; i++) {
			this.digits[i] = new Digit(c);
		}
	}

	public Number(Color c) {
		this(0, c);
	}

	public void setNumber(int n) {
		if (numDigits == 8)
			this.num = n & ~(0x1 << 31);
		else if (num < 0)
			this.num = n % (int) Math.pow(10, numDigits);
		else
			this.num = n % (int) Math.pow(10, numDigits + 1);
	}

	public BufferedImage getImage() {
		if (img == null) {
			img = new BufferedImage(numDigits * (Digit.WIDTH + SPACING) - SPACING, Digit.HEIGHT,
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D canvas = img.createGraphics();
			for (int i = 0; i < numDigits; i++) {
				canvas.drawImage(digits[i].getImage(), i * (Digit.WIDTH + SPACING), 0, null);
			}
			canvas.dispose();
		}
		return img;
	}

	public void update() {
		img = null;
		if (numDigits != 8) {
			int n = num;
			int d = 0;
			if (n < 0) {
				++d;
				n = -n;
				this.digits[0].step(10);
			}
			for (int i = numDigits - 1, j = n; i >= d; --i, j /= 10) {
				this.digits[i].step(j % 10);
			}
		} else {
			int sec = num % 60;
			int min = num / 60 % 60;
			int hrs = num / 60 / 60;
			this.digits[2].step(11);
			this.digits[5].step(11);
			for (int i = 7, j = sec; i >= 6; i--, j /= 10) {
				this.digits[i].step(j % 10);
			}
			for (int i = 4, j = min; i >= 3; i--, j /= 10) {
				this.digits[i].step(j % 10);
			}
			for (int i = 1, j = hrs; i >= 0; i--, j /= 10) {
				this.digits[i].step(j % 10);
			}
		}
	}

	public void update(int reps) {
		for (int i = 0; i < reps; i++)
			update();
	}

	private static final int SPACING = 6;
}
