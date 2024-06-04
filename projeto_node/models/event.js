const mongoose = require('mongoose');

const eventSchema = new mongoose.Schema({
  type: { type: String, required: true },
  timestamp: { type: Date, default: Date.now, required: true },
  device: { type: String, required: true },
  details: { type: String, required: true },
  status: { type: String, enum: ['ligado', 'desligado'], required: true },
  personId: { type: mongoose.Schema.Types.ObjectId, ref: 'Person' },
  gatewayId: { type: mongoose.Schema.Types.ObjectId, ref: 'Gateway' },
  sensorId: { type: mongoose.Schema.Types.ObjectId, ref: 'Sensor' },
  actuatorId: { type: mongoose.Schema.Types.ObjectId, ref: 'Actuator' },
  readingId: { type: mongoose.Schema.Types.ObjectId, ref: 'Reading' },
});

module.exports = mongoose.model('Event', eventSchema);
