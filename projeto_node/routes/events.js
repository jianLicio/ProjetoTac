const express = require('express');
const router = express.Router();
const Event = require('../models/event');
const auth = require('../middleware/auth');

router.get('/', auth, async (req, res) => {
  try {
    const events = await Event.find();
    res.json(events);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

router.post('/', auth, async (req, res) => {
  const event = new Event({
    eventType: req.body.eventType,
    deviceId: req.body.deviceId,
    timestamp: new Date(),
    details: req.body.details
  });
  
  try {
    const newEvent = await event.save();
    res.status(201).json(newEvent);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// PUT e DELETE similarmente usando auth e Event.findByIdAndUpdate / Event.findByIdAndDelete

module.exports = router;