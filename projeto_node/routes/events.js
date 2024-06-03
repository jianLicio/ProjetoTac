const express = require('express');
const router = express.Router();
const Event = require('../models/event');
const app = express();
const authenticateToken = require('../middleware/auth');
const axios = require('axios');
const URL_API_JAVA = 'jdbc:mariadb://localhost:3306/projetotac';

router.use(express.json());
router.use(authenticateToken);




router.get('/', async (req, res) => {
  try {
    const events = await Event.find();
    res.json(events);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

router.get('/:id',  async (req, res) => {
  try {
    const event = await Event.findById(req.params.id);
    if (!event) return res.status(404).json({ message: 'Evento não encontrado' });
    res.json(event);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

router.post('/',  async (req, res) => {
  const event = new Event({
    // eventType: req.body.eventType,
    // deviceId: req.body.deviceId,
    // timestamp: new Date(),
    // details: req.body.details
    name: req.body.name,
    description: req.body.description,
    date: req.body.date,
    person: req.body.person,
    gateway: req.body.gateway,
    device: req.body.device,
    sensor: req.body.sensor,
    actuator: req.body.actuator,
    reading: req.body.reading,
  });

  try {
    const newEvent = await event.save();
    res.status(201).json(newEvent);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

router.put('/:id',  async (req, res) => {
  try {
    const event = await Event.findById(req.params.id);
    if (!event) return res.status(404).json({ message: 'Evento não encontrado' });

    event.name = req.body.name || event.name;
    event.description = req.body.description || event.description;
    event.date = req.body.date || event.date;
    event.person = req.body.person || event.person;
    event.gateway = req.body.gateway || event.gateway;
    event.device = req.body.device || event.device;
    event.sensor = req.body.sensor || event.sensor;
    event.actuator = req.body.actuator || event.actuator;
    event.reading = req.body.reading || event.reading;

    const updatedEvent = await event.save();
    res.json(updatedEvent);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

router.delete('/:id', async (req, res) => {
  try {
    const event = await Event.findById(req.params.id);
    if (!event) return res.status(404).json({ message: 'Evento não encontrado' });

    await event.remove();
    res.json({ message: 'Evento deletado' });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

router.get('/external-data', async (req, res) => {
  try {
      const response = await axios.get(URL_API_JAVA);
      res.json(response.data);
  } catch (err) {
      res.status(500).json({ message: err.message });
  }
});

module.exports = router;
