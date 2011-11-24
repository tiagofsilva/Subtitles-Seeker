package seeker;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

public class SeekerTests {

	public static File file;
	
	@BeforeClass
	public static void init() {
		file = new File("input.txt");
	}
	
	@Test
	public void testSimpleSearchReturning1000Results() {
		Seeker seeker = new SimpleSeeker(file);
		Result res = seeker.seek(new Search("carro"));
		assertEquals(1000, res.results());
	}
	
	@Test
	public void testSimpleSearchReturning1Results() {
		Seeker seeker = new SimpleSeeker(file);
		Result res = seeker.seek(new Search("still"));
		assertEquals(1, res.results());
	}
	
	@Test
	public void testSimpleSearchReturning0Results() {
		Seeker seeker = new SimpleSeeker(file);
		Result res = seeker.seek(new Search("rua"));
		assertEquals(0, res.results());
	}
	
	@Test
	public void testSimpleEmptySearch() {
		Seeker seeker = new SimpleSeeker(file);
		Result res = seeker.seek(new Search(""));
		assertEquals(0, res.results());
	}
	
	@Test
	public void testSimpleSearchWithSearchObjectBeingNull() {
		Seeker seeker = new SimpleSeeker(file);
		Result res = seeker.seek(null);
		assertEquals(0, res.results());
	}
	
	@Test
	public void testSimpleSearchWithTermsNull() {
		Seeker seeker = new SimpleSeeker(file);
		Result res = seeker.seek(new Search(null));
		assertEquals(0, res.results());
	}
	
	@Test
	public void testCompoundSearchReturning2000Results() {
		Seeker seeker = new SimpleSeeker(file);
		Result res = seeker.seek(new Search("carro,casa"));
		assertEquals(2000, res.results());
	}
	
	@Test
	public void testCompoundSearchReturning2000ResultsWithSpaces() {
		Seeker seeker = new SimpleSeeker(file);
		Result res = seeker.seek(new Search("carro,    casa"));
		assertEquals(2000, res.results());
	}
	
	@Test
	public void testCompoundSearchReturning3000Results() {
		Seeker seeker = new SimpleSeeker(file);
		Result res = seeker.seek(new Search("carro,casa, roupa"));
		assertEquals(3000, res.results());
	}
	
	@Test
	public void testNormalSearchForLetters() {
		Seeker seeker = new SimpleSeeker(file);
		Result res = seeker.seek(new Search("a, b, c, r, o, w, e,n,e,v,e,r,c,h,a,n,g,e,g,r,e,e,n,e,y,e,s, m,o,s,e,s, x,a,n,d,y, s,p,a,r,k,s"));
		assertEquals(68039, res.results());
	}
	
	@Test
	public void testParallelSearchReturning1Results() {
		Seeker seeker = new SuperSeeker(file);
		Result res = seeker.seek(new Search("still"));
		assertEquals(1, res.results());
	}
	
	@Test
	public void testParallelSearchReturning0Results() {
		Seeker seeker = new SuperSeeker(file);
		Result res = seeker.seek(new Search("zzz"));
		assertEquals(0, res.results());
	}
	
	@Test
	public void testParallelSearchReturning1000Results() {
		Seeker seeker = new SuperSeeker(file);
		Result res = seeker.seek(new Search("casa"));
		assertEquals(1000, res.results());
	}
	
	@Test
	public void testParallelSearchReturning2000Results() {
		Seeker seeker = new SuperSeeker(file);
		Result res = seeker.seek(new Search("casa, carro"));
		assertEquals(2000, res.results());
	}
	
	@Test
	public void testParallelSearchReturning3000Results() {
		Seeker seeker = new SuperSeeker(file);
		Result res = seeker.seek(new Search("casa, carro,   roupa"));
		assertEquals(3000, res.results());
	}
	
	@Test
	public void testParallelSearchReturning3001Results() {
		Seeker seeker = new SuperSeeker(file);
		Result res = seeker.seek(new Search("casa, carro,   roupa, still"));
		assertEquals(3001, res.results());
	}
	
	@Test
	public void testParallelSearchForLetters() {
		Seeker seeker = new SuperSeeker(file);
		Result res = seeker.seek(new Search("a, b, c, r, o, w, e,n,e,v,e,r,c,h,a,n,g,e,g,r,e,e,n,e,y,e,s, m,o,s,e,s, x,a,n,d,y, s,p,a,r,k,s"));
		assertEquals(68039, res.results());
	}
	
	@Test
	public void testParallelEmptySearch() {
		Seeker seeker = new SuperSeeker(file);
		Result res = seeker.seek(new Search(""));
		assertEquals(0, res.results());
	}
}
