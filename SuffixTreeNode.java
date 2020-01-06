package SuffixTree;
import java.util.TreeSet;
import java.util.Arrays;

public class SuffixTreeNode implements Comparable{
	TreeSet<SuffixTreeNode> children = new TreeSet<SuffixTreeNode>();
	boolean isRoot = false;
	SuffixTreeNode parent = null;
	String label = null;
	int depth=1;
	char characterMarker = '$';
	public SuffixTreeNode() {
		characterMarker = 2 ;
		isRoot = true;
	}
	public SuffixTreeNode(char c, SuffixTreeNode p) {
		parent = p;
		characterMarker = c;
		if(p != null) {
			depth = p.depth + 1;
		}
		//System.out.println("Parent " + p.characterMarker);
	}

	public void insertIn(char[] b) {
		SuffixTreeNode match = null;
		if(b.length != 0) {
			for(SuffixTreeNode s: children) {
				if(s.characterMarker == b[0]) {
					match = s;
				}
			}
			if(match == null) {
				//System.out.println("No child match " + b[0]);
				match = new SuffixTreeNode(b[0], this);
			}
			match.insertIn(Arrays.copyOfRange(b, 1, b.length));
		} else {
			match = new SuffixTreeNode((char)3, this);
		}
		children.add(match);

		
	}

	public void displayNode() {
		// TODO Auto-generated method stub
		//System.out.println("Children Size: " + this.children.size());
		if(this.parent != null ) {
			System.out.println(depth + " " + characterMarker + " P: " + this.parent.characterMarker);
		} else {
			System.out.println(depth + " " + characterMarker);
		}
		for(SuffixTreeNode node: this.children) {
			node.displayNode();
			
		}


	}
	public int compareTo(SuffixTreeNode arg0) {
		// TODO Auto-generated method stub
		if(this.characterMarker > arg0.characterMarker) {return 1;}
		else if(this.characterMarker < arg0.characterMarker) {return -1;}
		else {
			return 0;
		}
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if(this.characterMarker > ((SuffixTreeNode)o).characterMarker) {return 1;}
		else if(this.characterMarker < ((SuffixTreeNode)o).characterMarker) {return -1;}
		else {
			return 0;
		}
	}

}
