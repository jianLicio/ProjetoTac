const mongoose = require('mongoose');

const eventSchema = new mongoose.Schema({
  // eventType: String,
  // deviceId: String,
  // timestamp: Date,
  // details: String
  name: { type: String, required: true },
  description: String,
  date: { type: Date, required: true },
  person: String,
  gateway: String,
  device: String,
  sensor: String,
  actuator: String,
  reading: String,
});

module.exports = mongoose.model('Event', eventSchema);