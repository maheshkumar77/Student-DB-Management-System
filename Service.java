
package schoolmanagementdb;

import java.sql.*;
import java.util.Scanner;

public class Service {
    private static final String URL = "jdbc:postgresql://localhost:5432/schoolmanagement";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    static Connection con;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fetch() throws SQLException {
        String query = "SELECT * FROM student";

        try (Statement st = con.createStatement(); 
        		ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int age = rs.getInt(3);
                String email = rs.getString(4);
                String course = rs.getString(5);
                System.out.println(id + " " + name + " " + age + " " + email + " " + course);
            }
        } 
    }
    
   
        // Assume the static connection setup is already provided

        public void insert() {
            String s = "INSERT INTO student VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement ps = con.prepareStatement(s)) {
            	Scanner sc = new Scanner(System.in);
                    while(true) {
                System.out.println("Enter id: ");
                int id = sc.nextInt();
                sc.nextLine();  // Consume leftover newline

                System.out.println("Enter name: ");
                String name = sc.nextLine();

                System.out.println("Enter age: ");
                int age = sc.nextInt();
                sc.nextLine();  // Consume leftover newline

                System.out.println("Enter email: ");
                String email = sc.nextLine();

                System.out.println("Enter course: ");
                String course = sc.nextLine();

                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setInt(3, age);
                ps.setString(4, email);
                ps.setString(5, course);

                ps.executeUpdate();
                System.out.println("*******SAVED******");
                
                System.out.println("Do you want to add another record? (yes/no)");
                String rp= sc.nextLine();
                
                if(rp.equalsIgnoreCase("no")) {
                	break;
                }
                
                }
                    
                    
                    

            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            
        }
        public void delete() {
        	String s2="DELETE from student where id=?";
        	    
        	try {
        		Scanner sc=new Scanner(System.in);  
				PreparedStatement pst=con.prepareStatement(s2);
				 while(true) {

				
				System.out.println("Enter the id you want to Delete::");
				   int n=sc.nextInt();
				         sc.nextLine();// Consume the leftover newline
				   pst.setInt(1,n);
				  int deleted = pst.executeUpdate();

		            if (deleted > 0) {
		                System.out.println("Row deleted successfully.");
		            } else {
		                System.out.println("No record found with the given id.");
		            }
				
				
				 System.out.println("Do you want to DELETE another record? (yes/no)");
		            String rp = sc.nextLine();
		            if(rp.equals("no")) {
		            	break;
		            }
				
        	   }
				 //con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
//Update
        public void update() {
            String queryTemplate = "UPDATE student SET %s = ? WHERE id = ?";
            Scanner sc = new Scanner(System.in);

            try {
                System.out.println("Enter the ID of the student to update:");
                int id = sc.nextInt();
                sc.nextLine(); // Consume leftover newline

                System.out.println("Which field would you like to update? (name, age, email, course)");
                String field = sc.nextLine().toLowerCase();

                String query;
                switch (field) {
                    case "name":
                    case "email":
                    case "course":
                        query = String.format(queryTemplate, field);
                        break;
                    case "age":
                        query = String.format(queryTemplate, "age");
                        break;
                    default:
                        System.out.println("Invalid field. Please try again.");
                        return;
                }

                try (PreparedStatement pst = con.prepareStatement(query)) {
                    System.out.println("Enter the new value for " + field + ":");
                    if (field.equals("age")) {
                        int newValue = sc.nextInt();
                        pst.setInt(1, newValue);
                    } else {
                        String newValue = sc.nextLine();
                        pst.setString(1, newValue);
                    }
                    pst.setInt(2, id);

                    int up = pst.executeUpdate();
                    if (up > 0) {
                        System.out.println("**********UPDATED SUCCESSFULLY***********");
                    } else {
                        System.out.println("No record found with the given ID.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        
        public void exit() {
        	//System.out.println("OUT FROM THE APPLICATION");
        	try {
				con.close();
				System.out.println("Connection was closed....");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
    }


