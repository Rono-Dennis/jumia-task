FROM node:alpine3.11
WORKDIR /jumiatask-frontend 
ENV PATH="./node_modules/.bin:PATH"
COPY . .
RUN npm run build
CMD ["npm","start"]