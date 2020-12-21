class Solution {
    public List<String> generatePalindromes(String s) {
        
        List<String> result = new ArrayList<>();

        if(s == null && s.length()==0)
        	return result;

        generatePalindromes(s.toCharArray(), 0, result);
        return result;
    }


    public List<String> generatePalindromes(char []ch, int idx, List<String> result) {

    		if(ch.length == idx) {

    			if(isPalindrome(ch))
    				result.add(new String(ch));

    			return;
    		}

    		for(int i=idx; i<ch.length; ++i) {
    			swap(ch, idx, i);
    			generatePalindromes(ch, idx+1, result);
    			swap(ch, idx, i);
    		}
    }

    private boolean isPalindrome(char []ch) {
    	int left=0;
    	int right=ch.length-1;

    	while(left < right) {
    		if(ch[left++]!=ch[right--])
    			return false;
    	}
    	return true;
    }

    private void swap(char []ch, int f, int t) {
    	char temp = ch[f];
    	ch[f] = ch[t];
    	ch[t] = temp;
    }
}