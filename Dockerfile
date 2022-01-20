FROM openjdk:11 AS backend
WORKDIR '/REST API'
EXPOSE 8080
ADD build/libs/*.jar mainApplication.jar
ENTRYPOINT ["java","-jar","/mainApplication.jar"]

FROM node:alpine AS frontend
WORKDIR '/personal'
WORKDIR '/app'
COPY package.json .
RUN npm install 
COPY . ./
CMD ["npm", "start", "npm bin", "cypress run"]

