#build stage
FROM node:lts-alpine as build-stage

# make the 'app' folder the current working directory
WORKDIR /app

# copy both 'package.json' and 'package-lock.json' (if available)
COPY package*.json ./

# install project dependencies leaving out dev dependencies
RUN npm install --production

# copy project files and folders to the current working directory (i.e. 'app' folder)
COPY . .

#production stage 
FROM nginx:stable-alpine as production-stage

# build app for production with minification
RUN npm run build

COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 443
CMD [ "nginx","-g", "daemon off;" ]