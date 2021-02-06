//314. Binary Tree Vertical Order Traversal
//Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).
//If two nodes are in the same row and column, the order should be from left to right.


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    //DFS
    int min = 0;
    int max = 0;
        
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root != null){
             Map<Integer, List<Integer>> map = new TreeMap<>();
             
            helper(root, map, 0,0);
           
            for(int i = min; i < max + 1; ++i) {
                res.add(map.get(i));
            }
            

           // return res;
        }
        return res;
    }
    
    private void helper(TreeNode root, Map<Integer, List<Integer>> map, int x, int y){
        if(root==null) 
            return;
        map.putIfAbsent(x, new ArrayList<>());
        min = Math.min(min, x);
        max = Math.max(max, x);
        map.get(x).add(root.val);
        
        helper(root.left, map, x-1, y+1);// it is y+1 for both left and right as we always traverse down
        helper(root.right, map, x+1, y+1);
    }
}
