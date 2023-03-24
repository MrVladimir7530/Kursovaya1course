import java.util.Iterator;
import java.util.NoSuchElementException;

public class EmployeeBook {
    private Object[] array;
    private int id;
    private int capacity;
    private float coefficientGrow = 1.5f;

    public EmployeeBook() {
        id = 0;
        capacity = 10;
        array = new Object[capacity];
    }

    public EmployeeBook(int size) {
        id = 0;
        this.capacity = size;
        array = new Object[size];
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
                int index = i;
                return index;
            }
        }
        return -1;
    }

    private void newArrayCreateIfFull() {
        if (isFull()) {
            capacity = (int) (capacity * coefficientGrow);
            Object[] newArray = new Object[capacity];
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
        return (Employee) array[index];
    }

    public int averageSalary() {
        int averageSalary = allSalary() / id;
        return averageSalary;
    }

    public int averageSalaryByDepartment(int department) {
        int sumSalary = 0;
        int index = 0;
        for (int i = 0; i < id; i++) {
            Employee o = (Employee) array[i];
            if (o.getDepartment() == department) {
                sumSalary += o.getSalary();
                index++;
            }
        }
        int averageSalary = sumSalary / index;
        return averageSalary;
    }

    public int allSalary(){
        int sumSalary = 0;
        for (int i = 0; i < id; i++) {
            Employee o = (Employee) array[i];
            sumSalary += o.getSalary();
        }
        return sumSalary;
    }

    public int allSalaryByDepartment(int department){
        int sumSalary = 0;
        for (int i = 0; i < id; i++) {
            Employee o = (Employee) array[i];
            if (o.getDepartment() == department) {
                sumSalary += o.getSalary();
            }
        }
        return sumSalary;
    }

    public Employee minSalary() {
        Employee employee = (Employee) array[0];
        int minSalary = employee.getSalary();
        for (int i = 0; i < id; i++) {
            Employee o = (Employee) array[i];
            if (o.getSalary() < minSalary) {
                employee = o;
                minSalary = o.getSalary();
            }
        }
        return employee;
    }

    public Employee minSalaryByDepartment(int department) {
        for (int i = 0; i < id; i++) {
            Employee employee = (Employee) array[i];
            if (employee.getDepartment() == department) {
                int minSalary = employee.getSalary();
                for (; i < id; i++) {
                    Employee o = (Employee) array[i];
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
        Employee employee = (Employee) array[0];
        int maxSalary = employee.getSalary();
        for (int i = 0; i < id; i++) {
            Employee o = (Employee) array[i];
            if (o.getSalary() > maxSalary) {
                employee = o;
                maxSalary = o.getSalary();
            }
        }
        return employee;
    }

    public Employee maxSalaryByDepartment(int department) {
        for (int i = 0; i < id; i++) {
            Employee employee = (Employee) array[i];
            if (employee.getDepartment() == department) {
                int maxSalary = employee.getSalary();
                for (; i < id; i++) {
                    Employee o = (Employee) array[i];
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

    public void growSalaryOfPercent(int percent){
        float coefficientGrowPercent =1 + percent/100f;
        for (int i = 0; i < id; i++) {
            Employee o = (Employee) array[i];
            o.setSalary((int) (o.getSalary()*coefficientGrowPercent));
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
