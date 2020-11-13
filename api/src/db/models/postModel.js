const { model } = require('mongoose');
const PostSchema = require('../migrations/PostSchema');

const Post = model('Post', PostSchema);

module.exports = Post;
