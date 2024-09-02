# Event-Ticketing-API
## Business requirement
1. **User registration**: Allow users to create an account with email and password.
2. **Account management**: Let users view and update their account details.
3. **Event details**: Show events information, like name and description.
4. **Place orders**: Enable users to buy tickets online for an event.
5. **Event categorization**: Group events into basic categories like concerts, sports, or theater.
6. **Order confirmation**: Send a confirmation message to users after they purchase tickets.
7. **Search function**: Provide a simple search bar for finding events by name or category.
8. **Ticket validation**: Offer tools for QR code or barcode ticket check-in.
9. **Order management**: Provide users access to order history, cancellation, and refunds.
10. **Event management**: Allow administrators to create, edit, and delete events and categories.

## Feature for MVP
Based on the given business requirements, here are descriptions of the five main features for a Minimum Viable Product (MVP):

#### 1. **User registration**
   - **Description:** This feature allows users to create an account using their email address and a password. Users must provide the following basic informations: email, password and name. The system will include basic validations for password length and empty values.

#### 2. **Account management**
   - **Description:** This feature lets users view and update their account details after registration. This ensures that users can keep their information up-to-date and secure. This feature also provides user with information about their orders and tickets.

#### 3. **Event details**
   - **Description:** This feature provides comprehensive information about each event, including the event name, description, available and sold tickets and the price of a ticket. The data will be updated every time a new ticket is purchased for an event.

#### 4. **Place orders**
   - **Description:** Users and get tickets for an event by placing an order. This allows users to purchase multiple tickets for an event and view the total cost of the order.

#### 5. **Event categorization**
   - **Description:** Events are grouped into basic categories such as concerts, sports, and theater, making it easier for users to browse and find events of interest. Users can view all categories and can search all the event from a certain category. An event can have multiple categories.

## Implementation
The application runs on http://localhost:8081 and has a compose.yaml for setting up the database using the command `docker compose up -d`. The adminer for the database runs on http://localhost:8080. A `data.sql` file for seeding the database is included in the resources folder that runs at application startup.

### Database schema
![event ticketing schema drawio](https://github.com/user-attachments/assets/3196d6b4-632f-4f15-8b97-b0c4fdac6035)

### Endpoints
- The endpoint are documented using Swagger 3.0 at http://localhost:8081/swagger-ui/index.html#/. A Postman collection is also included in the repository for testing the endpoints.
<img width="1425" alt="image" src="https://github.com/user-attachments/assets/1bd2a925-9a4a-41f5-be59-a991c84a179e">

### Unit tests
- Test results for controller and service methods
<img width="1005" alt="image" src="https://github.com/user-attachments/assets/79a21d72-a5f2-453f-ab60-4522731d7189">

- All endpoints and service methods are covered by unit tests
<img width="943" alt="image" src="https://github.com/user-attachments/assets/ec041e87-a156-4d4f-abc2-2b07631d0094">
