## Training center
### Prerequisites
The following needs to be installed before building the project:
* Java 17
* PostgreSQL 14.2:
  * A database with the following configuration needs to be created:
    * username: `postgres`
    * password: `123`
    * database name: `training_center`
  * If you'd like to specify your own database configuration, you need to modify `application-postgres.yaml`

The application will be available at http://localhost:8082/

### Application authentication
Authentication is provided by a separate application "Authorization_Server".
To authenticate yourself, you need to:
1. Add a new user and his role to the Authentication server (has its own README.md).
For example:

` curl -XPOST -H "content-type: application/json" -d "{\"username\":\"YourName\", \"password\":\"YourPassword\", \"role\":\"admin\"}" http://localhost:8080/users/add`
2. Send your credentials to a 'Training_center' login page.
For example:

`curl -H "username:YourName" -H "password:YourPassword" http://localhost:8082/login`
3. Retrieve a code issued to you from 'authentication' database ('Authentication_Server' application), table otp.
4. Send your username and the code to the 'Training_center' login page.
For example:

`curl -v -H "username:YourName" -H "code:YourCode" http://localhost:8082/login`

5. If everything is correct, you should receive an authentication token in the "Authentication" response header.
6. With this authorization token you can access other application pages. For example:

`curl -H "Authorization:eyJhbGciOiJub25lIn0.eyJ1c2VybmFtZSI6Im9sZ2EifQ." http://localhost:8082/contributors`

POST, DELETE, PUT requests are allowed only for an admin.

GET requests are available to any authenticated client.