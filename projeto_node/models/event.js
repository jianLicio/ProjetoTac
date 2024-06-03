const mongoose = require('mongoose');

const eventSchema = new mongoose.Schema({
  eventType: String,
  deviceId: String,
  timestamp: Date,
  details: String
});

module.exports = mongoose.model('Event', eventSchema);