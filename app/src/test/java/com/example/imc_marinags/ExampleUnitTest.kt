package com.example.imc_marinags

import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun testCalculateIMC() {
        //casos normales
        assertEquals(24.69, calculateIMC(70.0, 1.68), 0.01)
        assertEquals(18.52, calculateIMC(60.0, 1.80), 0.01)

        //peso o altura en cero
        assertEquals(0.0, calculateIMC(0.0, 1.75), 0.01)
        assertEquals(0.0, calculateIMC(70.0, 0.0), 0.01)

        //valores negativos
        assertEquals(0.0, calculateIMC(-70.0, 1.75), 0.01)
        assertEquals(0.0, calculateIMC(70.0, -1.75), 0.01)
    }

    @Test
    fun testCategorizeIMC() {
        assertEquals("Bajo peso", categorizeIMC(17.0))
        assertEquals("Normal", categorizeIMC(22.0))
        assertEquals("Sobrepeso", categorizeIMC(26.0))
    }
}