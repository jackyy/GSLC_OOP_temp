import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static class Connection{
        public static ArrayList<String[]>  read(String nama_file){
            ArrayList<String[]> firstColumn = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(nama_file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] columns = line.split(",");
                    firstColumn.add(columns);
                }
            } catch (Exception e) {
                System.out.println("file tidak ada");
            }

            return firstColumn;
        }
        public static void write(String nama_file, String out) {

            try{
                FileWriter file = null;
                PrintWriter print = null;

                file = new FileWriter(nama_file, true);
                print = new PrintWriter(file, true);

                print.println(out);

                file.close();
                print.close();
            }catch(IOException e){
                System.out.println("gagal :<");
            }
        }
    }

    public static class model{
        static ArrayList<String[]> temp_list;
        public static void read(String nama_file, Connection conn){
            temp_list = conn.read(nama_file);
        }
    }

    public static class User extends model{
//        ArrayList<String> nama;
//        ArrayList<String> nim;
//        ArrayList<int> no_team;
        String file_name = "user.csv";
        public User(Connection conn){
            super.read(file_name, conn);


            for(String[] a : temp_list){
                for(String b : a){
                    //dari temp_list dibagi nama nim no_team
                }
            }

        }
    }
    interface Repository{
        void find(String filter, String[] condition, boolean join, String join_file, Connection conn);
    }

    public static class UserRepository implements Repository{
        String nama_file1 = "user.csv";
        String nama_file2 = "team.csv";
        public void find(String filter, String[] condition, boolean join, String join_file, Connection conn){
            User list_of_user = new User(conn);
            /*
                Nanti list of user isinya tuh
                    ArrayList<String> nama;
                    ArrayList<String> nim;
                    ArrayList<String> no_team;

                    terus tinggal filter sesuai keiginian
             */

        }
        public void find_one(String filter, String[] condition, boolean join, String join_file, Connection conn){
            User list_of_user = new User( conn);
            /*
                untuk find_one print sekali
            */
        }
        public void insert(String[] print, Connection conn){
            /*
                validasi terus dari print
            */
            String print_2 = print[0] + ',' + print[1] + ',' + print[2];
            conn.write(nama_file1, print_2);
        }
    }


    public static void main(String[] args) {
        Connection conn = new Connection();
        UserRepository UserRepo = new UserRepository();

        // filter
        String[] condition = {"=", "kevin"};
        UserRepo.find("name", condition, true, "Team", conn);

        // insert
        String[] input = {"2602069596", "Jack", "777"};
        UserRepo.insert(input, conn);
    }

}