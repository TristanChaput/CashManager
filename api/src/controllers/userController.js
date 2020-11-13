// const PostService = require('../services/postService');

exports.show = async function (req, res) {
    // const post = await PostService.findOne(req.params.post);
    const user = {
        id: 1,
        name: 'Jason',
        accounts: [
            {
                number: 230071,
                amount: 0.0
            },
            {
                number: 873330,
                amount: 0.0
            },
            {
                number: 664552,
                amount: 0.0
            }
        ]
    }

    res.send({ user })
}
