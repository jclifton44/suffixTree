
public class SuffixTree {
	SuffixTreeNode root = null;
	public SuffixTree() {
		SuffixTreeNode stn = new SuffixTreeNode();
		root = stn;
	}
	
	public SuffixTree(String s) {
		root = new SuffixTreeNode(null);
		if(s.toCharArray()[s.length()-1] != '$'){
			s+="$";
		}
		for(int i = s.length()-2; i >= 0; i--) {
			
			insert(s.substring(i, s.length()));
			
		}
	}
	public Boolean search(String s) {
		if(s.toCharArray()[s.length()-1] != '$'){
			s+="$";
		}
		return searchSuffixTree(s,root);
	}
	public Boolean searchSuffixTree(String s,SuffixTreeNode node) {
		
		if(s.length() == 0) {
			return true;
		}
		int match = 0;
		for(SuffixTreeNode n: node.children) {
			match = prefixMatch(n.label.toCharArray(), s.toCharArray());
			if(match == n.label.length()) {
				return searchSuffixTree(s.substring(match, s.length()),n);
			}
			
		}
		return false;
	}
	public static int prefixMatch(char[] a, char[] b) {
		int i = a.length < b.length ? a.length : b.length;
		int s = 0;
		int j = 0;
		while(j < i && a[j] == b[j]) {
			j++;
		}
		return j;
		
	}
	public void printTree() {
		root.displayNode();
	}
	public SuffixTree insert(String s){
		
		int match = 0;
		//System.out.println(s);
		
		
		root.insertIn(s);
	
		return this;
	}
}
