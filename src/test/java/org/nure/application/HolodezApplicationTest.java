package org.nure.application;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nure.application.HolodezApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(HolodezApplication.class)
public class HolodezApplicationTest {

	@Test
	public void contextTest() {
		assertTrue(true);
	}

}
