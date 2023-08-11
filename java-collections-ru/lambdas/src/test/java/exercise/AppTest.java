package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


// BEGIN
public class AppTest {
    @Test
    void testAppTiny() {
        String[][] image = {
                {"*"},
        };
        String[][] actual = App.enlargeArrayImage(image);
        String[][] expected = {
                {"*", "*"},
                {"*", "*"},
        };
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    void testAppSmall() {
        String[][] image = {
                {"*", "*"},
                {"*", "*"},
        };
        String[][] actual = App.enlargeArrayImage(image);
        String[][] expected = {
                {"*", "*", "*", "*"},
                {"*", "*", "*", "*"},
                {"*", "*", "*", "*"},
                {"*", "*", "*", "*"},
        };
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testAppStandard() {
        String[][] image = {
                {"*", "*", "*", "*"},
                {"*", " ", " ", "*"},
                {"*", " ", " ", "*"},
                {"*", "*", "*", "*"},
        };
        String[][] actual = App.enlargeArrayImage(image);
        String[][] expected = {
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        };
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testAppEmpty() {
        String[][] image = {
                {},
        };
        String[][] actual = App.enlargeArrayImage(image);
        String[][] expected = {
                {},
        };
        assertThat(actual).isEqualTo(expected);
    }

}
// END
