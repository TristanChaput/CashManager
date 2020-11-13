const mongoose = require('mongoose');

const PostSchema = new mongoose.Schema({
    title: String,
    url: String,
    summary: String,
    source: String,
    date: Date,
    image: String
}, { timestamps: true })

module.exports = PostSchema;
