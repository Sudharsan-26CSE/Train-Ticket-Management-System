import java.util.*;
import java.io.*;
import java.nio.file.*;

public class TrainBookingSystem {

    // ===== User Class =====
    static class User {
        String username;
        String password;
        String email;
        String mobile;
        String generatedOTP;

        User(String username, String password, String email, String mobile) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.mobile = mobile;
            this.generatedOTP = null;
        }
    }

    // ===== Train Class =====
    static class Train {
        int trainId;
        String trainName;
        int totalSeats;
        int availableSeats;

        Train(int trainId, String trainName, int totalSeats) {
            this.trainId = trainId;
            this.trainName = trainName;
            this.totalSeats = totalSeats;
            this.availableSeats = totalSeats;
        }

        void displayTrain() {
            System.out.println(trainId + " | " + trainName +
                    " | Available Seats: " + availableSeats);
        }
    }

    // ===== Ticket Class =====
    static class Ticket {
        int ticketId;
        String passengerName;
        Train train;
        String bookingDate;
        String bookingTime;
        double ticketPrice;

        Ticket(int ticketId, String passengerName, Train train) {
            this.ticketId = ticketId;
            this.passengerName = passengerName;
            this.train = train;
            this.bookingDate = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());
            this.bookingTime = new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
            this.ticketPrice = 500 + (Math.random() * 1000); // Random price between 500-1500
        }

        void displayTicket() {
            System.out.println("Ticket ID: " + ticketId +
                    ", Passenger: " + passengerName +
                    ", Train: " + train.trainName);
        }

        void displayReceipt() {
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║         🎫 TRAIN BOOKING RECEIPT 🎫        ║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║ Ticket ID       : " + String.format("%-25s", ticketId) + "║");
            System.out.println("║ Passenger Name  : " + String.format("%-25s", passengerName) + "║");
            System.out.println("║ Train Name      : " + String.format("%-25s", train.trainName) + "║");
            System.out.println("║ Train ID        : " + String.format("%-25s", train.trainId) + "║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║ Booking Date    : " + String.format("%-25s", bookingDate) + "║");
            System.out.println("║ Booking Time    : " + String.format("%-25s", bookingTime) + "║");
            System.out.println("║ Total Seats     : " + String.format("%-25d", train.totalSeats) + "║");
            System.out.println("║ Available Seats : " + String.format("%-25d", train.availableSeats) + "║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║ Ticket Price    : ₹" + String.format("%-24.2f", ticketPrice) + "║");
            System.out.println("║ Tax (5%)        : ₹" + String.format("%-24.2f", ticketPrice * 0.05) + "║");
            System.out.println("║ Total Amount    : ₹" + String.format("%-24.2f", ticketPrice * 1.05) + "║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║ Status          : ✅ CONFIRMED             ║");
            System.out.println("║ PNR Number      : " + String.format("%-25s", "PNR" + ticketId + "2026") + "║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║ Thank you for using Train Booking System! ║");
            System.out.println("╚════════════════════════════════════════════╝\n");
        }
    }

    // ===== Booking System =====
    static class BookingSystem {
        List<Train> trains = new ArrayList<>();
        List<Ticket> tickets = new ArrayList<>();
        Map<String, User> users = new HashMap<>();
        int ticketCounter = 1;
        String loggedInUser = null;
        static final String USERS_FILE = "login_data.txt";

        BookingSystem() {
            trains.add(new Train(101, "Express Line", 5));
            trains.add(new Train(102, "City Mail", 3));
            trains.add(new Train(103, "Super Fast", 4));
            loadUsersFromFile();
        }

        void showTrains() {
            System.out.println("\nAvailable Trains:");
            for (Train t : trains) {
                t.displayTrain();
            }
        }

        void bookTicket(String name, int trainId) {
            for (Train t : trains) {
                if (t.trainId == trainId) {
                    if (t.availableSeats > 0) {
                        t.availableSeats--;
                        Ticket ticket = new Ticket(ticketCounter++, name, t);
                        tickets.add(ticket);
                        System.out.println("✅ Ticket Booked Successfully!");
                        ticket.displayReceipt();
                        return;
                    } else {
                        System.out.println("❌ No seats available.");
                        return;
                    }
                }
            }
            System.out.println("❌ Train not found.");
        }

        void cancelTicket(int ticketId) {
            Iterator<Ticket> it = tickets.iterator();
            while (it.hasNext()) {
                Ticket t = it.next();
                if (t.ticketId == ticketId) {
                    t.train.availableSeats++;
                    it.remove();
                    System.out.println("🗑 Ticket Cancelled Successfully.");
                    return;
                }
            }
            System.out.println("❌ Ticket not found.");
        }

        void showTickets() {
            if (tickets.isEmpty()) {
                System.out.println("No tickets booked yet.");
            } else {
                System.out.println("\nBooked Tickets:");
                for (Ticket t : tickets) {
                    t.displayTicket();
                }
            }
        }

        // ===== Authentication Methods =====
        boolean createAccount(String username, String password, String email, String mobile) {
            if (users.containsKey(username)) {
                System.out.println("❌ Username already exists!");
                return false;
            }
            if (username.length() < 3) {
                System.out.println("❌ Username must be at least 3 characters long!");
                return false;
            }
            if (password.length() < 4) {
                System.out.println("❌ Password must be at least 4 characters long!");
                return false;
            }
            if (mobile.length() != 10) {
                System.out.println("❌ Mobile number must be 10 digits!");
                return false;
            }
            users.put(username, new User(username, password, email, mobile));
            saveUsersToFile();
            System.out.println("✅ Account Created Successfully!");
            return true;
        }

        // Generate Random OTP
        String generateOTP() {
            Random rand = new Random();
            int otp = 100000 + rand.nextInt(900000); // 6-digit OTP
            return String.valueOf(otp);
        }

        // Simulate sending OTP to mobile
        void sendOTPToMobile(String username) {
            User user = users.get(username);
            String otp = generateOTP();
            user.generatedOTP = otp;
            
            // Simulate SMS sending
            String maskedMobile = user.mobile.substring(0, 2) + "XXXXXX" + user.mobile.substring(8);
            System.out.println("\n📱 OTP Sent Successfully!");
            System.out.println("📲 Message sent to: " + maskedMobile);
            System.out.println("🔐 OTP: " + otp + " (expires in 5 minutes)");
            System.out.println("─────────────────────────────────");
        }

        boolean login(String username, String password) {
            if (!users.containsKey(username)) {
                System.out.println("❌ Username not found!");
                return false;
            }
            User user = users.get(username);
            if (!user.password.equals(password)) {
                System.out.println("❌ Incorrect password!");
                return false;
            }
            
            // Send OTP
            sendOTPToMobile(username);
            
            // OTP Verification
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter OTP: ");
            String enteredOTP = sc.nextLine();
            
            if (!enteredOTP.equals(user.generatedOTP)) {
                System.out.println("❌ Invalid OTP! Login failed.");
                user.generatedOTP = null;
                return false;
            }
            
            loggedInUser = username;
            user.generatedOTP = null; // Clear OTP after verification
            System.out.println("✅ OTP Verified! Login Successful! Welcome " + username);
            return true;
        }

        void logout() {
            loggedInUser = null;
            System.out.println("✅ Logged out successfully!");
        }

        // ===== File Operations =====
        void saveUsersToFile() {
            try (PrintWriter writer = new PrintWriter(new FileWriter(USERS_FILE))) {
                for (User user : users.values()) {
                    writer.println(user.username + "|" + user.password + "|" + user.email + "|" + user.mobile);
                }
                System.out.println("💾 User data saved to file!");
            } catch (IOException e) {
                System.out.println("❌ Error saving user data: " + e.getMessage());
            }
        }

        void loadUsersFromFile() {
            try {
                File file = new File(USERS_FILE);
                if (!file.exists()) {
                    System.out.println("📄 No existing user file found. Starting fresh!");
                    return;
                }
                List<String> lines = Files.readAllLines(Paths.get(USERS_FILE));
                for (String line : lines) {
                    if (line.trim().isEmpty()) continue;
                    String[] parts = line.split("\\|");
                    if (parts.length == 4) {
                        users.put(parts[0], new User(parts[0], parts[1], parts[2], parts[3]));
                    }
                }
                System.out.println("✅ Loaded " + users.size() + " user(s) from file!");
            } catch (IOException e) {
                System.out.println("❌ Error loading user data: " + e.getMessage());
            }
        }
    }

    // ===== Main Method =====
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookingSystem system = new BookingSystem();

        // Authentication Menu
        boolean authenticated = false;
        while (!authenticated) {
            System.out.println("\n===== Train Ticket Booking System - Authentication =====");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int authChoice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (authChoice) {
                case 1:
                    System.out.print("Enter Username: ");
                    String newUsername = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String newPassword = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Mobile Number (10 digits): ");
                    String mobile = sc.nextLine();
                    system.createAccount(newUsername, newPassword, email, mobile);
                    break;
                case 2:
                    System.out.print("Enter Username: ");
                    String username = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();
                    if (system.login(username, password)) {
                        authenticated = true;
                    }
                    break;
                case 3:
                    System.out.println("👋 Thank you for using the system!");
                    System.exit(0);
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }

        // Main Menu (After Authentication)
        while (true) {
            System.out.println("\n===== Train Ticket Booking System =====");
            System.out.println("Welcome: " + system.loggedInUser);
            System.out.println("1. Show Trains");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Show Booked Tickets");
            System.out.println("5. Logout");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    system.showTrains();
                    break;
                case 2:
                    System.out.print("Enter Passenger Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Train ID: ");
                    int trainId = sc.nextInt();
                    system.bookTicket(name, trainId);
                    break;
                case 3:
                    System.out.print("Enter Ticket ID: ");
                    int ticketId = sc.nextInt();
                    system.cancelTicket(ticketId);
                    break;
                case 4:
                    system.showTickets();
                    break;
                case 5:
                    system.logout();
                    return; // Go back to authentication menu
                case 6:
                    System.out.println("👋 Thank you for using the system!");
                    System.exit(0);
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }
}
