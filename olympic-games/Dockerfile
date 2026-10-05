# =========================================
# Stage 1: Build the Angular Application
# =========================================
ARG NODE_VERSION=22.23-alpine3.24
ARG NGINX_VERSION=alpine3.24

# Use a lightweight Node.js image for building (customizable via ARG)
FROM node:${NODE_VERSION} AS builder
LABEL author="delamarre michael"

# Set the working directory inside the container
WORKDIR /app

# Copy package-related files first to leverage Docker's caching mechanism
COPY package.json *package-lock.json* ./

# Install project dependencies using npm ci (ensures a clean, reproducible install)
RUN --mount=type=cache,target=/root/.npm npm ci

# Copy the rest of the application source code into the container
COPY . .

# Build the Angular application
RUN npm run build

# =========================================
# Stage 2: Prepare Nginx to Serve Static Files
# =========================================

FROM nginxinc/nginx-unprivileged:${NGINX_VERSION} AS runner

# Replace the main nginx configuration (SPA fallback, gzip, caching)
COPY nginx/nginx.conf /etc/nginx/nginx.conf

# Copy the static build output from the build stage to the root served by nginx.conf
COPY --chown=nginx:nginx --from=builder /app/dist/olympic-games-starter/browser /app

# Expose port 80 to allow HTTP traffic (the base image runs as non-root user nginx)
EXPOSE 80

# Start Nginx in the foreground
CMD ["nginx", "-g", "daemon off;"]

