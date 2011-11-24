package seeker;

public class Result {

	private long results;
	
	public Result() {}
	
	public void add(Result result) {
		this.results = this.results() + result.results();
	}

	public long results() {
		return results;
	}

	public void setResults(long results) {
		this.results = results;
	}
	
	
	
}
