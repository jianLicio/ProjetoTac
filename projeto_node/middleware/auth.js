const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
const jwt = require('jsonwebtoken');

const app = express();
const mongoUri = process.env.MONGO_URI;

app.use(bodyParser.json());

// const connectWithRetry = () => {
//   console.log('Tentando conectar ao MongoDB...');
//   mongoose.connect(mongoUri)
//     .then(() => {
//       console.log('Conectado ao MongoDB com sucesso!');
//     })
//     .catch((err) => {
//       console.error('Erro ao conectar ao MongoDB:', err);
//       console.log('Tentando reconectar em 5 segundos...');
//       setTimeout(connectWithRetry, 5000);
//     });
// };

// connectWithRetry();

const eventSchema = new mongoose.Schema({
  method: String,
  path: String,
  body: mongoose.Schema.Types.Mixed,
  timestamp: { type: Date, default: Date.now }
});

const Event = mongoose.model('Evento', eventSchema);

function authenticateToken(req, res, next) {
  const authHeader = req.header('Authorization');
  if (!authHeader) return res.status(401).send('Acesso negado. TOKEN não fornecido.');

  const token = authHeader.split(' ')[1];
  if (!token) return res.status(401).send('Acesso negado. TOKEN inválido.');

  try {
    const decoded = jwt.verify(authHeader, process.env.JWT_SECRET);
    req.user = decoded;
    next();
  } catch (ex) {
    res.status(400).send('Token Invalido.');
  }
}

app.post('/evento', authenticateToken, async (req, res) => {

  try {
    const event = new Event(req.body);
    await event.save();
    res.status(201).send(event);

  } catch (err) {
    res.status(500).send('Erro ao salvar evento: ' + err.message);
  }

});

// const PORT = process.env.PORT || 3000;
// app.listen(PORT, () => {
//   console.log(`Servidor rodando na porta ${PORT}`);
// });

module.exports = authenticateToken;