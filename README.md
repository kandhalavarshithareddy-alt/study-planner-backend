# Student Study Planner Backend

Spring Boot 3.5, Java 17, Maven, Spring Data JPA, and MySQL.

## Database

Create the database if it does not already exist:

```sql
CREATE DATABASE IF NOT EXISTS studyplanner;
```

Hibernate's `ddl-auto=update` creates or updates the `study_task` table at startup; it does not drop tables or records. Back up production data before schema changes.

Configure these environment variables (never commit database passwords):

| Variable | Value |
| --- | --- |
| `DATABASE_URL` | `jdbc:mysql://mysql-2ded90d3-kandhalavarshithareddy-24c7.l.aivencloud.com:18515/defaultdb?sslMode=REQUIRED` |
| `DB_HOST` | `mysql-2ded90d3-kandhalavarshithareddy-24c7.l.aivencloud.com` |
| `DB_PORT` | `18515` |
| `DB_NAME` | `defaultdb` |
| `DB_USERNAME` | `avnadmin` |
| `DB_PASSWORD` | Set privately in the hosting dashboard; never commit it |
| `PORT` | Optional server port; defaults to `8080` |
| `CORS_ALLOWED_ORIGIN` | `https://kandhalavarshithareddy-alt.github.io` |

`DATABASE_URL` takes precedence. If omitted, the application builds a JDBC URL from `DB_HOST`, `DB_PORT`, and `DB_NAME`. Include `?sslMode=REQUIRED` in the URL for Aiven. Local development origins on ports 5500 are also allowed; no credentials are enabled for CORS. A local database is not reachable from the cloud by default.

## Build and run

```powershell
mvn clean package
java -jar target/studyplanner-0.0.1-SNAPSHOT.jar
```

Koyeb can build this repository with `mvn clean package` and start it with the `java -jar` command above. Select Java 17; `system.properties` pins that runtime. The health check at `/api/health` returns `UP` only when the task database is reachable. Koyeb currently lists one free web service (512 MB RAM, 0.1 vCPU, Frankfurt or Washington, D.C.). Its signup requires a payment card, and its pricing FAQ describes a $29 authorization hold and possible plan charges. Confirm the account is on the free Starter plan before creating a service; do not upgrade without approval.

## API

| Method | Path | Purpose |
| --- | --- | --- |
| `GET` | `/api/tasks` | List tasks |
| `GET` | `/api/tasks/{id}` | Get a task |
| `POST` | `/api/tasks` | Create a task |
| `PUT` | `/api/tasks/{id}` | Update a task |
| `DELETE` | `/api/tasks/{id}` | Delete a task |

`GET /api/health` checks both the application and its database connection.