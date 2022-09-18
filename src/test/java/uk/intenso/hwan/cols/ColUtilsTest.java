package uk.intenso.hwan.cols;

import org.assertj.core.util.Arrays;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ColUtilsTest {

    private List<Integer> intList;

    private String[] stringArray;


    private List<Character> bigList;

    private char[] bigArray;


    @BeforeEach
    void setUp() {

        intList = Lists.list(2, 54, 6, 7, 9, 1, 4);
        stringArray = Arrays.array("World", "Hello", "Cheese", "Tomato");
    }

    @Test
    void shouldConvertArrayToList() {

        var result = ColUtils.toList(stringArray);
        assertThat(result).hasSize(stringArray.length);
        assertThat(result.get(2)).isEqualTo(stringArray[2]);
    }

    @Test
    void setToList() {

        var set = Sets.set("X", "Y", "T", "L");
        var result = ColUtils.toList(set);
        assertThat(result).hasSize(set.size());
        assertThat(result.containsAll(set));
    }

    @Test
    void shouldConvertToIntArray() {

        Integer[] result = ColUtils.toIntArray(intList);
        assertThat(result.getClass().isArray()).isTrue();
        assertThat(result).hasSize(intList.size());
    }

    @Test
    void shouldConvertToStringArray() {

        String[] result = ColUtils.toStringArray(Lists.list("A", "B"));
        assertThat(result.getClass().isArray()).isTrue();
        assertThat(result).hasSize(2);
    }

    @Test
    void shouldConvertToArray() {

        String[] result = ColUtils.toArray(Lists.list("A", "B"));
        assertThat(result.getClass().isArray()).isTrue();
        assertThat(result).hasSize(2);
        Integer[] result2 = ColUtils.toArray(Lists.list(1, 2));
        assertThat(result2.getClass().isArray()).isTrue();
        assertThat(result2).hasSize(2);
    }

    @Test
    void shouldRemoveLastFromList() {

        var result = ColUtils.removeLast(intList);
        assertThat(result).hasSize(intList.size() - 1);
        assertThat(result).doesNotContain(ColUtils.lastValue(intList));
    }

    @Test
    void removeFirstFromList() {
        var result = ColUtils.removeFirst(intList);
        assertThat(result).hasSize(intList.size() - 1);
        assertThat(result).doesNotContain(2);
    }

    @Test
    void testRemoveLastFromList() {
        var result = ColUtils.removeLast(intList);
        assertThat(result).hasSize(intList.size() - 1);
        assertThat(result).doesNotContain(4);
    }

    @Test
    void removeFirstFromArray() {
        var result = ColUtils.removeFirst(stringArray);
        assertThat(result).hasSize(stringArray.length - 1);
        assertThat(result).doesNotContain("World");
    }

    @Test
    void testRemoveLastFromArray() {
        var result = ColUtils.removeLast(stringArray);
        assertThat(result).hasSize(stringArray.length - 1);
        assertThat(result).contains("World");
        assertThat(result).doesNotContain("Tomato");
    }


    @Test
    void testRemove() {
        assertThat(ColUtils.firstValue(stringArray)).isEqualTo("World");
        assertThat(ColUtils.lastValue(stringArray)).isEqualTo("Tomato");
    }
}