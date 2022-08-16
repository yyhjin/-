#build stage
FROM node:lts-alpine as build-stage

# make the 'app' folder the current working directory
WORKDIR /frontend
# copy both 'package.json' and 'package-lock.json' (if available)
# COPY package*.json ./

# install project dependencies leaving out dev dependencies
RUN npm install 

# # copy project files and folders to the current working directory (i.e. 'app' folder)
# COPY . .

# build app for production with minification
RUN npm run build --prod

#production stage 
FROM nginx:stable-alpine as production-stage
COPY  ./nginx/nginx.conf /etc/nginx/conf.d/default2.conf
RUN rm -rf /usr/share/nginx/html/*

COPY --from=build-stage frontend/dist /usr/share/nginx/html/

EXPOSE 7602
CMD [ "nginx","-g", "daemon off;" ]

