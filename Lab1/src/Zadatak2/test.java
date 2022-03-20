package Zadatak2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class test {

	@Test
	void Test() {
		float[] HSBtest = new float[3];
		HSBtest = MyColor.RGBtoHSB(31, 240, 255, HSBtest);
		assertEquals(0.5111607313156128, HSBtest[0]);
		assertEquals(0.8784313797950745, HSBtest[1]);
		assertEquals(255, HSBtest[2]);
		
		float[] HSLtest = new float[3];
		HSLtest = MyColor.RGBtoHSL(31, 240, 255, HSLtest);
		assertEquals(0.5111607313156128, HSLtest[0]);
		assertEquals(1.0, HSLtest[1]);
		assertEquals(0.5607843399047852, HSLtest[2]);
		
		float[] CMYKtest = new float[4];
		CMYKtest = MyColor.RGBtoCMYK(31, 240, 255, CMYKtest);
		assertEquals(0.8784313797950745, CMYKtest[0]);
		assertEquals(0.05882352590560913, CMYKtest[1]);
		assertEquals(0.0, CMYKtest[2]);
		assertEquals(0.0, CMYKtest[3]);
	}

}
