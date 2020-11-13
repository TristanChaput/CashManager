const { Seeder } = require('mongoose-data-seed');
const faker = require('faker');
const Post = require('../models/postModel');

const data = [];
let title;

for (let i = 0; i < 10; i++) {
    title = faker.lorem.words(3)

    data.push({
        title,
        url: faker.internet.url(),
        summary: faker.lorem.sentence(),
        source: faker.lorem.sentence(),
        date: Date.now(),
        image: faker.image.imageUrl(),
    })
}

class PostsSeeder extends Seeder {

    async shouldRun() {
        return Post.countDocuments().exec().then(count => count === 0);
    }

    async run() {
        return Post.create(data);
    }
}

module.exports = PostsSeeder;
