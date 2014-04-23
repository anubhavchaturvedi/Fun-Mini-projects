import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class DataReader {

	private ArrayList<Record> records ;
	private File dataFile ;
	
	public DataReader(File dataFile)
	{
		this.dataFile = dataFile;
		this.records = new ArrayList<Record>();
	}
	
	public ArrayList<Record> process() throws FileNotFoundException
	{
		Scanner scan = new Scanner(dataFile);
		
		scan.nextLine();
		while(scan.hasNext())
		{
			String record = scan.nextLine();
			String values[] = record.split(";");
			
			char gender = values[2].equals("Male")?'M':'F';
			
			int age = Integer.parseInt(values[1]);
			
			String educationLevel = values[3];
			
			int numbers[] = new int[3];
			for( int i=0;i<3;i++)
				numbers[i] = Integer.parseInt(values[4+i]);
			
			records.add(new Record(age, gender, educationLevel, numbers));
		}
		
		scan.close();
		
		return records;
	}
}
