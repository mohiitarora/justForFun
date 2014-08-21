//Question: https://oj.leetcode.com/problems/reverse-words-in-a-string/


class ReverseWords {
    public String reverseWords(String s) {
	if (s == null || s.length() == 0 || s.equals(" "))
	    return "";
	
	if(s.length() == 1 && !s.equals(" "))
	    return s;
	
 
	// Split string
	String[] strArray = s.split(" ");
	StringBuilder finalString = new StringBuilder();
	for (int i = strArray.length - 1; i >= 0; i--) 
	    if (!strArray[i].equals(""))
		finalString.append(strArray[i]).append(" ");
	
	return finalString.length() == 0 ? "" : finalString.substring(0, finalString.length() - 1);
    }
} 
