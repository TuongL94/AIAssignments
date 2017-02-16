import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;

public class dataReader {
	
	public static void encodeData(String dataFile, String encodedDataFile) {
		Charset charset = Charset.forName("US-ASCII");
		Path path = FileSystems.getDefault().getPath("",dataFile);
		Path newPath = FileSystems.getDefault().getPath("",encodedDataFile);
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
	}
	
	public static void main(String args[]){
		String data = "rawData.txt";
		String encodedData = "encodedData.txt";
		encodeData(data,encodedData);
	}
}
