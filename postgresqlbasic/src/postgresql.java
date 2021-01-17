import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class postgresql {
	
	public static void main(String[] args) {
		
		System.out.print("Menu\n0 : exit\n1 : family tree\n2 : descendants\n" );
		
		Scanner option = new Scanner(System.in);
		System.out.print("Please write an option: ");
		int opt = option.nextInt();
		
		Connection c = null;
		Statement stmt = null;
		
		try {
			
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/personvtys",
					"postgres", "brnuser");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			
			if(opt == 0) {
				
				System.exit(0);
				
			}else if(opt == 1) {
				
				System.out.println( "ID ?");
				Scanner sc = new Scanner(System.in);
		    	int id = sc.nextInt();

				ResultSet rs1 = stmt.executeQuery( "select * from person where id = " + id);
					while ( rs1.next() ) {
			            int idd = rs1.getInt("id");
			            String  namee = rs1.getString("name");
			            System.out.println("ID : "+ idd + " (" + namee + ") family tree :\n");			            
			         }
				
				ResultSet rs2 = stmt.executeQuery( "select * from person p1, person p2 where p2.parentid = p1.id and p2.id = " + id);
				while ( rs2.next() ) {
					int idd = rs2.getInt("id");
					String  namee = rs2.getString("name");
					System.out.println(idd + " " + namee);
				}
				rs1.close();
				rs2.close();
		        stmt.close();
		        c.close();
		        
			}else if(opt == 2 ) {
				
				System.out.println( "ID ?");
				Scanner sc = new Scanner(System.in);
		    	int id = sc.nextInt();
		    	
		    	ResultSet rs1 = stmt.executeQuery( "select * from person where id = " +id );
				while ( rs1.next() ) {
					int idd = rs1.getInt("id");
					String  namee = rs1.getString("name");			
					System.out.println("ID : "+ idd + " (" + namee + ") descendants :");
				}
				
				ResultSet rs2 = stmt.executeQuery( "select * from person where parentid = " + id );
				while ( rs2.next() ) {
					int idd = rs2.getInt("id");
					String  namee = rs2.getString("name");
					System.out.println(idd + " " + namee);
				}
				rs1.close();
				rs2.close();
				stmt.close();
				c.close();
				
			}else {
				
				System.out.print("Please write an existing option: ");
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
	        System.exit(0);
		}
	}
}
