const Post = require('../db/models/postModel');

exports.find = async function () {
    return Post
        .find()
        .sort({
            updatedAt: -1,
            createdAt: -1,
        });
}

exports.findOne = async function (slug) {
    return Post
        .findOne({ slug });
}
