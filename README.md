# Student Study Planner Backend

Spring Boot 3.5, Java 17, Maven, Spring Data JPA, and MySQL.

## Database

For local development, create `studyplanner` if needed. For the supplied Aiven service, use its existing `defaultdb` database; do not rename or replace it:

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
| `PORT` | Render supplies its web port (normally `10000`); defaults to `8080` locally |
| `CORS_ALLOWED_ORIGIN` | `https://kandhalavarshithareddy-alt.github.io` |

`DATABASE_URL` takes precedence. If omitted, the application builds a JDBC URL from `DB_HOST`, `DB_PORT`, and `DB_NAME`. Include `?sslMode=REQUIRED` in the URL for Aiven. Local development origins on ports 5500 are also allowed; no credentials are enabled for CORS. A local database is not reachable from the cloud by default.

## Build and run locally

```powershell
mvn clean package
java -jar target/studyplanner-0.0.1-SNAPSHOT.jar
```

## Render deployment

Connect this existing GitHub repository to Render as a **Web Service**, choose the **Docker** runtime, set the Dockerfile path to `./Dockerfile`, and select the **Free** compute plan. The Dockerfile builds the existing project with Java 17 and Maven (`mvn -B clean package`), then starts it with `java -jar /app/app.jar`. Render supplies `PORT` (default `10000`), which Spring Boot reads from `application.properties`. Set the health check path to `/api/health`.

Add the database environment variables in the Render dashboard. Set `DB_PASSWORD` as a private secret and set `CORS_ALLOWED_ORIGIN` to the final Vercel production origin after Vercel assigns its URL. Do not put credentials in this repository. Render's Free web services use 512 MB RAM and 0.1 CPU, spin down after 15 minutes without inbound requests, and have a 750-hour monthly workspace limit. They can take about a minute to wake. If no payment method is attached, usage-limit overages suspend free services rather than charging them. Free services are intended for testing and hobby projects, not production guarantees.

## API

| Method | Path | Purpose |
| --- | --- | --- |
| `GET` | `/api/tasks` | List tasks |
| `GET` | `/api/tasks/{id}` | Get a task |
| `POST` | `/api/tasks` | Create a task |
| `PUT` | `/api/tasks/{id}` | Update a task |
| `DELETE` | `/api/tasks/{id}` | Delete a task |

`GET /api/health` checks both the application and its database connection.