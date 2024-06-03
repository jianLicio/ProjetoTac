const jwt = require('jsonwebtoken');

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

module.exports = authenticateToken;