package Zadatak2;

public class ColorConverter {
 public static void main(String[] args) {

 String hexColor = "0x1FF0FF";

 MyColor c = MyColor.decode(hexColor);

 float[] hsbCode = new float[3];
 float[] hslCode = new float[3];
 float[] cmykCode = new float[4];

 MyColor.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsbCode);
 System.out.println("Boja u HEX formatu: 0x" +
Integer.toHexString(c.getRGB() & 0x00FFFFFF));
 System.out.println("Boja u RGB formatu: " + c.getRed() + ", " +
c.getGreen() + ", " + c.getBlue());
 System.out.println("Boja u HSB formatu: " + hsbCode[0] * 360 + "°, " +
hsbCode[1] * 100 + "%, " + hsbCode[2] * 100 + "%");
 System.out.println(
			"Boja HSL formatu: " + hslCode[0] * 360 + "°, " + hslCode[1] * 100 + "%, " + hslCode[2] * 100 + "%");
	MyColor.RGBtoCMYK(c.getRed(), c.getGreen(), c.getBlue(), cmykCode);
	System.out.println(
			"Boja u CMYK formatu: " + cmykCode[0] + ", " + cmykCode[1] + ", " + cmykCode[2] + ", " + cmykCode[3]);
 }
}

