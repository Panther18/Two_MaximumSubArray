
public class MaxSum {
	int[] arr;
	int[][] mid;
	int leastElement = -1000000;
	// Declare the array
	MaxSum(int[] a){
		arr = a;
		mid = new int[2][3];
	}
	
	// Start
	public void start(){
		int[][] a = doubleMaxSum(arr, 0, arr.length - 1);
		System.out.println("First maximum sum: " + a[0][0] + ": " + a[0][1] + "-" + a[0][2]);
		System.out.println("Second maximum sum: " + a[1][0] + ": " + a[1][1] + "-" + a[1][2]);
	}
	
	public int[][] doubleMaxSum(int[] a , int start, int end){
		int[][] ret = new int[2][3];
		int[][] left = new int[2][3];
		int[][] right = new int[2][3];
		
		// Return if there is single element
		if(start == end){
			ret[0][0] = a[start];
			ret[0][1] = start;
			ret[0][2] = start;
			
			ret[1][0] = leastElement;

			return ret;
		}
		int middle = (start + end) / 2;
		left = doubleMaxSum(a, start, middle);
		right = doubleMaxSum(a, middle + 1, end);
		
		// Find max suffix sum of A[start...end]
		int LMax = a[middle];
		int LSum = a[middle];
		int leftIndex = middle;
		for(int count = middle -1; count >= start; count--){
			LSum += a[count];
			if(LSum > LMax){
				LMax = LSum;
				leftIndex = count;
			}
		}
		
		
		int LMax1 = a[middle];
		int LSum1 = a[middle];
		int leftIndex1 = middle;
		for(int count = middle -1; count > start; count--){
			LSum1 += a[count];
			if(LSum1 > LMax1){
				LMax1 = LSum1;
				leftIndex1 = count;
			}
		}
		
		int RMax = a[middle + 1];
		int RSum = a[middle + 1];
		int rightIndex = middle + 1;
		for(int count = middle + 2; count <= end; count++){
			RSum += a[count];
			if(RSum > RMax){
				RMax = RSum;
				rightIndex = count;
			}
		}
		
		int RMax1 = a[middle + 1];
		int RSum1 = a[middle + 1];
		int rightIndex1 = middle + 1;
		for(int count = middle + 2; count < end; count++){
			RSum += a[count];
			if(RSum1 > RMax1){
				RMax1 = RSum1;
				rightIndex1 = count;
			}
		}
		
		int middleMax = LMax + RMax;
		// Create the middle array
		mid[0][0] = middleMax;
		mid[0][1] = leftIndex;
		mid[0][2] = rightIndex;
		
		int middleMax1;
		if(LMax1 > RMax1){
			middleMax1 = RMax + LMax1;
			mid[1][0] = middleMax1;
			mid[1][1] = leftIndex1;
			mid[1][2] = rightIndex;
		}
		else{
			middleMax1 = LMax + RMax1;
			mid[1][0] = middleMax1;
			mid[1][1] = leftIndex;
			mid[1][2] = rightIndex1;
		}
		// Find the two largest sub-arrays
		// If both the left sided are greater
		
		if(left[0][0] > right[0][0] && left[0][0] > mid[0][0]){
			if(left[1][0] > right[0][0] && left[1][0] > mid[0][0]){
				return left;
			}
			else if(right[0][0] > mid[0][0]){
				copy(left, 1, right, 0);
				return left;
			}
			else{
				copy(left, 1, mid, 0);
				return left;
			}
		}
		
		else if(right[0][0] > mid[0][0]){
			if(right[1][0] > left[0][0] && right[1][0] > mid[0][0]){
				return right;
			}
			else if(left[0][0] > mid[0][0]){
				copy(right, 1, left, 0);
				return right;
			}
			else{
				copy(right, 1, mid, 0);
				return right;
			}
		}
		// If middle is greater than both left and right
		else{
			copy(ret, 0, mid, 0);
			if(mid[1][0] > left[0][0] && mid[1][0] > right[0][0]){
				copy(ret, 1, mid, 1);
				return ret;
			}
			else if(left[0][0] > right[0][0]){
				copy(ret, 1, left, 0);
				return ret;
			}
			else{
				copy(ret, 1, right, 0);
				return ret;
			}
		}
	}
	
	// Copy the 3 columns of b[row2] to a[row1]
	public void copy(int[][] a, int row1, int[][] b, int row2){
		a[row1][0] = b[row2][0];
		a[row1][1] = b[row2][1];
		a[row1][2] = b[row2][2];
	}
}