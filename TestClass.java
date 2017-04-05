
public class TestClass {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SuffixTree st = new SuffixTree("CAGTCAGG");
		String t[] = {"GG", "CAGG", "TCAGG", "GS","AGTCAGG", "CAG", "CAGTCAGG", "CAGG", "AGG", "TCA"};
		for(int i = 0; i < t.length; i++) {
			System.out.println(t[i] + ": " + st.search(t[i]));
		}
		
		st.printTree();
	}

}
