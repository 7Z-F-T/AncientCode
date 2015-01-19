package org.thu.core.enity.test;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.thu.core.enity.Memory;

public class MemoryTest extends TestCase{
	Memory mem;
	
	@Before
	public void setUp(){
		mem = new Memory(1024);
	}

	@Test
	public void testReadWriteByte() {
		assertEquals(true, mem.writeByte(0, (byte)3));
		assertEquals(true, mem.writeByte(1023, (byte)(-1)));
		
		assertEquals(false, mem.writeByte(-1, (byte)3));
		assertEquals(false, mem.writeByte(1024, (byte)3));
		
		assertEquals(3, (byte)mem.readByte(0));
		assertEquals(-1, (byte)mem.readByte(1023));
	}

	@Test
	public void testWriteWord() {
		assertEquals(true, mem.writeWord(0, 3));
		assertEquals(true, mem.writeWord(1020, (-1)));
		
		assertEquals(false, mem.writeWord(-1, 3));
		assertEquals(false, mem.writeWord(1021, 3));
		
		assertEquals(0, (int)mem.readByte(0));
		assertEquals(3,(int) mem.readByte(3));
		assertEquals(-1,(int) mem.readByte(1020));
		assertEquals(-1,(int) mem.readByte(1023));
		

		assertEquals(true, mem.writeWord(1020, 0x01020304));
		assertEquals(1,(int) mem.readByte(1020));
		assertEquals(2,(int) mem.readByte(1021));
		assertEquals(3,(int) mem.readByte(1022));
		assertEquals(4,(int) mem.readByte(1023));
		

		assertEquals(true, mem.writeWord(0, 0x01020304));
		assertEquals(0x01020304, (int)mem.readWord(0));
		
		assertEquals(true, mem.writeWord(12, 0x01020304));
		assertEquals(0x01020304,(int) mem.readWord(12));
		
		assertEquals(true, mem.writeWord(16, 0x01020304));
		assertEquals(0x01020304,(int) mem.readWord(16));
		
		assertEquals(true, mem.writeWord(16, 0x24eeddcc));
		assertEquals(0x24eeddcc,(int) mem.readWord(16));
		assertEquals(true, mem.writeWord(16, new byte[]{(byte) 0xcc,(byte) 0xdd,(byte) 0xee,0x24}));
		assertEquals(0x24eeddcc,(int) mem.readWord(16));
	}

	@Test
	public void testInteger(){
		assertEquals(16, (int)Integer.decode("0x10"));
		assertEquals(0xFFFFFE80, 0xd0000000 >> 0x15);
	}
}
