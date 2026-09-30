import java.util.InputMismatchException;
import java.util.Scanner;
//implementation of stack using array
class ImpArray{
    int n;
    int stack[];
    int top = -1;


    ImpArray(int size){
        this.n = size;
        this.stack = new int[n];
    }


    void push(int x){
        if(top == n-1){
            System.out.println("Stack Overflow");
        }
        else{
            top++;
            stack[top] = x;
        }
    }


    void pop(){
        if(top == -1){
            System.out.println("Stack Underflow");
        }
        else{
            top--;  
        }
    }
 
    void peek(){//returns the top element of the stack
        if(top == -1){
            System.out.println("Stack is empty");
        }
        else{
            System.out.println("Element at top: " + stack[top]);
        }
    }
 
    void display(){
        if(top == -1){
            System.out.println("Stack is empty");
        }
        else{
            System.out.println("Elements in stack: ");
            for(int i=top; i>=0; i--){
                System.out.println(stack[i]);
            }
        }
    }
 
    void evaluatePostfix(String exp){
        top = -1; // Reset stack for new evaluation
       
        // Check for empty expression
        if (exp == null || exp.trim().isEmpty()) {
            System.out.println("Invalid postfix expression: Empty input");
            return;
        }
        String token = ""; // to store multi-digit numbers
        for(int i=0; i<exp.length(); i++){
            char c = exp.charAt(i);
           
            if(Character.isDigit(c)){
                token = token + c; // accumulate digits
            }
            else if(c == ' '){
                // If we have accumulated digits, push them
                if(!token.isEmpty()){
                    push(Integer.parseInt(token));
                    token = "";//again empty token
                }
            }
            else if(c == '+' || c == '-' || c == '*' || c == '/' || c == '^'){
                 // Need at least two operands
                if (top < 1) {
                    System.out.println("Invalid postfix expression: Not enough operands");
                    top = -1;
                    return;
                }
                // Process operator
                int val1 = stack[top];//store the top value in variable 1
                pop();//then delete it
                //similarly for next element
                int val2 = stack[top];
                pop();
               
                    switch(c){
                        case '+':
                            push(val2 + val1);
                            break;
                        case '-':
                            push(val2 - val1);
                            break;
                        case '*':
                            push(val2 * val1);
                            break;
                        case '/':
                            if (val1 == 0) {
                                System.out.println("Cannot divide by zero");
                                top = -1;
                                return;
                            }
                            push(val2 / val1);
                            break;
                        case '^':
                            int result = 1;
                            for(int j=0; j<val1; j++){
                                result = result * val2;
                            }
                            push(result);
                            break;
                    }
               
            }
            else{
                System.out.println("Invalid character in postfix expression: " + c);
                top = -1;
                return;
            }
        }


        if(!token.isEmpty()){
            push(Integer.parseInt(token));
        }


        if(top != 0){
            System.out.println("Invalid postfix expression");
            top = -1;
            return;
        }


        System.out.println("Result of postfix expression: " + stack[top]);
        pop();
    }
    void reverseStack(String str){
        top = -1; // Reset stack for new evaluation
        for(int i=0; i<str.length(); i++){
            push(str.charAt(i));
        }
        System.out.print("Reversed string: ");
        while(top != -1){
            System.out.print((char)stack[top]);//// Print top char
            pop();// Remove top char
        }
        System.out.println();
    }
}


public class PostfixEvaluation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
     
        ImpArray s = new ImpArray(100);
        int choice=0;
        do {
            try {
                System.out.println("Enter your choice:");
                System.out.println("1.Evaluate postfix expression");
                System.out.println("2.Reverse the string");
                System.out.println("3.Exit");
                choice = sc.nextInt();
                sc.nextLine();
                switch(choice) {
                    case 1:
                        System.out.println("Enter the postfix expression");
                        String exp = sc.nextLine();
                        s.evaluatePostfix(exp);
                        break;
                    case 2 :
                        System.out.println("Enter the string you want to reverse");
                        String str = sc.nextLine();
                        s.reverseStack(str);
                        break;
                    case 3:
                        System.out.println("Exiting the program");
                        break;
                    default:
                        System.out.println("Enter valid choice!");
                }
               
            }catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                sc.nextLine(); // Clear the invalid input
            }
        }while(choice!=3);
        sc.close();
    }
}