package org.thu.core.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.thu.core.SimUtils;

public class SimUtilsTest {

	@Test
	public void testMux() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSignedExtended() {
		assertEquals(-1,SimUtils.signedExtended((short)(-1)));
		assertEquals(0,SimUtils.signedExtended((short)(0)));
		assertEquals(0xffffaffe,SimUtils.signedExtended((short)(0xaffe)));
		assertEquals(0xffff8123,SimUtils.signedExtended((short)(0x8123)));
		assertEquals(0x7123,SimUtils.signedExtended((short)(0x7123)));
		assertEquals(0x123,SimUtils.signedExtended((short)(0x123)));
		assertEquals(-3,SimUtils.signedExtended((short)(-3)));
	}

	public void testGetBits(){
		assertEquals(0x1234,SimUtils.getBits(0x1234, 31, 0));
		assertEquals(-1,SimUtils.getBits(0x1234, 32, 0));
		assertEquals(-1,SimUtils.getBits(0x1234, 31, -1));
		
		assertEquals(0x7234,SimUtils.getBits(0xf235, 30, 1));
		assertEquals(0x3fff,SimUtils.getBits(0xffff, 30, 2));
		assertEquals(0x3fff,SimUtils.getBits(0xffff, 2, 30));
		assertEquals(1,SimUtils.getBits(0xffff, 2, 2));
	}
}
