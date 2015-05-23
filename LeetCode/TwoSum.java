//Question: https://leetcode.com/problems/two-sum/

import java.util.HashMap;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> solutionMap = new HashMap<Integer, Integer>();
        int[] solutionIndex = new int[2];
	for(int i = 0; i< nums.length; ++i){
	    if(solutionMap.containsKey(target - nums[i])){
		solutionIndex[0] = solutionMap.get(target - nums[i]) + 1;
		solutionIndex[1] = i + 1;
		return solutionIndex;
	    }
	    else{
		solutionMap.put(nums[i], i);
	    }
        }
	return solutionIndex;
    }
}