package seeker;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Search {

	private Set<String> terms;
	
	public Search(String search) {
		this.terms = formatTerms(search);
	}
	
	public Result within(String content) {
		long count = 0;
		Result result = new Result();
		for(String term : terms) {
			int lastIndex = 0;
			while((lastIndex = content.indexOf(term, lastIndex)) > -1) {
				lastIndex += term.length();
				count++;
			}
		}
		result.setResults(count);
		
		return result;
	}

	private Set<String> formatTerms(String search) {
		if(search == null || search.equals("")) {
			return new HashSet<String>();
		}
		search = search.trim();
		String[] termsArray = search.split(",\\s*");
		Set<String> termsSet = new HashSet<String>(Arrays.asList(termsArray));
		return termsSet;
	}
	
	public Set<String> getTerms() {
		return terms;
	}
	
}
