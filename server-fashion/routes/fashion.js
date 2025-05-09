const express = require('express');
const router = express.Router();
const Fashion = require('../classes/Fashion'); // Ensure the correct path

// Get all Fashion items sorted by creation date (newest first)
router.get('/', async (req, res) => {
  try {
    const fashions = await Fashion.find().sort({ createdAt: -1 }); // Sorting by newest first
    res.json(fashions); // Send JSON response
  } catch (err) {
    console.error("Error fetching fashion data:", err);
    res.status(500).json({ message: err.message });
  }
});

// Get Fashion items by Style
router.get('/style/:style', async (req, res) => {
  try {
    const fashions = await Fashion.find({ style: req.params.style }); // Fixed
    res.json(fashions);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Get specific Fashion item by ID
router.get('/:id', async (req, res) => {
  try {
    const fashion = await Fashion.findById(req.params.id); // Fixed
    if (!fashion) return res.status(404).json({ message: 'Fashion item not found' });
    res.json(fashion);
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

// Add new Fashion item
router.post('/', async (req, res) => {
  const fashion = new Fashion({
    style: req.body.style,
    fashion_subject: req.body.fashion_subject,
    fashion_detail: req.body.fashion_detail,
    fashion_image: req.body.fashion_image
  });

  try {
    const newFashion = await fashion.save();
    res.status(201).json(newFashion);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Update a Fashion item
router.put('/:id', async (req, res) => {
  try {
    const updatedFashion = await Fashion.findByIdAndUpdate(
      req.params.id,  // Fixed
      {
        style: req.body.style,
        fashion_subject: req.body.fashion_subject,
        fashion_detail: req.body.fashion_detail,
        fashion_image: req.body.fashion_image
      },
      { new: true, runValidators: true } // Return updated doc & validate inputs
    );

    if (!updatedFashion) return res.status(404).json({ message: 'Fashion item not found' });

    res.json(updatedFashion);
  } catch (err) {
    res.status(400).json({ message: err.message });
  }
});

// Delete a Fashion item
router.delete('/:id', async (req, res) => {
  try {
    const fashion = await Fashion.findById(req.params.id); // Fixed
    if (!fashion) return res.status(404).json({ message: 'Fashion item not found' });

    await Fashion.deleteOne({ _id: req.params.id });
    res.json({ message: 'Fashion item deleted' });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
});

module.exports = router;
