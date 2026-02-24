package eu.tvato.lempie

import eu.tvato.lempie.utils.parseIsoDate
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class UtilsTests {
    @Test fun isoDateParse_CorrectType_1_ReturnsTrue(){
        val result = parseIsoDate("2026-01-27T20:25:08.769277Z")
        assertTrue(result == "Jan 27, 2026, 22:25")
    }

    @Test fun isoDateParse_CorrectType_2_ReturnsTrue(){
        val result = parseIsoDate("2026-01-27T20:25:08.769Z")
        assertTrue(result == "Jan 27, 2026, 22:25")
    }

    @Test fun isoDateParse_CorrectType_3_ReturnsTrue(){
        val result = parseIsoDate("2026-01-27T20:25:08Z")
        assertTrue(result == "Jan 27, 2026, 22:25")
    }

    @Test fun isoDateParse_CorrectType_4_ReturnsTrue(){
        val result = parseIsoDate("2026-01-27T20:25:08.769456457Z")
        assertTrue(result == "Jan 27, 2026, 22:25")
    }
}