package SuffixTree;

import java.util.Arrays;

public class SuffixTree {
	SuffixTreeNode root = null;
	public SuffixTree() {
		SuffixTreeNode stn = new SuffixTreeNode();
		root = stn;
	}
	
	public SuffixTree(String s) {
		root = new SuffixTreeNode();
		//if(s.toCharArray()[s.length()-1] != '$'){
		//	s+="$";
		//}
		for(int i = s.length()-1; i >= 0; i--) {
			System.out.println("Inserting String: " + s.substring(i, s.length()));
			insert(s.substring(i, s.length()));
			
		}
	}
	public Boolean search(String s) {
		char[] b = s.toCharArray();
		return SuffixTree.searchSuffixTree(root,b);
	}
	public Boolean search(char[] b) {
		return SuffixTree.searchSuffixTree(root, b);
	}
	public static Boolean searchSuffixTree(SuffixTreeNode stn, char[] b) {
		
		//System.out.println("Searching " + new String(b));
		//System.out.println("Character: " + stn.characterMarker);
		if(b.length == 0) {
			for(SuffixTreeNode c: stn.children) {
				if(c.characterMarker == 3) {
					return true;
				}
			}
			return false;
		} 
		if(stn.children.size() == 0 && b.length == 1 && b[0] == stn.characterMarker) {
			return true;
		}
		for(SuffixTreeNode c: stn.children){
			//System.out.println("Child: " + c.characterMarker);
			if(c.characterMarker == b[0]) {
				return searchSuffixTree(c, Arrays.copyOfRange(b,1,b.length));			
			}
		}
		return false;
	}
	public static int prefixMatch(SuffixTreeNode stn, char[] b) {
		if(stn.label.contentEquals(b.toString())) {
			return b.length;
		} else if(b.length > 1 && stn.characterMarker == b[0]) { 
			for(SuffixTreeNode s: stn.children) {
				if(s.characterMarker == b[1]) {
					return 1 + SuffixTree.prefixMatch(s, Arrays.copyOfRange(b, 1, b.length));
				}
			}
			return 0;
		} else {
			return 0;
		}
		
		
	}
	public void printTree() {
		root.displayNode();
	}
	public SuffixTree insert(String s){
		
		int match = 0;
		//System.out.println(s);
		
		
		root.insertIn(s.toCharArray());
	
		return this;
	}
}
