package org.nure.main;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HolodezApplicationTest {

	@Autowired
	public KieContainer container;
	
	@Test
	public void contextLoads() {
		System.out.println(container.getClass().toString());
		assertTrue(true);
	}

}
