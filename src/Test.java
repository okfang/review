
enum Colors{
    red, yellow, blue, green, black;

    @Override
    public String toString() {
        return name();
    }
}

public class Test{

    public int id;
    public String name;
    public Object c;

    public static void main(String[] args) {
        for (Colors colors: Colors.values()){
            System.out.println(colors);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (id != test.id) return false;
        if (!name.equals(test.name)) return false;
        return c.equals(test.c);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + c.hashCode();
        return result;
    }
}
