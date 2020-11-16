const userService = require('../services/userService');

exports.show = async function (req, res) {
    const user = await userService.findOne(req.params.name)

    res.send({ user })
}
