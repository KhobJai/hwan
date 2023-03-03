package uk.intenso.hwan.testutils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

class TestDataClass {
    private String name;
    private int age;
    private float weight;

    private SubClass subClass;

    static
    class SubClass {
        private List<String> things;


        public SubClass(List<String> things) {
            this.things = things;
        }


        public List<String> getThings() {
            return things;
        }

        public void setThings(List<String> things) {
            this.things = things;
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public SubClass getSubClass() {
        return subClass;
    }

    public void setSubClass(SubClass subClass) {
        this.subClass = subClass;
    }
}