const express = require('express');
const mongoose = require('mongoose');
const eventsRouter = require('./routes/events');
require('dotenv').config();

const app = express();
const mongoUri = process.env.MONGO_URI;

mongoose.connect(mongoUri)
  .then(() => console.log('Conectando ao MongoDB...'))
  .catch(err => console.error('Não foi possivel se conectar ao MongoDB...', err));

app.use(express.json());
app.use('/api/events', eventsRouter);

const port = process.env.PORT || 3000;
app.listen(port, () => console.log(`Listening on port ${port}...`));

const connectWithRetry = () => {
  console.log('Tentando conectar ao MongoDB...');
  mongoose.connect(mongoUri, {
    useNewUrlParser: true,
    useUnifiedTopology: true
  })
  .then(() => {
    console.log('Conectado ao MongoDB com sucesso!');
  })
  .catch((err) => {
    console.error('Erro ao conectar ao MongoDB:', err);
    console.log('Tentando reconectar em 5 segundos...');
    setTimeout(connectWithRetry, 5000);  // Tenta reconectar após 5 segundos
  });
};

connectWithRetry();