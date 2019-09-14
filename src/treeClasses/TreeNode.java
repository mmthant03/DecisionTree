//Robert Dutile, Myo Min Thant
package treeClasses;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	
	private List<TreeNode> children = new ArrayList<>();
	
	private int branchValue; //value of the branch in parents test attribute.
	private String testAttribute; //value branching upon
	
	

	public TreeNode(String testAttribute, int branchValue) {
		this.branchValue = branchValue;
		this.testAttribute = testAttribute;
	}
	
	/*
	 * getter for testAttribute variable.
	 */
	public String getTestAttribute() {
		return testAttribute;
	}
	/*
	 * getter for branchValue variable
	 */
	public int getBranchValue() {
		return branchValue;
	}
	
	/*
	 * getter for children
	 */
	public List<TreeNode> getChildren() {
		return children;
	}
	
	/*
	 * function to create a new child node
	 */
	public void makeChild(String newAttribute, int newBranchValue) {
		TreeNode child = new TreeNode(newAttribute, newBranchValue);
		this.children.add(child);
	}
	
	/*
	 * function to add a pre-existing node to the list of children
	 */
	public void addChild(TreeNode newChild) {
		this.children.add(newChild);
	}

}
