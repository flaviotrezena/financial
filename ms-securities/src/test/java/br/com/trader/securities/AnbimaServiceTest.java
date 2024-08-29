package br.com.trader.securities;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.trader.securities.config.AnbimaClient;
import br.com.trader.securities.config.AnbimaService;

class AnbimaServiceTest {

    @Mock
    private AnbimaClient anbimaClient;

    @InjectMocks
    private AnbimaService anbimaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetData() {
        String param = "testParam";
        String expectedResponse = "testResponse";

        when(anbimaClient.getData(param)).thenReturn(expectedResponse);

        String actualResponse = anbimaService.getData(param);

        assertEquals(expectedResponse, actualResponse);
    }
}