import java.util.Scanner;
class Job{
	String jobId;
	int deadline;
	int profit;
	Job(){}
	Job(String jobId,int deadline,int profit){
		this.jobId=jobId;
		this.deadline=deadline;
		this.profit=profit;		
	}
}
 
class JobScheduling{
	int count;
    Job[] job;//array of jobs

    JobScheduling(int count) {
        this.count = count;
        job = new Job[count];// Initialize the job array 
    }
	//accept the details
	void accept(Scanner sc) {
		for(int i = 0;i<count;i++) {
			System.out.print("Enter the JobID:");
			String jobId = sc.nextLine();
            if(jobId.isEmpty()) {
                System.out.println("JobID cannot be empty. Please enter a valid JobID.");
                i--; // Decrement i to repeat this iteration
                continue; 
            }
            boolean isDuplicate=false;
            for (int j = 0; j < i; j++) {
                if (job[j].jobId.equals(jobId)) {
                    System.out.println("JobID must not be duplicate. Please enter a different JobID.");
                    i--; // Decrement i to repeat this iteration
                    isDuplicate=true;
                    break;
                }
            }
            if(isDuplicate) {
                continue; // Skip the rest of the loop and repeat this iteration
            }

			System.out.print("Enter the deadline:");
			int deadline = sc.nextInt();
            if(deadline <= 0) {
                System.out.println("Deadline must be a positive integer. Please enter a valid deadline.");
                i--; 
                continue; 
            }
            if (deadline > count) {
                System.out.println("Deadline cannot be greater than number of jobs");
                i--;
                continue;   
            }

			System.out.print("Enter the profit:");
			int profit = sc.nextInt();
			if(profit < 0) {
                System.out.println("Profit must be a non-negative integer. Please enter a valid profit.");
                i--; 
                continue; 
            }
			job[i]=new Job(jobId,deadline,profit);
			sc.nextLine();
		}
	}
	//display the details
	void display() {
        System.out.println("Job Details:");
		for(int i = 0;i<count;i++) {
			System.out.println("JobID:"+ job[i].jobId);
			System.out.println("Job deadline:"+ job[i].deadline);
			System.out.println("Job profit:"+ job[i].profit);
            System.out.println("-------------------------");			
		}
	}
	//return the maximum deadline by using insertion sort
	int return_max_deadline(){
		int n = count;
		for(int i=1; i<n;i++){
		    Job key = job[i];
            int j = i-1;
            while(j>=0 && key.deadline < job[j].deadline){
                job[j+1] = job[j];
                j--;
            }
            job[j+1] = key;
		}
		return job[n-1].deadline;
	}
	//return the jobs sorted by profit in descending order
	Job[] sorted_profit() {	
		int n = count;

		for(int i=1;i<n;i++){
			Job key = job[i];
            int j = i-1;

            while(j>=0 && key.profit > job[j].profit){
                job[j+1] = job[j];
                j--;
            }

            job[j+1] = key;
          
		}
		return job;
	}
    int size;
    Job[] schedule;
	//job scheduling function
	void job_scheduling() {
        size = return_max_deadline();// Get the maximum deadline to determine the size of the schedule array  
		Job array[]=sorted_profit();// Get the jobs sorted by profit in descending order

        schedule = new Job[size];// Create an array to hold the scheduled jobs
        // Initialize the schedule array with empty jobs
        for (int i = 0; i < size; i++) {
            schedule[i] = new Job();
        }

        for (int j = 0; j < count; j++) {
            int deadline = array[j].deadline;

            for (int i=deadline-1 ; i>= 0 ; i--) {

                if (schedule[i].jobId == null) {

                    schedule[i].jobId = array[j].jobId;
                    schedule[i].deadline = array[j].deadline;
                    schedule[i].profit = array[j].profit;

                    break;
                }
            }
        }

        System.out.println("Scheduled Jobs:");

        for (int i = 0; i < size; i++) {
            if (schedule[i].jobId != null) {
                System.out.println("Slot " + (i + 1) +" : JobID: " + schedule[i].jobId +" Profit = " + schedule[i].profit);
            }
        }

	}
    int calculate_profit() {
        int totalProfit = 0;
        for (int i = 0; i < size; i++) {
            totalProfit += schedule[i].profit;
        }
        return totalProfit;
    }
}
public class JobSequencing {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of jobs:");
        int n = sc.nextInt();
        if(n <= 0) {
            System.out.println("Number of jobs must be a positive integer. Please enter a valid number.");
            return; // Exit the program if the input is invalid
        }
        sc.nextLine();

        JobScheduling js = new JobScheduling(n);
        js.accept(sc);
        js.display();
        js.job_scheduling();
        System.out.println("Total Profit: " + js.calculate_profit());
	}
}
