public class Solution { // Alien Dictionary 269
    /**
     * @param words: a list of words
     * @return: a string which is correct order
     */
    public String alienOrder(String[] words) {
        // Write your code here
        int []indegree = new int[26];
        Map<Character, Set<Character>> graph = new HashMap<>();
        
        //build Graph
        buildGraph(words, indegree, graph);
        
        //fs - topological sort
        int totalChars = graph.size();
        Queue<Character> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for(Character key : graph.keySet()) {
            
            if(indegree[key - 'a'] == 0) {
                queue.offer(key);
                sb.append(key);
            }
        }
        
        while(!queue.isEmpty()) {
            
            Character cur = queue.poll();
            if(graph.get(cur) == null || graph.get(cur).size()==0) continue;
            
            for(Character nbr: graph.get(cur)) {
                indegree[nbr - 'a']--;
                
                if(indegree[nbr - 'a'] == 0) {
                    sb.append(nbr);
                    queue.offer(nbr);
                }
            }
        }
        return sb.length() == totalChars ? sb.toString() : "";
    }
    
    private void buildGraph(String[] words, int []indegree, Map<Character, Set<Character>> graph){
        for(int i=1; i<words.length; ++i) {
            String first = words[i-1];
            String second = words[i];
            int len = Math.min(first.length(), second.length());
            for(int j=0; j<len; ++j) {
                
                graph.putIfAbsent(first.charAt(j), new HashSet<>());
                graph.putIfAbsent(second.charAt(j), new HashSet<>());
                
                if(first.charAt(j)!=second.charAt(j)) {
                    Character in = first.charAt(j);
                    Character out = second.charAt(j);
                    
                    if( graph.get(in).contains(out) ) continue;
                    
                    graph.get(in).add(out);
                    indegree[out - 'a']++;
                    
                    break;
                }
            }
        }//for 
    }
}
