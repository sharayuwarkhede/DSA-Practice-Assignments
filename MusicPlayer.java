
//Develop a Music Player
import java.util.*;
class Song{
    int id;
    String name;
    String artistName;

    Song prev;
    Song next;

    Song(int ID,String Name, String ArtistName){
        id= ID;
        name=Name;
        artistName=ArtistName;
        prev=null;
        next=null;
    }
}
class Playlist{
    Song head=null;
    Song ptr=null;
    Song current;//pointer for current playing song
    int count =0;

    Playlist(){
        add_song(101, "Believer", "Imagine Dragons");
        add_song(102, "Shape Of You", "Ed Sheeran");
        add_song(103, "Perfect", "Ed Sheeran");
        add_song(104, "Kesariya", "Arijit Singh");
        add_song(105, "Raataan Lambiyan", "Jubin Nautiyal");
        add_song(106, "Tum Hi Ho", "Arijit Singh");
    }
    //method to add hardcoded song
    void add_song(int id, String name, String artist)
    {
        Song temp = new Song(id,name,artist);

        if(head == null){
            head = temp;
            current = head;
            head.next = head;
            head.prev = head;
        }
        else{
            Song ptr = head;
            while(ptr.next != head){
                ptr = ptr.next;
            }
            ptr.next = temp;
            temp.prev = ptr;
            temp.next = head;
            head.prev = temp;
        }

        count++;
    }
    //method to add song at last
    void add_song(Scanner sc){
        int id;
        String name;
        String artistName;
        System.out.println("Details of song");
        //input validation for song id
        while(true){
            try{
                System.out.println("Enter ID of the Song:");
                id= sc.nextInt();
                sc.nextLine();
                if(id<=0){
                    System.out.println("SongID must be positive");
                    continue;
                }
                //check for duplicate
                boolean duplicate = false;
                ptr=head;
                if(head!=null){
                    do{
                        if(ptr.id==id){
                            duplicate=true;
                            System.out.println("Song ID already exists");
                            break;
                        }
                        ptr=ptr.next;
                    }while(ptr!=head);
                }
                if(duplicate){
					continue;//again asks for the id
				}
				break;
            }catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid positive integer  for SongID");
				sc.nextLine(); // Clear the invalid input
			}
        }
        //input validation for song name
        while(true){
            System.out.println("Enter Name of the Song:");
            name=sc.nextLine();

            if(name.isEmpty()){
                System.out.println("Song name cannot be empty.");
                continue;
            }
            break;
        }
        //input validation for song name
        while(true){
            System.out.println("Enter Artist name of the Song:");
            artistName=sc.nextLine();


            if(artistName.isEmpty()){
                System.out.println("Artist name cannot be empty.");
                continue;
            }
            break;
        }
 
        Song temp=new Song(id,name,artistName);

