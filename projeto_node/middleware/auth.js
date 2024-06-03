const jwt = require('jsonwebtoken');

function auth(req, res, next) {
  const token = req.header('Authorization');
  if (!token) return res.status(401).send('Acesso negado. TOKEN não validado.');

  try {
    const decoded = jwt.verify(token, process.env.JWT_SECRET);
    req.user = decoded;
    next();
  } catch (ex) {
    res.status(400).send('Token Invalido.');
  }
}

module.exports = auth;