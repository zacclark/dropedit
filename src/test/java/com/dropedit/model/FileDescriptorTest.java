package com.dropedit.model;

import static org.mockito.Mockito.*;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

@Test
public class FileDescriptorTest {
	
	public void canSetAndRetreiveProperValues() throws Exception {
		FileDescriptor fileDescriptor = new FileDescriptor();
    fileDescriptor.setName("/testing");
    fileDescriptor.setPath("/testing");
    fileDescriptor.setModifiedDate("2010-02-23");

		assertEquals("testing", fileDescriptor.getName());
		assertEquals("/testing", fileDescriptor.getPath());
		assertEquals("2010-02-23", fileDescriptor.getModifiedDate());
	}
	
	public void properlyFindDirectory() throws Exception {
		FileDescriptor fileDescriptor = new FileDescriptor();
    fileDescriptor.setName("/testing");

		assertTrue(fileDescriptor.getIsDirectory());
	}
	
	public void properlyFindNotDirectory() throws Exception {
		FileDescriptor fileDescriptor = new FileDescriptor();
    fileDescriptor.setName("/testing.txt");

		assertFalse(fileDescriptor.getIsDirectory());
	}
	
}