        if(head==null){
            head= temp;
            current = temp;
            head.prev=head;
            head.next=head;
        }
        else{
            ptr=head;
            while(ptr.next!=head){
                ptr=ptr.next;
            }
            ptr.next=temp;
            temp.prev=ptr;
            temp.next=head;
            head.prev=temp;    
        }
        System.out.println("Song added successfully!!!");
        count++;
    }
    //method to show whole playlist from first to last and total number of songs
    void show_playlist(){
        if(head==null){
            System.out.println("No song in playlist!!");
        }
        else{
            current_song();
            System.out.println("<<<<<<<< Your Playlist >>>>>>>>");
            System.out.println("Total Songs in playlist = " + count);
			System.out.println("-----------------------------");
            ptr=head;
            do{
                System.out.println("Song ID: "+ptr.id);
                System.out.println("Song Name: " +ptr.name);
                System.out.println("Artist: " + ptr.artistName);
                System.out.println("=================================");

                ptr=ptr.next;
            }while(ptr!=head);
            
        }
    }
    //method to show the currrent song
    void current_song(){
        if(current == null){
            System.out.println("No song is playing.");
            return;
        }

        System.out.println("=================================");
        System.out.println("<<<<<<<<<< NOW PLAYING >>>>>>>>>>");
        System.out.println(current.name + " - " + current.artistName);
        System.out.println("Song ID:"+ current.id);
        System.out.println("=================================");
    }
    //method to move song pointer forward 
    void play_next(){
        if(head == null){
            System.out.println("No song in playlist.");
            return;
        }
        else if (head.next==head){
            System.out.println("Only one song is in playlist!!");
            return;
        }
        
        current=current.next;
        current_song();
    }
    //method to move pointer to previous song0
    void play_previous(){
        if(head == null){
            System.out.println("No song in playlist.");
            return;
        }
        else if (head.prev==head){
            System.out.println("Only one song is in playlist!!");
            return;
        }
        
        current=current.prev;
        current_song();
    
    }
    //method to delete song
    void delete_song(Scanner sc){
        if(head==null){
            System.out.println("No song in playlist!!");
            return;
        }
        int item;
        while(true){
            try{
                System.out.println("Enter ID of the Song:");
                item= sc.nextInt();
                sc.nextLine();
                if(item<=0){
                    System.out.println("SongID must be positive");
                    continue;
                }
				break;
            }catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid positive integer for SongID.");
				sc.nextLine(); // Clear the invalid input
			}
        }
        if(head == current && head.next == head && current.id == item){
            head = null;
            current = null;
        }
        else if(current.id == item){
            if(current == head){
                head=head.next;
            }
            current.next.prev=current.prev;
            current.prev.next=current.next;
            current = current.next;
        }
        
        else{
            ptr=head;
            while(ptr.id!=item && ptr.next!=head){
                ptr=ptr.next;
            }
            if(ptr.id==item){
                ptr.next.prev=ptr.prev;
                ptr.prev.next=ptr.next;
                    
            }
            else{
                System.out.println("Song id not found");
                return;
            }
        }
        System.out.println("Song deleted successfully!!");
        count--;
    }
    //method to show reverse playlist
    void show_reverse(){
        if(head==null){
            System.out.println("No song in playlist!!");
        }
        else{
            System.out.println("<<<<<<<<<< Reverse playlist >>>>>>>>>>");
            ptr=head.prev;
            do{
                System.out.println("Song ID: "+ptr.id);
                System.out.println("Song Name: " +ptr.name);
                System.out.println("Artist: " + ptr.artistName);
                System.out.println("=================================");
                ptr=ptr.prev;
            }while(ptr!=head.prev);
        }
    }
    //method to search song and play it
    void search_and_play(Scanner sc){
        if(head==null){
            System.out.println("No song in playlist!!");
            return;
        }
        
        int item;
        while(true){
            try{
                System.out.print("Enter the songID:");
                item=sc.nextInt();
                sc.nextLine();
                if(item<=0){
                    System.out.println("SongID must be positive");
                    continue;
                }
				break;
            }catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid positive integer for SongID");
				sc.nextLine(); // Clear the invalid input
			}
        }
        ptr=head;
        do{
            if(ptr.id==item){
                current=ptr;
                System.out.println("Song Found!!!");
                System.out.println("=================================");
                System.out.println("<<<<<<<<<< Now Playing >>>>>>>>>>");
                System.out.println(current.name + " - " + current.artistName);
                System.out.println("=================================");
                return;
            }
            ptr=ptr.next;
        }while(ptr!=head); 

        System.out.println("Song not found");   
    }
}
public class MusicPlayer {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        Playlist p = new Playlist();
        while(true){
            try{
                int choice;
                do {
                    System.out.println("\n1. Add Song");
                    System.out.println("2. Show Playlist");
                    System.out.println("3. Play Next");
                    System.out.println("4. Play Previous");
                    System.out.println("5. Current Song");
                    System.out.println("6. Delete Song");
                    System.out.println("7. Show Reverse Playlist");
                    System.out.println("8. Search and Play the Song");
                    System.out.println("9. Exit");

                    System.out.print("Enter choice: ");
                    choice = sc.nextInt();

                    switch(choice) {

                        case 1://adding song 
                            p.add_song(sc);
                            break;

                        case 2://show whole playlist and total song
                            p.show_playlist();
                            break;

                        case 3://play the next song
                            p.play_next();
                            break;

                        case 4://play the previous song
                            p.play_previous();
                            break;

                        case 5://shows the current song
                            p.current_song();
                            break;

                        case 6://delete song
                            p.delete_song(sc);
                            break;

                        case 7://show the playlist in reverse 
                            p.show_reverse();
                            break;

                        case 8:
                            p.search_and_play(sc);
                            break;
                        case 9:
                            System.out.println("Thank You!");
                            break;

                        default:
                            System.out.println("Invalid Choice");
                    }

                } while(choice != 9);
                
            }catch (InputMismatchException e) {
				System.out.println("Invalid input.");
				sc.nextLine(); // Clear the invalid input
			}
        }
        
    }
    
}



