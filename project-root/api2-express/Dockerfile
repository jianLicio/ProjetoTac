# Dockerfile para API Express
FROM node:16
WORKDIR /app
COPY projeto_express/package*.json ./
RUN npm install
COPY projeto_express .
EXPOSE 3001
CMD ["node", "app.js"]
