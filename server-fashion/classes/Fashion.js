const mongoose = require('mongoose');

const fashionSchema = new mongoose.Schema({
  style: { type: String, required: true },
  fashion_subject: { type: String, required: true },
  fashion_detail: { type: String, required: true },
  fashion_image: { type: String, required: true },
}, { timestamps: true });

module.exports = mongoose.model('Fashion', fashionSchema, 'Fashion');