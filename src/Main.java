import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Employee person1 = new Employee("Vladimir", 150_000, 2);
        Employee person2 = new Employee("Ivan", 100_000, 1);
        Employee person3 = new Employee("Petr", 70_000, 4);
        Employee person4 = new Employee("Maria", 30_000, 3);
        Employee person5 = new Employee("Ania", 50_000, 5);
        Employee person6 = new Employee("Dima", 55_000, 2);
        Employee person7 = new Employee("Andrey", 120_000, 4);
        Employee person8 = new Employee("Lisa", 60_000, 5);
        Employee person9 = new Employee("Nikolay", 40_000, 3);

        EmployeeBook employeeBook = new EmployeeBook(15);
        employeeBook.add(person1);
        employeeBook.add(person2);
        employeeBook.add(person3);
        employeeBook.add(3, person4);
        employeeBook.add(person5);
        employeeBook.add(5, person6);
        employeeBook.add(person7);
        employeeBook.add(person8);
        employeeBook.add(person9);
        employeeBook.remove(person9);

        employeeBook.growSalaryOfPercent(20);


        Iterator<Employee> iterator = employeeBook.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("---------------------------------------");

        System.out.println("Средняя зарплата: " + employeeBook.averageSalary());
        System.out.println("Минимальная зарплата: " + employeeBook.minSalary());
        System.out.println("Максимальная зарплата по отделу: " + employeeBook.maxSalaryByDepartment(5));
        System.out.println("Средняя зарплата по отделу: " + employeeBook.averageSalaryByDepartment(5));


    }
}