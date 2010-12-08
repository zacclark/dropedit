package com.dropedit.model;

import static org.mockito.Mockito.*;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

@Test
public class RootPathTest {
	
	public void emptyRootPathShouldReturnString() throws Exception {
		assertEquals("", RootPath.popRootPath());
	}
	
	public void addingToRootPathShouldWork() throws Exception {
		RootPath.addRootPath("testpath");
		assertEquals("testpath", RootPath.popRootPath());
		assertEquals("", RootPath.popRootPath());
	}
	
	public void shouldBePoppingInReverseFIFO() throws Exception {
		RootPath.addRootPath("testpath");
		RootPath.addRootPath("testpath_again");
		RootPath.addRootPath("testpath_the_third");
		assertEquals("testpath_the_third", RootPath.popRootPath());
		assertEquals("testpath_again", RootPath.popRootPath());
		assertEquals("testpath", RootPath.popRootPath());
		assertEquals("", RootPath.popRootPath());
	}
	
}
