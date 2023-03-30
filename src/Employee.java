import java.util.Objects;

public class Employee {
    private String name;
    private int salary;
    private int department;

    public Employee(String name, int salary, int department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public int getDepartment() {
        return department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee that = (Employee) o;
        return salary == that.salary && department == that.department && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, department);
    }

    @Override
    public String toString() {
        return "EmployeeBook {" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }
}