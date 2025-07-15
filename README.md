# Birthday Email Project

## Setup Instructions
1. Create MySQL database: `birthday_db`
2. Update `application.properties` with your MySQL credentials
3. Configure Gmail SMTP settings in `application.properties`:
    - Use an App Password if 2FA is enabled
4. Run the application: `mvn spring-boot:run`

## API Endpoints
- GET `/api/employees` - List all employees
- POST `/api/employees` - Create new employee
- GET `/api/employees/birthdays/today` - Get employees with birthday today

## Notes
- Birthday emails are sent daily at 10 AM IST
- Email configuration uses Gmail SMTP
- Database is populated with 100 sample employees on startup