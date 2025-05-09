const express = require('express');
const app = express();
const mongoose = require('mongoose');
const cors = require('cors');
const bodyParser = require('body-parser');
const fashionRoutes = require('./routes/fashion');

const PORT = 4040;

// Middleware (should be placed BEFORE routes)
app.use(cors());
app.use(bodyParser.json());

// Connect to MongoDB
mongoose.connect('mongodb://localhost:27017/FashionData', {
  useNewUrlParser: true,
  useUnifiedTopology: true
})
.then(() => console.log('Connected to MongoDB'))
.catch(err => console.error('Could not connect to MongoDB', err));

// Routes (AFTER middleware)
app.use('/api/fashion', fashionRoutes);

// Start server
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));
