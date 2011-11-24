package seeker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SimpleSeeker implements Seeker{
	
	private final File file;
	
	private final String content;
	
	public SimpleSeeker(File file) {
		this.file = file;
		this.content = loadFile();
	}
	
	@Override
	public Result seek(Search search) {
		if(search == null) {
			return new Result();
		}
		Result result = search.within(content);
		return result;
	}
	
	public String loadFile() {
		StringBuilder appender = new StringBuilder();
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			String line = null;
			while((line = br.readLine()) != null) {
				appender.append(line);
			}
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return appender.toString();
	}

	
}
