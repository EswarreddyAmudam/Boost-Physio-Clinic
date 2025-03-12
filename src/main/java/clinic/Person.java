package clinic;

public class Person {
    protected int id;
    protected String fullName;
    protected String address;
    protected String phoneNumber;

    public Person(int id, String fullName, String address, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public int getId() {
        return id;
    }
}
