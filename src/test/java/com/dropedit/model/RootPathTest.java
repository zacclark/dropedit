package com.dropedit.model;

import static org.mockito.Mockito.*;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

@Test
public class RootPathTest {
	
	public void emptyRootPathShouldReturnString() throws Exception {
		assertEquals("", RootPath.getRootPath());
	}
	
	public void addingToRootPathShouldWork() throws Exception {
		RootPath.addRootPath("testpath");
		assertEquals("testpath", RootPath.getRootPath());
		assertEquals("", RootPath.getRootPath());
	}
	
	public void shouldBePoppingInReverseFIFO() throws Exception {
		RootPath.addRootPath("testpath");
		RootPath.addRootPath("testpath_again");
		RootPath.addRootPath("testpath_the_third");
		assertEquals("testpath_the_third", RootPath.getRootPath());
		assertEquals("testpath_again", RootPath.getRootPath());
		assertEquals("testpath", RootPath.getRootPath());
		assertEquals("", RootPath.getRootPath());
	}
	
}