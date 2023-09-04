package uk.intenso.hwan.str;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InterpolatableTest {


    class InterImpl implements Interpolatable{
        private String name;
        private int age;

        private float weightInKgs;

        private String other =  "Other";

        public InterImpl(String name, int age, float weightInKgs) {
            this.name = name;
            this.age = age;
            this.weightInKgs = weightInKgs;
        }

    }

    @Test
    void interpolatableTest() {
        var preInterpolated = "My name is {name}, I am {age} " +
                "years old and weigh {weightInKgs}kgs";

        var impleOb = new InterImpl("John",35,76.5f);

        var formatted =impleOb.format(preInterpolated);
        assertThat(formatted).isEqualTo("My name is John, I am 35 years old and weigh 76.5kgs");
    }

}