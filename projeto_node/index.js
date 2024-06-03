// Importa o módulo HTTP do Node.js
const http = require('http');

// Cria um servidor que responde com "Hello, World!" para todas as requisições
const server = http.createServer((req, res) => {
    res.statusCode = 200; // Define o status HTTP para 200 (OK)
    res.setHeader('Content-Type', 'text/plain'); // Define o cabeçalho Content-Type para texto simples
    res.end('Hello, World!\n'); // Envia a resposta e termina a requisição
});

// Define a porta que o servidor vai escutar (neste caso, 3000)
const port = 3000;
server.listen(port, () => {
    console.log(`Servidor rodando em http://localhost:${port}/`);
});
