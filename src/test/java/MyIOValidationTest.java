import org.junit.jupiter.api.Test;
import ru.astondevs.week3.MyIOValidation;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyIOValidationTest {
    private Method getPrivateMethod(String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
        Method method = MyIOValidation.class.getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method;
    }

    @Test
    void testFormatEndOfInputRightFormat_EndsWithDot() throws Exception {
        String input = "Week3 task1.";
        Method method = getPrivateMethod("formatEndOfInputRightFormat", String.class);
        String result = (String) method.invoke(new MyIOValidation(), input);
        assertEquals("Week3 task1.", result);
    }

    @Test
    void testFormatEndOfInputRightFormat_DoesNotEndWithDot() throws Exception {
        String input = "Week3 task1";
        Method method = getPrivateMethod("formatEndOfInputRightFormat", String.class);
        String result = (String) method.invoke(new MyIOValidation(), input);
        assertEquals("Week3 task1.", result);
    }

    @Test
    void testFormatEndOfInputRightFormat_EmptyString() throws Exception {
        String input = "";
        Method method = getPrivateMethod("formatEndOfInputRightFormat", String.class);
        String result = (String) method.invoke(new MyIOValidation(), input);
        assertEquals(".", result);
    }

    @Test
    void testFormatEndOfInputRightFormat_StringWithSpaces() throws Exception {
        String input = "   ";
        Method method = getPrivateMethod("formatEndOfInputRightFormat", String.class);
        String result = (String) method.invoke(new MyIOValidation(), input);
        assertEquals("   .", result);
    }

    @Test
    void testFormatEndOfInputRightFormat_StringWithWhitespaceAndDot() throws Exception {
        String input = "Week3 task1. ";
        Method method = getPrivateMethod("formatEndOfInputRightFormat", String.class);
        String result = (String) method.invoke(new MyIOValidation(), input);
        assertEquals("Week3 task1. ", result);
    }

    @Test
    void testFormatUpperLowerCaseInputRightFormat_NormalCase() throws Exception {
        String input = "week3 task1.";
        String expected = "Week3 task1.";
        Method method = getPrivateMethod("formatUpperLowerCaseInputRightFormat", String.class);
        String result = (String) method.invoke(new MyIOValidation(), input);
        assertEquals(expected, result);
    }

    @Test
    void testFormatUpperLowerCaseInputRightFormat_MixedCaseAndSpaces() throws Exception {
        String input = "  wEEK3 \n  taSk1.";
        String expected = "Week3 task1.";
        Method method = getPrivateMethod("formatUpperLowerCaseInputRightFormat", String.class);
        String result = (String) method.invoke(new MyIOValidation(), input);
        assertEquals(expected, result);
    }

    @Test
    void testFormatUpperLowerCaseInputRightFormat_EmptyLines() throws Exception {
        String input = "\n week3 task1. ";
        String expected = "Week3 task1.";
        Method method = getPrivateMethod("formatUpperLowerCaseInputRightFormat", String.class);
        String result = (String) method.invoke(new MyIOValidation(), input);
        assertEquals(expected, result);
    }

    @Test
    void testFormatUpperLowerCaseInputRightFormat_SingleLine() throws Exception {
        String input = "week3 task1";
        String expected = "Week3 task1";
        Method method = getPrivateMethod("formatUpperLowerCaseInputRightFormat", String.class);
        String result = (String) method.invoke(new MyIOValidation(), input);
        assertEquals(expected, result);
    }

    @Test
    void testFormatNullOrDoesntExist_NormalLines() throws Exception {
        String input = "Week 3 Task 1";
        String expected = "Week 3 Task 1";
        Method method = getPrivateMethod("formatNullOrDoesntExist", String.class);
        String result = (String) method.invoke(new MyIOValidation(), input);
        assertEquals(expected, result);
    }

    @Test
    void testFormatNullOrDoesntExist_EmptyLines() throws Exception {
        String input = "\n ";
        String expected = "There's no data.\nThere's no data.";
        Method method = getPrivateMethod("formatNullOrDoesntExist", String.class);
        String result = (String) method.invoke(new MyIOValidation(), input);
        assertEquals(expected, result);
    }

    @Test
    void testFormatNullOrDoesntExist_SpacesOnlyLines() throws Exception {
        String input = "   \n   ";
        String expected = "There's no data.\nThere's no data.";
        Method method = getPrivateMethod("formatNullOrDoesntExist", String.class);
        String result = (String) method.invoke(new MyIOValidation(), input);
        assertEquals(expected, result);
    }

    @Test
    void testFormatNullOrDoesntExist_MixedLines() throws Exception {
        String input = "Data\nMore data";
        String expected = "Data There's no data.\nThere's no data.\nMore data";
        Method method = getPrivateMethod("formatNullOrDoesntExist", String.class);
        String result = (String) method.invoke(new MyIOValidation(), input);
        assertEquals(expected, result);
    }

    @Test
    void testFormatNullOrDoesntExist_NullInput() {
        assertThrows(NullPointerException.class, () -> {
            Method method = getPrivateMethod("formatNullOrDoesntExist", String.class);
            method.invoke(new MyIOValidation(), (Object) null);
        });
    }
}
