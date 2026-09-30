import java.util.*;
class Member{ //create a class for node
   int member_id;
   String name;
   String address;
   String position; //position could be member/secretary/president
   Member next;//pointer to next node
  
   Member (int id,String Name,String addr,String Position){
	member_id = id;
   	name = Name;
   	address = addr;
   	position = Position;
	next = null; //initialize next to null
   }  
}
class Code_club {//create a class for linked list

	Member head;
	Member ptr;
	int count=0;
	Code_club(){
		head=null;//list empty
    }
    void addMember(Scanner sc) {
		
		int member_id;
		String name;
		String address;
		String position;
		//input validation for member_id
		while(true){
			try{
				System.out.println("Enter member_id:");
   	            member_id=sc.nextInt();
   	            sc.nextLine();
		        if(member_id <= 0 || member_id >= 1000){
    				System.out.println("member_id must be positive and less than 1000.");
    				continue;
				}
				//Check for duplicate member_id
				boolean duplicate = false;
                ptr=head;
				while(ptr!=null) {
					if (ptr.member_id == member_id) {
                    duplicate = true;
					System.out.println("member_id already exists. Enter another member_id.");
                    break;//it will not check further
                    }
					ptr=ptr.next;
                }
				if(duplicate){
					continue;//again asks for the id
				}
				break;
			}catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid positive integer for member_id.");
				sc.nextLine(); // Clear the invalid input
			}
		}
		// Input validation for name  
   	    
        while(true){
			
				System.out.println("Enter Name:");
            	name = sc.nextLine().trim();
        
            	if(name.isEmpty()){
                	System.out.println("Name cannot be empty.");
                	continue;
            	}
        
            	if(!name.matches("[a-zA-Z ]+")){
                	System.out.println("Name should contain only alphabets.");
                	continue;
            	}
				break;
        	
		}
            
		// Input validation for address
		
   	    while(true){
			
				System.out.println("Enter Address:");
            	address = sc.nextLine().trim();

            	if(address.isEmpty()){
					System.out.println("Address cannot be empty.");
                	continue;
            	}
		    	break;
        	
		}
	
		// Input validation for position
		while(true){
			
				System.out.println("Enter Position (Member/Secretary/President):");
				position = sc.nextLine().trim();
				// Check if President already exists
				boolean presidentExists = false;
                ptr = head;
				while(ptr != null){
					if(ptr.position.equalsIgnoreCase("President")){
        				presidentExists = true;
        				break;
    				}
    			ptr = ptr.next;
	            }
				if(position.equalsIgnoreCase("President") && presidentExists){
    					System.out.println("President already exists.");
    					continue;
				}
				else if(position.equalsIgnoreCase("Member") ||
				   position.equalsIgnoreCase("Secretary") ||
				   position.equalsIgnoreCase("President"))
				{
				   break;
				}
				else{
					System.out.println("Invalid Position.");
				}
	    } 
   	
   	    Member temp = new Member(member_id,name,address,position);
   	
