const axios = require('axios');

async function getJWT() {
    try {
        const response = await axios.post('http://127.0.0.1:8081/autenticar', {
            email: 'maria.oliveira@example.com',
            senha: 'senha123'
        });
        const token = response.data;
        return token;
    } catch (error) {
        console.error('Error getting JWT:', error);
    }
}

getJWT().then(token => {
    console.log('JWT:', token);
});