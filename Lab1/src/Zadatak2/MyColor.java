package Zadatak2;

public class MyColor {
	private int red;
	private int green;
	private int blue;
	
	public MyColor(int r, int g, int b) {
		red=r;
		green=g;
		blue=b;
	}
	public int getRed() {
		return red;
	}
	public int getGreen() {
		return green;
	}
	public int getBlue() {
		return blue;
	}
	
	public static MyColor decode(String hex)
	{
		int red = Integer.valueOf(hex.substring(2, 4), 16);
		int green = Integer.valueOf(hex.substring(4, 6), 16);
		int blue = Integer.valueOf(hex.substring(6, 8), 16);

		MyColor col = new MyColor(red, green, blue);
		return col;
		
		
	}
	public int getRGB() {
		return (256 * 256 * red + 256 * green + blue);
	}
	
	 public static float[] RGBtoHSB(int r, int g, int b, float[] hsbvals) {
	    

			float rgbmax = Math.max(r, Math.max(g, b));
			float rgbmin = Math.min(r, Math.min(g, b));
			float rgbdiff = (rgbmax - rgbmin);

			float h = -1, s = -1;
			if (rgbmax == rgbmin) {
				h = 0f;
			} else if (rgbmax == r) {
				h = (60 * ((g - b) / rgbdiff) + 360) / 360f;
			} else if (rgbmax == g) {
				h = (60 * ((b - r) / rgbdiff) + 120) / 360f;
			} else if (rgbmax == b) {
				h = (60 * ((r - g) / rgbdiff) + 240) / 360f;
			}

			if (rgbmax == 0)
				s = 0f;
			else
				s = rgbdiff / rgbmax;

			float b2 = rgbmax;

			hsbvals[0] = h;
			hsbvals[1] = s;
			hsbvals[2] = b2;

			return hsbvals;
	    }
	 
	 public static float[] RGBtoHSL(int r, int g, int b, float[] hslCode) {

			float red = r / 255f; 
			float green = g / 255f;
			float blue = b / 255f;

			float rgbmax = Math.max(red, Math.max(green, blue));
			float rgbmin = Math.min(red, Math.min(green, blue));
			float rgbdiff = (rgbmax - rgbmin);

			float h = -1, s = -1, l = -1;
			l = (rgbmax + rgbmin) / 2f;
			if (rgbmax == rgbmin) {
				h = 0f;
			} else if (rgbmax == red) {
				h = (60 * ((green - blue) / rgbdiff) + 360) / 360;
			} else if (rgbmax == green) {
				h = (60 * (2 + (blue - red) / rgbdiff)) / 360;
			} else if (rgbmax == blue) {
				h = (60 * (4 + (red - green) / rgbdiff)) / 360;
			}

			if (l <= 0.5)
				s = (rgbmax - rgbmin) / (rgbmax + rgbmin);
			else
				s = (rgbmax - rgbmin) / (2 - rgbmax - rgbmin);

			hslCode[0] = h;
			hslCode[1] = s;
			hslCode[2] = l;

			return hslCode;

		}
	 public static float[] RGBtoCMYK(int r, int g, int b, float[] cmykCode) {

			float red = r / 255f;
			float green = g / 255f;
			float blue = b / 255f;
			float Black = 1.0f - Math.max(red, Math.max(green, blue));

			if (red == 0 && green == 0 && blue == 0) {
				Black = 1;
			}
			float[] cmykC = { 0, 0, 0, 1 };
			if (Black == 1) {
				cmykCode = cmykC;
				return cmykCode;
			}

			else {
				float Cyan = (1f - red - Black) / (1f - Black);
				float Magenta = (1f - green - Black) / (1f - Black);
				float Yellow = (1f - blue - Black) / (1f - Black);

				cmykCode[0] = Cyan;
				cmykCode[1] = Magenta;
				cmykCode[2] = Yellow;
				cmykCode[3] = Black;

				return cmykCode;
			}

		}
	 
	
	
	
}
