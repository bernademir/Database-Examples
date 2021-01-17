package net.mongodb;

import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class mongodb {

	public static void main(String[] args) {
		
		String uri = "mongodb://berna:brnuser@localhost";
		
		MongoClient mongoClient = MongoClients.create(uri);
		
		MongoDatabase db = mongoClient.getDatabase("persondb");
		MongoCollection<Document> person = db.getCollection("person");
		
//		FindIterable<Document> result = person.find(new Document("name", "cafer"));
//		result.forEach(d -> { System.out.println(d.get("id") + " " + d.get("name"));
//		
//		});
		
		
		System.out.print("Menu\n0 : exit\n1 : family tree\n2 : descendants\n" );
		Scanner option = new Scanner(System.in);
		System.out.print("Please write an option: \n");
		int opt = option.nextInt();
		
		if(opt == 0) {
			
			System.exit(0);
			
		}else if(opt == 1) {
			
			System.out.println( "ID ?");
			Scanner sc = new Scanner(System.in);
	    	int id = sc.nextInt();
	    	
	    	FindIterable<Document> result = person.find(new Document("id", id));
			result.forEach(d1 -> { 
				System.out.println(d1.get("id") + " (" + d1.get("name") + ") family tree : \n");
				int parentid = (int) d1.get("parentid");
				FindIterable<Document> result2 = person.find(new Document("id", parentid));
				result2.forEach(d2 -> { System.out.println(d2.get("id") + " " + d2.get("name"));
				});
			});
			
		}else if(opt == 2) {
			
			System.out.println( "ID ?");
			Scanner sc = new Scanner(System.in);
	    	int id = sc.nextInt();
	    	
	    	FindIterable<Document> result1 = person.find(new Document("id", id));
			result1.forEach(d -> { 
				System.out.println(d.get("id") + " (" + d.get("name") +") descendants : \n" );
			});
			FindIterable<Document> result2 = person.find(new Document("parentid", id));
			result2.forEach(d -> { 
				System.out.println(d.get("id") + " " + d.get("name"));
			});
			
		}else {
			
			System.out.print("Please write an existing option: ");
			
		}
	}
}