   	    if(head==null){
			head = temp;
   	    }
   	    else{
   	        ptr = head;
   	        while(ptr.next!= null) {
				ptr = ptr.next;
   	        }
			
   	        ptr.next=temp;
   	    }
		count++;
		System.out.println("Member details add successfully");

   }
    void display(){
		if(head==null) {
			System.out.println("The list is empty");
   		}
   		else {
			System.out.println("Total Members = " + count);
			System.out.println("-----------------------------");
   			ptr = head;
   			while(ptr!=null) {
   				System.out.println("Member ID: " + ptr.member_id);
   				System.out.println("Name: " + ptr.name);
   				System.out.println("Address: " + ptr.address);
   				System.out.println("Position: " + ptr.position);
   				ptr = ptr.next;
   			}
			System.out.println( " ");
   		}
   	}   
    void delete_node(Scanner sc){
		if(head==null){
			System.out.println("The list is empty");
   	    }
		else{
			int item;
			while(true){
    			try{
       				System.out.println("Enter member_id:");
        			item = sc.nextInt();
        			sc.nextLine();

        			if(item <= 0 || item >= 1000){
    					System.out.println("member_id must be positive and less than 1000.");
    					continue;
					}
        			break;
    			}
    			catch(InputMismatchException e){
        			System.out.println("Invalid member_id.");
        			sc.nextLine();
    			}
			}
			 
			ptr=head;
		
			if(head.member_id==item){//deletion of first node
            	head= ptr.next;
            	ptr.next = null;
				count--;
				System.out.println("Member deleted successfully.");
        	}
        	else{//deletion of node in between or end
            	Member prev = ptr = head;
            	while(ptr!=null && ptr.member_id!=item){
                	prev=ptr;
                	ptr=ptr.next;
            	}
            	if(ptr == null){
        			System.out.println("Member ID not found.");
    			}
    			else{
        			prev.next = ptr.next;
        			ptr.next = null;
        			count--;
        			System.out.println("Member deleted successfully.");
    			}
			}
		}	
	}
    void update(Scanner sc) {
		
	    if(head==null) {
   	    	System.out.println("The list is empty");
   	    }
		else{
			int item;
			while(true){
    			try{
        			System.out.println("Enter member_id:");
        			item = sc.nextInt();
        			if(item <= 0 || item >= 1000){
    					System.out.println("member_id must be positive and less than 1000.");
    					continue;
					}
        			break;
    			}
    			catch(InputMismatchException e){
        			System.out.println("Invalid member_id.");
        			sc.nextLine();
    			}
			}
			sc.nextLine(); // Clear the input buffer
	    	boolean found =false;
			ptr = head;
	    	while(ptr!=null){
	    		if(ptr.member_id==item){
	    			found = true;
					// Input validation for name
	    			while(true){        
        			    System.out.println("Enter the new name:");
        			    ptr.name = sc.nextLine().trim();
					
        			    if(ptr.name.isEmpty()){
        			        System.out.println("Name cannot be empty.");
        			        continue;
        			    }
        			    if(!ptr.name.matches("[a-zA-Z ]+")){
        			        System.out.println("Name should contain only alphabets.");
        			        continue;
        			    }
						break;
        			}
				// Input validation for address
					while(true){
						System.out.println("Enter the new address:");
        	    		ptr.address = sc.nextLine().trim();

        	    		if(ptr.address.isEmpty()){
							System.out.println("Address cannot be empty.");
        	        		continue;
        	    		}
			    		break;
        			}
				// Input validation for position
					while(true){
						System.out.println("Enterthe newPosition (Member/Secretary/President):");
						String newPosition = sc.nextLine().trim();
        				//ptr.position = sc.nextLine().trim();
						// Check if President already exists
						boolean presidentExists = false;
                		Member temp = head;
						while(temp != null){
							if(temp.position.equalsIgnoreCase("President")&& temp.member_id!=item){
								// If another member is already President, set presidentExists to true
        						presidentExists = true;
        						break;
    						}
    					temp = temp.next;
	            		}
						if(newPosition.equalsIgnoreCase("President") && presidentExists){
    						System.out.println("President already exists.");
    						continue;
						}
						else if(newPosition.equalsIgnoreCase("Member") ||
				   			newPosition.equalsIgnoreCase("Secretary") ||
        		   			newPosition.equalsIgnoreCase("President")){
							ptr.position = newPosition;//update position
				   			break;
						}
						else{
							System.out.println("Invalid Position.");
					    }
	    			}
	    			System.out.println("Member details updated successfully.");
	    			break;
	    		}
	    		ptr=ptr.next;
	    	}
			if(!found){//member not found
	    		System.out.println("Member with ID " + item + " is not present!!");
	    	}
        }
    }
}

public class ClubDataManagement{
	public static void main(String[] args){
		Scanner sc=new Scanner (System.in);
		Code_club c = new Code_club();
		
        try{
			int choice =0;
        	do{ //Make Menu Driven Program
		    	System.out.println("--------------Menu------------");
				System.out.println("1.Add new Club member details");
            	System.out.println("2.Display Club member details");
            	System.out.println("3.Remove details of club member");
            	System.out.println("4.Update details of club member");
				System.out.println("5.Exit");
            	System.out.println("Enter your choice:");
            	choice = sc.nextInt();
            	switch(choice){
            	    case 1://Call display function
       	    	        c.addMember(sc);
						break;
            	    case 2://Call insert function
            	        c.display();
            	        break;
            	    case 3://Call delete function
       	    	        c.delete_node(sc);
            	        break;
					case 4://Call update function
       	    	        c.update(sc);
            	        break;
					case 5:
						System.out.println("Exiting...");
            	        break;
            	    default:
						System.out.println("Enter valid choice");
            	}
            
        	}while(choice!=5);

		}catch(Exception e){
			System.out.println("Invalid input. Please enter a valid input.");
			sc.nextLine(); // Clear the invalid input
		}
		
    
}
}
