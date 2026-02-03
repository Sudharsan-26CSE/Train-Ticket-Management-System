
                    TRAIN BOOKING SYSTEM - PROJECT ABSTRACT


PROJECT TITLE:
    Advanced Train Ticket Booking System with OTP Authentication

OBJECTIVE:
    To develop a secure, user-friendly train ticket booking application that
    allows users to create accounts, authenticate via OTP, and manage train
    ticket reservations efficiently.

KEY FEATURES:

1. USER AUTHENTICATION SYSTEM
   • Account Creation with validation (username, password, email, mobile)
   • Secure Login with Username & Password verification
   • One-Time Password (OTP) verification via mobile
   • OTP generation and validation (6-digit random code)
   • User data persistence using file storage
   • Logout functionality

2. TRAIN MANAGEMENT
   • Display available trains with real-time seat availability
   • Train information includes: Train ID, Train Name, Total Seats, Available Seats
   • Pre-loaded sample trains in the system

3. TICKET BOOKING & MANAGEMENT
   • Book tickets for available trains
   • Automatic seat deduction upon successful booking
   • Professional receipt generation with detailed information
   • Ticket cancellation with automatic seat restoration
   • View all booked tickets with complete details
   • Unique Ticket ID and PNR Number generation
   • Dynamic ticket pricing (₹500-₹1500 per ticket)
   • Tax calculation (5% on ticket price)

4. DATA PERSISTENCE
   • User credentials saved in login_data.txt
   • File format: username|password|email|mobile
   • Automatic loading of existing users on system startup
   • Data survives across multiple program sessions

5. USER INTERFACE
   • Menu-driven command-line interface
   • Clear authentication menu before access
   • Main menu with 6 options after login
   • Real-time feedback with emoji indicators (✅, ❌, 📱, 🎫, etc.)
   • Professional receipt display with box formatting

SYSTEM COMPONENTS:


1. User Class
   - Attributes: username, password, email, mobile, generatedOTP
   - Stores user account information

2. Train Class
   - Attributes: trainId, trainName, totalSeats, availableSeats
   - Methods: displayTrain()

3. Ticket Class
   - Attributes: ticketId, passengerName, train, bookingDate, bookingTime, ticketPrice
   - Methods: displayTicket(), displayReceipt()

4. BookingSystem Class
   - Manages trains, tickets, and users
   - Authentication methods: createAccount(), login(), logout()
   - File operations: saveUsersToFile(), loadUsersFromFile()
   - Train operations: showTrains(), bookTicket(), cancelTicket(), showTickets()
   - OTP operations: generateOTP(), sendOTPToMobile()


TECHNICAL SPECIFICATIONS:


Language: Java
Required Libraries: java.util.*, java.io.*, java.nio.file.*
Data Storage: Text file (login_data.txt)
Architecture: Object-Oriented Programming with static nested classes


WORKFLOW:

1. STARTUP PHASE
   → Load existing users from login_data.txt
   → Initialize train database
   → Display authentication menu

2. AUTHENTICATION PHASE
   → User creates account OR login with existing credentials
   → On login: Username & Password verification
   → OTP sent to registered mobile number
   → User enters OTP for verification
   → Successful authentication grants access

3. BOOKING PHASE
   → User can view available trains
   → Select train and book ticket
   → Receipt generated with all details
   → Can cancel tickets anytime
   → View booked tickets history

4. LOGOUT PHASE
   → User logs out
   → Returns to authentication menu
   → User data saved in file


SAMPLE DATA:


Pre-loaded Trains:
   • Train 101: Express Line (5 seats)
   • Train 102: City Mail (3 seats)
   • Train 103: Super Fast (4 seats)

Sample User (after first run):
   • Can create multiple accounts
   • Each account is persistent
   • Credentials stored securely in text file

SECURITY FEATURES:


✓ OTP-based two-factor authentication
✓ Password validation (minimum 4 characters)
✓ Username validation (minimum 3 characters)
✓ Mobile number validation (10 digits)
✓ Duplicate username prevention
✓ Session-based login management
✓ Automatic OTP expiration after use
✓ Masked mobile number display (98XXXXXX34 format)


FUTURE ENHANCEMENTS:


• Database integration (MySQL/PostgreSQL)
• Email notification system
• SMS integration for real OTP delivery
• Payment gateway integration
• Booking history with filters
• User profile management
• Admin panel for train management
• Cancellation refund system
• Seat selection UI
• Multiple language support
• Mobile app version
• Web application version


BENEFITS:


✓ User-Friendly: Simple menu-driven interface
✓ Secure: OTP-based authentication
✓ Efficient: Quick ticket booking and cancellation
✓ Reliable: Data persistence across sessions
✓ Scalable: Easy to add new features
✓ Professional: Detailed receipt generation
✓ Practical: Real-world train booking simulation


CONCLUSION:


The Train Booking System is a comprehensive solution that demonstrates
core programming concepts including Object-Oriented Programming, file I/O,
user authentication, and real-world application development. It provides
a solid foundation that can be extended with additional features for
production use.


                            END OF ABSTRACT

