# Documentação da API de IoT com Spring Boot

## Introdução

Esta documentação descreve a implementação de uma API para uma aplicação de Internet das Coisas (IoT) utilizando o framework Spring Boot. A API oferece recursos para cadastro e gerenciamento de entidades como Pessoa, Gateway, Dispositivo, Sensor, Atuador e Leitura.

## Diagrama de Classes

O diagrama de classes abaixo representa as principais entidades e seus relacionamentos:

### Entidades

#### Pessoa
- **id (Long)**: Identificador único da pessoa.
- **nome (String)**: Nome da pessoa.
- **email (String)**: Email da pessoa.
- **senha (String)**: Senha da pessoa.
- **dataNascimento (LocalDate)**: Data de nascimento da pessoa.
- **gateways (List<Gateway>)**: Lista de gateways associados à pessoa.

#### Gateway
- **id (Long)**: Identificador único do gateway.
- **nome (String)**: Nome do gateway.
- **localizacao (String)**: Localização do gateway.
- **pessoa (Pessoa)**: Referência à pessoa proprietária do gateway.
- **dispositivos (List<Dispositivo>)**: Lista de dispositivos associados ao gateway.

#### Dispositivo
- **id (Long)**: Identificador único do dispositivo.
- **nome (String)**: Nome do dispositivo.
- **descricao (String)**: Descrição do dispositivo.
- **latitude (Double)**: Latitude da localização do dispositivo.
- **longitude (Double)**: Longitude da localização do dispositivo.
- **gateway (Gateway)**: Referência ao gateway associado ao dispositivo.
- **sensores (List<Sensor>)**: Lista de sensores associados ao dispositivo.
- **atuadores (List<Atuador>)**: Lista de atuadores associados ao dispositivo.

#### Sensor
- **id (Long)**: Identificador único do sensor.
- **nome (String)**: Nome do sensor.
- **tipo (String)**: Tipo do sensor.
- **unidade (String)**: Unidade de medida do sensor.
- **dispositivo (Dispositivo)**: Referência ao dispositivo associado ao sensor.
- **leituras (List<Leitura>)**: Lista de leituras geradas pelo sensor.

#### Atuador
- **id (Long)**: Identificador único do atuador.
- **nome (String)**: Nome do atuador.
- **tipo (String)**: Tipo do atuador.
- **dispositivo (Dispositivo)**: Referência ao dispositivo associado ao atuador.

#### Leitura
- **id (Long)**: Identificador único da leitura.
- **valor (String)**: Valor da leitura.
- **timestamp (LocalDateTime)**: Data e hora da leitura.
- **sensor (Sensor)**: Referência ao sensor que gerou a leitura.

## Funcionalidades das APIs

A API oferecerá operações CRUD (Create, Read, Update, Delete) para as entidades mencionadas. A seguir estão os principais endpoints, seguindo as melhores práticas de nomenclatura.

### Endpoints

#### Pessoa
- **GET** `/pessoas`
- **POST** `/pessoas`
- **GET** `/pessoas/{id}`
- **PUT** `/pessoas/{id}`
- **DELETE** `/pessoas/{id}`

#### Gateway
- **GET** `/gateways`
- **POST** `/gateways`
- **GET** `/gateways/{id}`
- **PUT** `/gateways/{id}`
- **DELETE** `/gateways/{id}`

#### Dispositivo
- **GET** `/dispositivos`
- **POST** `/dispositivos`
- **GET** `/dispositivos/{id}`
- **PUT** `/dispositivos/{id}`
- **DELETE** `/dispositivos/{id}`

#### Sensor
- **GET** `/sensores`
- **POST** `/sensores`
- **GET** `/sensores/{id}`
- **PUT** `/sensores/{id}`
- **DELETE** `/sensores/{id}`

#### Atuador
- **GET** `/atuadores`
- **POST** `/atuadores`
- **GET** `/atuadores/{id}`
- **PUT** `/atuadores/{id}`
- **DELETE** `/atuadores/{id}`

#### Leitura
- **GET** `/leituras`
- **POST** `/leituras`
- **GET** `/leituras/{id}`
- **PUT** `/leituras/{id}`
- **DELETE** `/leituras/{id}`

## Autenticação e Autorização

A API implementará um sistema de autenticação utilizando JWT (JSON Web Token) para proteger as rotas sensíveis. Apenas usuários autenticados poderão acessar determinados recursos.

## Formato de Dados

O formato de dados utilizado para troca de informações será JSON. As requ