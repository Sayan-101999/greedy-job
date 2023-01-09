package cxom.jsp.exam;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

class Solution {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the no of jobs");
		int n = sc.nextInt();
		
		int[] startTime  = new int[n];
		int[] endTime = new int[n];
		int[] profit = new int[n];
		
		for(int i = 0; i<n; i++) {
			//sc.next();
			System.out.println("Enter Start time");
			int stTime = sc.nextInt();
			System.out.println("Enter End time");
			int eTime = sc.nextInt();
			System.out.println("Enter profit");
			int prfit = sc.nextInt();
			startTime[i] = stTime;
			endTime[i] = eTime;
			profit[i] = prfit;
		}
		
		Solution solution = new Solution();
		int maxProfit = solution.jobScheduling(startTime, endTime, profit);
		//System.out.println(solution.jobScheduling(startTime, endTime, profit));
		int count = 0;
		int sum = 0;
		if(profit[0]==maxProfit ||profit[1]==maxProfit || profit[2]==maxProfit ) {
			count = 2;
		}
		else if(profit[0]+profit[1]==maxProfit || profit[1]+profit[2]==maxProfit || profit[0]+profit[2]==maxProfit ) {
			count =1;
		}
		else count = 0;
		
		for(int i=0;i<n;i++) {
			sum+=profit[i];
		}
		System.out.println("The number of tasks and earnings available for others");
		System.out.println("Tasks: "+count);
		System.out.println("Earnings: "+(sum-maxProfit));
		
	}

	public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
		int n = profit.length;
		Job[] jobs = new Job[n];
		for (int i = 0; i < startTime.length; i++) {
			jobs[i] = (new Job(startTime[i], endTime[i], profit[i]));
		}

		int dp[] = new int[jobs.length];
		Arrays.sort(jobs, (a, b) -> (a.end - b.end));

		dp[0] = jobs[0].profit;
		for (int i = 1; i < jobs.length; i++) {
			dp[i] = Math.max(jobs[i].profit, dp[i - 1]);
			for (int j = i - 1; j >= 0; j--) {
				if (jobs[j].end <= jobs[i].start) {
					dp[i] = Math.max(dp[i], jobs[i].profit + dp[j]);
					break;
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for (int val : dp) {
			max = Math.max(val, max);
		}
		return max;
	}

	class Job {
		int start, end, profit;

		public Job(int s, int e, int p) {
			this.start = s;
			this.end = e;
			this.profit = p;
		}
	}
}