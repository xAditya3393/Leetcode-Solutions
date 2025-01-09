/*

Brute Force 
Repeated multiplication
x^y => x multiplied (y-1) times => O(y)

There are two ways of defining this problem in a recurrance relational 

1. if recurrance relation is optimized (discarding constant work)
t(x,y) = x + t(x,y-1) => O(y)

2. if recurrance relation is not optimized (discarding constant work)
t(x,y) = t(x,y/2) + t(x,y-y/2)
       = 2*t(x,y/2), if y is even 
       = t(x,floor(y/2)) + t(x,ceil(y/2)), if y is odd 
        <= 2*t(x,ceil(y/2)) 
        >= 2*t(x,floor(y/2))

If the recursive tree is drawn, notice how at each level the number of subproblems are doubled 
Also the same subproblem is evaluated everytime, if the subproblem and its solution is not stored

Since the current recurrance relation doesnt account for storing subproblem solution and 
considers each subproblem an unique encountered subproblem
If y takes n levels to reach 1 => Time complexity = O(2^n)

In terms of number of bits in binary form of y
Therefore time complexity = O(2^n), where n is the number of bits in y's binary form 
Notice how the complexity which is defined on the number of multiplication(recursive function calls) occur with an approximation on t(x,y) = 2*t(x,y-1) 
the problem size is decreasing by one each time
t(2,3) -> t(2,1) + t(2,2) 3 - 11, 2 - 10, 1 - 01

Space 
Recursion - Maximum depth of recursive tree - O(logn)



Improvement 
To improve brute force solution -> we need to reduce the number of multiplications steps

Observe:
To get more number of multiplications at each stage, we need to change the way we divide the subproblem
t(x,y) = x*t(x,y-1), if y is odd
       = (t(x,y/2))^2, if y is even


Notice how both the improvement and brute force are based on the following property of exponent
x^(a+b) = x^(a) . x^(b)
the main difference is 
brute force - creates two subproblems at each stage 
    => in recursion - return mul(x,y/2)*mult(x,y/2)
    => in recurrance relation - addition of work done on the two subproblems 
improvement - creates on subproblem at each stage - squares a single subproblem => in recursion - return Math.pow(mul(x,y/2), 2)


Improvement's recurrance relation (discarding constant work)
t(x,y) = t(x,y/2), if y is even  
       = x*t(x,y/2) -> t(x,y/2), if y is odd (multiplying x is to the result of t(x,y/2) is constant work)
Time complexity = O(logy)

In terms of number of bits in binary form of y - y/2 subproblem is an equivalent operation of right shifting by 1 
Time conplexity - O(n) where n is number of bits of y 

Space 
Iteration - O(1)
Recursion - Maximum depth of recursive tree - O(logn)

For negative powers/fractional numbers - we can resolve it before sending it to common function
mult(1/2,4) = 1/mult(2,4)
mult(2,-4) = 1/mult(2,4)


Implementation 
We implement the improvement with iterative squaring 
ex: 2^(4) -> pow(2*2, 4/2) -> pow(4*4, 2/2) -> return 16 as we reach 1 on exponent 
more precisely 2^(4) = (2^(2))^2 -> ((2^(1))^2)^2


For this problem, there is a test case which fails while implementaion with base function signature pow(double x, int y)
x = 2.00000
n = -2147483648

This happens because while negative n conversaion where n = n*(-1) / -n => leads to overflow as max int value is 2^31 - 1 = 2147483647
conversion to long is required here to preserve the positive power conversion 

*/


class Solution {
    public double myPow(double x, int n) {
        long N = n;  //long conversion due to overflow 
        if(n < 0){
            x = 1/x;
            N = -N;
        } 
        
        return multiply(x,N);
        //return multiplyRecursion(x, N);
    }
    
    //iteration
    /*
        dry run
              
        2^5 -> multiply(2,5)
        multiply(2,5) -> x = 4, result = 2, y = 2
        multiply(4,2) -> x = 16, result = 2, y = 1
        multiply(16,1) -> x = 256, result = 32, y = 0

    */      
    private double multiply(double x, long y){
        
        double result = 1;
        
        while(y > 0){
            
            if(y%2 != 0){
                result *= x;
            }
            
            x *= x;
            y >>= 1;
        }
        
        return result;
    }
    
    
    private double multiplyRecursion(double x, long y){
        if(y == 0) return 1;
        if(y == 1) return x;
        
        
        if(y%2 == 1){
            return x*multiplyRecursion(x, y-1); 
        }
        
        
        return multiplyRecursion(x*x, y/2);
        
     }
}