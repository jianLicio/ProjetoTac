const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
const jwt = require('jsonwebtoken');

const app = express();
const mongoUri = process.env.MONGO_URI;

app.use(bodyParser.json());

mongoose.connect(mongoUri, {
  useNewUrlParser: true,
  useUnifiedTopology: true,
})

const eventSchema = new mongoose.Schema({
  method: String,
  path: String,
  body: mongoose.Schema.Types.Mixed,
  timestamp: { type: Date, default: Date.now }
});

const Event = mongoose.model('Evento', eventSchema);

app.post('/evento', authenticateJWT, async (req, res) => {
  const event = new Event(req.body);
  await event.save();
  res.status(201).send(event);
});

function authenticateToken(req, res, next) {
  const authHeader = req.header('Authorization');
  if (!authHeader) return res.status(401).send('Acesso negado. TOKEN não fornecido.');

  const token = authHeader.split(' ')[1]; // Extrai o token do formato "Bearer <token>"
  if (!token) return res.status(401).send('Acesso negado. TOKEN inválido.');

  try {
    const decoded = jwt.verify(authHeader, process.env.JWT_SECRET);
    req.user = decoded;
    next();
  } catch (ex) {
    res.status(400).send('Token Invalido.');
  }
}

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Servidor rodando na porta ${PORT}`);
});


module.exports = authenticateToken;