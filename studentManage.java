import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.*;

public class studentManage {
    String FirstN;
    String LastN;
    String Id;
    List<String> courses;
    BigDecimal value;
    Scanner Kb= new Scanner(System.in);
    studentManage(String fName, String lName)
    {
        this.FirstN = FirstN;
        this.LastN= LastN;
    }
    studentManage()
    {

    }
    BigDecimal getTuition() //Using Getters and Setters Method
    {
        return value;
    }
    void setTuition(BigDecimal money) // BigDecimal handle very large and very small floating point numbers
    {
        this.value = money;
    }

    String getName()
    {
        return FirstN + " " +  LastN;
    }
    void setFirstName(String FirstN)
    {
        this.FirstN = FirstN;
    }
    void setLastName(String LastN)
    {
        this.LastN = LastN;
    }

    String getId()
    {
        return Id;
    }
    void setId(String Id)
    {
        this.Id = Id;
    }
    List<String> getCourses()
    {
        return courses;
    }
    void setCourses(List<String> courses)
    {
        this.courses = courses;
    }
    void makeID()
    {
        String grade;
        boolean checked = false;

        while (!checked)
        {
            System.out.println("Name of the Collage Year Enter:\n  1. First Year \n  2. Second Year \n  3. Third Year \n  4. Fourth Year \n ");
            grade = Kb.nextLine();
            if (grade.length() == 1 && Integer.parseInt(grade) > 0 &&         Integer.parseInt(grade) < 5)
            {
                setId(grade.concat(randomString()));
                checked = true;
            } else {
                System.out.println("The input you enter is incorrect please try again");
            }
        }

    }

    String randomString()
    {
        String AB ="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();// it is randomly generated 5 character for student id
        int great = AB.length();
        int temp;
        String codeword = "";
        for (int i = 0; i < 4; i++)
        {
            temp = (int) (random.nextFloat() * great);
            codeword = codeword.concat(Character.toString(AB.charAt(temp)));
        }
        return codeword;
    }
    void payForCourses()
    {
        String answer;
        BigDecimal payment;
        BigDecimal moneyLeftOver;

        while (getTuition().compareTo(BigDecimal.ZERO) > 0)
        {
            System.out.println("Your current balance is " + getTuition()+"Rs");
            System.out.println("Do you want pay your balance? yes or no:");

            answer = Kb.nextLine();

            if (answer.toLowerCase().equals("yes"))
            {
                System.out.println("How much balance you Pay:");

                if (Kb.hasNextBigDecimal())
                {
                    payment = Kb.nextBigDecimal();
                    payment = payment.setScale(2, RoundingMode.HALF_UP);
                    Kb.nextLine();
                    if ((payment.compareTo(BigDecimal.ZERO) > 0) && payment.compareTo(getTuition()) <= 0)
                    {
                        moneyLeftOver = getTuition().subtract(payment);
                        setTuition(moneyLeftOver);
                    }

                    else if (payment.compareTo(getTuition()) > 0)
                    {
                        System.out.println("The value you have given is greater than your tuition:");
                    }

                    else if (payment.compareTo(BigDecimal.ZERO) < 0) {
                        System.out.println("You gave an negative number as a payment value. Please enter a positive value next time:");
                    }

                }
                else
                {
                    Kb.nextLine();
                    System.out.println("You entered the wrong input so try next time:");
                }

            }
            else if (answer.toLowerCase().equals("no"))
            {
                break;
            }
            else
            {
                System.out.println("You give the wrong input either enter yes or no:");
            }
        }
    }
    void chooseCourses(List<String> classes, int courseNumber)
    {
        switch (courseNumber)
        {
            case 1:
                if (check(classes, "C Programming"))
                    classes.add("C Programming");
                break;
            case 2:
                if (check(classes, "C++"))
                    classes.add("C++");
                break;
            case 3:
                if (check(classes, "Python"))
                    classes.add("Python");
                break;
            case 4:
                if (check(classes, "Java Programming"))
                    classes.add("Java Programming");
                break;
            case 5:
                if (check(classes, "Mathematics"))
                    classes.add("Mathematics");
                break;
            default:
                System.out.println("You gave the wrong input:");
                break;
        }
    }
    private void addCourses()
    {
        List<String> classes = new LinkedList<>();
        setCourses(classes);

        String answer;
        int nextCourse;
        BigDecimal size;
        BigDecimal cost;
        System.out.println("Do you want to add any courses? yes or no:");
        answer = Kb.nextLine();
        while (!answer.toLowerCase().equals("no"))
        {
            if (answer.toLowerCase().equals("yes"))
            {
                System.out.println("Which classes would you like to add now? choose from the following Subjects. ");
                System.out.println("1. C Programming");
                System.out.println("2. C++");
                System.out.println("3. Python");
                System.out.println("4. Java Programming");
                System.out.println("5. Mathematics");

                if (Kb.hasNextInt())
                {
                    nextCourse = Kb.nextInt();
                    Kb.nextLine();
                    chooseCourses(classes, nextCourse);
                }
                else
                {
                    System.out.println("You put in the wrong input: Enter a number 1 - 5 for each class");
                    Kb.nextLine();
                }
            }
            else
            {
                System.out.println("You put in the wrong input: Enter either yes or no next time");
            }
            System.out.println("Do you want to add any more courses? yes or no:");
            answer = Kb.nextLine();
        }
        size = new BigDecimal(classes.size());
        cost = new BigDecimal(600);

        cost = cost.multiply(size);
        setTuition(cost);
    }
    boolean check(List<String> list, String word)
    {
        for (String temp : list)
        {
            if (word.equals(temp))
            {
                System.out.println("You are already enrolled in that course");
                return false;
            }
        }
        return true;
    }
    void displayInfo(studentManage[] studentList)
    {
        for (studentManage student : studentList)
        {
            System.out.println("Student Name: " + student.getName());
            System.out.println("Student ID: " + student.getId());

            if (student.getCourses().size() > 0) {
                System.out.println("Student's Current Courses:" + student.getCourses());
            } else {
                System.out.println("Student's Current Courses: The student isn't enrolled in any courses");
            }
            System.out.println("Student's Current Balance: " + student.getTuition()+"Rs");
            System.out.println("***************************************************************************");
        }
    }

    public static void main(String[] args)
    {
        try {                                 //Used Exceptional Handling
            int size;
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the number of students you add:");
            size = keyboard.nextInt();
            keyboard.nextLine();
            studentManage[] students = new studentManage[size];
            studentManage student;
            String firstName = "";
            String lastName = "";

            for (int i = 0; i < size; i++)
            {
                student = new studentManage(firstName, lastName);
                students[i] = student;
                System.out.println("Enter the first name of Student: ");
                firstName = keyboard.nextLine();
                student.setFirstName(firstName);

                System.out.println("Enter your last name:");
                lastName = keyboard.nextLine();
                student.setLastName(lastName);


                student.makeID();
                student.addCourses();
                student.payForCourses();

                if (i == size - 1)
                    student.displayInfo(students);
            }

        }
        catch (NegativeArraySizeException e)
        {
            System.out.println("You can't use a negative number for size");

        }
    }
}