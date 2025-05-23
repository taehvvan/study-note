public class Student {
    private String name;
    private int korean;
    private int english;
    private int math;

    public Student(String name, int korean, int english, int math) {
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
    }

    public String getName() {
        return name;
    }

    public int getKorean() {
        return korean;
    }

    public int getEnglish() {
        return english;
    }

    public int getMath() {
        return math;
    }

    public int getTotal() {
        return korean + english + math;
    }

    public double getAverage() {
        return getTotal() / 3.0;
    }

    @Override
    public String toString() {
        return String.format("%s\t국어:%d\t영어:%d\t수학:%d\t총점:%d\t평균:%.2f",
                name, korean, english, math, getTotal(), getAverage());
    }
}
