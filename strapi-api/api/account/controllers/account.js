'use strict';

/**
 * Read the documentation (https://strapi.io/documentation/v3.x/concepts/controllers.html#core-controllers)
 * to customize this controller
 */

module.exports = {
  async pay(ctx) {
    const { number, total } = ctx.request.body;

    const account = await strapi.services.account.findOne({ number });

    if (account) {
      if (account.amount < total) {
        return ctx.badRequest('You don\'t have enough money !');
      }

      await strapi.services.account.update(account.id, {
        amount: account.amount - total,
      });

      return {
        message: 'Congratulation. Have good holidays !'
      };
    }

    return ctx.badRequest('You can\'t perform this action');
  }
};
