import java.util.ArrayList;

public class SuffixTreeNode implements Comparable{
	ArrayList<SuffixTreeNode> children = new ArrayList<SuffixTreeNode>();
	SuffixTreeNode parent = null;
	String label = null;
	int depth=1;
	public SuffixTreeNode(String s) {
		// TODO Auto-generated constructor stub
		label = s;
	}
	public SuffixTreeNode(String s, SuffixTreeNode p) {
		parent = p;
		label = s;
		if(p != null) {
			depth = p.depth + 1;
		}
	}
	public SuffixTreeNode(){}
	public void insertIn(String substring) {
		if(label == null) {
			label = "root";
			//System.out.println("Root: "+substring);
			this.insertIn(substring);
			return;
		} else {
			
			int match = SuffixTree.prefixMatch(substring.toCharArray(), label.toCharArray());
			if(match < label.length() && match > 0) {
				//new label
				//LABEL: ABABC
				//SUBST: ABAB
				if(substring.length() >= match) {
					//EXAMP:
					//LABEL: ABABC
					//SUBST: ABAB
					//LABEL: A
					//SUBST: 
					
					SuffixTreeNode stn = new SuffixTreeNode(label.substring(match,label.length()),this);
					for(SuffixTreeNode s: this.children) {
						stn.children.add(s);
					}
					this.children.clear();
					this.children.add(stn); 
					if((substring.substring(match,substring.length()).length() > match)) {
						SuffixTreeNode substringstn = new SuffixTreeNode(substring.substring(match, substring.length()), this);
						this.children.add(substringstn);
					}
				}
				label = substring.substring(0,match);

				//create new label
				//give all children of old label to new label
				
				//add child for substring(0, match)
			} else if(match == label.length() || match == 0){
				//if substring contains post-suffix
				//look through labels children
				//if no match create leaf
				//if substring is exact copy of label with no matching children
				//create new leaf
				
				Boolean inTree = false;
				int childrenMatch = 0;
				for(SuffixTreeNode s: this.children) {
					childrenMatch = SuffixTree.prefixMatch(s.label.toCharArray(), substring.substring(match,substring.length()).toCharArray());
					//System.out.println("childrenmatch " + childrenMatch);
					if(0 < childrenMatch){
						if(childrenMatch == s.label.length()) {
							//Do nothing / Node Exists in Tree
						} else {
							//Insert into submatching node
							s.insertIn(substring);
						}
						inTree = true;
					}
				}
				if(!inTree) {
					this.children.add(new SuffixTreeNode(substring.substring(match, substring.length()),this));
				}
			}
		}
	}
	public void displayNode() {
		// TODO Auto-generated method stub
		//System.out.println("Children Size: " + this.children.size());
		for(SuffixTreeNode node: this.children) {
			System.out.println(node.depth + " " + node.label);
			node.displayNode();
			
		}
		for(SuffixTreeNode node: this.children) {
			//node.displayNode();
			//System.out.println("----");
		}

	}
	public int compareTo(SuffixTreeNode arg0) {
		// TODO Auto-generated method stub
		return this.label.compareTo(((SuffixTreeNode)arg0).label);
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return this.label.compareTo(((SuffixTreeNode)o).label);
	}

}
