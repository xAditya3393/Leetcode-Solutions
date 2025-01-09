/*

Brute Force - sort all points with their distance  
Time - O(nlogn) Space - O(1), sort implementation details - https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html#sort(int[])  
 

Improvement

1. Priority Queue - place k elements -> for k+1, poll largest & add current 

Observation
assuming we have k elements, the operation for k+1 will be remove largest -> add k+1 => Max Priority Queue for maintaining k elements

Time - O(nlogk) Space - O(k) 
add all n elements, while maintaining k elements in priority queue 
removing an element, in this case max in queue - O(logk)
inserting an element, in this case new element - O(logk)
where logk is the height of tree with k elements

Implementations 
- Priority Queue - Using an anonymous comparator class implementation
- Priority Queue - Using an anonymous comparator class + distance method implementation
- Priority Queue - Using a comparator class + distance method implementation
- Priority Queue - Using a comparable class implementation



2. Binary Search

Intuition - usually performed on a sorted data while eliminating half data based on our target  

Observation 
We have two options here, our sorted set of data can be
list of all points - Not viable since we have two coordinates to handle
List od all distances - easier, one data point + constant time for calculation

Algorithm
- Calculate distance for all points and note min and max distances
- Do binary seach over min and max distance -> filter closer (less) and farther(greaterand equal) points 
- Check if the closer points against number of required points 
=> less -> include all closer points in final result + update number of required points, mid and search space (in this case farther points)
=> equal or more -> update number of required points, mid and search space (in this case closer points)

Time - O(n) Space - O(n) 
Average analysis -> iterating over search space terminates in logn steps
(n + n/2 + n/4 + .... + 1)(logn terms) = n(1 + 1/2 + 1/4 + .... + 1/n)(geometric series) = 2 - 1/2^n, where 1/2^n tends to zero as n tends to infinity 
=> O(n)
O(n) space for storing the filtered points at each stage


3. QuickSelect

Observation - Instead of maintaining extra space for filtering data, we can do the segregation in the input array

Intuition - Quickselect is similar to Quicksort -> partition data until required number of elements are found!
 

Algorithm 
- define search space -> start and end indices 
- find a random pivot in the given search space and find the correct position of pivot based on distance 
-> all elements on left have lesser distance & have right have greater distance
- return the position of pivot 
- check if number of elements less than pivot equals desired number of elements, if not update search space accordingly
-> update start if less, update end if more


Time - O(n) Space - O(1) 
Average analysis -> iterating over search space terminates in logn steps
(n + n/2 + n/4 + .... + 1)(logn terms) = n(1 + 1/2 + 1/4 + .... + 1/n)(geometric series) = 2 - 1/2^n, where 1/2^n tends to zero as n tends to infinity 
=> O(n)
O(1), no recursion is used as opposed to quick sort 

Proof:
https://en.wikipedia.org/wiki/1/2_%2B_1/4_%2B_1/8_%2B_1/16_%2B_%E2%8B%AF
https://www.math.toronto.edu/mathnet/questionCorner/geomsum.html


More Information:
https://docs.oracle.com/javase/7/docs/api/java/util/Collections.html#reverseOrder(java.util.Comparator)
https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html#reverseOrder--
https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html#compareTo-T-
https://www.baeldung.com/java-comparator-comparable
https://stackoverflow.com/questions/4108604/java-comparable-vs-comparator

https://leetcode.com/problems/k-closest-points-to-origin/solution/

*/

//Quickselect 
class Solution {
    
    public int[][] kClosest(int[][] points, int k) {
        return quickSelect(points, k);
    }
    
    
    public int[][] quickSelect(int[][] points, int k){
        
        int left = 0, right = points.length-1;
        int pivotIndex =  points.length;
        
        while(pivotIndex != k){

            pivotIndex = partition(points, left, right);            
            if(pivotIndex < k){
                left = pivotIndex;
            }
            else{
                right = pivotIndex - 1;
            }
            
        }
        
        return Arrays.copyOf(points, k);
    }
    
    
    private int partition(int[][] points, int left, int right){
        int[] pivotPoint = choosePivotPoint(points, left, right);
        int pivotDist = squaredDistance(pivotPoint);
        
        while(left < right){
            if (squaredDistance(points[left]) >= pivotDist) {
                int[] temp = points[left];
                points[left] = points[right];
                points[right] = temp; 
                right--;
            } else {
                left++;
            }
        }
        
        if (squaredDistance(points[left]) < pivotDist) left++;
        return left;
        
    }
    
    private int[] choosePivotPoint(int[][] points, int left, int right) {
        return points[left + (right - left) / 2];
    }
    
    private int squaredDistance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
    
}


//Binary Search
class Solution {
    
    public class pointInfo {
        
        public int[] point;
        public double distance;
        
        pointInfo(int[] p){
            point = p;
            distance = calculateDistance();
        }
        
        private double calculateDistance(){
            return Math.sqrt(Math.pow(this.point[0], 2) + Math.pow(this.point[1], 2));
        }
        
    }
    
    
    public int[][] kClosest(int[][] points, int k) {
        
        List<pointInfo> selectionSpace = new ArrayList<>();
        double minDistance = Double.MAX_VALUE, maxDistance = Double.MIN_VALUE;
        
        for(int[] point: points){
            
            pointInfo current = new pointInfo(point);
            
            selectionSpace.add(current);
            minDistance = Math.min(minDistance, current.distance);
            maxDistance = Math.max(maxDistance, current.distance);
        }
        
        List<List<pointInfo>> divisionAroundMid = new ArrayList<List<pointInfo>>();
        List<pointInfo> kPointsWithDistance = new ArrayList<>();
        
        while(k > 0){
            double midDistance = (minDistance + maxDistance + 1)/2;
            
            divisionAroundMid = convergeToResult(selectionSpace, midDistance, k);
            
            if(divisionAroundMid.get(0).size() <= k){
                kPointsWithDistance.addAll(divisionAroundMid.get(0));
                k -= divisionAroundMid.get(0).size();
                selectionSpace = divisionAroundMid.get(1);
                minDistance = midDistance;
            }
            else{
                selectionSpace = divisionAroundMid.get(0);
                maxDistance = midDistance-1;
            }
        }
        
        
        int[][] resultPoints = new int[kPointsWithDistance.size()][];
        int index = 0;
        
        for(pointInfo pi: kPointsWithDistance){
            resultPoints[index++] = pi.point;
        }
        
        return resultPoints;
    }
    
