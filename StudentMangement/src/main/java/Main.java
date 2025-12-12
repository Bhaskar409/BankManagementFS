import java.util.*;

class student{
    private String id,name;
    private int marks;

    public student(String id,String name, int marks){
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId(){return id;}

    public int getMarks(){return marks;}

    public String getRole() {return "Undergrad";}

    @Override
    public String toString(){
        return id + " - " + name + "( "+ marks + ") " + getRole();
    }
}

class graduatestudent extends student{
    private String area;

    public graduatestudent(String id,String name, int marks)
    {
        super(id,name,marks);
        this.area = "Undergrad";
    }

    public String getRole()
    {
        return "Grad (" + area +")";
    }
}

class hornorstudent extends student{
    private int bonusmarks;

    public hornorstudent(String id,String name, int marks, int bonusmarks)
    {
        super(id,name,marks);
        this.bonusmarks = bonusmarks;
    }
    public String getRole()
    {
        return "honors student with ("+ bonusmarks+")";
    }
}

//generic class to compose different objects with different requirement
//repository is data for different objects like teacher, staff etc

class Repository<T>{
    private Map<String, T> data = new HashMap<>();

    public void save(String id, T obj){
        data.put(id, obj);
    }

    public T find(String id){
        return data.get(id);
    }

    public void delete(String id) {
        data.remove(id);
    }
}


public class Main {
    public static void main(String[] args) {
        List<student> list = new ArrayList<>();

        list.add(new student("s1","Alice", 89));
        list.add(new student("s2","Bob", 75));
        list.add(new student("s3","Carl", 64));

        list.add(new graduatestudent("g1","Dave", 70));
        list.add(new graduatestudent("g2","Eve", 90));


        list.add(new hornorstudent("h1","hola",79,5));
        list.add(new hornorstudent("h2","milo",83,4));


        Repository<student> repo = new Repository<>();

        for(student s: list)
            repo.save(s.getId(),s);

        System.out.println("All: ");
        list.forEach(System.out::println);

        System.out.println("\nLOOKUP s2: ");

        student s = repo.find("s2");

        System.out.println(s != null ? s : "not found");

        Iterator<student> it = list.iterator();

        while(it.hasNext()){
            student st = it.next();

            if(st.getMarks() < 80){
                it.remove();
                repo.delete(st.getId());
            }
        }

        System.out.println("\nAfter Removal: ");
        list.forEach(System.out::println);

        student maxStudent = null;

        for (student st : list) {
            if (maxStudent == null || st.getMarks() > maxStudent.getMarks()) {
                maxStudent = st;
            }
        }

        System.out.println("\nStudent with MAX marks:");
        System.out.println(maxStudent);
    }
}