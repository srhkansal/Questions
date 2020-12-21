267. Palindrome Permutation II
Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

Example 1:
Input: "aabb"
Output: ["abba", "baab"]

Example 2:
Input: "abc"
Output: []


class Solution {
    public List<String> generatePalindromes(String s) {
        
        List<String> result = new ArrayList<>();

        if( (s == null && s.length()==0) || !canPermutePalindrome(s) )
            return result;

        generatePalindromes(s.toCharArray(), 0, result);
        return result;
    }

    public void generatePalindromes(char []ch, int idx, List<String> result) {

        if(ch.length == idx) {
            if(isPalindrome(ch))
                result.add(new String(ch));
            return;
        }

        Set<Character> set = new HashSet<>();
        for(int i=idx; i<ch.length; ++i) {
            if(set.contains(ch[i]))
                continue;
            
            set.add(ch[i]);
            swap(ch, idx, i);
            generatePalindromes(ch, idx+1, result);
            swap(ch, idx, i);
        }
    }

    private boolean canPermutePalindrome(String s) {
        int[] map = new int[128];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
            if (map[s.charAt(i)] % 2 == 0)
                count--;
            else
                count++;
        }
        return count <= 1;
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