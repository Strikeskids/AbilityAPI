package sk.general;

import java.awt.image.BufferedImage;

public enum ImageCodes {
	LOG("iVBORw0KGgoAAAANSUhEUgAAABYAAAAWCAYAAADEtGw7AAABTElEQVR42mNgoA9g/A/B5Mrj0HTz"
			+ "6OL/11ew49AMkZ/ekvKfRAsY/4dbKfwvCdXBYTjE4MNr28CYRAsghk8rskAzHNVQZIxqAV5LMA2H"
			+ "GZqV4P2/qyYVTGOzgAhfYBqObgiIBlmCzReqiuKkuRxmIAzX5IdgiIHUWZlp/cebQpANR3Y1umGw"
			+ "IHK2NfxflOmDKygQYQpSjMtwkGHIFoD4IINhehjwJSlYGILEQckQ2WCYHMjwqEBbsPdhhiIFBeN/"
			+ "QQEBrBGBHHYgjJ4iQHwdLY3/83uzUQ0VE+bGCDP0FADSDMPIchvnVf63tdLFNBRmMMgryIbD0iQy"
			+ "xmYpTkNBACRgoSwOxqEexngNItpQmItBEiBvwiwgZDhIPSyX4UmziOIQFCQgV+CzgARDEUFCyAKS"
			+ "DUU2HJcF5vqK5BlKjAUUGYrPAqoYit0C4gEAXYxtzHAylDAAAAAASUVORK5CYII="),

	SETTINGS("iVBORw0KGgoAAAANSUhEUgAAABYAAAAWCAYAAADEtGw7AAABTElEQVR42mNgoA9g/A/B5Mrj0HTz"
			+ "6OL/11ew49AMkZ/ekvKfRAsY/4dbKfwvCdXBYTjE4MNr28CYRAsghk8rskAzHNVQZIxqAV5LMA2H"
			+ "GZqV4P2/qyYVTGOzgAhfYBqObgiIBlmCzReqiuKkuRxmIAzX5IdgiIHUWZlp/cebQpANR3Y1umGw"
			+ "IHK2NfxflOmDKygQYQpSjMtwkGHIFoD4IINhehjwJSlYGILEQckQ2WCYHMjwqEBbsPdhhiIFBeN/"
			+ "QQEBrBGBHHYgjJ4iQHwdLY3/83uzUQ0VE+bGCDP0FADSDMPIchvnVf63tdLFNBRmMMgryIbD0iQy"
			+ "xmYpTkNBACRgoSwOxqEexngNItpQmItBEiBvwiwgZDhIPSyX4UmziOIQFCQgV+CzgARDEUFCyAKS"
			+ "DUU2HJcF5vqK5BlKjAUUGYrPAqoYit0C4gEAXYxtzHAylDAAAAAASUVORK5CYII="),

	SKILL("iVBORw0KGgoAAAANSUhEUgAAABYAAAAWCAYAAADEtGw7AAAA5klEQVR42mNgoBWQZ5H/T4w6Li7O"
			+ "/+iYYoNBhsy60A/Haw5t+B80KQ6/4SCDpaWlwZgYQ0EYZKhMlAB+g3kYeP9bmWmBMS6DkzcUomCQ"
			+ "i4kymFAwgFyIjIkymIGBkT4GY4t9ig0GKbwxJQMFU2wwNkOP5ARTx2CQQcj4z46d1DF4p5MTCqaC"
			+ "wYz/yTUYT1ZnBGcOcg02WvQYBZv6L4UZTl5QYDMUhFW1vQgb/HbyVKwGT5oDEQe5DhlvPXxh1GAI"
			+ "7l59GNVgWDJBN3jb3EysBhd0lmE1uKqjB93FtMBAACvoqYVBZgIAFKaAiz8WhQwAAAAASUVORK5CYII="),

	;

	private final String code;
	private BufferedImage img;

	private ImageCodes(String s) {
		this.code = s;
	}

	public BufferedImage get() {
		return img = (img == null ? IOUtil.getImage(getCode()) : img);
	}

	public String getCode() {
		return code;
	}
}
