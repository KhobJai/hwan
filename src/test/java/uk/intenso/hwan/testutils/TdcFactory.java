package uk.intenso.hwan.testutils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TdcFactory {

    private TdcFactory() {
    }

    public static TestDataClass testDataClass() {
        var testObject = new TestDataClass();
        testObject.setName("John");
        testObject.setAge(35);
        testObject.setWeight(65.5f);
        testObject.setSubClass(new TestDataClass.SubClass(new ArrayList<>()));
        testObject.getSubClass().getThings().addAll(List.of("Complete Hwan Project", "Answer People on Linkedin"));
        return testObject;
    }

    public static TestPerson person(String name, int age) {
        return new TestPerson(name, age);
    }

    public static TestPerson person() {
        return new TestPerson("Jane", 47);
    }
}
