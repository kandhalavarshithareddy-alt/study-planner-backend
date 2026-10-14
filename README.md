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
| `DATABASE_URL` | JDBC URL, for example `jdbc:mysql://HOST:3306/studyplanner` |
| `DB_USERNAME` | MySQL username |
| `DB_PASSWORD` | MySQL password |
| `PORT` | Optional server port; defaults to `8080` |
| `CORS_ALLOWED_ORIGIN` | Allowed browser origin; use the frontend origin only, with no path |

For the deployed frontend, the CORS origin is `https://kandhalavarshithareddy-alt.github.io`. Set `DATABASE_URL`, `DB_USERNAME`, and `DB_PASSWORD` to values supplied by the online MySQL provider; a local database is not reachable from the cloud by default.

## Build and run

```powershell
mvn clean package
java -jar target/studyplanner-0.0.1-SNAPSHOT.jar
```

Railway can build this repository with `mvn clean package` and start it with the `java -jar` command above. Set the environment variables in the service dashboard and generate a public domain in its Networking settings. Railway's free tier has limited monthly usage credits; verify the current billing settings before enabling a service.

## API

| Method | Path | Purpose |
| --- | --- | --- |
| `GET` | `/api/tasks` | List tasks |
| `GET` | `/api/tasks/{id}` | Get a task |
| `POST` | `/api/tasks` | Create a task |
| `PUT` | `/api/tasks/{id}` | Update a task |
| `DELETE` | `/api/tasks/{id}` | Delete a task |