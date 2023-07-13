package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> elements1 = new ArrayList<>(
                List.of(1,2,3,4,5,6,7,8,9));
        int count1 = 0;
        List<Integer> result1 = new ArrayList<>();
        List<Integer> actual1 = App.take(elements1, count1);
        assertThat(actual1).isEqualTo(result1);

        List<Integer> elements2 = new ArrayList<>(
                List.of(1,2,3,4,5,6,7,8,9));
        int count2 = 20;
        List<Integer> actual2 = App.take(elements2, count2);
        assertThat(actual2).isEqualTo(elements2);

        List<Integer> elements3 = new ArrayList<>(
                List.of(1,2,3,4,5,6,7,8,9));
        int count3 = 4;
        List<Integer> result3 = new ArrayList<>(
                List.of(1,2,3,4));
        List<Integer> actual3 = App.take(elements3, count3);
        assertThat(actual3).isEqualTo(result3);

        List<Integer> elements4 = new ArrayList<>();
        int count4 = 3;
        List<Integer> actual4 = App.take(elements4, count4);
        assertThat(actual4).isEqualTo(elements4);

        List<Integer> elements5 = new ArrayList<>();
        int count5 = 0;
        List<Integer> actual5 = App.take(elements5, count5);
        assertThat(actual5).isEqualTo(elements5);
        // END
    }
}
