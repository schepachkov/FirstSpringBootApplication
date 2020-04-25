package app.entity;

public class User {
    private String firstName;
    private String lastName;
    private String middleName;
    private int age;
    private double salary;
    private String workName;
    private String mail;

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", workName='" + workName + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String getWorkName() {
        return workName;
    }

    public String getMail() {
        return mail;
    }
}
