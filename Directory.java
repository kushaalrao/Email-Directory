import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Directory{
	private Map dir;

	public Directory(){
		dir = new TreeMap();
	}

	public Directory(String dirFile){
		dir = new TreeMap();
		Scanner in;
		try{
			in = new Scanner(new File(dirFile));
			while(in.hasNext()){
				String lastName = "";
				String firstName = "";
				String emailAddr = "";
				if(in.hasNext()){
					firstName = in.next();
				}
                if(in.hasNext()){
                	lastName = in.next();
                }
                if(in.hasNext()){
                	emailAddr = in.next();
                }
                addEntry(new Name(lastName, firstName), emailAddr);
			}
		}catch(IOException i){
				System.out.println("Error: " + i.getMessage());
		}
	}

	public void addEntry(Name name, String emailAddr){
		dir.put(name, emailAddr);
	}

	public String lookup(Name name){
		if (dir.containsKey(name)){
			return (String) dir.get(name);
		} else{
			return "Name does not exist";
		}
	}
	
	public Set lookupLastName(String lastName) {
		Set result = new TreeSet();
		Iterator iterator = dir.entrySet().iterator();
		while (iterator.hasNext()){
			Entry entry = (Entry) iterator.next();
			Name name = (Name) entry.getKey();
			if (name.last().equals(lastName)){
				result.add(dir.get(name));
			}
		}
    	return result;
	}

	public void listAll(){
		Iterator iterator = dir.entrySet().iterator();
		while (iterator.hasNext()){
			Entry entry = (Entry) iterator.next();
			Name name = (Name) entry.getKey();
			System.out.println(name + (String) dir.get(name));
		}
	}
}