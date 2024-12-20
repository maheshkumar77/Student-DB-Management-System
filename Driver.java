package schoolmanagementdb;

import java.sql.SQLException;
import java.util.Scanner;

public class Driver {
public static void main(String[] args) throws SQLException {
	
	while(true) {
	System.out.println("*****WELCOME TO MY SCHOOL DATABASE*****");
	System.out.println("Enter 1 to save student::\nEnter 2 to update student::\nEnter 3 to delete the student::\nEnter 4 to fetch the student::\nEnter 5 to exit the application::");
	System.out.println("enter your choice::");
	Scanner s =new Scanner(System.in);
	  int i=s.nextInt();
	Service s2=new Service();
	 switch(i){
	 case 1:
		 if(i==1) {
			 s2.insert();
				System.out.println("****SAVE THE DATA");
		 }
		 
	 case 2:
		 if(i==2) {
			 s2.update();
				//System.out.println("****UPDATE THE DATA****");
		 }
		
	 case 3:
		 if(i==3) {
			 s2.delete();
				System.out.println("****DELETE THE DATA****");
		 }
		 
	 case 4:
		   if(i==4) {
			 s2.fetch();
				System.out.println("****FETCH THE DATA****");
		   }
		
	 case 5:
		 if(i==5) {
			 s2.exit();
				System.out.println("***OUT FROM THE APPLICATION***");
				s.close();  // Close the scanner
                return;  // Exit the program
		 }
		 default:
			 
		 System.out.println("***********INVALID CHOICE********");
		
		 
	 }
	
	}
}
}