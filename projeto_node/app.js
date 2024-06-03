const express = require('express');
const mongoose = require('mongoose');
const eventsRouter = require('./routes/events');
require('dotenv').config();

const app = express();
const mongoUri = process.env.MONGO_URI;

const connectWithRetry = () => {
  console.log('Tentando conectar ao MongoDB...');
  mongoose.connect(mongoUri)
    .then(() => {
      console.log('Conectado ao MongoDB com sucesso!');
    })
    .catch((err) => {
      console.error('Erro ao conectar ao MongoDB:', err);
      console.log('Tentando reconectar em 5 segundos...');
      setTimeout(connectWithRetry, 5000);
    });
};

connectWithRetry();

app.use(express.json());
app.use('/api/events', eventsRouter);

const port = process.env.PORT || 3000;
app.listen(port, () => console.log(`Listening on port ${port}...`));
