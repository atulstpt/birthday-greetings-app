# Birthday Email Project

## Setup Instructions
1. Create MySQL database: `birthday_db`
   - This database will store employee details and their birthdays.
2. Update `application.yml` with your MySQL credentials
   - Ensure correct username, password, and database URL are provided.
3. Configure Gmail SMTP settings in `application.yml`:
   - Use an App Password if 2FA is enabled for your Gmail account.
   - This is required for sending birthday emails.
4. Run the application: `mvn spring-boot:run`
   - This command starts the Spring Boot application.

## API Endpoints
- **GET `/api/employees`** - List all employees
   - Returns a list of all employees stored in the database.
- **POST `/api/employees`** - Create new employee
   - Adds a new employee to the database.
- **GET `/api/employees/birthdays/today`** - Get employees with birthday today
   - Fetches employees whose birthdays match the current date.

## Notes
- Birthday emails are sent daily at 10 AM IST
   - The application uses a scheduled task for this functionality.
- Email configuration uses Gmail SMTP
   - Ensure proper configuration in `application.yml`.
- Database is populated with 100 sample employees on startup
   - This is done for testing purposes.