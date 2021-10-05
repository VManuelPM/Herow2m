package com.w2m.dto;

import com.w2m.utils.TestConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageDtoTest {

    @Test
    void getMensaje() {
        MessageDto messageDto = new MessageDto(TestConstants.HEROE_NAME);
        assertEquals(TestConstants.HEROE_NAME, messageDto.getMensaje());
    }
}