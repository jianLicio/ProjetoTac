const express = require('express');
const mongoose = require('mongoose');
require('dotenv').config();

const app = express();
const mongoUri = process.env.MONGO_URI;
const apiNode = require('./routes/events');

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
app.use('/events', apiNode);

app.get('/test-connection', async (req, res) => {
  try {
    const collections = await mongoose.connection.db.listCollections().toArray();
    res.json({
      message: 'Conexão com o banco de dados bem-sucedida!',
      collections: collections.map(col => col.name)
    });
  } catch (err) {
    res.status(500).json({ message: 'Erro ao conectar ao banco de dados', error: err.message });
  }
});

const port = process.env.PORT || 3000;


const server = app.listen(port, () => {
  console.log(`Servidor rodando na porta ${port}`);
});

server.on('error', (err) => {
  if (err.code === 'EADDRINUSE') {
    console.error(`Porta ${port} já está em uso. Não foi possível iniciar o servidor.`);
    process.exit(1);
  } else {
    console.error('Erro ao iniciar o servidor:', err);
  }
});
