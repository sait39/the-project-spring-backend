# Use the official PostgreSQL image from the Docker Hub
FROM postgres:16.3-alpine3.18

# Add custom configurations or initialization scripts if needed
#COPY init.sql /docker-entrypoint-initdb.d/

# Optional: Set environment variables here or via Docker Compose
# These will be used as default variables and will be overwritten
# by docker compose and the referenced .env file. This makes
# sense there are env variables that HAVE to be set.
ENV POSTGRES_USER=default_user
ENV POSTGRES_PASSWORD=default_password
ENV POSTGRES_DB=default_db
