import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;

public class dataReader {
	
	public static void main(String args[]){
		Charset charset = Charset.forName("US-ASCII");
		Path path = FileSystems.getDefault().getPath("","test.txt");
		Path newPath = FileSystems.getDefault().getPath("","svm.txt");
		File f = new File(newPath.getFileName().toString());
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(f));
			BufferedReader reader = Files.newBufferedReader(path, charset);
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] splitStr = line.split("\\s+");
				if(splitStr[0].equals("English")){
					String s = "1 "+"1:"+splitStr[1]+" 2:"+splitStr[2];
					writer.write(s);
					writer.newLine();
				} else {
					String s = "0 "+"1:"+splitStr[1]+" 2:"+splitStr[2];
					writer.write(s);
					writer.newLine();
				}
	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try{
				if(writer!=null) {
					writer.close();
				}
			}
			catch(Exception e){
				
			}
		}


		
		
		
//		String workingdir = System.getProperty("user.dir");
//		System.out.println(workingdir);
		
//		try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
//			String line = null;
//			while ((line = reader.readLine()) != null) {
//				String[] splitStr = line.split("\\s+");
//				for(int i = 0; i < splitStr.length; i++) {
//					writer.write(splitStr[i]);
//				}
//			}
//		} catch (IOException x) {
//			System.err.format("IOException: %s%n", x);
//		}

		
		
	}

}
