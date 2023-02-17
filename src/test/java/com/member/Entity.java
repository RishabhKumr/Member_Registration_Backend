package com.member;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.member.payload.MessageResponse;
@SpringBootTest(classes = { Entity.class })
public class Entity {
	
	@InjectMocks
	MessageResponse messageResponse;
	@Test
	public void test_setMessage() {
		messageResponse.setMessage("test");
		assertTrue(messageResponse.getMessage()=="test");
	}
}
