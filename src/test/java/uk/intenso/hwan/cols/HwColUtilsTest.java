package uk.intenso.hwan.cols;

import org.assertj.core.util.Arrays;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.*;

class HwColUtilsTest {

    private List<Integer> intList;

    private String[] stringArray;


    private List<Character> bigList;

    private char[] bigArray;


    @BeforeEach
    void setUp() {

        Function<Integer,String> function = (i) -> "dsfasdf";

        intList = Lists.list(2, 54, 6, 7, 9, 1, 4);
        stringArray = Arrays.array("World", "Hello", "Cheese", "Tomato");
    }

    @Test
    void shouldConvertArrayToList() {

        var result = HwColUtils.toList(stringArray);
        assertThat(result).hasSize(stringArray.length);
        assertThat(result.get(2)).isEqualTo(stringArray[2]);
    }

    @Test
    void setToList() {

        var set = Sets.set("X", "Y", "T", "L");
        var result = HwColUtils.toList(set);
        assertThat(result).hasSize(set.size());
        assertThat(result.containsAll(set));
    }

    @Test
    void shouldConvertToIntArray() {

        Integer[] result = HwColUtils.toIntArray(intList);
        assertThat(result.getClass().isArray()).isTrue();
        assertThat(result).hasSize(intList.size());
    }

    @Test
    void shouldConvertToStringArray() {

        String[] result = HwColUtils.toStringArray(Lists.list("A", "B"));
        assertThat(result.getClass().isArray()).isTrue();
        assertThat(result).hasSize(2);
    }

    @Test
    void shouldConvertToArray() {

        String[] result = HwColUtils.toArray(Lists.list("A", "B"));
        assertThat(result.getClass().isArray()).isTrue();
        assertThat(result).hasSize(2);
        Integer[] result2 = HwColUtils.toArray(Lists.list(1, 2));
        assertThat(result2.getClass().isArray()).isTrue();
        assertThat(result2).hasSize(2);
    }

    @Test
    void shouldRemoveLastFromList() {

        var result = HwColUtils.removeLast(intList);
        assertThat(result).hasSize(intList.size() - 1);
        assertThat(result).doesNotContain(HwColUtils.lastValue(intList));
    }

    @Test
    void removeFirstFromList() {
        var result = HwColUtils.removeFirst(intList);
        assertThat(result).hasSize(intList.size() - 1);
        assertThat(result).doesNotContain(2);
    }

    @Test
    void testRemoveLastFromList() {
        var result = HwColUtils.removeLast(intList);
        assertThat(result).hasSize(intList.size() - 1);
        assertThat(result).doesNotContain(4);
    }

    @Test
    void removeFirstFromArray() {
        var result = HwColUtils.removeFirst(stringArray);
        assertThat(result).hasSize(stringArray.length - 1);
        assertThat(result).doesNotContain("World");
    }

    @Test
    void testRemoveLastFromArray() {
        var result = HwColUtils.removeLast(stringArray);
        assertThat(result).hasSize(stringArray.length - 1);
        assertThat(result).contains("World");
        assertThat(result).doesNotContain("Tomato");
    }


    @Test
    void testRemove() {
        assertThat(HwColUtils.firstValue(stringArray)).isEqualTo("World");
        assertThat(HwColUtils.lastValue(stringArray)).isEqualTo("Tomato");
    }
}