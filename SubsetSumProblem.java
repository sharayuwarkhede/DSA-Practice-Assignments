 //Subset Sum Problem
import java.util.ArrayList;
import java.util.Scanner;

class PS{
    //recurssive + backtracking implementation
    boolean found = false;
    void printSubset(int arr[], ArrayList<Integer> ans,int i,int sum,int target){
        if(i== arr.length){
            if(sum==target){
                System.out.println(ans);
                found = true;
            }
            return;
        }
        //include
        ans.add(arr[i]);
        printSubset(arr, ans, i + 1,sum+arr[i],target);
        //backtrack
        ans.remove(ans.size() - 1);
        //exclude
        printSubset(arr, ans, i + 1,sum,target);
    }
}


class SubsetSumProblem{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int n=0;
        int target=0;
        //take the size of array
        while (true) {
            try {
                System.out.print("Enter the size of array: ");
                n = sc.nextInt();
                // validation
                if (n <= 0) {
                    System.out.println("Invalid array size. Please enter a positive integer.");
                    continue;
                }
                break; // valid input, exit loop
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer for the size of the array.");
                sc.next(); // clear invalid input
            }
        }

        int arr[] = new int[n];

        //accepting non-negative elements
        System.out.println("Enter non-negative elements:");

            for (int i = 0; i < n; i++) {
                while (true) {
                    try {
                        arr[i] = sc.nextInt();
                        // validation
                        if (arr[i] < 0) {
                            System.out.println("Invalid input. Elements must be non-negative.");
                            continue;
                        }
                        break; // valid input, exit loop
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a valid positive integer for the array elements.");
                        sc.next(); // clear invalid input
                    }
                }  
            }

        while(true){
                try{
                //accepting target sum
                System.out.print("Enter target sum: ");
                target = sc.nextInt();
                // validation
                if (target < 0) {
                    System.out.println("Invalid target. Target must be non-negative.");
                    continue;
                }
                break; // valid input, exit loop

            }catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid positive integer for the target sum.");
                sc.next(); // clear invalid input
            }
        } 
        ArrayList<Integer> ans = new ArrayList<>();
        PS p = new PS();
        p.printSubset(arr, ans, 0, 0, target);
        if (!p.found) {
            System.out.println("No subset with the given target sum exists.");
        }   
    }
}


/*Enter the size of array:6
Enter non-negative elements:
25
55
556
155
4565
52
Enter target sum: 4617
[4565, 52] */