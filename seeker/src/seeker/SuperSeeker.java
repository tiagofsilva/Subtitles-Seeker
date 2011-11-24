package seeker;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class SuperSeeker implements Seeker, Runnable {

	private SimpleSeeker seeker;
	
	private Queue<Search> searches;
	
	private Result result;
	
	private int threads;
	
	public SuperSeeker(File file) {
		this.seeker = new SimpleSeeker(file);
		this.result = new Result();
		this.searches = new LinkedList<Search>();
	}
	
	@Override
	public Result seek(Search search) {
		threads = search.getTerms().size();
		for(String term : search.getTerms()) {
			this.searches.offer(new Search(term));
			Thread concurrentSeeker = new Thread(this);
			concurrentSeeker.start();
		}
		
		while(threads != 0);
		
		return result;
	}

	@Override
	public void run() {
		Search search = this.searches.poll();
		Result tempResult = seeker.seek(search);
		this.result.add(tempResult);
		threads--;
	}
}