    public List<List<pointInfo>> convergeToResult(List<pointInfo> remaining, double midDistance, int k){
        
        List<pointInfo> lessThanMid = new ArrayList<>();
        List<pointInfo> greaterOrEqualToMid = new ArrayList<>();
        
        for(pointInfo pi: remaining){
            if(pi.distance < midDistance) lessThanMid.add(pi);
            else greaterOrEqualToMid.add(pi);
        }
        
        List<List<pointInfo>> separate = new ArrayList<List<pointInfo>>();
        separate.add(lessThanMid);
        separate.add(greaterOrEqualToMid);
        
        return separate;
    }
   
}

//Priority Queue - Using an anonymous comparator class implementation
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(k, new Comparator<int[]>(){
            
             private double calculateDistance(int[] point){
                return Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2));
            }
            
            @Override
            public int compare(int[] one, int[] two){
                
                double distanceOne = calculateDistance(one);
                double distanceTwo = calculateDistance(two);
                
                if(distanceOne > distanceTwo) return -1;
                else if(distanceOne < distanceTwo) return 1;
                else return 0;
                
            }
        });
        
        
        for(int[] point: points){
            
            pq.add(point);
            if(pq.size() == k+1) pq.poll();
        }
        
        
        int[][] result = new int[k][];
        int index = 0;
        
        while(!pq.isEmpty()){
            result[index++] = pq.poll();
        }
        
        return result;
    }
    
   
}

//Priority Queue - Using an anonymous comparator class + distance method implementation 
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(k, new Comparator<int[]>(){
            
             private double calculateDistance(int[] point){
                return Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2));
            }
            
            @Override
            public int compare(int[] one, int[] two){
                
                double distanceOne = calculateDistance(one);
                double distanceTwo = calculateDistance(two);
                
                if(distanceOne > distanceTwo) return -1;
                else if(distanceOne < distanceTwo) return 1;
                else return 0;
                
            }
        });
        
        
        for(int[] point: points){
            
            pq.add(point);
            if(pq.size() == k+1) pq.poll();
        }
        
        
        int[][] result = new int[k][];
        int index = 0;
        
        while(!pq.isEmpty()){
            result[index++] = pq.poll();
        }
        
        return result;
    }
    
   
}


//Priority Queue - Using a comparator class + distance method implementation 
class Solution {
    
    public class Point {
        
        private int xCoordinate;
        private int yCoordinate;
        
        Point(int[] p){
            xCoordinate = p[0];
            yCoordinate = p[1];
        }
        
        public double calculateDistance(){
            return Math.sqrt(Math.pow(this.xCoordinate, 2) + Math.pow(this.yCoordinate, 2));
        }
        
        public int[] pointToArray(){
            int[] result = new int[2];
            result[0] = this.xCoordinate;
            result[1] = this.yCoordinate;
            
            return result;
        }
        
    }
    
    public static class PointComparator implements Comparator<Point>{
        
        @Override
        public int compare(Point one, Point two){

            double distanceOne = one.calculateDistance();
            double distanceTwo = two.calculateDistance();

            if(distanceOne > distanceTwo) return -1;
            else if(distanceOne < distanceTwo) return 1;
            else return 0;

        }
        
    }
    
    public int[][] kClosest(int[][] points, int k) {
        
        PointComparator pointComparator = new PointComparator();
        
        PriorityQueue<Point> pq = new PriorityQueue<Point>(k, pointComparator);
        
        
        for(int[] point: points){
            Point current = new Point(point);
            pq.add(current);
            if(pq.size() == k+1) pq.poll();
        }
        
        
        int[][] result = new int[k][];
        int index = 0;
        
        while(!pq.isEmpty()){
            result[index++] = pq.poll().pointToArray();
        }
        
        return result;
    }
    
   
}

//Priority Queue - Using a comparable class implementation
class Solution {
    
    public class Point implements Comparable<Point>{
        
        private int xCoordinate;
        private int yCoordinate;
        
        Point(int[] p){
            xCoordinate = p[0];
            yCoordinate = p[1];
        }
        
        private double calculateDistance(){
            return Math.sqrt(Math.pow(this.xCoordinate, 2) + Math.pow(this.yCoordinate, 2));
        }
        
        @Override
        public int compareTo(Point two){
            return Double.compare(this.calculateDistance(), two.calculateDistance());
        }
        
    }    

    public int[][] kClosest(int[][] points, int k) {
        
        PriorityQueue<Point> pq = new PriorityQueue<Point>(k, Collections.reverseOrder());
        
        
        for(int[] point: points){
            
            pq.add(new Point(point));
            if(pq.size() == k+1) pq.poll();
        
        }
        
        
        int[][] result = new int[pq.size()][];
        int index = 0;
        while(!pq.isEmpty()){
            Point current = pq.poll();
            
            int[] temp = new int[2];
            temp[0] = current.xCoordinate;
            temp[1] = current.yCoordinate;
            result[index++] = temp;
        }
    
        
        return result;
        
        
    }
    
}