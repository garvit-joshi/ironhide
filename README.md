# ***IronHide***: A simple, robust Audit service

## Building and Running the Project: 🔨

### Prerequisites:

- JAVA 21 Installed
- PostgreSQL

### Steps:

1. **Database Setup:**
   Run the following commands with superuser privileges in your PostgreSQL database:

   ```postgresql
   CREATE USER ironhide WITH PASSWORD 'CSzuvcUEoc';
   CREATE DATABASE audit_db;
   ALTER DATABASE audit_db OWNER TO ironhide;
   GRANT ALL PRIVILEGES ON DATABASE audit_db TO ironhide;
   ```
   These commands create a new user 'ironhide' with a password, set up a database named 'audit_db', assign ownership to the
   user, and grant all privileges to the user.


2. Ensure JAVA 21 is set as your JAVA_HOME environment variable.
   Navigate to the project directory.
   Run the following commands in the terminal:

   ```shell
   ./mvnw clean install
   cd target
   java -Dspring.profiles.active=local -jar IronHide-1.0.jar
   ```
   These commands will build the project using Maven, navigate to the target directory, and run ironhide with the '
   local' profile activated.

## Docker Experimental Support

**Note:** Docker Compose in this project is still in experimental support. Currently, it supports logging into a volume, and launching a PostgreSQL container and connecting ironhide to it.

To get started, follow these simple steps:
Build the Docker Compose setup:

   ```shell
   docker-compose build
   docker-compose up
   ```

With these commands, you'll be up and running with experimental Docker Compose setup, allowing to seamlessly manage
containers. Please keep in mind that this feature is currently in the experimental stage.

## Notes:

1. **Coding Style:**
   All pull requests (PRs) should adhere to the [Google Java Style](https://github.com/google/google-java-format)
   guidelines for consistent and clean code formatting.
