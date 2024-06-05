const express = require('express');
const mongoose = require('mongoose');
const eventsRouter = require('./routes/events');
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
app.use(bodyParser.json());

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
app.listen(port, () => console.log(`Conectado à porta ${port}...`));
