/*
 Observe how operators and numbers need to be evaluated
 Use stack to store numbers and process when operator is encountered
 Time O(n) Space O(n) 
 */

public int evalRPN(String[] tokens) {
    Deque<Integer> numbers = new LinkedList<>();
    
    int result;
    
    for(String token: tokens){
        if(token.length() == 1 && ("+-*/").contains(token)){
            int one = numbers.pop(), two = numbers.pop();
            switch(token){
                case "+":
                    result = one+two;
                    break;
                case "-":
                    result = two-one;
                    break;
                case "*":
                    result = one*two;
                    break;
                case "/":
                    result = two/one;
                    break;
                default:
                    throw new IllegalArgumentException(); 
            }
        }
        else{
            result = Integer.parseInt(token);
        }
        numbers.push(result);
    }
    
    return numbers.peek();
}