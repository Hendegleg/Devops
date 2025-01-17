# Use the official Node.js image as a build stage
FROM node:18 AS build

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application
COPY . .

# Build the Angular application
RUN npm run build --prod

# Use a lightweight server to serve the application
FROM nginx:alpine

# Copy the built files from the build stage
COPY --from=build /app/dist/universite-app /usr/share/nginx/html


# Start Nginx server
CMD ["nginx", "-g", "daemon off;"]

EXPOSE 8080
