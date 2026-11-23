import com.mongodb.client.*;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import java.util.*;
public class StudentRecords {
 public static void main(String[] args) {
 try (MongoClient client =
 MongoClients.create("mongodb://localhost:27017")) {
 MongoDatabase db = client.getDatabase("college");
 MongoCollection<Document> s = db.getCollection("students");
 s.drop(); 
 
 s.insertOne(new Document("rollNo",101).append("name","Arun")
 .append("dept","CSE").append("marks",78).append("city","Chennai"));
 
 s.insertMany(Arrays.asList(
 new Document("rollNo",102).append("name","Priya").append("dept","ECE").append("marks",85).append("city","Jaipur"),
 new Document("rollNo",103).append("name","Karthik").append("dept","CSE").append("marks",62).append("city","Chennai"),
 new Document("rollNo",104).append("name","Meena").append("dept","EEE").append("marks",74).append("city","Jaipur"),
 new Document("rollNo",105).append("name","Suresh").append("dept","CSE").append("marks",91).append("city","Trichy")
 ));
 
 System.out.println("All students:");
 s.find().forEach(System.out::println);
 System.out.println("CSE students:");
 s.find(eq("dept","CSE")).forEach(System.out::println);
 
 s.updateOne(eq("rollNo",103), set("marks",68));
 
 s.updateMany(eq("dept","CSE"), set("semester",3));
 
 s.deleteOne(eq("rollNo",104));

 System.out.println("Remaining students: " + s.countDocuments());
 }
 }
}
