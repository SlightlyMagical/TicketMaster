import bll.util.InputCheck;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class InputCheckTest {

    @Test
    @DisplayName("Valid time input")
    void timeCheckValid() {

        LocalTime actualValue = InputCheck.timeCheck("14:45");

        LocalTime expectedValue = LocalTime.of(14, 45,0);

        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    @DisplayName("Invalid time input - Wrong separator")
    void timeCheckInvalid1() {

        LocalTime actualValue = InputCheck.timeCheck("14.45");

        LocalTime expectedValue = null;

        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    @DisplayName("Invalid time input - Single number input")
    void timeCheckInvalid2() {

        LocalTime actualValue = InputCheck.timeCheck("14");

        LocalTime expectedValue = null;

        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    @DisplayName("Invalid time input - Number outside accepted range")
    void timeCheckInvalid3() {

        LocalTime actualValue = InputCheck.timeCheck("30:05");

        LocalTime expectedValue = null;

        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    @DisplayName("Invalid time input - Text input")
    void timeCheckInvalid4() {

        LocalTime actualValue = InputCheck.timeCheck("Two:thirty");

        LocalTime expectedValue = null;

        Assertions.assertEquals(expectedValue, actualValue);
    }

}