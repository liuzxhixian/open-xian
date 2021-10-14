package com.xian.open.leetcode.simple;

import java.util.HashSet;
import java.util.Objects;

/**
 * @author xian
 * @description
 * @createTime 2021/10/12 14:03
 */
public class HashSetTest {

    public static void main(String[] args) {
        HashSet<Student> students = new HashSet<>();
        Student student = new Student();
        student.setId(1);
        students.add(student);
        student.setSex("nv");
        students.add(student);
        students.forEach(System.out::println);
    }

    static class Student {
        int id;
        String userName;
        String password;
        String sex;
        String age;
        String desc;
        String image;

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", userName='" + userName + '\'' +
                    ", password='" + password + '\'' +
                    ", sex='" + sex + '\'' +
                    ", age='" + age + '\'' +
                    ", desc='" + desc + '\'' +
                    ", image='" + image + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return id == student.id &&
                    Objects.equals(userName, student.userName) &&
                    Objects.equals(password, student.password) &&
                    Objects.equals(sex, student.sex) &&
                    Objects.equals(age, student.age) &&
                    Objects.equals(desc, student.desc) &&
                    Objects.equals(image, student.image);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, userName, password, sex, age, desc, image);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

}
