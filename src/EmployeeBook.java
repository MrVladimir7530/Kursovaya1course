import java.util.Iterator;
import java.util.NoSuchElementException;

public class EmployeeBook {
    private Employee[] array;
    private int id;
    private int capacity;
    private float coefficientGrow = 1.5f;

    public EmployeeBook() {
        id = 0;
        capacity = 10;
        array = new Employee[capacity];
    }

    public EmployeeBook(int size) {
        id = 0;
        this.capacity = size;
        array = new Employee[size];
    }

    private boolean isEmpty() {
        return id == 0;
    }

    private boolean isFull() {
        return id >= capacity;
    }

    public boolean contains(Employee o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(Employee o) {
        if (isEmpty()) {
            return -1;
        }
        for (int i = 0; i <= id; i++) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    private void newArrayCreateIfFull() {
        if (isFull()) {
            capacity = (int) (capacity * coefficientGrow);
            Employee[] newArray = new Employee[capacity];
            System.arraycopy(array, 0, newArray, 0, id);
            array = newArray;
        }
    }

    public void add(Employee o) {
        newArrayCreateIfFull();
        array[id] = o;
        id++;
    }

    public void add(int index, Employee o) {
        newArrayCreateIfFull();
        System.arraycopy(array, index, array, index + 1, id - index);
        array[index] = o;
        id++;
    }

    public boolean remove(Employee o) {
        int index = indexOf(o);
        if (index == -1) {
            return false;
        } else {
            remove(index);
        }
        return true;
    }

    public boolean remove(int index) {
        if (!isEmpty()) {
            System.arraycopy(array, index + 1, array, index, id - index);
            id--;
            return true;
        }
        return false;
    }

    public Employee get(int index) {
        return array[index];
    }

    public int averageSalary() {
        return allSalary() / id;
    }

    public int averageSalaryByDepartment(int department) {
        int sumSalary = 0;
        int index = 0;
        for (int i = 0; i < id; i++) {
            Employee o = array[i];
            if (o.getDepartment() == department) {
                sumSalary += o.getSalary();
                index++;
            }
        }
        return sumSalary / index;
    }

    public int allSalary() {
        int sumSalary = 0;
        for (int i = 0; i < id; i++) {
            Employee o = array[i];
            sumSalary += o.getSalary();
        }
        return sumSalary;
    }

    public int allSalaryByDepartment(int department) {
        int sumSalary = 0;
        for (int i = 0; i < id; i++) {
            Employee o = array[i];
            if (o.getDepartment() == department) {
                sumSalary += o.getSalary();
            }
        }
        return sumSalary;
    }

    public Employee minSalary() {
        Employee employee = array[0];
        int minSalary = employee.getSalary();
        for (int i = 0; i < id; i++) {
            Employee o = array[i];
            if (o.getSalary() < minSalary) {
                employee = o;
                minSalary = o.getSalary();
            }
        }
        return employee;
    }

    public Employee minSalaryByDepartment(int department) {
        for (int i = 0; i < id; i++) {
            Employee employee = array[i];
            if (employee.getDepartment() == department) {
                int minSalary = employee.getSalary();
                for (; i < id; i++) {
                    Employee o = array[i];
                    if (o.getSalary() < minSalary && department == o.getDepartment()) {
                        employee = o;
                        minSalary = o.getSalary();
                    }
                }
                return employee;
            }
        }
        return null;
    }

    public Employee maxSalary() {
        Employee employee = array[0];
        int maxSalary = employee.getSalary();
        for (int i = 0; i < id; i++) {
            Employee o = array[i];
            if (o.getSalary() > maxSalary) {
                employee = o;
                maxSalary = o.getSalary();
            }
        }
        return employee;
    }

    public Employee maxSalaryByDepartment(int department) {
        for (int i = 0; i < id; i++) {
            Employee employee = array[i];
            if (employee.getDepartment() == department) {
                int maxSalary = employee.getSalary();
                for (; i < id; i++) {
                    Employee o = array[i];
                    if (o.getSalary() > maxSalary && department == o.getDepartment()) {
                        employee = o;
                        maxSalary = o.getSalary();
                    }
                }
                return employee;
            }
        }
        return null;
    }

    public void displayEmployeeByDepartment(int department) {
        for (int i = 0; i < id; i++) {
            Employee employee = array[i];
            if (employee.getDepartment() == department) {
                System.out.println("EmployeeBook" +
                        "{name='" + employee.getName() + '\'' +
                        ", salary=" + employee.getSalary() +
                        "}");
            }
        }
    }

    public void growSalaryOfPercent(int percent) {
        float coefficientGrowPercent = 1 + percent / 100f;
        for (int i = 0; i < id; i++) {
            Employee o = array[i];
            o.setSalary((int) (o.getSalary() * coefficientGrowPercent));
        }
    }

    public void employeeWithSalaryFrom(int salary) {
        for (int i = 0; i < id; i++) {
            Employee employee = array[i];
            if (employee.getSalary() >= salary) {
                System.out.println(employee);
            }
        }
    }

    public void employeeWithSalaryTo(int salary) {
        for (int i = 0; i < id; i++) {
            Employee employee = array[i];
            if (employee.getSalary() < salary) {
                System.out.println(employee);
            }
        }
    }


    public Iterator<Employee> iterator() {
        return new EmployeeIterator();
    }


    public class EmployeeIterator implements Iterator {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < id;
        }

        @Override
        public Object next() {
            if (current >= id) {
                throw new NoSuchElementException();
            }
            current++;
            return array[current - 1];
        }
    }

}